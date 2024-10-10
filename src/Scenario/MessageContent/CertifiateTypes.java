package Scenario.MessageContent;

import java.util.ArrayList;
import java.util.List;

public class CertifiateTypes implements MessageContent {
    private List<CertificateType> certificateTypeList;
    public CertifiateTypes() {
        certificateTypeList = new ArrayList<CertificateType>();
    }
    public void addCertificateType(CertificateType certificateType) {
        certificateTypeList.add(certificateType);
    }
}
