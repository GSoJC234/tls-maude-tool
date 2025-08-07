package runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//docker run --rm --network=host openssl-client:3.5.0 -connect 127.0.0.1:4433 -tls1_3 -ciphersuites TLS_AES_128_CCM_SHA256 -curves P-256 -sigalgs ecdsa_secp256r1_sha256 -no_ticket -noservername
//docker run -it -v cert-data:/cert/:ro,nocopy --rm openssl-server -key /cert/ec256key.pem -cert /cert/ec256cert.pem
public class RunOpenSSL extends RunTLSLibrary{

    private static final Map<String, String> transformMap = Map.ofEntries(
            Map.entry("TLS-AES-128-CCM-SHA256", "TLS13-AES-128-CCM-SHA256"),
            Map.entry("TLS-AES-128-CCM-8-SHA256", "TLS13-AES-128-CCM-8-SHA256"),
            Map.entry("TLS-AES-128-GCM-SHA256", "TLS13-AES-128-GCM-SHA256"),
            Map.entry("TLS-AES-256-GCM-SHA384", "TLS13-AES-256-GCM-SHA384"),
            Map.entry("secp192k1", "SECP192K1"),
            Map.entry("secp192r1", "SECP192R1"),
            Map.entry("secp224k1", "SECP224K1"),
            Map.entry("secp224r1", "SECP224R1"),
            Map.entry("secp256r1", "SECP256R1"),
            Map.entry("secp256k1", "SECP256K1"),
            Map.entry("secp384r1", "SECP384R1"),
            Map.entry("secp521r1", "SECP521R1"),
            Map.entry("ecdsa-secp256r1-sha256", "ECDSA+SHA256"),
            Map.entry("ecdsa-secp384r1-sha384", "ECDSA+SHA384"),
            Map.entry("ecdsa-secp521r1-sha521", "ECDSA+SHA521"),
            Map.entry("rsa-pkcs-sha256", "RSA+SHA256"),
            Map.entry("rsa-pkcs-sha384", "RSA+SHA384"),
            Map.entry("rsa-pkcs-sha512", "RSA+SHA512")
    );

    @Override
    public String TLSLibraryName() {
        return "openssl";
    }

    @Override
    protected String transformValue(String value) {
        return Arrays.stream(value.split("\\s+"))
                .map(v -> transformMap.getOrDefault(v, v))
                .collect(Collectors.joining(":"));
    }

}
