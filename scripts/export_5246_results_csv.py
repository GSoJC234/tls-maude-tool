#!/usr/bin/env python3
import argparse
import csv
import re
from pathlib import Path


DEFAULT_TARGETS = (
    "openssl-1_1_1i",
    "openssl-1_1_1j",
    "wolfssl-5_1_0",
    "wolfssl-5_3_0",
    "wolfssl-5_4_0",
    "wolfssl-5_5_0",
)


ACTION_ASSERT_RE = re.compile(
    r"ActionExecutionException: Assertion fails: expected -> \[([^\]]*)\], actual -> \[([^\]]*)\]"
)
FIELD_ASSERT_RE = re.compile(
    r"Assertion fails(?:\(([^)]*)\))?: expected -> ([^,\n]*), actual -> ([^\n]*)"
)
CASE_RE = re.compile(r"requirement(\d+)_(\d+)$")


def read_text(path: Path) -> str:
    if not path.exists():
        return ""
    return path.read_text(errors="replace")


def compact(text: str) -> str:
    return re.sub(r"\s+", " ", text).strip()


def parse_maude_mutations(path: Path) -> dict[int, str]:
    text = read_text(path)
    mutations: dict[int, str] = {}
    pattern = re.compile(
        r"op\s+scen(\d+)\s*:.*?eq\s+scen\1\s*=.*?(changeContent\(.*?\))\s*->",
        re.S,
    )
    for match in pattern.finditer(text):
        requirement = int(match.group(1))
        mutations[requirement] = compact(match.group(2))
    return mutations


def target_mode(requirement: int) -> str:
    return "server" if requirement <= 19 else "client"


def tester_mode(requirement: int) -> str:
    return "client" if target_mode(requirement) == "server" else "server"


def parse_case(path: Path) -> tuple[int, int] | None:
    match = CASE_RE.match(path.name)
    if not match:
        return None
    return int(match.group(1)), int(match.group(2))


def assertion_failures(scenario_text: str) -> list[tuple[str, str]]:
    failures = ACTION_ASSERT_RE.findall(scenario_text)
    if failures:
        return [(normalize_value(exp), normalize_value(act)) for exp, act in failures]

    # Fallback for truncated logs that only have the concise AssertEqualAction line.
    out: list[tuple[str, str]] = []
    for _, exp, act in FIELD_ASSERT_RE.findall(scenario_text):
        out.append((normalize_value(exp), normalize_value(act)))
    return out


def normalize_value(value: str) -> str:
    value = value.strip()
    if not value:
        return "null"
    replacements = {
        "15": "ALERT",
        "16": "HANDSHAKE",
        "14": "CHANGE_CIPHER_SPEC",
        "02": "FATAL",
        "0A": "UNEXPECTED_MESSAGE",
        "28": "HANDSHAKE_FAILURE",
        "2F": "ILLEGAL_PARAMETER",
        "32": "DECODE_ERROR",
        "33": "DECRYPT_ERROR",
        "46": "PROTOCOL_VERSION",
        "6E": "UNSUPPORTED_EXTENSION",
    }
    if value in replacements:
        return replacements[value]
    wrapped = re.fullmatch(r"AlertDescription\{value=([^}]+)\}", value)
    if wrapped:
        return wrapped.group(1)
    wrapped = re.fullmatch(r"AlertLevel\{value=([^}]+)\}", value)
    if wrapped:
        return wrapped.group(1)
    return value


def is_success(scenario_text: str, target_text: str, runner_text: str) -> bool:
    failure_markers = (
        "Assertion fails",
        "ActionExecutionException",
        "No messages were received",
        "scenario output was not generated",
        "could not connect",
        "UnknownHostException",
        "Connection refused",
        "background endpoint was not ready",
        "foreground timed out",
        "foreground exited with status",
    )
    combined = "\n".join((scenario_text, target_text, runner_text))
    return all(marker not in combined for marker in failure_markers)


def expected_final_alert(generated_tester_text: str) -> str:
    match = re.search(r"assertEqual\(c\[([a-z0-9-]+)\],\s*getAlertDescription", generated_tester_text)
    if not match:
        return ""
    return match.group(1).replace("-", "_").upper()


def has_library_error(target_text: str, runner_text: str) -> bool:
    combined = f"{target_text}\n{runner_text}".lower()
    patterns = (
        "ssl_accept error",
        "ssl_connect error",
        "wolfssl error",
        "openssl error",
        "error:",
        "unexpected message",
        "wrong client/server type",
        "can't match cipher suite",
        "no shared cipher",
        "no cipher match",
        "unsupported",
        "illegal",
        "decode",
        "decrypt",
        "bad ",
        "invalid",
        "wrong version",
        "protocol version",
        "handshake failure",
        "certificate verify failed",
        "no ciphers available",
    )
    return any(pattern in combined for pattern in patterns)


def has_specific_malformed_log(target_text: str, runner_text: str) -> bool:
    combined = f"{target_text}\n{runner_text}".lower()
    patterns = (
        "unexpected message",
        "wrong client/server type",
        "can't match cipher suite",
        "no shared cipher",
        "no cipher match",
        "unsupported",
        "illegal",
        "decode",
        "decrypt",
        "bad ",
        "invalid",
        "wrong version",
        "protocol version",
        "handshake failure",
        "certificate verify failed",
        "no ciphers available",
    )
    return any(pattern in combined for pattern in patterns)


def has_crash(target_text: str, runner_text: str) -> bool:
    combined = f"{target_text}\n{runner_text}".lower()
    return any(
        marker in combined
        for marker in (
            "segmentation fault",
            "core dumped",
            "addresssanitizer",
            "exit code[0]: 139",
            "exit code[0]: 134",
            "library exit code[0]: 139",
            "library exit code[0]: 134",
        )
    )


def library_exited(target_text: str) -> bool:
    lower = target_text.lower()
    return "library exit code" in lower or "ssl_accept error" in lower or "ssl_connect error" in lower


def actual_content_type(failures: list[tuple[str, str]]) -> str:
    for expected, actual in failures:
        if expected == "ALERT":
            return actual
    return ""


def actual_alert_description(failures: list[tuple[str, str]]) -> tuple[str, str] | None:
    alert_names = {
        "UNEXPECTED_MESSAGE",
        "BAD_RECORD_MAC",
        "DECRYPTION_FAILED",
        "RECORD_OVERFLOW",
        "DECOMPRESSION_FAILURE",
        "HANDSHAKE_FAILURE",
        "NO_CERTIFICATE",
        "BAD_CERTIFICATE",
        "UNSUPPORTED_CERTIFICATE",
        "CERTIFICATE_REVOKED",
        "CERTIFICATE_EXPIRED",
        "CERTIFICATE_UNKNOWN",
        "ILLEGAL_PARAMETER",
        "UNKNOWN_CA",
        "ACCESS_DENIED",
        "DECODE_ERROR",
        "DECRYPT_ERROR",
        "EXPORT_RESTRICTION",
        "PROTOCOL_VERSION",
        "INSUFFICIENT_SECURITY",
        "INTERNAL_ERROR",
        "USER_CANCELED",
        "NO_RENEGOTIATION",
        "UNSUPPORTED_EXTENSION",
    }
    for expected, actual in failures:
        if expected in alert_names and actual != "null" and expected != actual:
            return expected, actual
    return None


def precondition_failures(failures: list[tuple[str, str]]) -> list[tuple[str, str]]:
    alert_names = {
        "UNEXPECTED_MESSAGE",
        "BAD_RECORD_MAC",
        "DECRYPTION_FAILED",
        "RECORD_OVERFLOW",
        "DECOMPRESSION_FAILURE",
        "HANDSHAKE_FAILURE",
        "NO_CERTIFICATE",
        "BAD_CERTIFICATE",
        "UNSUPPORTED_CERTIFICATE",
        "CERTIFICATE_REVOKED",
        "CERTIFICATE_EXPIRED",
        "CERTIFICATE_UNKNOWN",
        "ILLEGAL_PARAMETER",
        "UNKNOWN_CA",
        "ACCESS_DENIED",
        "DECODE_ERROR",
        "DECRYPT_ERROR",
        "EXPORT_RESTRICTION",
        "PROTOCOL_VERSION",
        "INSUFFICIENT_SECURITY",
        "INTERNAL_ERROR",
        "USER_CANCELED",
        "NO_RENEGOTIATION",
        "UNSUPPORTED_EXTENSION",
    }
    preconditions = []
    for expected, actual in failures:
        if expected in {"ALERT", "FATAL"} or expected in alert_names:
            continue
        preconditions.append((expected, actual))
    return preconditions


def result_description(
    scenario_text: str,
    target_text: str,
    runner_text: str,
    generated_tester_text: str,
) -> str:
    combined = "\n".join((scenario_text, target_text, runner_text))
    lower = combined.lower()
    if "scenario output was not generated" in lower:
        return "Scenario output was not generated; the library/tester run did not reach scenario execution."
    if "unknownhostexception" in lower:
        return "Scenario execution failed because Docker DNS did not resolve the peer container name."
    if "connection refused" in lower or "could not connect" in lower:
        return "Scenario execution failed because the client could not connect to the peer endpoint."
    if "background endpoint was not ready" in lower or "timed out waiting for container listen" in lower:
        return "Harness readiness failed before scenario execution; the background endpoint did not become ready."
    if has_crash(target_text, runner_text):
        return "The library process crashed while handling the test case."

    failures = assertion_failures(scenario_text)
    if not failures and "No messages were received" not in scenario_text:
        return "The scenario failed without a parsed assertion failure."

    parts: list[str] = []
    preconditions = precondition_failures(failures)
    if preconditions:
        details = "; ".join(
            f"expected {expected}, actual {actual}" for expected, actual in preconditions[:4]
        )
        parts.append(f"Precondition assertion failed before evaluating the mutated response: {details}.")

    content_actual = actual_content_type(failures)
    final_alert = expected_final_alert(generated_tester_text)
    if "No messages were received" in scenario_text or content_actual == "null":
        if final_alert:
            parts.append(f"Expected a fatal {final_alert} alert, but no message was received.")
        else:
            parts.append("Expected a response, but no message was received.")
    elif content_actual and content_actual != "ALERT":
        if final_alert:
            parts.append(f"Expected a fatal {final_alert} alert, but the library sent {content_actual}.")
        else:
            parts.append(f"Expected an alert, but the library sent {content_actual}.")

    alert_desc = actual_alert_description(failures)
    if alert_desc:
        expected, actual = alert_desc
        parts.append(f"Alert description assertion failed: expected {expected}, actual {actual}.")

    if not parts:
        details = "; ".join(f"expected {expected}, actual {actual}" for expected, actual in failures[:5])
        parts.append(f"Assertion failed: {details}.")

    return " ".join(parts)


def library_behavior(
    scenario_text: str,
    target_text: str,
    runner_text: str,
    generated_tester_text: str,
) -> str:
    lower = "\n".join((scenario_text, target_text, runner_text)).lower()
    failures = assertion_failures(scenario_text)
    content_actual = actual_content_type(failures)
    alert_desc = actual_alert_description(failures)

    if has_crash(target_text, runner_text):
        return "The library crashed while processing the mutated message."

    if (
        "scenario output was not generated" in lower
        or "background endpoint was not ready" in lower
        or "unknownhostexception" in lower
        or "could not connect" in lower
        or "connection refused" in lower
        or "socket initialization" in lower
    ):
        return "The scenario did not reach a reliable TLS behavior observation because the harness failed before execution."

    if not failures and "No messages were received" not in scenario_text:
        return "The library sent the expected fatal alert and terminated."

    if failures and not content_actual and not alert_desc and "No messages were received" not in scenario_text:
        return "The library sent the expected fatal alert and terminated, but earlier precondition assertions failed."

    if content_actual == "ALERT" or alert_desc:
        return "The library sent a fatal alert and terminated."

    if content_actual in {"HANDSHAKE", "CHANGE_CIPHER_SPEC", "APPLICATION_DATA"}:
        if has_specific_malformed_log(target_text, runner_text):
            return "The library logged an error for the mutated message but still proceeded to the next TLS state."
        return "The library did not visibly reject the mutated message and proceeded to the next TLS state."

    if "No messages were received" in scenario_text or content_actual == "null":
        if has_specific_malformed_log(target_text, runner_text):
            return "The library recognized the malformed message and terminated without sending an alert."
        if has_library_error(target_text, runner_text) or library_exited(target_text):
            return "The library terminated without sending an alert."
        return "The library remained waiting for further messages and was terminated by the harness."

    if has_library_error(target_text, runner_text):
        return "The library recognized the malformed message, but its externally visible response did not match the scenario."

    return "The library behavior did not match the expected alert response."


def sorted_case_files(scenario_dir: Path) -> list[Path]:
    files = [path for path in scenario_dir.iterdir() if path.is_file() and parse_case(path)]
    return sorted(files, key=lambda path: parse_case(path) or (0, 0))


def export_csv(args: argparse.Namespace) -> tuple[Path, int, list[str]]:
    results_root = Path(args.results_root)
    generated_root = Path(args.generated_root)
    mutations = parse_maude_mutations(Path(args.maude_file))
    targets = args.targets or DEFAULT_TARGETS
    missing_targets: list[str] = []
    rows: list[dict[str, str | int]] = []

    for target in targets:
        target_root = results_root / target
        if not target_root.exists():
            missing_targets.append(target)
            continue
        for scenario_file in sorted_case_files(target_root / "scenario"):
            parsed = parse_case(scenario_file)
            if parsed is None:
                continue
            requirement, case = parsed
            generated_tester = generated_root / f"requirement{requirement}_{case}.tester_scenario"
            target_artifact = target_root / "library" / f"requirement{requirement}_{case}.target_scenario"
            target_runner = target_root / "runner" / f"requirement{requirement}_{case}.target_runner.log"
            tester_runner = target_root / "runner" / f"requirement{requirement}_{case}.tester_runner.log"

            scenario_text = read_text(scenario_file)
            target_text = read_text(target_artifact)
            target_runner_text = read_text(target_runner)
            all_runner_text = "\n".join((target_runner_text, read_text(tester_runner)))
            generated_tester_text = read_text(generated_tester)
            success = is_success(scenario_text, target_text, all_runner_text)

            rows.append(
                {
                    "suite": "5246",
                    "library_version": target,
                    "requirement": requirement,
                    "case": case,
                    "target mode": target_mode(requirement),
                    "tester mode": tester_mode(requirement),
                    "maude mutational behavior": mutations.get(requirement, ""),
                    "results": "success" if success else "fail",
                    "result_description": ""
                    if success
                    else result_description(
                        scenario_text,
                        target_text,
                        all_runner_text,
                        generated_tester_text,
                    ),
                    "library_behavior": library_behavior(
                        scenario_text,
                        target_text,
                        target_runner_text,
                        generated_tester_text,
                    ),
                }
            )

    out_path = Path(args.output)
    out_path.parent.mkdir(parents=True, exist_ok=True)
    fieldnames = [
        "suite",
        "library_version",
        "requirement",
        "case",
        "target mode",
        "tester mode",
        "maude mutational behavior",
        "results",
        "result_description",
        "library_behavior",
    ]
    with out_path.open("w", newline="", encoding="utf-8") as handle:
        writer = csv.DictWriter(handle, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)
    return out_path, len(rows), missing_targets


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--results-root", default="work/results/5246")
    parser.add_argument("--generated-root", default="work/generated/5246")
    parser.add_argument("--maude-file", default="maude/casestudy/rfc/5246-core.maude")
    parser.add_argument(
        "--output",
        default="work/results/5246_openssl_wolfssl_analysis.csv",
    )
    parser.add_argument("--targets", nargs="*")
    args = parser.parse_args()

    out_path, row_count, missing_targets = export_csv(args)
    print(f"wrote {row_count} rows to {out_path}")
    if missing_targets:
        print("missing targets: " + ", ".join(missing_targets))


if __name__ == "__main__":
    main()
