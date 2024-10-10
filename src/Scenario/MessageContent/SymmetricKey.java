package Scenario.MessageContent;

public class SymmetricKey implements Key {
    private EncryptionAlgorithm encryptionAlgorithm;
    private MasterSecret masterSecret;
    private Nonce clientRandom;
    private Nonce serverRandom;


    public void setEncryptionAlgorithm(EncryptionAlgorithm encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
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
