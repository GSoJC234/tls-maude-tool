package Scenario.MessageContent;

public class SessionId implements MessageContent {
    private Nonce nonce;
    public SessionId(Nonce nonce) {
        this.nonce = nonce;
    }
}
