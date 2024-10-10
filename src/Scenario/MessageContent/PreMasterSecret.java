package Scenario.MessageContent;

public class PreMasterSecret extends Nonce{
    private KeyExchangeAlgorithm algorithm;
    private Key key1;
    private Key key2;
    private Nonce nonce;

    public void setAlgorithm(KeyExchangeAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
    public void setKey1(Key key1) {
        this.key1 = key1;
    }
    public void setKey2(Key key2) {
        this.key2 = key2;
    }
    public void setNonce(Nonce nonce) {
        this.nonce = nonce;
    }
}
