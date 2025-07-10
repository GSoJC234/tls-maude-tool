package runner;

import docker.DockerRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// ./client -version TLS-13 -ciphersuites TLS13-AES128-CCM-SHA256 -groups SECP256R1 -sigalgs ECDSA+SHA256 -ip 127.0.0.1 -port 4433
//[sigalgs, ecdsa-secp256r1-sha256], [version, TLS-13], [groups, secp256r1]
public class RunWolfSSL extends RunTLSLibrary {

    private static final Map<String, String> transformMap = Map.ofEntries(
            Map.entry("TLS-AES-128-CCM-SHA256", "TLS13-AES128-CCM-SHA256"),
            Map.entry("TLS-AES-128-CCM-8-SHA256", "TLS13-AES128-CCM8-SHA256"),
            Map.entry("TLS-AES-128-GCM-SHA256", "TLS13-AES128-GCM-SHA256"),
            Map.entry("TLS-AES-256-GCM-SHA384", "TLS13-AES256-GCM-SHA384"),
            Map.entry("secp192k1", "SECP192K1"),
            Map.entry("secp192r1", "SECP192R1"),
            Map.entry("secp224k1", "SECP224K1"),
            Map.entry("secp224r1", "SECP224R1"),
            Map.entry("secp256k1", "SECP256K1"),
            Map.entry("secp256r1", "SECP256R1"),
            Map.entry("secp384r1", "SECP384R1"),
            Map.entry("secp521r1", "SECP521R1"),
            Map.entry("ecdsa-secp256r1-sha256", "ECDSA+SHA256")
    );

    @Override
    public String TLSLibraryName() {
        return "wolfssl";
    }

    @Override
    protected String transformValue(String value) {
        return Arrays.stream(value.split("\\s+"))
                .map(v -> transformMap.getOrDefault(v, v))
                .collect(Collectors.joining(":"));
    }
}