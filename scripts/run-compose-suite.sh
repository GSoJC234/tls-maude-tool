#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

SUITE="5246"
TARGET_LIBRARY="openssl"
TARGET_VERSION="1.1.1j"
REQUIREMENTS=""
CASES=""
MODE="all"
PORT="4433"
TIMEOUT_SECONDS="15"
STARTUP_DELAY_SECONDS="30"
MTA_STARTUP_DELAY_SECONDS="120"
BUILD_MTA="false"
PRINT_OUTPUT="false"
CURRENT_BG_NAME=""
CURRENT_BG_LOG_PID=""

usage() {
  cat <<'EOF'
Usage:
  scripts/run-compose-suite.sh [options]

Options:
  --suite 5246|8446|hrr|psk       Requirement suite. Default: 5246
  --target openssl|gnutls|mbedtls|wolfssl
                                  Target TLS library. Default: openssl
  --version VERSION               Target docker image version tag. Default: 1.1.1j
  --requirements LIST             Requirement ids/ranges, e.g. 1,3,10-14. Default: suite range
  --case LIST                     Case ids/ranges inside each requirement. Default: all generated cases
  --mode all|generate|run         Generate only, run only, or both. Default: all
  --port PORT                     In-compose test port. Default: 4433
  --timeout SECONDS               Foreground side timeout. Default: 15
  --startup-delay SECONDS         Background target server readiness timeout. Default: 30
  --mta-startup-delay SECONDS     Background MTA server readiness timeout. Default: 120
  --mta-ready-timeout SECONDS     Alias for --mta-startup-delay
  --build                         Run docker compose build mta before starting
  --print                         Also stream foreground/background logs to stdout
  -h, --help                      Show this help

Examples:
  OPENSSL_VERSION=1.1.1j scripts/run-compose-suite.sh --suite 5246
  scripts/run-compose-suite.sh --suite 5246 --requirements 12 --case 0 --print
EOF
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --suite)
      SUITE="$2"; shift 2 ;;
    --target)
      TARGET_LIBRARY="$2"; shift 2 ;;
    --version)
      TARGET_VERSION="$2"; shift 2 ;;
    --requirements)
      REQUIREMENTS="$2"; shift 2 ;;
    --case|--cases)
      CASES="$2"; shift 2 ;;
    --mode)
      MODE="$2"; shift 2 ;;
    --port)
      PORT="$2"; shift 2 ;;
    --timeout)
      TIMEOUT_SECONDS="$2"; shift 2 ;;
    --startup-delay)
      STARTUP_DELAY_SECONDS="$2"; shift 2 ;;
    --mta-startup-delay)
      MTA_STARTUP_DELAY_SECONDS="$2"; shift 2 ;;
    --mta-ready-timeout)
      MTA_STARTUP_DELAY_SECONDS="$2"; shift 2 ;;
    --build)
      BUILD_MTA="true"; shift ;;
    --print)
      PRINT_OUTPUT="true"; shift ;;
    -h|--help)
      usage; exit 0 ;;
    *)
      echo "Unknown argument: $1" >&2
      usage >&2
      exit 2 ;;
  esac
done

case "$MODE" in
  all|generate|run) ;;
  *)
    echo "Unknown mode: $MODE" >&2
    exit 2 ;;
esac

case "$TARGET_LIBRARY" in
  openssl)
    TARGET_SERVICE="openssl-target"
    VERSION_ENV="OPENSSL_VERSION"
    ;;
  gnutls)
    TARGET_SERVICE="gnutls-target"
    VERSION_ENV="GNUTLS_VERSION"
    ;;
  mbedtls)
    TARGET_SERVICE="mbedtls-target"
    VERSION_ENV="MBEDTLS_VERSION"
    ;;
  wolfssl)
    TARGET_SERVICE="wolfssl-target"
    VERSION_ENV="WOLFSSL_VERSION"
    ;;
  *)
    echo "Unknown target library: $TARGET_LIBRARY" >&2
    exit 2 ;;
esac

case "$SUITE" in
  5246)
    MODULE_PATH="/app/maude/requirements/5246-core.maude"
    DEFAULT_REQUIREMENTS="1-39"
    ;;
  8446)
    MODULE_PATH="/app/maude/requirements/8446-core.maude"
    DEFAULT_REQUIREMENTS="1-93"
    ;;
  hrr)
    MODULE_PATH="/app/maude/requirements/hrr.maude"
    DEFAULT_REQUIREMENTS="1-64"
    ;;
  psk)
    MODULE_PATH="/app/maude/requirements/psk.maude"
    DEFAULT_REQUIREMENTS="1-141"
    ;;
  *)
    echo "Unknown suite: $SUITE" >&2
    exit 2 ;;
esac

if [[ -z "$REQUIREMENTS" ]]; then
  REQUIREMENTS="$DEFAULT_REQUIREMENTS"
fi

SAFE_VERSION="${TARGET_VERSION//[^A-Za-z0-9]/_}"
GENERATED_CONTAINER_DIR="/work/generated/$SUITE"
RESULT_CONTAINER_BASE="/work/results/$SUITE/${TARGET_LIBRARY}-${SAFE_VERSION}"
GENERATED_HOST_DIR="$REPO_ROOT/work/generated/$SUITE"
RESULT_HOST_BASE="$REPO_ROOT/work/results/$SUITE/${TARGET_LIBRARY}-${SAFE_VERSION}"
RUNNER_HOST_DIR="$RESULT_HOST_BASE/runner"

mkdir -p "$GENERATED_HOST_DIR" "$RESULT_HOST_BASE/scenario" "$RESULT_HOST_BASE/library" "$RUNNER_HOST_DIR"

MISSING_TESTER_SCENARIOS=()

compose() {
  (cd "$REPO_ROOT" && env COMPOSE_IGNORE_ORPHANS="${COMPOSE_IGNORE_ORPHANS:-true}" "$VERSION_ENV=$TARGET_VERSION" docker compose --profile targets "$@")
}

cleanup_current_background() {
  if [[ -n "$CURRENT_BG_NAME" ]]; then
    cleanup_container "$CURRENT_BG_NAME"
    CURRENT_BG_NAME=""
  fi
  if [[ -n "$CURRENT_BG_LOG_PID" ]]; then
    wait "$CURRENT_BG_LOG_PID" 2>/dev/null || true
    CURRENT_BG_LOG_PID=""
  fi
}

on_interrupt() {
  echo
  echo "Interrupted."
  cleanup_current_background
  exit 130
}

trap on_interrupt INT TERM

expand_ranges() {
  local input="$1"
  local part start end value
  input="${input//,/ }"
  for part in $input; do
    if [[ "$part" == *-* ]]; then
      start="${part%-*}"
      end="${part#*-}"
      for ((value=start; value<=end; value++)); do
        printf '%s\n' "$value"
      done
    else
      printf '%s\n' "$part"
    fi
  done
}

tls_version_for_requirement() {
  case "$SUITE" in
    5246)
      echo "2"
      ;;
    8446|hrr|psk)
      echo "3"
      ;;
  esac
}

target_mode_for_requirement() {
  local requirement="$1"
  case "$SUITE" in
    5246)
      if (( requirement <= 19 )); then echo "server"; else echo "client"; fi
      ;;
    8446)
      if (( requirement <= 35 )); then echo "server"; else echo "client"; fi
      ;;
    hrr)
      if (( requirement <= 20 )); then
        echo "client"
      elif (( requirement <= 43 )); then
        echo "server"
      else
        echo "client"
      fi
      ;;
    psk)
      case "$requirement" in
        1[5-9]|2[0-9]|4[4-9]|5[0-9]|6[0-1]|7[2-6]|8[7-9]|9[0-5]|10[4-9]|11[0-9]|120|12[9-9]|13[0-5]|13[8-9]|14[0-1])
          echo "client"
          ;;
        *)
          echo "server"
          ;;
      esac
      ;;
  esac
}

opposite_mode() {
  if [[ "$1" == "server" ]]; then
    echo "client"
  else
    echo "server"
  fi
}

cert_for_mode() {
  local mode="$1"
  if [[ "$mode" == "server" ]]; then
    echo "/certs/server-ecc-cert.pem /certs/server-ecc-key.pem"
  else
    echo "/certs/client-ecc-cert.pem /certs/client-ecc-key.pem"
  fi
}

cleanup_container() {
  local name="$1"
  docker rm -f "$name" >/dev/null 2>&1 || true
}

follow_container_logs() {
  local name="$1"
  local logfile="$2"

  if [[ "$PRINT_OUTPUT" == "true" ]]; then
    docker logs -f "$name" 2>&1 | tee "$logfile" &
  else
    docker logs -f "$name" >"$logfile" 2>&1 &
  fi
  CURRENT_BG_LOG_PID="$!"
}

run_logged() {
  local logfile="$1"
  shift
  if [[ "$PRINT_OUTPUT" == "true" ]]; then
    "$@" </dev/null 2>&1 | tee "$logfile"
    return "${PIPESTATUS[0]}"
  fi
  "$@" </dev/null >"$logfile" 2>&1
}

run_compose_with_timeout() {
  local logfile="$1"
  local timeout_seconds="$2"
  shift 2

  if [[ "$PRINT_OUTPUT" == "true" ]]; then
    (compose "$@" </dev/null 2>&1 | tee "$logfile") &
  else
    (compose "$@" </dev/null >"$logfile" 2>&1) &
  fi

  local pid="$!"
  local deadline=$((SECONDS + timeout_seconds))
  while kill -0 "$pid" >/dev/null 2>&1; do
    if (( SECONDS >= deadline )); then
      kill "$pid" >/dev/null 2>&1 || true
      wait "$pid" 2>/dev/null || true
      return 124
    fi
    sleep 1
  done

  wait "$pid"
}

wait_for_service_dns() {
  local service="$1"
  local hostname="$2"
  local timeout_seconds="$3"
  local logfile="$4"
  local dns_script

  dns_script='hostname="$1"; timeout_seconds="$2"; i=0
resolve_host() {
  if command -v getent >/dev/null 2>&1; then
    getent hosts "$hostname" >/dev/null 2>&1
  elif command -v python3 >/dev/null 2>&1; then
    python3 -c "import socket, sys; socket.getaddrinfo(sys.argv[1], None)" "$hostname" >/dev/null 2>&1
  elif command -v ping >/dev/null 2>&1; then
    ping -c 1 -W 1 "$hostname" >/dev/null 2>&1
  else
    return 127
  fi
}
while [ "$i" -lt "$timeout_seconds" ]; do
  if resolve_host; then
    exit 0
  fi
  i=$((i + 1))
  sleep 1
done
echo "[runner] timed out waiting for Docker DNS: hostname=$hostname timeout=${timeout_seconds}s" >&2
exit 1'

  compose run --rm --no-deps --entrypoint /bin/sh "$service" \
    -c "$dns_script" _ "$hostname" "$timeout_seconds" >>"$logfile" 2>&1 && return 0

  echo "[runner] Docker DNS readiness check failed: service=$service hostname=$hostname" >>"$logfile"
  return 1
}

wait_for_container_listen() {
  local container="$1"
  local port="$2"
  local timeout_seconds="$3"
  local logfile="$4"
  local port_hex
  local check_script
  local failure_reason="timeout"
  local deadline=$((SECONDS + timeout_seconds))

  port_hex="$(printf '%04X' "$port")"
  check_script='port_hex="$1"; for file in /proc/net/tcp /proc/net/tcp6; do [ -r "$file" ] || continue; awk -v port_hex="$port_hex" '\''NR > 1 { split($2, local_addr, ":"); if (local_addr[2] == port_hex && $4 == "0A") found = 1 } END { exit found ? 0 : 1 }'\'' "$file" && exit 0; done; exit 1'

  while (( SECONDS < deadline )); do
    local running
    running="$(docker inspect -f '{{.State.Running}}' "$container" 2>/dev/null || true)"
    if [[ "$running" != "true" ]]; then
      failure_reason="exited"
      break
    fi
    if docker exec "$container" /bin/sh -c "$check_script" _ "$port_hex" >/dev/null 2>&1; then
      return 0
    fi
    sleep 1
  done

  {
    if [[ "$failure_reason" == "exited" ]]; then
      echo "[runner] background container exited before listening: container=$container port=$port"
    else
      echo "[runner] timed out waiting for container listen: container=$container port=$port timeout=${timeout_seconds}s"
    fi
    docker inspect -f 'state={{.State.Status}} exit={{.State.ExitCode}} error={{.State.Error}}' "$container" || true
    if [[ "$failure_reason" != "exited" ]]; then
      docker exec "$container" /bin/sh -c "cat /proc/net/tcp /proc/net/tcp6 2>/dev/null" || true
    fi
  } >>"$logfile" 2>&1
  return 1
}

wait_for_background_ready() {
  local container="$1"
  local port="$2"
  local foreground_service="$3"
  local timeout_seconds="$4"
  local logfile="$5"

  wait_for_container_listen "$container" "$port" "$timeout_seconds" "$logfile" || return 1
  wait_for_service_dns "$foreground_service" "$container" "$timeout_seconds" "$logfile" || return 1
}

generate_requirement() {
  local requirement="$1"
  local tls_version
  tls_version="$(tls_version_for_requirement "$requirement")"
  local logfile="$RUNNER_HOST_DIR/generate_requirement${requirement}.log"

  echo "[generate] suite=$SUITE requirement=$requirement tls-version=$tls_version"
  run_logged "$logfile" \
    compose run --rm --no-deps mta \
      generate "$tls_version" "$requirement" "$MODULE_PATH" "$GENERATED_CONTAINER_DIR"
}

record_missing_tester_scenario() {
  local requirement="$1"
  local case_id="${2:-}"

  if [[ -n "$case_id" ]]; then
    MISSING_TESTER_SCENARIOS+=("$GENERATED_HOST_DIR/requirement${requirement}_${case_id}.tester_scenario")
  else
    MISSING_TESTER_SCENARIOS+=("$GENERATED_HOST_DIR/requirement${requirement}_*.tester_scenario")
  fi
}

print_missing_tester_scenarios() {
  if [[ "${#MISSING_TESTER_SCENARIOS[@]}" -eq 0 ]]; then
    return 0
  fi

  echo
  echo "Skipped missing generated tester_scenario files:"
  local scenario_path
  for scenario_path in "${MISSING_TESTER_SCENARIOS[@]}"; do
    echo "  - $scenario_path"
  done
}

run_case() {
  local requirement="$1"
  local case_id="$2"
  local target_mode scenario_mode
  target_mode="$(target_mode_for_requirement "$requirement")"
  scenario_mode="$(opposite_mode "$target_mode")"

  local tester_scenario="$GENERATED_CONTAINER_DIR/requirement${requirement}_${case_id}.tester_scenario"
  local target_scenario="$GENERATED_CONTAINER_DIR/requirement${requirement}_${case_id}.target_scenario"
  local scenario_out="$RESULT_CONTAINER_BASE/scenario/requirement${requirement}_${case_id}"
  local target_out="$RESULT_CONTAINER_BASE/library"
  local target_log="$RUNNER_HOST_DIR/requirement${requirement}_${case_id}.target_runner.log"
  local tester_log="$RUNNER_HOST_DIR/requirement${requirement}_${case_id}.tester_runner.log"
  local bg_name="mta-${SUITE}-${TARGET_LIBRARY}-${SAFE_VERSION}-r${requirement}-c${case_id}-bg"
  local fg_status=0 bg_log_pid=""
  local cert key

  cleanup_container "$bg_name"
  CURRENT_BG_NAME=""
  CURRENT_BG_LOG_PID=""

  echo "[run] suite=$SUITE requirement=$requirement case=$case_id target=$TARGET_LIBRARY:$TARGET_VERSION target-mode=$target_mode scenario-mode=$scenario_mode"

  if [[ "$target_mode" == "server" ]]; then
    read -r cert key < <(cert_for_mode "$target_mode")
    compose run --rm --no-deps -d --name "$bg_name" "$TARGET_SERVICE" \
      --scenario "$target_scenario" \
      --mode "$target_mode" \
      --host "0.0.0.0" \
      --port "$PORT" \
      --ca-file "/certs/ca-cert.pem" \
      --cert-file "$cert" \
      --key-file "$key" \
      --out "$target_out" >/dev/null

    CURRENT_BG_NAME="$bg_name"
    follow_container_logs "$bg_name" "$target_log"
    bg_log_pid="$CURRENT_BG_LOG_PID"

    read -r cert key < <(cert_for_mode "$scenario_mode")
    if wait_for_background_ready "$bg_name" "$PORT" mta "$STARTUP_DELAY_SECONDS" "$tester_log"; then
      set +e
      run_compose_with_timeout "$tester_log" "$TIMEOUT_SECONDS" \
        run --rm --no-deps mta \
          run "$tester_scenario" "$scenario_mode" "$bg_name" "$PORT" \
          "/certs/ca-cert.pem" "$cert" "$key" \
          "/app/resources/tls-attacker/default_config.xml" "$scenario_out"
      fg_status="$?"
      set -e
    else
      fg_status=125
    fi
  else
    read -r cert key < <(cert_for_mode "$scenario_mode")
    compose run --rm --no-deps -d --name "$bg_name" mta \
      run "$tester_scenario" "$scenario_mode" "0.0.0.0" "$PORT" \
      "/certs/ca-cert.pem" "$cert" "$key" \
      "/app/resources/tls-attacker/default_config.xml" "$scenario_out" >/dev/null

    CURRENT_BG_NAME="$bg_name"
    follow_container_logs "$bg_name" "$tester_log"
    bg_log_pid="$CURRENT_BG_LOG_PID"

    read -r cert key < <(cert_for_mode "$target_mode")
    if wait_for_background_ready "$bg_name" "$PORT" "$TARGET_SERVICE" "$MTA_STARTUP_DELAY_SECONDS" "$target_log"; then
      set +e
      run_compose_with_timeout "$target_log" "$TIMEOUT_SECONDS" \
        run --rm --no-deps "$TARGET_SERVICE" \
          --scenario "$target_scenario" \
          --mode "$target_mode" \
          --host "$bg_name" \
          --port "$PORT" \
          --ca-file "/certs/ca-cert.pem" \
          --cert-file "$cert" \
          --key-file "$key" \
          --out "$target_out"
      fg_status="$?"
      set -e
    else
      fg_status=125
    fi
  fi

  cleanup_container "$bg_name"
  CURRENT_BG_NAME=""
  if [[ -n "$bg_log_pid" ]]; then
    wait "$bg_log_pid" 2>/dev/null || true
    CURRENT_BG_LOG_PID=""
  fi

  if [[ "$fg_status" -eq 125 ]]; then
    echo "  background endpoint was not ready"
    if [[ "$PRINT_OUTPUT" == "true" && "$target_mode" == "server" ]]; then
      local target_artifact="$RESULT_HOST_BASE/library/requirement${requirement}_${case_id}.target_scenario"
      if [[ -f "$target_artifact" ]]; then
        echo "  background target output: $target_artifact"
        tail -20 "$target_artifact"
      fi
    fi
  elif [[ "$fg_status" -ne 0 && "$fg_status" -ne 124 ]]; then
    echo "  foreground exited with status $fg_status"
  elif [[ "$fg_status" -eq 124 ]]; then
    echo "  foreground timed out after ${TIMEOUT_SECONDS}s"
  fi
}

run_requirement() {
  local requirement="$1"
  local tester_path base case_id

  if [[ -n "$CASES" ]]; then
    while IFS= read -r case_id; do
      tester_path="$GENERATED_HOST_DIR/requirement${requirement}_${case_id}.tester_scenario"
      if [[ -f "$tester_path" ]]; then
        run_case "$requirement" "$case_id"
      else
        record_missing_tester_scenario "$requirement" "$case_id"
      fi
    done < <(expand_ranges "$CASES")
    return 0
  fi

  local pattern="$GENERATED_HOST_DIR/requirement${requirement}_"'*.tester_scenario'
  shopt -s nullglob
  local tester_files=( $pattern )
  shopt -u nullglob

  if [[ "${#tester_files[@]}" -eq 0 ]]; then
    record_missing_tester_scenario "$requirement"
    return 0
  fi

  for tester_path in "${tester_files[@]}"; do
    base="$(basename "$tester_path")"
    case_id="${base#requirement${requirement}_}"
    case_id="${case_id%.tester_scenario}"
    run_case "$requirement" "$case_id"
  done
}

main() {
  if [[ "$BUILD_MTA" == "true" ]]; then
    echo "[build] docker compose build mta"
    compose build mta
  fi

  local requirement
  local requirements_to_process=()
  mapfile -t requirements_to_process < <(expand_ranges "$REQUIREMENTS")

  if [[ "$MODE" == "all" || "$MODE" == "generate" ]]; then
    for requirement in "${requirements_to_process[@]}"; do
      generate_requirement "$requirement"
    done
  fi

  if [[ "$MODE" == "all" || "$MODE" == "run" ]]; then
    for requirement in "${requirements_to_process[@]}"; do
      run_requirement "$requirement"
    done
  fi

  echo "Done."
  echo "Generated scenarios: $GENERATED_HOST_DIR"
  echo "Results: $RESULT_HOST_BASE"
  print_missing_tester_scenarios
}

main
