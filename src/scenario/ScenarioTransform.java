package scenario;

import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScenarioTransform {

    private int port;
    private String id;
    private String ip;
    private String privateKeyPath;
    private String certificatePath;

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }
    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public String transform(String result) {
        String currentDirectory = Paths.get("").toAbsolutePath().toString();
        String regex = null;

        // 1. v[N] -> vN
        regex = "v\\[(\\d+)]";
        result = result.replaceAll(regex, "v$1");

        // 2. v0 := ... -> Variable v0 = ...
        regex = "(v\\d+)\\s*:=\\s*(.*?);";
        result = result.replaceAll(regex, "Variable $1 = $2;");

        // 3. (Variable v0 = ...) -> Variable v0 = ...
        regex = "\\((Variable \\w+ = .*?)\\)";
        result = result.replaceAll(regex, "$1");

        // 4. pre-defined function -> session . pre-defined function
        Set<String> pre_defined_functions = new HashSet<>();
        pre_defined_functions.add("checkConnection");
        pre_defined_functions.add("connect");
        pre_defined_functions.add("accept");
        pre_defined_functions.add("assertEqual");
        pre_defined_functions.add("getContentType");
        pre_defined_functions.add("getRecordVersion");
        pre_defined_functions.add("getHandshakeMessageType");
        pre_defined_functions.add("getProtocolVersion");
        pre_defined_functions.add("getCipherSuite");
        pre_defined_functions.add("getCompressionMethod");
        pre_defined_functions.add("getSupportedVersion");
        pre_defined_functions.add("getSignatureAndHashAlgorithm");
        pre_defined_functions.add("getSignatureAlgorithm");
        pre_defined_functions.add("getKeyShareEntry");
        pre_defined_functions.add("getNamedGroupFromKeyShare");
        pre_defined_functions.add("getNamedGroup");
        pre_defined_functions.add("getAlertLevel");
        pre_defined_functions.add("getAlertDescription");
        pre_defined_functions.add("getCertificate");
        pre_defined_functions.add("getPublicKeyFromCertificate");
        pre_defined_functions.add("getRandom");
        pre_defined_functions.add("getSessionId");
        pre_defined_functions.add("getHandshakeBody");
        pre_defined_functions.add("getRSAPreMasterSecret");
        pre_defined_functions.add("calculateMasterSecret");
        pre_defined_functions.add("buildKeyShareEntry");
        pre_defined_functions.add("addKeyShareExtension");
        pre_defined_functions.add("addSupportedVersionExtension");
        pre_defined_functions.add("addSignatureAndHashAlgorithmExtension");
        pre_defined_functions.add("addSupportedGroupExtension");
        pre_defined_functions.add("updateContext");
        pre_defined_functions.add("send");
        pre_defined_functions.add("recv");
        pre_defined_functions.add("buildClientHello");
        pre_defined_functions.add("buildServerHello");
        pre_defined_functions.add("buildEncryptedExtension");
        pre_defined_functions.add("buildCertificate");
        pre_defined_functions.add("buildCertificateVerify");
        pre_defined_functions.add("buildChangeCipher");
        pre_defined_functions.add("buildFinished");
        pre_defined_functions.add("buildAlert");
        pre_defined_functions.add("buildRecord");
        pre_defined_functions.add("genCertificatePrivateKey");
        pre_defined_functions.add("genCertificate");
        pre_defined_functions.add("changeCertificate");
        pre_defined_functions.add("reEncryptRSAClientKeyExchange");
        pre_defined_functions.add("buildInvalidPaddingRSAClientKeyExchange");
        pre_defined_functions.add("changeVerifyData");
        pre_defined_functions.add("encrypt");
        pre_defined_functions.add("decrypt");

        for (String entry : pre_defined_functions) {
            regex = "\\b" + entry + "\\((.*?)\\)";
            result = result.replaceAll(regex, "session." + entry + "($1)");
        }
        // 5. generateNonce
        regex = "generateRandom\\(nonce\\((N\\d+\\s\\.\\s\\w+),\\s\\d+\\)\\)";
        result = result.replaceAll(regex, "session.constant(Random.NONCE)");
        regex = "generateRandom\\(noNonce\\)";
        result = result.replaceAll(regex, "session.constant(Random.EMPTY)");

        // 6. N1 . CI -> "N1 . CI"
        regex = "(N\\d+)\\s+\\.\\s+(\\w+)";
        result = result.replaceAll(regex, "\"$1 . $2\"");

        // 7. c[T] -> session.constant(T)
        regex = "c\\[\\s*(.*?)\\s*\\]";
        result = result.replaceAll(regex, "session.constant($1)");

        // 8. Constant T Mapping
        result = result.replaceAll("handshake", "ProtocolMessageType.HANDSHAKE");
        result = result.replaceAll("alert", "ProtocolMessageType.ALERT");
        result = result.replaceAll("change-cipher-spec", "ProtocolMessageType.CHANGE_CIPHER_SPEC");

        result = result.replaceAll("TLS-11", "ProtocolVersion.TLS11");
        result = result.replaceAll("TLS-12", "ProtocolVersion.TLS12");
        result = result.replaceAll("TLS-13", "ProtocolVersion.TLS13");

        result = result.replaceAll("client-hello", "HandshakeMessageType.CLIENT_HELLO");
        result = result.replaceAll("server-hello-done", "HandshakeMessageType.SERVER_HELLO_DONE");
        result = result.replaceAll("server-hello", "HandshakeMessageType.SERVER_HELLO");
        result = result.replaceAll("encrypted-extension", "HandshakeMessageType.ENCRYPTED_EXTENSION");
        result = result.replaceAll("certificate-request", "HandshakeMessageType.CERTIFICATE_REQUEST");
        result = result.replaceAll("certificate-verify", "HandshakeMessageType.CERTIFICATE_VERIFY");
        result = result.replaceAll("certificate", "HandshakeMessageType.CERTIFICATE");
        result = result.replaceAll("server-key-exchange", "HandshakeMessageType.SERVER_KEY_EXCHANGE");
        result = result.replaceAll("client-key-exchange", "HandshakeMessageType.CLIENT_KEY_EXCHANGE");
        result = result.replaceAll("finished", "HandshakeMessageType.FINISHED");

        result = result.replaceAll("TLS-DHE-RSA-WITH-3DES-EDE-CBC-SHA",       "CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA");
        result = result.replaceAll("TLS-DHE-RSA-WITH-AES-256-CBC-SHA",        "CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA");
        result = result.replaceAll("TLS-DHE-RSA-WITH-AES-128-CBC-SHA",        "CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA");
        result = result.replaceAll("TLS-DH-anon-WITH-AES-128-CBC-SHA",        "CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA");
        result = result.replaceAll("TLS-RSA-WITH-AES-256-CBC-SHA",            "CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA");
        result = result.replaceAll("TLS-RSA-WITH-AES-128-CBC-SHA",            "CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA");
        result = result.replaceAll("TLS-RSA-WITH-NULL-MD5",                   "CipherSuite.TLS_RSA_WITH_NULL_MD5");
        result = result.replaceAll("TLS-RSA-WITH-NULL-SHA",                   "CipherSuite.TLS_RSA_WITH_NULL_SHA");
        result = result.replaceAll("TLS-PSK-WITH-AES-256-CBC-SHA",            "CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA");
        result = result.replaceAll("TLS-PSK-WITH-AES-128-CBC-SHA256",         "CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256");
        result = result.replaceAll("TLS-PSK-WITH-AES-256-CBC-SHA384",         "CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384");
        result = result.replaceAll("TLS-PSK-WITH-AES-128-CBC-SHA",            "CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA");
        result = result.replaceAll("TLS-PSK-WITH-NULL-SHA256",                "CipherSuite.TLS_PSK_WITH_NULL_SHA256");
        result = result.replaceAll("TLS-PSK-WITH-NULL-SHA384",                "CipherSuite.TLS_PSK_WITH_NULL_SHA384");
        result = result.replaceAll("TLS-PSK-WITH-NULL-SHA",                   "CipherSuite.TLS_PSK_WITH_NULL_SHA");
        result = result.replaceAll("TLS-ECDHE-RSA-WITH-AES-256-CBC-SHA",      "CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
        result = result.replaceAll("TLS-ECDHE-RSA-WITH-AES-128-CBC-SHA",      "CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-AES-256-CBC-SHA",    "CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-AES-128-CBC-SHA",    "CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");
        result = result.replaceAll("TLS-ECDHE-RSA-WITH-RC4-128-SHA",          "CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-RC4-128-SHA",        "CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA");
        result = result.replaceAll("TLS-ECDHE-RSA-WITH-3DES-EDE-CBC-SHA",     "CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-3DES-EDE-CBC-SHA",   "CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA");
        result = result.replaceAll("TLS-ECDHE-RSA-WITH-AES-128-CBC-SHA256",   "CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-AES-128-CBC-SHA256", "CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256");
        result = result.replaceAll("TLS-ECDHE-RSA-WITH-AES-256-CBC-SHA384",   "CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-AES-256-CBC-SHA384", "CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-NULL-SHA",           "CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA");
        result = result.replaceAll("TLS-ECDHE-PSK-WITH-NULL-SHA256",          "CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256");
        result = result.replaceAll("TLS-ECDHE-PSK-WITH-AES-128-CBC-SHA256",   "CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256");
        result = result.replaceAll("TLS-ECDH-RSA-WITH-AES-256-CBC-SHA",       "CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA");
        result = result.replaceAll("TLS-ECDH-RSA-WITH-AES-128-CBC-SHA",       "CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA");
        result = result.replaceAll("TLS-ECDH-ECDSA-WITH-AES-256-CBC-SHA",     "CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA");
        result = result.replaceAll("TLS-ECDH-ECDSA-WITH-AES-128-CBC-SHA",     "CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA");
        result = result.replaceAll("TLS-ECDH-RSA-WITH-RC4-128-SHA",           "CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA");
        result = result.replaceAll("TLS-ECDH-ECDSA-WITH-RC4-128-SHA",         "CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA");
        result = result.replaceAll("TLS-ECDH-RSA-WITH-3DES-EDE-CBC-SHA",      "CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA");
        result = result.replaceAll("TLS-ECDH-ECDSA-WITH-3DES-EDE-CBC-SHA",    "CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA");
        result = result.replaceAll("TLS-ECDH-RSA-WITH-AES-128-CBC-SHA256",    "CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256");
        result = result.replaceAll("TLS-ECDH-ECDSA-WITH-AES-128-CBC-SHA256",  "CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256");
        result = result.replaceAll("TLS-ECDH-RSA-WITH-AES-256-CBC-SHA384",    "CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384");
        result = result.replaceAll("TLS-ECDH-ECDSA-WITH-AES-256-CBC-SHA384",  "CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384");
        result = result.replaceAll("TLS-DHE-RSA-WITH-AES-256-CBC-SHA256",     "CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256");
        result = result.replaceAll("TLS-DHE-RSA-WITH-AES-128-CBC-SHA256",     "CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");
        result = result.replaceAll("TLS-RSA-WITH-AES-256-CBC-SHA256",         "CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA256");
        result = result.replaceAll("TLS-RSA-WITH-AES-128-CBC-SHA256",         "CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256");
        result = result.replaceAll("TLS-RSA-WITH-NULL-SHA256",                "CipherSuite.TLS_RSA_WITH_NULL_SHA256");
        result = result.replaceAll("TLS-DHE-PSK-WITH-AES-128-CBC-SHA256",     "CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256");
        result = result.replaceAll("TLS-DHE-PSK-WITH-NULL-SHA256",            "CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256");
        result = result.replaceAll("TLS-DHE-PSK-WITH-AES-256-CBC-SHA384",     "CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384");
        result = result.replaceAll("TLS-DHE-PSK-WITH-NULL-SHA384",            "CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384");
        result = result.replaceAll("TLS-RSA-WITH-AES-128-GCM-SHA256",         "CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256");
        result = result.replaceAll("TLS-RSA-WITH-AES-256-GCM-SHA384",         "CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384");
        result = result.replaceAll("TLS-DHE-RSA-WITH-AES-128-GCM-SHA256",     "CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256");
        result = result.replaceAll("TLS-DHE-RSA-WITH-AES-256-GCM-SHA384",     "CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384");
        result = result.replaceAll("TLS-DH-anon-WITH-AES-256-GCM-SHA384",     "CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384");
        result = result.replaceAll("TLS-PSK-WITH-AES-128-GCM-SHA256",         "CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256");
        result = result.replaceAll("TLS-PSK-WITH-AES-256-GCM-SHA384",         "CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384");
        result = result.replaceAll("TLS-DHE-PSK-WITH-AES-128-GCM-SHA256",     "CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256");
        result = result.replaceAll("TLS-DHE-PSK-WITH-AES-256-GCM-SHA384",     "CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-AES-128-GCM-SHA256", "CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-AES-256-GCM-SHA384", "CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384");
        result = result.replaceAll("TLS-ECDH-ECDSA-WITH-AES-128-GCM-SHA256",  "CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256");
        result = result.replaceAll("TLS-ECDH-ECDSA-WITH-AES-256-GCM-SHA384",  "CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384");
        result = result.replaceAll("TLS-ECDHE-RSA-WITH-AES-128-GCM-SHA256",   "CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        result = result.replaceAll("TLS-ECDHE-RSA-WITH-AES-256-GCM-SHA384",   "CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");
        result = result.replaceAll("TLS-ECDH-RSA-WITH-AES-128-GCM-SHA256",    "CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256");
        result = result.replaceAll("TLS-ECDH-RSA-WITH-AES-256-GCM-SHA384",    "CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384");
        result = result.replaceAll("TLS-RSA-WITH-AES-128-CCM-8",              "CipherSuite.TLS_RSA_WITH_AES_128_CCM_8");
        result = result.replaceAll("TLS-RSA-WITH-AES-256-CCM-8",              "CipherSuite.TLS_RSA_WITH_AES_256_CCM_8");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-AES-128-CCM",        "CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-AES-128-CCM-8",      "CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8");
        result = result.replaceAll("TLS-ECDHE-ECDSA-WITH-AES-256-CCM-8",      "CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8");
        result = result.replaceAll("TLS-PSK-WITH-AES-128-CCM",                "CipherSuite.TLS_PSK_WITH_AES_128_CCM");
        result = result.replaceAll("TLS-PSK-WITH-AES-256-CCM",                "CipherSuite.TLS_PSK_WITH_AES_256_CCM");
        result = result.replaceAll("TLS-PSK-WITH-AES-128-CCM-8",              "CipherSuite.TLS_PSK_WITH_AES_128_CCM_8");
        result = result.replaceAll("TLS-PSK-WITH-AES-256-CCM-8",              "CipherSuite.TLS_PSK_WITH_AES_256_CCM_8");
        result = result.replaceAll("TLS-DHE-PSK-WITH-AES-128-CCM",            "CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM");
        result = result.replaceAll("TLS-AES-128-CCM-SHA256",                  "CipherSuite.TLS_AES_128_CCM_SHA256");
        result = result.replaceAll("TLS-AES-128-CCM-8-SHA256",                "CipherSuite.TLS_AES_128_CCM_8_SHA256");
        result = result.replaceAll("TLS-AES-128-GCM-SHA256",                  "CipherSuite.TLS_AES_128_GCM_SHA256");
        result = result.replaceAll("TLS-AES-256-GCM-SHA384",                  "CipherSuite.TLS_AES_256_GCM_SHA384");


        result = result.replaceAll("no-compression", "CompressionMethod.NO_COMPRESSION");
        result = result.replaceAll("ecdsa-secp256r1-sha256", "SignatureAndHashAlgorithm.ECDSA_SHA256");
        result = result.replaceAll("dsa-sha256", "SignatureAndHashAlgorithm.DSA_SHA256");
        result = result.replaceAll("secp160k1", "NamedGroup.SECP160K1");
        result = result.replaceAll("secp160r1", "NamedGroup.SECP160R1");
        result = result.replaceAll("secp160r2", "NamedGroup.SECP160R2");
        result = result.replaceAll("secp192k1", "NamedGroup.SECP192K1");
        result = result.replaceAll("secp192r1", "NamedGroup.SECP192R1");
        result = result.replaceAll("secp224r1", "NamedGroup.SECP224K1");
        result = result.replaceAll("secp224k1", "NamedGroup.SECP224R1");
        result = result.replaceAll("secp256r1", "NamedGroup.SECP256R1");
        result = result.replaceAll("secp256k1", "NamedGroup.SECP256K1");
        result = result.replaceAll("secp384r1", "NamedGroup.SECP384R1");
        result = result.replaceAll("secp521r1", "NamedGroup.SECP521R1");
        result = result.replaceAll("ffdhe2048", "NamedGroup.FFDHE2048");
        result = result.replaceAll("ffdhe3072", "NamedGroup.FFDHE3072");
        result = result.replaceAll("ffdhe4096", "NamedGroup.FFDHE4096");
        result = result.replaceAll("ffdhe6144", "NamedGroup.FFDHE6144");
        result = result.replaceAll("ffdhe8192", "NamedGroup.FFDHE8192");

        result = result.replaceAll("fatal", "AlertLevel.FATAL");
        result = result.replaceAll("close-notify", "AlertDescription.CLOSE_NOTIFY");
        result = result.replaceAll("unexpected-message", "AlertDescription.UNEXPECTED_MESSAGE");
        result = result.replaceAll("bad-record-mac", "AlertDescription.BAD_RECORD_MAC");
        result = result.replaceAll("record-overflow", "AlertDescription.RECORD_OVERFLOW");
        result = result.replaceAll("decompression-failure", "AlertDescription.DECOMPRESSION_FAILURE");
        result = result.replaceAll("handshake-failure", "AlertDescription.HANDSHAKE_FAILURE");
        result = result.replaceAll("no-certificate", "AlertDescription.NO_CERTIFICATE_RESERVED");
        result = result.replaceAll("bad-certificate", "AlertDescription.BAD_CERTIFICATE");
        result = result.replaceAll("unsupported-certificate", "AlertDescription.UNSUPPORTED_CERTIFICATE");
        result = result.replaceAll("certificate-revoked", "AlertDescription.CERTIFICATE_REVOKED");
        result = result.replaceAll("certificate-expired", "AlertDescription.CERTIFICATE_EXPIRED");
        result = result.replaceAll("certificate-unknown", "AlertDescription.CERTIFICATE_UNKNOWN");
        result = result.replaceAll("illegal-parameter", "AlertDescription.ILLEGAL_PARAMETER");
        result = result.replaceAll("unknown-ca", "AlertDescription.UNKNOWN_CA");
        result = result.replaceAll("access-denied", "AlertDescription.ACCESS_DENIED");
        result = result.replaceAll("decode-error", "AlertDescription.DECODE_ERROR");
        result = result.replaceAll("decrypt-error", "AlertDescription.DECRYPT_ERROR");
        result = result.replaceAll("protocol-version", "AlertDescription.PROTOCOL_VERSION");
        result = result.replaceAll("insufficient-security", "AlertDescription.INSUFFICIENT_SECURITY");
        result = result.replaceAll("internal-error", "AlertDescription.INTERNAL_ERROR");
        result = result.replaceAll("inappropriate-fallback", "AlertDescription.INAPPROPRIATE_FALLBACK");
        result = result.replaceAll("user-canceled", "AlertDescription.USER_CANCELED");
        result = result.replaceAll("no-renogitation", "AlertDescription.NO_RENEGOTIATION");
        result = result.replaceAll("unsupported-extension", "AlertDescription.UNSUPPORTED_EXTENSION");
        result = result.replaceAll("missing-extension", "AlertDescription.MISSING_EXTENSION");


        result = result.replaceAll("unexpected-message", "AlertDescription.UNEXPECTED_MESSAGE");
        result = result.replaceAll("decode-error", "AlertDescription.DECODE_ERROR");
        result = result.replaceAll("illegal-parameter", "AlertDescription.ILLEGAL_PARAMETER");
        result = result.replaceAll("bad-record-mac", "AlertDescription.BAD_RECORD_MAC");
        result = result.replaceAll("close-notify", "AlertDescription.CLOSE_NOTIFY");


        // 9. change connect or accept to include target IP address and port.
        result = convertConnectCommand(result, "accept");
        result = convertConnectCommand(result, "connect");

        // 10. change pre-defined constructor

        result = result.replaceAll("prvkey-path", "\"" + currentDirectory + "/" + privateKeyPath + "\"");
        result = result.replaceAll("cert-path", "\"" + currentDirectory + "/" + certificatePath + "\"");
        return result + ";";
    }

    private String convertConnectCommand(String scenario, String keyword) {
        Pattern connectPattern = Pattern.compile(keyword + "\\(\"(.+?)\"\\)");
        Matcher matcher = connectPattern.matcher(scenario);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String id = matcher.group(1).trim(); // ID 추출 후 공백 제거
            String replacement = String.format("%s(\"%s\", \"%s\", %d)", keyword, id, this.ip, this.port);
            matcher.appendReplacement(result, replacement); // 문자열 변경
        }
        matcher.appendTail(result);

        return result.toString();
    }
}
