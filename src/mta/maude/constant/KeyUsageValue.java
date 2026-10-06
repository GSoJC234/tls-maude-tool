package mta.maude.constant;

public enum KeyUsageValue implements OidFilterValue {
    DIGITAL_SIGNATURE(0),
    CONTENT_COMMITMENT(1),
    KEY_ENCIPHERMENT(2),
    DATA_ENCIPHERMENT(3),
    KEY_AGREEMENT(4),
    KEY_CERT_SIGN(5),
    CRL_SIGN(6),
    ENCIPHER_ONLY(7),
    DECIPHER_ONLY(8);

    private final int bitIndex;

    KeyUsageValue(int bitIndex) {
        this.bitIndex = bitIndex;
    }

    @Override
    public CertificateExtensionOid oid() {
        return CertificateExtensionOid.KEY_USAGE;
    }

    public int bitIndex() {
        return bitIndex;
    }
}
