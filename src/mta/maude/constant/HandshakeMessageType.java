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
        return switch (this) {
            case CLIENT_HELLO -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CLIENT_HELLO;
            case SERVER_HELLO -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.SERVER_HELLO;
            case ENCRYPTED_EXTENSION -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.ENCRYPTED_EXTENSIONS;
            case CERTIFICATE -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CERTIFICATE;
            case SERVER_KEY_EXCHANGE -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.SERVER_KEY_EXCHANGE;
            case CERTIFICATE_REQUEST -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CERTIFICATE_REQUEST;
            case SERVER_HELLO_DONE -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.SERVER_HELLO_DONE;
            case CLIENT_KEY_EXCHANGE -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CLIENT_KEY_EXCHANGE;
            case CERTIFICATE_VERIFY -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.CERTIFICATE_VERIFY;
            case FINISHED -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.FINISHED;
            case HELLO_REQUEST -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.HELLO_REQUEST;
            case NEW_SESSION_TICKET -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.NEW_SESSION_TICKET;
            case KEY_UPDATE_REQUEST -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.KEY_UPDATE;
            case UNKNOWN -> de.rub.nds.tlsattacker.core.constants.HandshakeMessageType.UNKNOWN;
            default -> throw new IllegalArgumentException("Unknown handshake message type: " + this);
        };
    }

    public String maudeTerm() {
        return switch (this) {
            case CLIENT_HELLO -> "client-hello";
            case SERVER_HELLO -> "server-hello";
            case ENCRYPTED_EXTENSION -> "encrypted-extension";
            case CERTIFICATE -> "certificate";
            case SERVER_KEY_EXCHANGE -> "server-key-exchange";
            case CERTIFICATE_REQUEST -> "certificate-request";
            case SERVER_HELLO_DONE -> "server-hello-done";
            case CLIENT_KEY_EXCHANGE -> "client-key-exchange";
            case CERTIFICATE_VERIFY -> "certificate-verify";
            case FINISHED -> "finished";
            case NEW_SESSION_TICKET -> "new-session-ticket";
            case KEY_UPDATE_REQUEST -> "key-update-request";
            default -> throw new IllegalArgumentException("Unknown handshake message type: " + this);
        };
    }
}
