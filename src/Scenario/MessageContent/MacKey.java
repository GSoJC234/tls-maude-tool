package Scenario.MessageContent;

public class MacKey implements Key {
    private HashAlgorithm algorithm;
    private MasterSecret masterSecret;
    private Nonce clientRandom;
    private Nonce serverRandom;

    public void setAlgorithm(HashAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
    public void setMasterSecret(MasterSecret masterSecret) {
        this.masterSecret = masterSecret;
    }
    public void setClientRandom(Nonce clientRandom) {
        this.clientRandom = clientRandom;
    }
    public void setServerRandom(Nonce serverRandom) {
        this.serverRandom = serverRandom;
    }
}
