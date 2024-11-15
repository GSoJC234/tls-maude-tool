package Maude;

public enum HandshakeMessageType {
    CLIENT_HELLO,
    SERVER_HELLO,
    CERTIFICATE,
    SERVER_KEY_EXCHANGE,
    CERTIFICATE_REQUEST,
    SERVER_HELLO_DONE,
    CLIENT_KEY_EXCHANGE,
    CERTIFICATE_VERIFY,
    FINISHED,
    UNKNOWN;

    public de.rub.nds.tlsattacker.core.constants.HandshakeMessageType transform(){
        switch(this){
            case CLIENT_HELLO: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CLIENT_HELLO;
            case SERVER_HELLO: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.SERVER_HELLO;
            case CERTIFICATE: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CERTIFICATE;
            case SERVER_KEY_EXCHANGE: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.SERVER_KEY_EXCHANGE;
            case CERTIFICATE_REQUEST: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CERTIFICATE_REQUEST;
            case SERVER_HELLO_DONE: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.SERVER_HELLO_DONE;
            case CLIENT_KEY_EXCHANGE: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CLIENT_HELLO;
            case CERTIFICATE_VERIFY: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CERTIFICATE_VERIFY;
            case FINISHED: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.FINISHED;
            case UNKNOWN: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.UNKNOWN;
            default: throw new IllegalArgumentException("Unknown handshake message type: " + this);
        }
    }
}
