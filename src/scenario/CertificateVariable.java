package scenario;

import Protocol.Variable;
import de.rub.nds.tlsattacker.core.protocol.message.cert.CertificateEntry;
import de.rub.nds.x509attacker.x509.model.X509Certificate;

import java.util.List;

public class CertificateVariable implements Variable {

    private List<CertificateEntry> certificate_container = null;
    public CertificateVariable(List<CertificateEntry> certificate_container) {
        this.certificate_container = certificate_container;
    }

    @Override
    public List<CertificateEntry> getValue() {
        return certificate_container;
    }
}
