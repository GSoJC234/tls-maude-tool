package Scenario.MessageContent;

public class VerifyData implements MessageContent {
    private MasterSecret masterSecret;
    private Hash hash;

    public VerifyData(MasterSecret masterSecret, Hash hash) {
        this.masterSecret = masterSecret;
        this.hash = hash;

    }
}
