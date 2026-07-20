#!/usr/bin/env bash
set -euo pipefail

COMMAND="${1:-generate}"
shift || true

case "$COMMAND" in
  generate|--generate)
    REQUIREMENT_TLS_VERSION="${1:-3}"
    REQUIREMENT_INDEX="${2:-0}"
    MODULE_PATH="${3:-/app/maude/requirements/requirement11.maude}"
    OUTPUT_DIR="${4:-/work/generated}"

    mkdir -p "$OUTPUT_DIR"

    echo "[mta] generate"
    echo "  maude: maude"
    echo "  module: $MODULE_PATH"
    echo "  tls-version: $REQUIREMENT_TLS_VERSION"
    echo "  requirement-index: $REQUIREMENT_INDEX"
    echo "  output: $OUTPUT_DIR"

    exec java -cp "$MTA_CLASSPATH" mta.main.Main \
      --generate \
      maude \
      "$MODULE_PATH" \
      "$REQUIREMENT_TLS_VERSION" \
      "$REQUIREMENT_INDEX" \
      "$OUTPUT_DIR"
    ;;

  run|--run)
    SCENARIO_PATH="${1:-/work/generated/requirement1_0.tester_scenario}"
    TESTER_MODE="${2:-client}"
    TESTER_IP="${3:-}"
    TESTER_PORT="${4:-4433}"
    CA_CERTIFICATE_PATH="${5:-/certs/ca-cert.pem}"
    CERTIFICATE_PATH="${6:-}"
    PRIVATE_KEY_PATH="${7:-}"
    TLS_ATTACKER_CONFIG_PATH="${8:-/app/resources/tls-attacker/default_config.xml}"
    OUTPUT_PATH="${9:-/work/results/scenario.log}"
    EXTRA_ARGS=("${@:10}")

    case "$TESTER_MODE" in
      client)
        TESTER_IP="${TESTER_IP:-gnutls-target}"
        CERTIFICATE_PATH="${CERTIFICATE_PATH:-/certs/client-ecc-cert.pem}"
        PRIVATE_KEY_PATH="${PRIVATE_KEY_PATH:-/certs/client-ecc-key.pem}"
        ;;
      server)
        TESTER_IP="${TESTER_IP:-0.0.0.0}"
        CERTIFICATE_PATH="${CERTIFICATE_PATH:-/certs/server-ecc-cert.pem}"
        PRIVATE_KEY_PATH="${PRIVATE_KEY_PATH:-/certs/server-ecc-key.pem}"
        ;;
      *)
        echo "Unknown run mode: $TESTER_MODE" >&2
        echo "Expected: client or server" >&2
        exit 2
        ;;
    esac

    mkdir -p "$(dirname "$OUTPUT_PATH")"

    echo "[mta] run"
    echo "  scenario: $SCENARIO_PATH"
    echo "  mode: $TESTER_MODE"
    echo "  ip: $TESTER_IP"
    echo "  port: $TESTER_PORT"
    echo "  ca-certificate: $CA_CERTIFICATE_PATH"
    echo "  certificate: $CERTIFICATE_PATH"
    echo "  private-key: $PRIVATE_KEY_PATH"
    echo "  tls-attacker-config: $TLS_ATTACKER_CONFIG_PATH"
    echo "  output: $OUTPUT_PATH"

    exec java -cp "$MTA_CLASSPATH" mta.main.Main \
      --run \
      "$SCENARIO_PATH" \
      "$TESTER_MODE" \
      "$TESTER_IP" \
      "$TESTER_PORT" \
      "$CA_CERTIFICATE_PATH" \
      "$CERTIFICATE_PATH" \
      "$PRIVATE_KEY_PATH" \
      "$TLS_ATTACKER_CONFIG_PATH" \
      "$OUTPUT_PATH" \
      "${EXTRA_ARGS[@]}"
    ;;

  shell)
    exec /bin/bash "$@"
    ;;

  *)
    echo "Usage:" >&2
    echo "  run-test.sh generate|--generate [tls-version] [requirement-index] [module-path] [output-dir]" >&2
    echo "  run-test.sh run|--run [scenario-path] [mode] [ip] [port] [ca-cert-path] [cert-path] [key-path] [tls-attacker-config-path] [output-path] [--profile tlsprofile.dsl]" >&2
    echo "  run-test.sh shell" >&2
    exit 2
    ;;
esac
