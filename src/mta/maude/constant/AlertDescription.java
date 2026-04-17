package mta.maude.constant;

public enum AlertDescription {
    CLOSE_NOTIFY,
    UNEXPECTED_MESSAGE,
    BAD_RECORD_MAC,
    DECRYPTION_FAILED_RESERVED,
    RECORD_OVERFLOW,
    DECOMPRESSION_FAILURE,
    HANDSHAKE_FAILURE,
    NO_CERTIFICATE_RESERVED,
    BAD_CERTIFICATE,
    UNSUPPORTED_CERTIFICATE,
    CERTIFICATE_REVOKED,
    CERTIFICATE_EXPIRED,
    CERTIFICATE_UNKNOWN,
    ILLEGAL_PARAMETER,
    UNKNOWN_CA,
    ACCESS_DENIED,
    DECODE_ERROR,
    DECRYPT_ERROR,
    EXPORT_RESTRICTION_RESERVED,
    PROTOCOL_VERSION,
    INSUFFICIENT_SECURITY,
    INTERNAL_ERROR,
    INAPPROPRIATE_FALLBACK,
    USER_CANCELED,
    NO_RENEGOTIATION,
    MISSING_EXTENSION,
    UNSUPPORTED_EXTENSION,
    CERTIFICATE_UNOBTAINABLE,
    UNRECOGNIZED_NAME,
    BAD_CERTIFICATE_STATUS_RESPONSE,
    BAD_CERTIFICATE_HASH_VALUE,
    UNKNOWN_PSK_IDENTITY,
    CERTIFICATE_REQUIRED,
    NO_APPLICATION_PROTOCOL;

    public static String title(){
        return "AlertDescription";
    }

    public de.rub.nds.tlsattacker.core.constants.AlertDescription transform(){
        return switch (this) {
            case CLOSE_NOTIFY -> de.rub.nds.tlsattacker.core.constants.AlertDescription.CLOSE_NOTIFY;
            case UNEXPECTED_MESSAGE -> de.rub.nds.tlsattacker.core.constants.AlertDescription.UNEXPECTED_MESSAGE;
            case BAD_RECORD_MAC -> de.rub.nds.tlsattacker.core.constants.AlertDescription.BAD_RECORD_MAC;
            case DECRYPTION_FAILED_RESERVED ->
                    de.rub.nds.tlsattacker.core.constants.AlertDescription.DECRYPTION_FAILED_RESERVED;
            case RECORD_OVERFLOW -> de.rub.nds.tlsattacker.core.constants.AlertDescription.RECORD_OVERFLOW;
            case DECOMPRESSION_FAILURE -> de.rub.nds.tlsattacker.core.constants.AlertDescription.DECOMPRESSION_FAILURE;
            case HANDSHAKE_FAILURE -> de.rub.nds.tlsattacker.core.constants.AlertDescription.HANDSHAKE_FAILURE;
            case NO_CERTIFICATE_RESERVED ->
                    de.rub.nds.tlsattacker.core.constants.AlertDescription.NO_CERTIFICATE_RESERVED;
            case BAD_CERTIFICATE -> de.rub.nds.tlsattacker.core.constants.AlertDescription.BAD_CERTIFICATE;
            case UNSUPPORTED_CERTIFICATE ->
                    de.rub.nds.tlsattacker.core.constants.AlertDescription.UNSUPPORTED_CERTIFICATE;
            case CERTIFICATE_REVOKED -> de.rub.nds.tlsattacker.core.constants.AlertDescription.CERTIFICATE_REVOKED;
            case CERTIFICATE_EXPIRED -> de.rub.nds.tlsattacker.core.constants.AlertDescription.CERTIFICATE_EXPIRED;
            case CERTIFICATE_UNKNOWN -> de.rub.nds.tlsattacker.core.constants.AlertDescription.CERTIFICATE_UNKNOWN;
            case ILLEGAL_PARAMETER -> de.rub.nds.tlsattacker.core.constants.AlertDescription.ILLEGAL_PARAMETER;
            case UNKNOWN_CA -> de.rub.nds.tlsattacker.core.constants.AlertDescription.UNKNOWN_CA;
            case ACCESS_DENIED -> de.rub.nds.tlsattacker.core.constants.AlertDescription.ACCESS_DENIED;
            case DECODE_ERROR -> de.rub.nds.tlsattacker.core.constants.AlertDescription.DECODE_ERROR;
            case DECRYPT_ERROR -> de.rub.nds.tlsattacker.core.constants.AlertDescription.DECRYPT_ERROR;
            case EXPORT_RESTRICTION_RESERVED ->
                    de.rub.nds.tlsattacker.core.constants.AlertDescription.EXPORT_RESTRICTION_RESERVED;
            case PROTOCOL_VERSION -> de.rub.nds.tlsattacker.core.constants.AlertDescription.PROTOCOL_VERSION;
            case INSUFFICIENT_SECURITY -> de.rub.nds.tlsattacker.core.constants.AlertDescription.INSUFFICIENT_SECURITY;
            case INTERNAL_ERROR -> de.rub.nds.tlsattacker.core.constants.AlertDescription.INTERNAL_ERROR;
            case INAPPROPRIATE_FALLBACK ->
                    de.rub.nds.tlsattacker.core.constants.AlertDescription.INAPPROPRIATE_FALLBACK;
            case USER_CANCELED -> de.rub.nds.tlsattacker.core.constants.AlertDescription.USER_CANCELED;
            case NO_RENEGOTIATION -> de.rub.nds.tlsattacker.core.constants.AlertDescription.NO_RENEGOTIATION;
            case MISSING_EXTENSION -> de.rub.nds.tlsattacker.core.constants.AlertDescription.MISSING_EXTENSION;
            case UNSUPPORTED_EXTENSION -> de.rub.nds.tlsattacker.core.constants.AlertDescription.UNSUPPORTED_EXTENSION;
            case CERTIFICATE_UNOBTAINABLE ->
                    de.rub.nds.tlsattacker.core.constants.AlertDescription.CERTIFICATE_UNOBTAINABLE;
            case UNRECOGNIZED_NAME -> de.rub.nds.tlsattacker.core.constants.AlertDescription.UNRECOGNIZED_NAME;
            case BAD_CERTIFICATE_STATUS_RESPONSE ->
                    de.rub.nds.tlsattacker.core.constants.AlertDescription.BAD_CERTIFICATE_STATUS_RESPONSE;
            case BAD_CERTIFICATE_HASH_VALUE ->
                    de.rub.nds.tlsattacker.core.constants.AlertDescription.BAD_CERTIFICATE_HASH_VALUE;
            case UNKNOWN_PSK_IDENTITY -> de.rub.nds.tlsattacker.core.constants.AlertDescription.UNKNOWN_PSK_IDENTITY;
            case CERTIFICATE_REQUIRED -> de.rub.nds.tlsattacker.core.constants.AlertDescription.CERTIFICATE_REQUIRED;
            case NO_APPLICATION_PROTOCOL ->
                    de.rub.nds.tlsattacker.core.constants.AlertDescription.NO_APPLICATION_PROTOCOL;
            default -> throw new IllegalArgumentException("Unknown alert description: " + this);
        };
    }
    
    public String maudeTerm() {
        return switch (this) {
            case CLOSE_NOTIFY -> "close-notify";
            case UNEXPECTED_MESSAGE -> "unexpected-message";
            case BAD_RECORD_MAC -> "bad-record-mac";
            case DECRYPTION_FAILED_RESERVED -> "decryption-failed-reserved";
            case RECORD_OVERFLOW -> "record_overflow";
            case DECOMPRESSION_FAILURE -> "decompression-failure";
            case HANDSHAKE_FAILURE -> "handshake-failure";
            case NO_CERTIFICATE_RESERVED -> "no-certificate-reserved";
            case BAD_CERTIFICATE -> "bad-certificate";
            case UNSUPPORTED_CERTIFICATE -> "unsupported-certificate";
            case CERTIFICATE_REVOKED -> "certificate-revoked";
            case CERTIFICATE_EXPIRED -> "certificate-expired";
            case CERTIFICATE_UNKNOWN -> "certificate-unknown";
            case ILLEGAL_PARAMETER -> "illegal-parameter";
            case UNKNOWN_CA -> "unknown-ca";
            case ACCESS_DENIED -> "access-denied";
            case DECODE_ERROR -> "decode-error";
            case DECRYPT_ERROR -> "decrypt-error";
            case EXPORT_RESTRICTION_RESERVED -> "export-restriction-reserved";
            case PROTOCOL_VERSION -> "protocol-version";
            case INSUFFICIENT_SECURITY -> "insufficient-security";
            case INTERNAL_ERROR -> "internal-error";
            case INAPPROPRIATE_FALLBACK -> "inappropriate-fallback";
            case USER_CANCELED -> "user-canceled";
            case NO_RENEGOTIATION -> "no-renegotiation";
            case MISSING_EXTENSION -> "missing-extension";
            case UNSUPPORTED_EXTENSION -> "unsupported-extension";
            case CERTIFICATE_UNOBTAINABLE -> "certificate-unobtainable";
            case UNRECOGNIZED_NAME -> "unrecognized-name";
            case BAD_CERTIFICATE_STATUS_RESPONSE -> "bad-certificate-status-response";
            case BAD_CERTIFICATE_HASH_VALUE -> "bad-certificate-hash-value";
            case UNKNOWN_PSK_IDENTITY -> "unknown-psk-identity";
            case CERTIFICATE_REQUIRED -> "certificate-required";
            case NO_APPLICATION_PROTOCOL -> "no-application-protocol";
            default -> throw new IllegalArgumentException("Unknown alert description: " + this);
        };
    }
}
