package mta.maude.constant;

public enum CertificateExtensionOid {
    KEY_USAGE("2.5.29.15"),
    EXTENDED_KEY_USAGE("2.5.29.37");

    private final String dottedDecimal;

    CertificateExtensionOid(String dottedDecimal) {
        this.dottedDecimal = dottedDecimal;
    }

    public String dottedDecimal() {
        return dottedDecimal;
    }
}
