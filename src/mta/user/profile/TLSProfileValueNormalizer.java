package mta.user.profile;

import mta.user.common.UserTerm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public final class TLSProfileValueNormalizer {

    private static final Set<String> METADATA_FIELDS = Set.of(
            "TestRole",
            "TLSRole",
            "LibraryName",
            "LibraryVersion",
            "LibraryPath");

    private static final Set<String> REMOVED_FIELDS = Set.of(
            "Certificates",
            "PublicKeys",
            "PrivateKeys",
            "CertificateTypes",
            "CertificateAlgorithms",
            "CertificateAlgos");

    private static final Set<String> ASYM_KEY_TYPE_FIELDS = Set.of(
            "CACertificateType",
            "CertificateType",
            "PrivateKeyType");

    private static final Set<String> CERTIFICATE_SIGNATURE_FIELDS = Set.of(
            "CertificateSignatureAlgorithm",
            "CACertificateSignatureAlgorithm");

    private static final Set<String> PATH_FIELDS = Set.of(
            "CACertificatePath",
            "CertificatePath",
            "PrivateKeyPath");

    private static final Set<String> SUPPORTED_ASYM_KEY_TYPES = Set.of(
            "ecdsa",
            "rsa",
            "dsa",
            "ed25519",
            "ed448");

    private static final Set<String> SUPPORTED_CERTIFICATE_SIGNATURE_ALGORITHMS = Set.of(
            "{ecdsa,sha256}",
            "{ecdsa,sha384}",
            "{ecdsa,sha512}",
            "{rsa,sha256}",
            "{rsa,sha384}",
            "{rsa,sha512}",
            "{ed25519,intrinsic}",
            "{ed448,intrinsic}",
            "{dsa,sha256}",
            "{dsa,sha384}",
            "{dsa,sha512}");

    private static final Set<String> FLAG_FIELDS = Set.of(
            "CertificateRequest",
            "Renegotiation",
            "SecureRenegotiation",
            "NewSessionTicketReq",
            "NewSessionTicketRequest",
            "NewSessionTicketWait",
            "Reconnect",
            "EarlyDataReq",
            "EarlyDataRequest",
            "EarlyDataWait",
            "PostClientAuthReq",
            "PostClientAuthRequest",
            "PostClientAuthWait",
            "KeyUpdateReq",
            "KeyUpdateRequest",
            "KeyUpdateWait",
            "CKS",
            "Cks");

    private static final Set<String> UNSUPPORTED_CIPHER_SUITES = Set.of(
            "TLS_CHACHA20_POLY1305_SHA256");

    private static final Set<String> SUPPORTED_CIPHER_SUITES = Set.of(
            "TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA",
            "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
            "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
            "TLS_DH_anon_WITH_AES_128_CBC_SHA",
            "TLS_RSA_WITH_AES_256_CBC_SHA",
            "TLS_RSA_WITH_AES_128_CBC_SHA",
            "TLS_RSA_WITH_NULL_MD5",
            "TLS_RSA_WITH_NULL_SHA",
            "TLS_PSK_WITH_AES_256_CBC_SHA",
            "TLS_PSK_WITH_AES_128_CBC_SHA256",
            "TLS_PSK_WITH_AES_256_CBC_SHA384",
            "TLS_PSK_WITH_AES_128_CBC_SHA",
            "TLS_PSK_WITH_NULL_SHA256",
            "TLS_PSK_WITH_NULL_SHA384",
            "TLS_PSK_WITH_NULL_SHA",
            "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
            "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
            "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
            "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
            "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
            "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
            "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA",
            "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA",
            "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",
            "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256",
            "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384",
            "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384",
            "TLS_ECDHE_ECDSA_WITH_NULL_SHA",
            "TLS_ECDHE_PSK_WITH_NULL_SHA256",
            "TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256",
            "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",
            "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
            "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",
            "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",
            "TLS_ECDH_RSA_WITH_RC4_128_SHA",
            "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
            "TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA",
            "TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA",
            "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256",
            "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256",
            "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384",
            "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384",
            "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256",
            "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256",
            "TLS_RSA_WITH_AES_256_CBC_SHA256",
            "TLS_RSA_WITH_AES_128_CBC_SHA256",
            "TLS_RSA_WITH_NULL_SHA256",
            "TLS_DHE_PSK_WITH_AES_128_CBC_SHA256",
            "TLS_DHE_PSK_WITH_NULL_SHA256",
            "TLS_DHE_PSK_WITH_AES_256_CBC_SHA384",
            "TLS_DHE_PSK_WITH_NULL_SHA384",
            "TLS_RSA_WITH_AES_128_GCM_SHA256",
            "TLS_RSA_WITH_AES_256_GCM_SHA384",
            "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256",
            "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384",
            "TLS_DH_anon_WITH_AES_256_GCM_SHA384",
            "TLS_PSK_WITH_AES_128_GCM_SHA256",
            "TLS_PSK_WITH_AES_256_GCM_SHA384",
            "TLS_DHE_PSK_WITH_AES_128_GCM_SHA256",
            "TLS_DHE_PSK_WITH_AES_256_GCM_SHA384",
            "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256",
            "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384",
            "TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256",
            "TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384",
            "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
            "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
            "TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256",
            "TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384",
            "TLS_RSA_WITH_AES_128_CCM_8",
            "TLS_RSA_WITH_AES_256_CCM_8",
            "TLS_ECDHE_ECDSA_WITH_AES_128_CCM",
            "TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8",
            "TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8",
            "TLS_PSK_WITH_AES_128_CCM",
            "TLS_PSK_WITH_AES_256_CCM",
            "TLS_PSK_WITH_AES_128_CCM_8",
            "TLS_PSK_WITH_AES_256_CCM_8",
            "TLS_DHE_PSK_WITH_AES_128_CCM",
            "TLS_DHE_PSK_WITH_AES_256_CCM",
            "TLS_AES_128_CCM_SHA256",
            "TLS_AES_128_CCM_8_SHA256",
            "TLS_AES_128_GCM_SHA256",
            "TLS_AES_256_GCM_SHA384");

    private static final Set<String> UNSUPPORTED_GROUPS = Set.of(
            "x25519",
            "x448");

    private static final Set<String> SUPPORTED_GROUPS = Set.of(
            "secp160k1",
            "secp160r1",
            "secp160r2",
            "secp192k1",
            "secp192r1",
            "secp224k1",
            "secp224r1",
            "secp256k1",
            "secp256r1",
            "secp384r1",
            "secp521r1",
            "ffdhe2048",
            "ffdhe3072",
            "ffdhe4096",
            "ffdhe6144",
            "ffdhe8192");

    private static final Set<String> UNSUPPORTED_SIGNATURE_SCHEMES = Set.of(
            "ed25519",
            "ed448");

    private static final Map<String, String> PROTOCOL_VERSION_TO_MAUDE = Map.of(
            "TLS12", "TLS-12",
            "TLS13", "TLS-13");

    private static final Map<String, String> COMPRESSION_TO_MAUDE = Map.of(
            "null", "no-compression",
            "no_compression", "no-compression",
            "deflate", "zlib-compression",
            "zlib", "zlib-compression",
            "zlib_compression", "zlib-compression",
            "lzs", "zlib-compression");

    private static final Map<String, String> CERTIFICATE_TYPE_TO_MAUDE = Map.of(
            "rsa_sign", "rsa-sign",
            "dss_sign", "dss-sign",
            "rsa_fixed_dh", "rsa-fixed-dh",
            "dss_fixed_dh", "dss-fixed-dh",
            "ecdsa_sign", "ecdsa-sign",
            "rsa_fixed_ecdh", "rsa-fixed-ecdh",
            "ecdsa_fixed_ecdh", "ecdsa-fixed-ecdh");

    private static final Map<String, String> PSK_MODE_TO_MAUDE = Map.of(
            "psk_ke", "psk-ke",
            "psk_dhe_ke", "psk-dhe-ke");

    private static final Map<String, String> SIGNATURE_SCHEME_TO_MAUDE = buildSignatureSchemeMap();

    private TLSProfileValueNormalizer() {
    }

    public static List<UserTerm> validate(String fieldName, List<UserTerm> values) {
        toMaudeTerms(fieldName, values);
        return values == null ? List.of() : List.copyOf(values);
    }

    public static List<UserTerm> toMaudeTerms(String fieldName, List<UserTerm> values) {
        if (values == null || values.isEmpty()) {
            return List.of();
        }
        if (fieldName != null && REMOVED_FIELDS.contains(fieldName)) {
            throw removedField(fieldName);
        }
        if (fieldName == null || METADATA_FIELDS.contains(fieldName)) {
            return List.copyOf(values);
        }
        if (PATH_FIELDS.contains(fieldName)) {
            return normalizeScalar(fieldName, values, TLSProfileValueNormalizer::normalizePathValue);
        }
        if (ASYM_KEY_TYPE_FIELDS.contains(fieldName)) {
            return normalizeScalar(fieldName, values, TLSProfileValueNormalizer::normalizeAsymKeyType);
        }
        if (CERTIFICATE_SIGNATURE_FIELDS.contains(fieldName)) {
            return normalizeScalar(fieldName, values,
                    TLSProfileValueNormalizer::normalizeCertificateSignatureAlgorithm);
        }
        if (FLAG_FIELDS.contains(fieldName)) {
            return normalizeEach(fieldName, values, TLSProfileValueNormalizer::normalizeBoolean);
        }
        return switch (fieldName) {
            case "Version" -> normalizeScalar(fieldName, values, TLSProfileValueNormalizer::normalizeProtocolVersion);
            case "CipherSuites" -> normalizeEach(fieldName, values, TLSProfileValueNormalizer::normalizeCipherSuite);
            case "Compressions" -> normalizeEach(fieldName, values, TLSProfileValueNormalizer::normalizeCompressionMethod);
            case "ClientCertificateTypes" ->
                    normalizeEach(fieldName, values, TLSProfileValueNormalizer::normalizeCertificateType);
            case "ClientCertificateAlgos", "SignatureAlgorithms" ->
                    normalizeEach(fieldName, values, TLSProfileValueNormalizer::normalizeSignatureScheme);
            case "SupportedVersions" -> normalizeEach(fieldName, values, TLSProfileValueNormalizer::normalizeProtocolVersion);
            case "SupportedGroups", "KeyShares" ->
                    normalizeEach(fieldName, values, TLSProfileValueNormalizer::normalizeNamedGroup);
            case "PSKKeyExchangeModes", "PskKeyExchangeModes" ->
                    normalizeEach(fieldName, values, TLSProfileValueNormalizer::normalizePskKeyExchangeMode);
            case "KeyUpdateReqType", "KeyUpdateRequestType" ->
                    normalizeScalar(fieldName, values, TLSProfileValueNormalizer::normalizeKeyUpdateRequestType);
            default -> List.copyOf(values);
        };
    }

    private static List<UserTerm> normalizeScalar(String fieldName,
                                                  List<UserTerm> values,
                                                  FieldValueNormalizer normalizer) {
        if (values.size() != 1) {
            throw profileViolation(fieldName, values.toString(), "expects exactly one value");
        }
        return List.of(normalizer.normalize(fieldName, values.get(0)));
    }

    private static List<UserTerm> normalizeEach(String fieldName,
                                                List<UserTerm> values,
                                                FieldValueNormalizer normalizer) {
        List<UserTerm> normalized = new ArrayList<UserTerm>();
        for (UserTerm value : values) {
            normalized.add(normalizer.normalize(fieldName, value));
        }
        return List.copyOf(normalized);
    }

    private static UserTerm normalizeProtocolVersion(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String maude = PROTOCOL_VERSION_TO_MAUDE.get(token.toUpperCase(Locale.ROOT));
        if (maude == null) {
            if (token.contains("-")) {
                throw profileViolation(fieldName, token, "use RFC-style values such as TLS12 or TLS13");
            }
            throw profileViolation(fieldName, token, "expected TLS12 or TLS13");
        }
        return atom(maude);
    }

    private static UserTerm normalizeCipherSuite(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        if (UNSUPPORTED_CIPHER_SUITES.contains(token.toUpperCase(Locale.ROOT))) {
            throw unsupported(fieldName, token, "RFC cipher suite is known but current Maude model does not define it");
        }
        String enumName = token.toUpperCase(Locale.ROOT).replace("_ANON_", "_anon_");
        if (!SUPPORTED_CIPHER_SUITES.contains(enumName)) {
            throw profileViolation(fieldName, token, "expected a supported RFC/IANA cipher suite name");
        }
        return atom(enumName.replace('_', '-'));
    }

    private static UserTerm normalizeCompressionMethod(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String maude = COMPRESSION_TO_MAUDE.get(token.toLowerCase(Locale.ROOT).replace('-', '_'));
        if (maude == null) {
            throw profileViolation(fieldName, token, "expected null, NO_COMPRESSION, DEFLATE, or LZS");
        }
        return atom(maude);
    }

    private static UserTerm normalizeCertificateType(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String maude = CERTIFICATE_TYPE_TO_MAUDE.get(token.toLowerCase(Locale.ROOT).replace('-', '_'));
        if (maude == null) {
            throw profileViolation(fieldName, token, "expected an RFC5246 certificate type name");
        }
        return atom(maude);
    }

    private static UserTerm normalizeAsymKeyType(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value).toLowerCase(Locale.ROOT);
        if (!SUPPORTED_ASYM_KEY_TYPES.contains(token)) {
            throw profileViolation(fieldName, token, "expected ecdsa, rsa, dsa, ed25519, or ed448");
        }
        return atom(token);
    }

    private static UserTerm normalizeCertificateSignatureAlgorithm(String fieldName, UserTerm value) {
        String maude = certificateSignatureAlgorithmText(fieldName, value);
        if (!SUPPORTED_CERTIFICATE_SIGNATURE_ALGORITHMS.contains(maude)) {
            throw profileViolation(fieldName, value.source(),
                    "expected one of " + SUPPORTED_CERTIFICATE_SIGNATURE_ALGORITHMS);
        }
        return rawMaude(maude);
    }

    private static UserTerm normalizeSignatureScheme(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String key = token.toLowerCase(Locale.ROOT);
        if (UNSUPPORTED_SIGNATURE_SCHEMES.contains(key)) {
            throw unsupported(fieldName, token, "RFC signature scheme is known but current Maude model cannot represent it");
        }
        String maude = SIGNATURE_SCHEME_TO_MAUDE.get(key);
        if (maude != null) {
            return rawMaude(maude);
        }
        throw profileViolation(fieldName, token, "expected an RFC-style signature scheme name");
    }

    private static String certificateSignatureAlgorithmText(String fieldName, UserTerm value) {
        if (value instanceof UserTerm.BraceTerm brace) {
            if (brace.values().size() != 2) {
                throw profileViolation(fieldName, value.source(), "expected {auth,hash}");
            }
            String auth = atomText(fieldName, brace.values().get(0)).toLowerCase(Locale.ROOT);
            String hash = atomText(fieldName, brace.values().get(1)).toLowerCase(Locale.ROOT);
            return "{" + auth + "," + hash + "}";
        }
        if (value instanceof UserTerm.Atom atom) {
            String key = atom.text().toLowerCase(Locale.ROOT);
            if ("ed25519".equals(key) || "ed448".equals(key)) {
                return "{" + key + ",intrinsic}";
            }
            String maude = SIGNATURE_SCHEME_TO_MAUDE.get(key);
            if (maude != null) {
                return maude;
            }
        }
        throw profileViolation(fieldName, value.source(),
                "expected a certificate signature algorithm such as {ecdsa,sha256}");
    }

    private static UserTerm normalizeNamedGroup(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String key = token.toLowerCase(Locale.ROOT);
        if (UNSUPPORTED_GROUPS.contains(key)) {
            throw unsupported(fieldName, token, "RFC named group is known but current Maude model does not define it");
        }
        if (!SUPPORTED_GROUPS.contains(key)) {
            throw profileViolation(fieldName, token, "expected a supported RFC named group name");
        }
        return atom(key);
    }

    private static UserTerm normalizePskKeyExchangeMode(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String maude = PSK_MODE_TO_MAUDE.get(token.toLowerCase(Locale.ROOT).replace('-', '_'));
        if (maude == null) {
            throw profileViolation(fieldName, token, "expected psk_ke or psk_dhe_ke");
        }
        return atom(maude);
    }

    private static UserTerm normalizeBoolean(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value).toLowerCase(Locale.ROOT);
        if ("true".equals(token) || "false".equals(token)) {
            return atom(token);
        }
        throw profileViolation(fieldName, token, "expected true or false");
    }

    private static UserTerm normalizeKeyUpdateRequestType(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value).toLowerCase(Locale.ROOT);
        return switch (token) {
            case "update_requested", "true" -> atom("true");
            case "update_not_requested", "false" -> atom("false");
            default -> throw profileViolation(fieldName, token,
                    "expected update_requested or update_not_requested");
        };
    }

    private static UserTerm normalizePathValue(String fieldName, UserTerm value) {
        if (value instanceof UserTerm.StringLiteral || value instanceof UserTerm.Atom) {
            return value;
        }
        throw profileViolation(fieldName, value.source(), "expected a quoted path string or identifier");
    }

    private static String atomText(String fieldName, UserTerm value) {
        if (value instanceof UserTerm.Atom atom) {
            return atom.text();
        }
        if (value instanceof UserTerm.RawMaude) {
            throw profileViolation(fieldName, value.source(), "raw Maude is not accepted in TLSProfiles");
        }
        throw profileViolation(fieldName, value.source(), "expected an RFC-style identifier");
    }

    private static UserTerm.Atom atom(String text) {
        return new UserTerm.Atom(text);
    }

    private static UserTerm.RawMaude rawMaude(String text) {
        return new UserTerm.RawMaude(text);
    }

    private static IllegalArgumentException profileViolation(String fieldName, String value, String expected) {
        return new IllegalArgumentException("TLSProfiles field " + fieldName
                + " RFC/profile value violation for '" + value + "': " + expected);
    }

    private static IllegalArgumentException unsupported(String fieldName, String value, String reason) {
        return new IllegalArgumentException("TLSProfiles field " + fieldName
                + " system unsupported value '" + value + "': " + reason);
    }

    private static IllegalArgumentException removedField(String fieldName) {
        String replacement = switch (fieldName) {
            case "CertificateTypes" -> "ClientCertificateTypes";
            case "CertificateAlgorithms", "CertificateAlgos" -> "ClientCertificateAlgos";
            case "Certificates", "PublicKeys", "PrivateKeys" ->
                    "CACertificateType, CertificateType, PrivateKeyType, and CertificateSignatureAlgorithm";
            default -> "the current TLS profile vocabulary";
        };
        return new IllegalArgumentException("TLSProfiles field " + fieldName
                + " has been removed; use " + replacement);
    }

    private static Map<String, String> buildSignatureSchemeMap() {
        Map<String, String> schemes = new LinkedHashMap<String, String>();
        putSignature(schemes, "rsa_pkcs1_sha1", "rsa", "sha");
        putSignature(schemes, "rsa_pkcs1_sha224", "rsa", "sha224");
        putSignature(schemes, "rsa_pkcs1_sha256", "rsa", "sha256");
        putSignature(schemes, "rsa_pkcs1_sha384", "rsa", "sha384");
        putSignature(schemes, "rsa_pkcs1_sha512", "rsa", "sha512");
        putSignature(schemes, "rsa_pss_rsae_sha256", "rsa", "sha256");
        putSignature(schemes, "rsa_pss_rsae_sha384", "rsa", "sha384");
        putSignature(schemes, "rsa_pss_rsae_sha512", "rsa", "sha512");
        putSignature(schemes, "rsa_pss_pss_sha256", "rsa", "sha256");
        putSignature(schemes, "rsa_pss_pss_sha384", "rsa", "sha384");
        putSignature(schemes, "rsa_pss_pss_sha512", "rsa", "sha512");
        putSignature(schemes, "ecdsa_sha1", "ecdsa", "sha");
        putSignature(schemes, "ecdsa_secp256r1_sha256", "ecdsa", "sha256");
        putSignature(schemes, "ecdsa_secp384r1_sha384", "ecdsa", "sha384");
        putSignature(schemes, "ecdsa_secp521r1_sha512", "ecdsa", "sha512");

        for (String hash : List.of("md5", "sha1", "sha224", "sha256", "sha384", "sha512")) {
            putSignature(schemes, "rsa_" + hash, "rsa", maudeHash(hash));
            putSignature(schemes, "dsa_" + hash, "dsa", maudeHash(hash));
            putSignature(schemes, "dss_" + hash, "dsa", maudeHash(hash));
            putSignature(schemes, "ecdsa_" + hash, "ecdsa", maudeHash(hash));
            putSignature(schemes, "anonymous_" + hash, "anon", maudeHash(hash));
            putSignature(schemes, "anon_" + hash, "anon", maudeHash(hash));
        }
        return Map.copyOf(schemes);
    }

    private static void putSignature(Map<String, String> schemes, String name, String auth, String hash) {
        schemes.put(name, "{" + auth + "," + hash + "}");
    }

    private static String maudeHash(String hash) {
        return "sha1".equals(hash) ? "sha" : hash;
    }

    @FunctionalInterface
    private interface FieldValueNormalizer {
        UserTerm normalize(String fieldName, UserTerm value);
    }
}
