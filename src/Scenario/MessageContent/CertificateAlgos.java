package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class CertificateAlgos implements MessageContent {

    private List<SignatureAndHashAlgorithm> signatureAlgorithmList;
    public CertificateAlgos() {
        signatureAlgorithmList = new ArrayList<SignatureAndHashAlgorithm>();
    }
    public void addSignatureAlgorithm(SignatureAndHashAlgorithm signatureAlgorithm) {
        signatureAlgorithmList.add(signatureAlgorithm);
    }
}
