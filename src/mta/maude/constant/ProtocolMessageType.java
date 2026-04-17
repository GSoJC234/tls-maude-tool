package mta.maude.constant;

public enum ProtocolMessageType {
    HANDSHAKE, ALERT, CHANGE_CIPHER_SPEC, UNKNOWN, APPLICATAION_DATA;

    public static String title(){
        return "ProtocolMessageType";
    }

    public de.rub.nds.tlsattacker.core.constants.ProtocolMessageType transform(){
        return switch (this) {
            case HANDSHAKE -> de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.HANDSHAKE;
            case ALERT -> de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.ALERT;
            case CHANGE_CIPHER_SPEC -> de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.CHANGE_CIPHER_SPEC;
            case UNKNOWN -> de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.UNKNOWN;
            case APPLICATAION_DATA -> de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.APPLICATION_DATA;
            default -> throw new IllegalArgumentException("Unrecognized ContentType: " + this);
        };
    }

    public String maudeTerm() {
        return switch (this) {
            case HANDSHAKE -> "handshake";
            case ALERT -> "alert";
            case CHANGE_CIPHER_SPEC -> "change-cipher-spec";
            case UNKNOWN -> "unknown";
            case APPLICATAION_DATA -> "application-data";
            default -> throw new IllegalArgumentException("Unrecognized ContentType: " + this);
        };
    }
}
