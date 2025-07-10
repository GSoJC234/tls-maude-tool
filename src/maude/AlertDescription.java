package maude;

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

    public de.rub.nds.tlsattacker.core.constants.AlertDescription transform(){
        switch(this){
            case CLOSE_NOTIFY: return de.rub.nds.tlsattacker.core.constants.AlertDescription.CLOSE_NOTIFY;
            case UNEXPECTED_MESSAGE: return de.rub.nds.tlsattacker.core.constants.AlertDescription.UNEXPECTED_MESSAGE;
            case BAD_RECORD_MAC: return de.rub.nds.tlsattacker.core.constants.AlertDescription.BAD_RECORD_MAC;
            case DECRYPTION_FAILED_RESERVED: return de.rub.nds.tlsattacker.core.constants.AlertDescription.DECRYPTION_FAILED_RESERVED;
            case RECORD_OVERFLOW: return de.rub.nds.tlsattacker.core.constants.AlertDescription.RECORD_OVERFLOW;
            case DECOMPRESSION_FAILURE: return de.rub.nds.tlsattacker.core.constants.AlertDescription.DECOMPRESSION_FAILURE;
            case HANDSHAKE_FAILURE: return de.rub.nds.tlsattacker.core.constants.AlertDescription.HANDSHAKE_FAILURE;
            case NO_CERTIFICATE_RESERVED: return de.rub.nds.tlsattacker.core.constants.AlertDescription.NO_CERTIFICATE_RESERVED;
            case BAD_CERTIFICATE: return de.rub.nds.tlsattacker.core.constants.AlertDescription.BAD_CERTIFICATE;
            case UNSUPPORTED_CERTIFICATE: return de.rub.nds.tlsattacker.core.constants.AlertDescription.UNSUPPORTED_CERTIFICATE;
            case CERTIFICATE_REVOKED: return de.rub.nds.tlsattacker.core.constants.AlertDescription.CERTIFICATE_REVOKED;
            case CERTIFICATE_EXPIRED: return de.rub.nds.tlsattacker.core.constants.AlertDescription.CERTIFICATE_EXPIRED;
            case CERTIFICATE_UNKNOWN: return de.rub.nds.tlsattacker.core.constants.AlertDescription.CERTIFICATE_UNKNOWN;
            case ILLEGAL_PARAMETER: return de.rub.nds.tlsattacker.core.constants.AlertDescription.ILLEGAL_PARAMETER;
            case UNKNOWN_CA: return de.rub.nds.tlsattacker.core.constants.AlertDescription.UNKNOWN_CA;
            case ACCESS_DENIED: return de.rub.nds.tlsattacker.core.constants.AlertDescription.ACCESS_DENIED;
            case DECODE_ERROR: return de.rub.nds.tlsattacker.core.constants.AlertDescription.DECODE_ERROR;
            case DECRYPT_ERROR: return de.rub.nds.tlsattacker.core.constants.AlertDescription.DECRYPT_ERROR;
            case EXPORT_RESTRICTION_RESERVED: return de.rub.nds.tlsattacker.core.constants.AlertDescription.EXPORT_RESTRICTION_RESERVED;
            case PROTOCOL_VERSION: return de.rub.nds.tlsattacker.core.constants.AlertDescription.PROTOCOL_VERSION;
            case INSUFFICIENT_SECURITY: return de.rub.nds.tlsattacker.core.constants.AlertDescription.INSUFFICIENT_SECURITY;
            case INTERNAL_ERROR: return de.rub.nds.tlsattacker.core.constants.AlertDescription.INTERNAL_ERROR;
            case INAPPROPRIATE_FALLBACK: return de.rub.nds.tlsattacker.core.constants.AlertDescription.INAPPROPRIATE_FALLBACK;
            case USER_CANCELED: return de.rub.nds.tlsattacker.core.constants.AlertDescription.USER_CANCELED;
            case NO_RENEGOTIATION: return de.rub.nds.tlsattacker.core.constants.AlertDescription.NO_RENEGOTIATION;
            case MISSING_EXTENSION: return de.rub.nds.tlsattacker.core.constants.AlertDescription.MISSING_EXTENSION;
            case UNSUPPORTED_EXTENSION: return de.rub.nds.tlsattacker.core.constants.AlertDescription.UNSUPPORTED_EXTENSION;
            case CERTIFICATE_UNOBTAINABLE: return de.rub.nds.tlsattacker.core.constants.AlertDescription.CERTIFICATE_UNOBTAINABLE;
            case UNRECOGNIZED_NAME: return de.rub.nds.tlsattacker.core.constants.AlertDescription.UNRECOGNIZED_NAME;
            case BAD_CERTIFICATE_STATUS_RESPONSE: return de.rub.nds.tlsattacker.core.constants.AlertDescription.BAD_CERTIFICATE_STATUS_RESPONSE;
            case BAD_CERTIFICATE_HASH_VALUE: return de.rub.nds.tlsattacker.core.constants.AlertDescription.BAD_CERTIFICATE_HASH_VALUE;
            case UNKNOWN_PSK_IDENTITY: return de.rub.nds.tlsattacker.core.constants.AlertDescription.UNKNOWN_PSK_IDENTITY;
            case CERTIFICATE_REQUIRED: return de.rub.nds.tlsattacker.core.constants.AlertDescription.CERTIFICATE_REQUIRED;
            case NO_APPLICATION_PROTOCOL: return de.rub.nds.tlsattacker.core.constants.AlertDescription.NO_APPLICATION_PROTOCOL;
            default: throw new IllegalArgumentException("Unknown alert description: " + this);
        }
    }
}
