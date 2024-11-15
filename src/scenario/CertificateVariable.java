package scenario;

import Protocol.Variable;
import de.rub.nds.x509attacker.x509.model.X509Certificate;

import java.util.List;

public class CertificateVariable implements Variable {

    private List<X509Certificate> certificate_container = null;
    public CertificateVariable(List<X509Certificate> certificate_container) {
        this.certificate_container = certificate_container;
    }

    @Override
    public List<X509Certificate> getValue() {
        return certificate_container;
    }
}
