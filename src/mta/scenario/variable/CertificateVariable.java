package mta.scenario.variable;

import mta.protocol.Variable;
import de.rub.nds.tlsattacker.core.protocol.message.cert.CertificateEntry;

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
