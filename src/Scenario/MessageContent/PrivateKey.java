package Scenario.MessageContent;

public class PrivateKey implements Key {
    private KeyExchangeAlgorithm algorithm;
    private SignatureAlgorithm signatureAlgorithm;
    private Nonce nonce;

    public void setAlgorithm(KeyExchangeAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
    public void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }
    public void setNonce(Nonce nonce) {
        this.nonce = nonce;
    }
}
