package mta.maude.constant;

public enum ProtocolMessageType {
    HANDSHAKE, ALERT, CHANGE_CIPHER_SPEC, UNKNOWN, APPLICATAION_DATA;

    public static String title(){
        return "ProtocolMessageType";
    }

    public de.rub.nds.tlsattacker.core.constants.ProtocolMessageType transform(){
        switch(this){
            case HANDSHAKE: return de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.HANDSHAKE;
            case ALERT: return de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.ALERT;
            case CHANGE_CIPHER_SPEC: return de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.CHANGE_CIPHER_SPEC;
            case UNKNOWN: return de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.UNKNOWN;
            case APPLICATAION_DATA: return de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.APPLICATION_DATA;
            default: throw new IllegalArgumentException("Unrecognized ContentType: " + this);
        }
    }
}
