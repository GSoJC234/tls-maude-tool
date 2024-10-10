package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class SignatureAndHashAlgorithms implements MessageContent{
    private List<SignatureAndHashAlgorithm> signatureAndHashAlgorithmList;

    public SignatureAndHashAlgorithms(){
        signatureAndHashAlgorithmList = new ArrayList<SignatureAndHashAlgorithm>();
    }

    public void add(SignatureAndHashAlgorithm signatureAndHashAlgorithm){
        signatureAndHashAlgorithmList.add(signatureAndHashAlgorithm);
    }
}
