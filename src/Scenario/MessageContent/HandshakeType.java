package Scenario.MessageContent;

public enum HandshakeType implements MessageContent {
    CLIENT_HELLO,
    SERVER_HELLO,
    CERTIFICATE,
    SERVER_KEY_EXCHANGE,
    CERTIFICATE_REQUEST,
    SERVER_HELLO_DONE,
    CLIENT_KEY_EXCHANGE,
    CERTIFICATE_VERIFY,
    FINISHED
}
