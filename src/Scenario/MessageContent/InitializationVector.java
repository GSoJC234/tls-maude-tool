package Scenario.MessageContent;

public class InitializationVector extends Nonce {
    private MasterSecret masterSecret;
    private Nonce clientRandom;
    private Nonce serverRandom;

    public InitializationVector(MasterSecret masterSecret, Nonce clientRandom, Nonce serverRandom) {
        this.masterSecret = masterSecret;
        this.clientRandom = clientRandom;
        this.serverRandom = serverRandom;
    }
}
