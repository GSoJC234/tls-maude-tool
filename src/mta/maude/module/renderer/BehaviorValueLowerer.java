package mta.maude.module.renderer;

import mta.maude.constant.CipherSuite;
import mta.user.common.UserTerm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;

final class BehaviorValueLowerer {

    private static final Set<String> UNSUPPORTED_CIPHER_SUITES = Set.of(
            "TLS_CHACHA20_POLY1305_SHA256");

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

    private static final Set<String> LENGTH_FIELDS = Set.of(
            "recordLen",
            "handshakeLen",
            "cipherSuitesLen",
            "sessionIdLen",
            "compressionsLen",
            "certificate-list-len",
            "certificateTypesLen",
            "certificateAlgosLen",
            "certificateAuthsLen",
            "signature-len",
            "supported-versions-len",
            "signature-algorithms-len",
            "key-shares-len",
            "supported-groups-len",
            "psk-key-exchange-mode-len",
            "pre-shared-key-len",
            "early-data-len",
            "post-handshake-len",
            "renegotiation-info-len",
            "cks-len",
            "extensionLen",
            "certificate-request-context-len");

    private static final Map<String, String> MESSAGE_FIELDS = buildMessageFields();
    private static final Map<String, String> ATTRIBUTES = buildAttributes();
    private static final Map<String, String> PROTOCOL_VERSION_TO_MAUDE = Map.of(
            "TLS10", "TLS-10",
            "TLS11", "TLS-11",
            "TLS12", "TLS-12",
            "TLS13", "TLS-13");
    private static final Map<String, String> CONTENT_TYPE_TO_MAUDE = Map.of(
            "handshake", "handshake",
            "change_cipher_spec", "change-cipher-spec",
            "alert", "alert",
            "application_data", "application-data");
    private static final Map<String, String> HANDSHAKE_TYPE_TO_MAUDE = buildHandshakeTypes();
    private static final Map<String, String> MESSAGE_SIZE_TO_MAUDE = Map.of(
            "valid", "valid",
            "smaller", "smaller",
            "larger", "larger",
            "max_size", "maxSize",
            "maxsize", "maxSize",
            "min_size", "minSize",
            "minsize", "minSize");
    private static final Map<String, String> COMPRESSION_TO_MAUDE = Map.of(
            "null", "no-compression",
            "no_compression", "no-compression",
            "deflate", "zlib-compression",
            "zlib", "zlib-compression",
            "zlib_compression", "zlib-compression",
            "lzs", "zlib-compression");
    private static final Map<String, String> PSK_MODE_TO_MAUDE = Map.of(
            "psk_ke", "psk-ke",
            "psk_dhe_ke", "psk-dhe-ke");
    private static final Map<String, String> CERTIFICATE_TYPE_TO_MAUDE = Map.of(
            "rsa_sign", "rsa-sign",
            "dss_sign", "dss-sign",
            "rsa_fixed_dh", "rsa-fixed-dh",
            "dss_fixed_dh", "dss-fixed-dh",
            "ecdsa_sign", "ecdsa-sign",
            "rsa_fixed_ecdh", "rsa-fixed-ecdh",
            "ecdsa_fixed_ecdh", "ecdsa-fixed-ecdh");
    private static final Map<String, String> KEY_UPDATE_REQUEST_TO_MAUDE = Map.of(
            "update_requested", "true",
            "true", "true",
            "update_not_requested", "false",
            "false", "false",
            "unknown", "unknown");
    private static final Map<String, String> ALERT_LEVEL_TO_MAUDE = Map.of(
            "fatal", "fatal",
            "warning", "warning");
    private static final Map<String, String> ALERT_DESCRIPTION_TO_MAUDE = buildAlertDescriptions();
    private static final Map<String, String> CIPHER_SUITE_TO_MAUDE = buildCipherSuites();
    private static final Map<String, String> SIGNATURE_SCHEME_TO_MAUDE = buildSignatureSchemeMap();

    private final MaudeUserTermRenderer termRenderer;

    BehaviorValueLowerer(MaudeUserTermRenderer termRenderer) {
        this.termRenderer = termRenderer;
    }

    String renderMessageField(UserTerm field) {
        return "#" + normalizeMessageField(field);
    }

    String renderMessageValue(UserTerm field, UserTerm value) {
        String fieldName = normalizeMessageField(field);
        LoweredValue lowered = lowerMessageValue(fieldName, value);
        if (lowered.direct()) {
            return termRenderer.renderTerm(lowered.term());
        }
        return "mv[" + termRenderer.renderTerm(lowered.term()) + "]";
    }

    String renderAttribute(UserTerm field) {
        return "@" + normalizeAttribute(field);
    }

    String renderAttributeValue(UserTerm field, UserTerm value) {
        String attributeName = normalizeAttribute(field);
        return "av[" + termRenderer.renderTerm(lowerAttributeValue(attributeName, value)) + "]";
    }

    String renderFeatureAttribute(String field) {
        return "@" + normalizeAttribute(field);
    }

    String renderFeatureValue(String field, UserTerm value) {
        String attributeName = normalizeAttribute(field);
        return "av[" + termRenderer.renderTerm(lowerAttributeValue(attributeName, value)) + "]";
    }

    String normalizeAttributeName(String field) {
        return normalizeAttribute(field);
    }

    String renderNode(UserTerm value) {
        return termRenderer.renderTerm(normalizeNode(value));
    }

    String renderEventType(UserTerm value) {
        return termRenderer.renderTerm(normalizeEventType(value));
    }

    String renderRuleLabel(UserTerm value) {
        return termRenderer.renderRuleLabel(value);
    }

    String renderNoCheckLabel(UserTerm label) {
        if (label instanceof UserTerm.Atom atom && atom.text().matches("[0-9]+")) {
            return "label[" + atom.text() + "]";
        }
        if (label instanceof UserTerm.Indexed indexed
                && "label".equals(indexed.name())
                && indexed.index() instanceof UserTerm.Atom atom
                && atom.text().matches("[0-9]+")) {
            return termRenderer.renderTerm(label);
        }
        throw behaviorViolation("noCheck", label.source(), "expected noCheck(14) or noCheck(label[14])");
    }

    String renderDelayHandshakeType(UserTerm handshakeType) {
        return termRenderer.renderTerm(normalizeHandshakeType("delay", handshakeType));
    }

    IllegalArgumentException unsupportedModification(String name, String reason) {
        return new IllegalArgumentException("BehaviorDeviationSpecification system unsupported modification '"
                + name
                + "': "
                + reason);
    }

    private LoweredValue lowerMessageValue(String fieldName, UserTerm value) {
        if (value instanceof UserTerm.RawMaude) {
            return LoweredValue.wrapped(value);
        }
        if (LENGTH_FIELDS.contains(fieldName)) {
            return LoweredValue.wrapped(normalizeMessageSize(fieldName, value));
        }
        return switch (fieldName) {
            case "version", "protocol", "supported-versions" ->
                    LoweredValue.wrapped(normalizeSequence(fieldName, value, this::normalizeProtocolVersion));
            case "contentType" -> LoweredValue.wrapped(normalizeContentType(fieldName, value));
            case "alertDesc" -> LoweredValue.wrapped(normalizeAlertDescription(fieldName, value));
            case "alertLev" -> LoweredValue.wrapped(normalizeAlertLevel(fieldName, value));
            case "handshakeType" -> LoweredValue.wrapped(normalizeHandshakeType(fieldName, value));
            case "cipherSuites" ->
                    LoweredValue.wrapped(normalizeSequence(fieldName, value, this::normalizeCipherSuite));
            case "sessionId", "random", "verifyData", "signature", "psk-binders" ->
                    LoweredValue.wrapped(normalizeNonceLike(fieldName, value));
            case "compressions" ->
                    LoweredValue.wrapped(normalizeSequence(fieldName, value, this::normalizeCompressionMethod));
            case "certificate-list" -> normalizeCertificateList(fieldName, value);
            case "certificateTypes" ->
                    LoweredValue.wrapped(normalizeSequence(fieldName, value, this::normalizeCertificateType));
            case "certificateAlgos", "certificate-verify-algorithm", "signature-algorithms" ->
                    LoweredValue.wrapped(normalizeSequence(fieldName, value, this::normalizeSignatureScheme));
            case "supported-groups", "key-shares-groups" ->
                    LoweredValue.wrapped(normalizeSequence(fieldName, value, this::normalizeNamedGroup));
            case "key-shares" ->
                    LoweredValue.wrapped(normalizeSequence(fieldName, value, this::normalizeKeyShare));
            case "psk-key-exchange-mode" ->
                    LoweredValue.wrapped(normalizeSequence(fieldName, value, this::normalizePskKeyExchangeMode));
            case "early-data", "post-handshake", "cks" -> LoweredValue.wrapped(normalizeBoolean(fieldName, value));
            case "selected-identifier" -> LoweredValue.wrapped(normalizeNat(fieldName, value));
            case "keyUpdateRequest" -> LoweredValue.wrapped(normalizeKeyUpdateRequest(fieldName, value));
            default -> throw unsupported(fieldName, value.source(),
                    "no user vocabulary is defined for this message field; use maude(\"...\") for an explicit raw Maude behavior");
        };
    }

    private UserTerm lowerAttributeValue(String attributeName, UserTerm value) {
        if (value instanceof UserTerm.RawMaude) {
            return value;
        }
        return switch (attributeName) {
            case "cipherSuites", "selectedCipherSuite" ->
                    normalizeSequence(attributeName, value, this::normalizeCipherSuite);
            case "compressions", "selectedCompression" ->
                    normalizeSequence(attributeName, value, this::normalizeCompressionMethod);
            case "supportedVersions", "selectedVersion", "selectedSupportedVersions" ->
                    normalizeSequence(attributeName, value, this::normalizeProtocolVersion);
            case "signatureAlgorithms", "selectedSignatureAlgorithms", "certificateAlgo" ->
                    normalizeSequence(attributeName, value, this::normalizeSignatureScheme);
            case "keyShares", "selectedKeyShares" ->
                    normalizeSequence(attributeName, value, this::normalizeKeyShare);
            case "supportedGroups", "selectedSupportedGroups", "keyExchangeGroup" ->
                    normalizeSequence(attributeName, value, this::normalizeNamedGroup);
            case "pskKeyExchangeModes", "selectedPskKeyExchangeModes" ->
                    normalizeSequence(attributeName, value, this::normalizePskKeyExchangeMode);
            case "certificateType" -> normalizeCertificateType(value);
            case "sessionId", "sharedSecret" -> normalizeNonceLike(attributeName, value);
            case "errorLog" -> normalizeAlertDescription(attributeName, value);
            case "reconnect", "reconnectInProgress", "sessionResumption", "secureRenegotiation",
                    "certificateRequested", "newSessionTicketWait", "earlyDataReq", "postClientAuthReq",
                    "clientRenegotiation", "clientCertificateReq", "newSessionTicketReq", "earlyDataWait",
                    "postClientAuthRequested", "pskDHEMode", "keyUpdateReq", "keyUpdateWait" ->
                    normalizeBoolean(attributeName, value);
            case "keyUpdateReqType" -> normalizeKeyUpdateRequest(attributeName, value);
            default -> throw unsupported(attributeName, value.source(),
                    "no user vocabulary is defined for this attribute; use maude(\"...\") for an explicit raw Maude behavior");
        };
    }

    private String normalizeMessageField(UserTerm field) {
        return normalizeMessageField(atomText("message field", field));
    }

    private String normalizeMessageField(String field) {
        String key = stripSigil(field, '#');
        String mapped = MESSAGE_FIELDS.get(key);
        if (mapped == null) {
            throw behaviorViolation("message field", field, "expected a supported behavior message field");
        }
        return mapped;
    }

    private String normalizeAttribute(UserTerm field) {
        return normalizeAttribute(atomText("attribute", field));
    }

    private String normalizeAttribute(String field) {
        String key = stripSigil(field, '@');
        String mapped = ATTRIBUTES.get(key);
        if (mapped == null) {
            mapped = ATTRIBUTES.get(normalizeKey(key));
        }
        if (mapped == null) {
            throw behaviorViolation("attribute", field, "expected a supported behavior attribute");
        }
        return mapped;
    }

    private UserTerm normalizeNode(UserTerm value) {
        String token = atomText("node", value).toLowerCase(Locale.ROOT);
        if ("client".equals(token) || "server".equals(token)) {
            return atom(token);
        }
        throw behaviorViolation("node", value.source(), "expected client or server");
    }

    private UserTerm normalizeEventType(UserTerm value) {
        String token = atomText("eventType", value).toLowerCase(Locale.ROOT);
        if ("send".equals(token) || "receive".equals(token)) {
            return atom(token);
        }
        throw behaviorViolation("eventType", value.source(), "expected send or receive");
    }

    private UserTerm normalizeProtocolVersion(UserTerm value) {
        String token = atomText("protocol version", value);
        String maude = PROTOCOL_VERSION_TO_MAUDE.get(token.toUpperCase(Locale.ROOT));
        if (maude == null) {
            if (token.contains("-")) {
                throw behaviorViolation("protocol version", token,
                        "use RFC-style values such as TLS11, TLS12, or TLS13");
            }
            throw behaviorViolation("protocol version", token, "expected TLS10, TLS11, TLS12, or TLS13");
        }
        return atom(maude);
    }

    private UserTerm normalizeCipherSuite(UserTerm value) {
        String token = atomText("cipher suite", value);
        if (token.contains("-")) {
            throw behaviorViolation("cipher suite", token,
                    "use the RFC/IANA enum name with underscores, e.g. TLS_AES_128_GCM_SHA256");
        }
        String key = token.toUpperCase(Locale.ROOT);
        if (UNSUPPORTED_CIPHER_SUITES.contains(key)) {
            throw unsupported("cipher suite", token,
                    "RFC cipher suite is known but current Maude model does not define it");
        }
        String maude = CIPHER_SUITE_TO_MAUDE.get(key);
        if (maude == null) {
            throw behaviorViolation("cipher suite", token, "expected a supported RFC/IANA cipher suite name");
        }
        return atom(maude);
    }

    private UserTerm normalizeCompressionMethod(UserTerm value) {
        String token = atomText("compression method", value);
        String maude = COMPRESSION_TO_MAUDE.get(normalizeKey(token));
        if (maude == null) {
            throw behaviorViolation("compression method", token, "expected null, no_compression, deflate, or zlib");
        }
        return atom(maude);
    }

    private UserTerm normalizeCertificateType(UserTerm value) {
        String token = atomText("certificate type", value);
        String maude = CERTIFICATE_TYPE_TO_MAUDE.get(normalizeKey(token));
        if (maude == null) {
            throw behaviorViolation("certificate type", token, "expected an RFC5246 certificate type name");
        }
        return atom(maude);
    }

    private UserTerm normalizeSignatureScheme(UserTerm value) {
        String token = atomText("signature scheme", value);
        String key = token.toLowerCase(Locale.ROOT);
        if (UNSUPPORTED_SIGNATURE_SCHEMES.contains(key)) {
            throw unsupported("signature scheme", token,
                    "RFC signature scheme is known but current Maude model cannot represent it");
        }
        String maude = SIGNATURE_SCHEME_TO_MAUDE.get(key);
        if (maude == null) {
            throw behaviorViolation("signature scheme", token, "expected an RFC-style signature scheme name");
        }
        return rawMaude(maude);
    }

    private UserTerm normalizeNamedGroup(UserTerm value) {
        String token = atomText("named group", value);
        String key = token.toLowerCase(Locale.ROOT);
        if (UNSUPPORTED_GROUPS.contains(key)) {
            throw unsupported("named group", token,
                    "RFC named group is known but current Maude model does not define it");
        }
        if (!SUPPORTED_GROUPS.contains(key)) {
            throw behaviorViolation("named group", token, "expected a supported RFC named group name");
        }
        return atom(key);
    }

    private UserTerm normalizePskKeyExchangeMode(UserTerm value) {
        String token = atomText("psk key exchange mode", value);
        String maude = PSK_MODE_TO_MAUDE.get(normalizeKey(token));
        if (maude == null) {
            throw behaviorViolation("psk key exchange mode", token, "expected psk_ke or psk_dhe_ke");
        }
        return atom(maude);
    }

    private UserTerm normalizeHandshakeType(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String maude = HANDSHAKE_TYPE_TO_MAUDE.get(normalizeKey(token));
        if (maude == null) {
            throw behaviorViolation(fieldName, token, "expected an RFC-style handshake type name");
        }
        return atom(maude);
    }

    private UserTerm normalizeContentType(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String maude = CONTENT_TYPE_TO_MAUDE.get(normalizeKey(token));
        if (maude == null) {
            throw behaviorViolation(fieldName, token, "expected handshake, change_cipher_spec, alert, or application_data");
        }
        return atom(maude);
    }

    private UserTerm normalizeMessageSize(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String maude = MESSAGE_SIZE_TO_MAUDE.get(normalizeKey(token));
        if (maude == null) {
            throw behaviorViolation(fieldName, token, "expected valid, smaller, larger, max_size, or min_size");
        }
        return atom(maude);
    }

    private UserTerm normalizeBoolean(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value).toLowerCase(Locale.ROOT);
        if ("true".equals(token) || "false".equals(token)) {
            return atom(token);
        }
        throw behaviorViolation(fieldName, token, "expected true or false");
    }

    private UserTerm normalizeNat(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        if (token.matches("[0-9]+")) {
            return atom(token);
        }
        throw behaviorViolation(fieldName, token, "expected a non-negative integer");
    }

    private UserTerm normalizeKeyUpdateRequest(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String maude = KEY_UPDATE_REQUEST_TO_MAUDE.get(normalizeKey(token));
        if (maude == null) {
            throw behaviorViolation(fieldName, token,
                    "expected update_requested, update_not_requested, true, false, or unknown");
        }
        return atom(maude);
    }

    private UserTerm normalizeAlertLevel(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String maude = ALERT_LEVEL_TO_MAUDE.get(normalizeKey(token));
        if (maude == null) {
            throw behaviorViolation(fieldName, token, "expected fatal or warning");
        }
        return atom(maude);
    }

    private UserTerm normalizeAlertDescription(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String maude = ALERT_DESCRIPTION_TO_MAUDE.get(normalizeKey(token));
        if (maude == null) {
            throw behaviorViolation(fieldName, token, "expected an RFC alert description name");
        }
        return atom(maude);
    }

    private UserTerm normalizeNonceLike(String fieldName, UserTerm value) {
        if (value instanceof UserTerm.Call call && "nonce".equals(call.name())) {
            return normalizeNonceCall(fieldName, call);
        }
        if ("verifyData".equals(fieldName) || "signature".equals(fieldName) || "sharedSecret".equals(fieldName)) {
            String token = atomText(fieldName, value);
            String key = normalizeKey(token);
            if ("no_nonce".equals(key) || "nononce".equals(key)) {
                return atom("noNonce");
            }
        }
        throw behaviorViolation(fieldName, value.source(), "expected nonce(client, n), nonce(server, n), nonce(self, n), or no_nonce where supported");
    }

    private UserTerm normalizeNonceCall(String fieldName, UserTerm.Call call) {
        if (call.arguments().size() != 2) {
            throw behaviorViolation(fieldName, call.source(), "nonce expects owner and index arguments");
        }
        String owner = atomText(fieldName, call.arguments().get(0));
        String index = atomText(fieldName, call.arguments().get(1));
        if (!index.matches("[0-9]+")) {
            throw behaviorViolation(fieldName, call.source(), "nonce index must be a non-negative integer");
        }
        return rawMaude("nonce(" + ownerToTid(owner, fieldName) + ", " + index + ")");
    }

    private UserTerm normalizeKeyShare(UserTerm value) {
        if (value instanceof UserTerm.Call call && "keyShare".equals(call.name())) {
            return normalizeKeyShareCall(call);
        }
        throw behaviorViolation("key share", value.source(),
                "expected keyShare(group) or keyShare(group, client|server|self, index)");
    }

    private UserTerm normalizeKeyShareCall(UserTerm.Call call) {
        if (call.arguments().size() != 1 && call.arguments().size() != 3) {
            throw behaviorViolation("key share", call.source(),
                    "expected keyShare(group) or keyShare(group, client|server|self, index)");
        }
        UserTerm groupTerm = normalizeNamedGroup(call.arguments().get(0));
        String group = ((UserTerm.Atom) groupTerm).text();
        String owner = "self";
        String index = "0";
        if (call.arguments().size() == 3) {
            owner = atomText("key share", call.arguments().get(1));
            index = atomText("key share", call.arguments().get(2));
            if (!index.matches("[0-9]+")) {
                throw behaviorViolation("key share", call.source(), "key share nonce index must be a non-negative integer");
            }
        }
        String keyExchange = group.startsWith("ffdhe") ? "dh" : "ecdh";
        return rawMaude("{"
                + group
                + ", "
                + keyExchange
                + "("
                + group
                + ", nonce("
                + ownerToTid(owner, "key share")
                + ", "
                + index
                + "))}");
    }

    private LoweredValue normalizeCertificateList(String fieldName, UserTerm value) {
        String token = atomText(fieldName, value);
        String key = normalizeKey(token);
        if ("empty_certificate_list".equals(key) || "emptycertificatelist".equals(key)) {
            return LoweredValue.direct(atom("emptyCertificateList"));
        }
        throw unsupported(fieldName, value.source(),
                "certificate list mutation currently supports empty_certificate_list only; use maude(\"...\") for explicit certificate terms");
    }

    private UserTerm normalizeSequence(String fieldName, UserTerm value, Function<UserTerm, UserTerm> normalizer) {
        if (value instanceof UserTerm.ListTerm list) {
            List<UserTerm> normalized = new ArrayList<UserTerm>();
            for (UserTerm item : list.values()) {
                normalized.add(normalizer.apply(item));
            }
            return new UserTerm.ListTerm(normalized);
        }
        return normalizer.apply(value);
    }

    private String ownerToTid(String owner, String fieldName) {
        return switch (owner.toLowerCase(Locale.ROOT)) {
            case "client", "tester" -> "N1 . CI";
            case "server", "target" -> "N2 . SI";
            case "self", "current", "applied" -> "@@TID@@";
            default -> throw behaviorViolation(fieldName, owner, "expected client, server, or self");
        };
    }

    private static String atomText(String context, UserTerm value) {
        if (value instanceof UserTerm.Atom atom) {
            return atom.text();
        }
        if (value instanceof UserTerm.RawMaude rawMaude) {
            throw behaviorViolation(context, rawMaude.source(),
                    "raw Maude is accepted only as an explicit raw value/modification escape");
        }
        throw behaviorViolation(context, value.source(), "expected an RFC-style identifier");
    }

    private static String stripSigil(String value, char sigil) {
        if (!value.isEmpty() && value.charAt(0) == sigil) {
            return value.substring(1);
        }
        return value;
    }

    private static String normalizeKey(String value) {
        return value.toLowerCase(Locale.ROOT).replace('-', '_');
    }

    private static UserTerm.Atom atom(String text) {
        return new UserTerm.Atom(text);
    }

    private static UserTerm.RawMaude rawMaude(String text) {
        return new UserTerm.RawMaude(text);
    }

    private static IllegalArgumentException behaviorViolation(String fieldName, String value, String expected) {
        return new IllegalArgumentException("BehaviorDeviationSpecification RFC/behavior value violation in "
                + fieldName
                + " for '"
                + value
                + "': "
                + expected);
    }

    private static IllegalArgumentException unsupported(String fieldName, String value, String reason) {
        return new IllegalArgumentException("BehaviorDeviationSpecification system unsupported value in "
                + fieldName
                + " for '"
                + value
                + "': "
                + reason);
    }

    private static Map<String, String> buildMessageFields() {
        Map<String, String> fields = new LinkedHashMap<String, String>();
        putField(fields, "contentType", "contentType", "content-type");
        putField(fields, "version", "version");
        putField(fields, "recordLen", "recordLen", "record-len");
        putField(fields, "alertDesc", "alertDesc", "alert-desc");
        putField(fields, "alertLev", "alertLev", "alert-lev");
        putField(fields, "handshakeType", "handshakeType", "handshake-type");
        putField(fields, "handshakeLen", "handshakeLen", "handshake-len");
        putField(fields, "protocol", "protocol");
        putField(fields, "cipherSuites", "cipherSuites", "cipher-suites");
        putField(fields, "cipherSuitesLen", "cipherSuitesLen", "cipher-suites-len");
        putField(fields, "random", "random");
        putField(fields, "sessionId", "sessionId", "session-id");
        putField(fields, "sessionIdLen", "sessionIdLen", "session-id-len");
        putField(fields, "compressions", "compressions");
        putField(fields, "compressionsLen", "compressionsLen", "compressions-len");
        putField(fields, "serverKeyExchange", "serverkeyExchange", "serverkeyExchange", "server-key-exchange");
        putField(fields, "clientPMSParam", "clientPMSParam", "client-pms-param");
        putField(fields, "clientDHParam", "clientDHParam", "client-dh-param");
        putField(fields, "clientECDHParam", "clientECDHParam", "client-ecdh-param");
        putField(fields, "certificateList", "certificate-list", "certificate-list");
        putField(fields, "certificateListLen", "certificate-list-len", "certificate-list-len");
        putField(fields, "certificateTypes", "certificateTypes", "certificate-types");
        putField(fields, "certificateTypesLen", "certificateTypesLen", "certificate-types-len");
        putField(fields, "certificateAlgos", "certificateAlgos", "certificate-algos");
        putField(fields, "certificateAlgosLen", "certificateAlgosLen", "certificate-algos-len");
        putField(fields, "certificateAuths", "certificateAuths", "certificate-auths");
        putField(fields, "certificateAuthsLen", "certificateAuthsLen", "certificate-auths-len");
        putField(fields, "verifyData", "verifyData", "verify-data");
        putField(fields, "signature", "signature");
        putField(fields, "signatureLen", "signature-len", "signature-len");
        putField(fields, "supportedVersions", "supported-versions", "supported-versions");
        putField(fields, "supportedVersionsLen", "supported-versions-len", "supported-versions-len");
        putField(fields, "signatureAlgorithms", "signature-algorithms", "signature-algorithms");
        putField(fields, "signatureAlgorithmsLen", "signature-algorithms-len", "signature-algorithms-len");
        putField(fields, "keyShares", "key-shares", "key-shares");
        putField(fields, "keySharesLen", "key-shares-len", "key-shares-len");
        putField(fields, "keySharesGroups", "key-shares-groups", "key-shares-groups");
        putField(fields, "supportedGroups", "supported-groups", "supported-groups");
        putField(fields, "supportedGroupsLen", "supported-groups-len", "supported-groups-len");
        putField(fields, "pskKeyExchangeMode", "psk-key-exchange-mode", "psk-key-exchange-mode");
        putField(fields, "pskKeyExchangeModeLen", "psk-key-exchange-mode-len", "psk-key-exchange-mode-len");
        putField(fields, "preSharedKey", "pre-shared-key", "pre-shared-key");
        putField(fields, "pskBinders", "psk-binders", "psk-binders");
        putField(fields, "preSharedKeyLen", "pre-shared-key-len", "pre-shared-key-len");
        putField(fields, "earlyData", "early-data", "early-data");
        putField(fields, "earlyDataLen", "early-data-len", "early-data-len");
        putField(fields, "postHandshake", "post-handshake", "post-handshake");
        putField(fields, "postHandshakeLen", "post-handshake-len", "post-handshake-len");
        putField(fields, "selectedIdentifier", "selected-identifier", "selected-identifier");
        putField(fields, "renegotiationInfo", "renegotiation-info", "renegotiation-info");
        putField(fields, "renegotiationInfoLen", "renegotiation-info-len", "renegotiation-info-len");
        putField(fields, "cks", "cks");
        putField(fields, "cksLen", "cks-len", "cks-len");
        putField(fields, "extensionLen", "extensionLen", "extension-len");
        putField(fields, "certificateRequestContext", "certificate-request-context", "certificate-request-context");
        putField(fields, "certificateRequestContextLen", "certificate-request-context-len", "certificate-request-context-len");
        putField(fields, "certificateVerifyAlgorithm", "certificate-verify-algorithm", "certificate-verify-algorithm");
        putField(fields, "newSessionTicket", "newSessionTicket", "new-session-ticket");
        putField(fields, "keyUpdateRequest", "keyUpdateRequest", "key-update-request");
        return Map.copyOf(fields);
    }

    private static void putField(Map<String, String> fields, String userName, String maudeName, String... aliases) {
        fields.put(userName, maudeName);
        fields.put(maudeName, maudeName);
        for (String alias : aliases) {
            fields.put(alias, maudeName);
        }
    }

    private static Map<String, String> buildAttributes() {
        Map<String, String> attributes = new LinkedHashMap<String, String>();
        for (String attribute : List.of(
                "cipherSuites",
                "compressions",
                "supportedVersions",
                "signatureAlgorithms",
                "keyShares",
                "supportedGroups",
                "pskKeyExchangeModes",
                "pskDHEMode",
                "extensions",
                "sessionId",
                "sessionState",
                "selectedVersion",
                "selectedCipherSuite",
                "selectedCompression",
                "selectedSupportedVersions",
                "selectedSignatureAlgorithms",
                "selectedKeyShares",
                "selectedSupportedGroups",
                "selectedPskKeyExchangeModes",
                "keyExchangeGroup",
                "sharedSecret",
                "certificateType",
                "certificateAlgo",
                "keyUpdateReq",
                "keyUpdateReqType",
                "keyUpdateWait",
                "errorLog",
                "reconnect",
                "reconnectInProgress",
                "sessionResumption",
                "secureRenegotiation",
                "clientState",
                "certificateRequested",
                "newSessionTicketWait",
                "earlyDataReq",
                "postClientAuthReq",
                "clientRenegotiation",
                "serverState",
                "clientCertificateReq",
                "newSessionTicketReq",
                "earlyDataWait",
                "postClientAuthRequested")) {
            putAttribute(attributes, attribute);
        }
        putAttribute(attributes, "errorLog", "alert", "alert-description", "alertDescription");
        return Map.copyOf(attributes);
    }

    private static void putAttribute(Map<String, String> attributes, String attribute, String... aliases) {
        attributes.put(attribute, attribute);
        attributes.put(normalizeKey(attribute), attribute);
        attributes.put(camelToSnake(attribute), attribute);
        for (String alias : aliases) {
            attributes.put(alias, attribute);
            attributes.put(normalizeKey(alias), attribute);
            attributes.put(camelToSnake(alias), attribute);
        }
    }

    private static String camelToSnake(String value) {
        return value
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z0-9])([A-Z])", "$1_$2")
                .toLowerCase(Locale.ROOT)
                .replace('-', '_');
    }

    private static Map<String, String> buildHandshakeTypes() {
        Map<String, String> types = new LinkedHashMap<String, String>();
        putValue(types, "client_hello", "client-hello");
        putValue(types, "server_hello", "server-hello");
        putValue(types, "encrypted_extensions", "encrypted-extension");
        putValue(types, "encrypted_extension", "encrypted-extension");
        putValue(types, "certificate", "certificate");
        putValue(types, "server_key_exchange", "server-key-exchange");
        putValue(types, "certificate_request", "certificate-request");
        putValue(types, "server_hello_done", "server-hello-done");
        putValue(types, "client_key_exchange", "client-key-exchange");
        putValue(types, "certificate_verify", "certificate-verify");
        putValue(types, "finished", "finished");
        putValue(types, "hello_request", "hello-request");
        putValue(types, "hello_retry_request", "hello-retry-request");
        putValue(types, "new_session_ticket", "new-session-ticket");
        putValue(types, "key_update", "key-update-request");
        putValue(types, "key_update_request", "key-update-request");
        return Map.copyOf(types);
    }

    private static void putValue(Map<String, String> values, String userName, String maudeName) {
        values.put(userName, maudeName);
        values.put(maudeName.replace('-', '_'), maudeName);
    }

    private static Map<String, String> buildCipherSuites() {
        Map<String, String> suites = new LinkedHashMap<String, String>();
        for (CipherSuite suite : CipherSuite.values()) {
            suites.put(suite.name().toUpperCase(Locale.ROOT), suite.maudeTerm());
        }
        return Map.copyOf(suites);
    }

    private static Map<String, String> buildAlertDescriptions() {
        Map<String, String> descriptions = new LinkedHashMap<String, String>();
        for (String alert : List.of(
                "close_notify",
                "unexpected_message",
                "bad_record_mac",
                "decryption_failed_reserved",
                "record_overflow",
                "decompression_failure",
                "handshake_failure",
                "no_certificate_reserved",
                "bad_certificate",
                "unsupported_certificate",
                "certificate_revoked",
                "certificate_expired",
                "certificate_unknown",
                "illegal_parameter",
                "unknown_ca",
                "access_denied",
                "decode_error",
                "decrypt_error",
                "export_restriction_reserved",
                "protocol_version",
                "insufficient_security",
                "internal_error",
                "inappropriate_fallback",
                "user_canceled",
                "no_renegotiation",
                "missing_extension",
                "unsupported_extension",
                "certificate_unobtainable",
                "unrecognized_name",
                "bad_certificate_status_response",
                "bad_certificate_hash_value",
                "unknown_psk_identity",
                "certificate_required",
                "no_application_protocol")) {
            descriptions.put(alert, alert.replace('_', '-'));
        }
        descriptions.put("record_overflow", "record_overflow");
        return Map.copyOf(descriptions);
    }

    private static Map<String, String> buildSignatureSchemeMap() {
        Map<String, String> schemes = new LinkedHashMap<String, String>();
        putSignature(schemes, "rsa_pkcs1_md5", "rsa", "md5");
        putSignature(schemes, "rsa_pkcs1_sha1", "rsa", "sha");
        putSignature(schemes, "rsa_pkcs1_sha224", "rsa", "sha224");
        putSignature(schemes, "rsa_pkcs1_sha256", "rsa", "sha256");
        putSignature(schemes, "rsa_pkcs1_sha384", "rsa", "sha384");
        putSignature(schemes, "rsa_pkcs1_sha512", "rsa", "sha512");
        putSignature(schemes, "rsa_pss_rsae_sha256", "rsa-pss-rsae", "sha256");
        putSignature(schemes, "rsa_pss_rsae_sha384", "rsa-pss-rsae", "sha384");
        putSignature(schemes, "rsa_pss_rsae_sha512", "rsa-pss-rsae", "sha512");
        putSignature(schemes, "rsa_pss_pss_sha256", "rsa-pss-pss", "sha256");
        putSignature(schemes, "rsa_pss_pss_sha384", "rsa-pss-pss", "sha384");
        putSignature(schemes, "rsa_pss_pss_sha512", "rsa-pss-pss", "sha512");
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

    private record LoweredValue(UserTerm term, boolean direct) {
        static LoweredValue wrapped(UserTerm term) {
            return new LoweredValue(term, false);
        }

        static LoweredValue direct(UserTerm term) {
            return new LoweredValue(term, true);
        }
    }
}
