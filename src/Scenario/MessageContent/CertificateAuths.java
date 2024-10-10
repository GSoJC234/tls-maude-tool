package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class CertificateAuths implements MessageContent{
    private List<String> auths;
    public CertificateAuths(){
        auths = new ArrayList<String>();
    }

    public void addAuth(String auth){
        auths.add(auth);
    }
}
