package Scenario.MessageContent;

public class Random implements MessageContent{
    private Nonce nonce;

    public Random(Nonce nonce){
        this.nonce = nonce;
    }
}
