package mta.maude.constant;

public enum HandshakeMessageType {
    CLIENT_HELLO,
    SERVER_HELLO,
    ENCRYPTED_EXTENSION,
    CERTIFICATE,
    SERVER_KEY_EXCHANGE,
    CERTIFICATE_REQUEST,
    SERVER_HELLO_DONE,
    CLIENT_KEY_EXCHANGE,
    CERTIFICATE_VERIFY,
    FINISHED,
    HELLO_REQUEST,
    NEW_SESSION_TICKET,
    KEY_UPDATE_REQUEST,
    UNKNOWN;

    public static String title(){
        return "HandshakeMessageType";
    }

    public de.rub.nds.tlsattacker.core.constants.HandshakeMessageType transform(){
        switch(this){
            case CLIENT_HELLO: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CLIENT_HELLO;
            case SERVER_HELLO: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.SERVER_HELLO;
            case ENCRYPTED_EXTENSION: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.ENCRYPTED_EXTENSIONS;
            case CERTIFICATE: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CERTIFICATE;
            case SERVER_KEY_EXCHANGE: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.SERVER_KEY_EXCHANGE;
            case CERTIFICATE_REQUEST: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CERTIFICATE_REQUEST;
            case SERVER_HELLO_DONE: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.SERVER_HELLO_DONE;
            case CLIENT_KEY_EXCHANGE: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CLIENT_KEY_EXCHANGE;
            case CERTIFICATE_VERIFY: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CERTIFICATE_VERIFY;
            case FINISHED: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.FINISHED;
            case HELLO_REQUEST: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.HELLO_REQUEST;
            case NEW_SESSION_TICKET: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.NEW_SESSION_TICKET;
            case KEY_UPDATE_REQUEST: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.KEY_UPDATE;
            case UNKNOWN: return de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.UNKNOWN;
            default: throw new IllegalArgumentException("Unknown handshake message type: " + this);
        }
    }
}
