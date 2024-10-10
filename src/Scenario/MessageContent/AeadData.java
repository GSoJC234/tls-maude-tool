package Scenario.MessageContent;

public class AeadData implements MessageContent {
    private int num;
    private CompressionMethod method;
    public AeadData(int num, CompressionMethod method) {
        this.num = num;
        this.method = method;
    }
}
