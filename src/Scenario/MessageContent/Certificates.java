package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class Certificates implements MessageContent {
    private List<Certificate> certificates;
    public Certificates(){
        certificates = new ArrayList<Certificate>();
    }
    public void addCertificate(Certificate certificate){
        certificates.add(certificate);
    }
}
