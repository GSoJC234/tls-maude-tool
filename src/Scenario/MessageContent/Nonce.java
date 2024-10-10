package Scenario.MessageContent;

public class Nonce  implements MessageContent {
    private int Num;
    private String Id;

    public Nonce(){
        this.Num = -1;
        this.Id = null;
    };

    public Nonce(int Num, String Id) {
        this.Num = Num;
        this.Id = Id;
    }

    public boolean isNoNonce(){
        return Num == -1;
    }
}
