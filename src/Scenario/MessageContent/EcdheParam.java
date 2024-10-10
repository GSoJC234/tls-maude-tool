package Scenario.MessageContent;

public class EcdheParam implements KeyParam {
    private NamedCurve namedCurve;
    private Key serverKey;
    private Key clientKey;

    public void setNamedCurve(NamedCurve namedCurve) {
        this.namedCurve = namedCurve;
    }
    public void setServerKey(Key serverKey) {
        this.serverKey = serverKey;
    }
    public void setClientKey(Key clientKey) {
        this.clientKey = clientKey;
    }
}
