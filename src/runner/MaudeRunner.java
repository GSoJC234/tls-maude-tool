package runner;

public class MaudeRunner implements Runnable {

    private byte[] requirementInfo;
    private int size;

    public MaudeRunner(byte[] requirementInfo, int size){
        this.requirementInfo = requirementInfo;
        this.size = size;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
