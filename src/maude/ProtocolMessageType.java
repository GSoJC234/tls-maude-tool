package maude;

public enum ProtocolMessageType {
    HANDSHAKE, ALERT, CHANGE_CIPHER_SPEC, UNKNOWN;

    public de.rub.nds.tlsattacker.core.constants.ProtocolMessageType transform(){
        switch(this){
            case HANDSHAKE: return de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.HANDSHAKE;
            case ALERT: return de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.ALERT;
            case CHANGE_CIPHER_SPEC: return de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.CHANGE_CIPHER_SPEC;
            case UNKNOWN: return de.rub.nds.tlsattacker.core.constants.ProtocolMessageType.UNKNOWN;
            default: throw new IllegalArgumentException("Unrecognized ContentType: " + this);
        }
    }
}
