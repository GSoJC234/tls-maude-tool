package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class CipherSuites implements MessageContent{
    private List<CipherSuite> cipherSuites;
    public CipherSuites() {
        cipherSuites = new ArrayList<CipherSuite>();
    }
    public void addCipherSuite(CipherSuite cipherSuite) {
        cipherSuites.add(cipherSuite);
    }
}
