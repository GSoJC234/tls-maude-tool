package mta.maude.constant;

import java.util.List;

public final class OidFilterSpec {
    private final CertificateExtensionOid oid;
    private final List<OidFilterValue> values;

    public OidFilterSpec(CertificateExtensionOid oid, OidFilterValue... values) {
        if (oid == null || values == null || values.length == 0) {
            throw new IllegalArgumentException("OID filter requires an OID and at least one value");
        }
        this.oid = oid;
        this.values = List.of(values);
        for (OidFilterValue value : this.values) {
            if (value.oid() != oid) {
                throw new IllegalArgumentException("OID filter value " + value + " does not belong to " + oid);
            }
        }
    }

    public CertificateExtensionOid oid() {
        return oid;
    }

    public List<OidFilterValue> values() {
        return values;
    }
}
