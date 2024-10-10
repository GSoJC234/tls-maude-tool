package Scenario.MessageContent;

public class AeadNonce implements MessageContent {
    private Nonce nonce1;
    private Nonce nonce2;
    public AeadNonce(Nonce nonce1, Nonce nonce2) {
        this.nonce1 = nonce1;
        this.nonce2 = nonce2;

    }
}
