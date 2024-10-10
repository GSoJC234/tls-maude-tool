package Scenario.MessageContent;

public class MasterSecret extends Nonce {
    private PreMasterSecret preMasterSecret;
    private Nonce clientRandom;
    private Nonce serverRandom;

    public MasterSecret(PreMasterSecret preMasterSecret, Nonce clientRandom, Nonce serverRandom) {
        this.preMasterSecret = preMasterSecret;
        this.clientRandom = clientRandom;
        this.serverRandom = serverRandom;
    }
}
