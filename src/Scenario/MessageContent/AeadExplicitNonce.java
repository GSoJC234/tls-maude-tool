package Scenario.MessageContent;

public class AeadExplicitNonce implements MessageContent{
    private Nonce nonce;
    public AeadExplicitNonce(Nonce nonce) {
        this.nonce = nonce;
    }
}
