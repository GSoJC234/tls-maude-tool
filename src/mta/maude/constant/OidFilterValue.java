package mta.maude.constant;

public sealed interface OidFilterValue permits ExtendedKeyUsageValue, KeyUsageValue {
    CertificateExtensionOid oid();
}
