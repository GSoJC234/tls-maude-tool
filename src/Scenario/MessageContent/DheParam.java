package Scenario.MessageContent;

public class DheParam implements KeyParam {
    private Nonce serverNonce1;
    private Nonce serverNonce2;
    private Key serverKey;

    private Key clientKey;

    public void setServerNonce1(Nonce serverNonce1) {
        this.serverNonce1 = serverNonce1;
    }
    public void setServerNonce2(Nonce serverNonce2) {
        this.serverNonce2 = serverNonce2;
    }
    public void setServerKey(Key serverKey) {
        this.serverKey = serverKey;
    }
    public void setClientKey(Key clientKey) {
        this.clientKey = clientKey;
    }
}
