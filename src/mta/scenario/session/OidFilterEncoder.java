package mta.scenario.session;

import java.io.IOException;
import mta.maude.constant.ExtendedKeyUsageValue;
import mta.maude.constant.KeyUsageValue;
import mta.maude.constant.OidFilterSpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;

/** Converts semantic certificate extension values from a Maude term into DER. */
final class OidFilterEncoder {
    private OidFilterEncoder() {}

    static byte[] encodeValue(OidFilterSpec filter) {
        try {
            return switch (filter.oid()) {
                case EXTENDED_KEY_USAGE -> encodeExtendedKeyUsage(filter);
                case KEY_USAGE -> encodeKeyUsage(filter);
            };
        } catch (IOException e) {
            throw new IllegalStateException("Cannot encode OID filter value", e);
        }
    }

    private static byte[] encodeExtendedKeyUsage(OidFilterSpec filter) throws IOException {
        ASN1Encodable[] purposes = filter.values().stream()
                .map(value -> new ASN1ObjectIdentifier(((ExtendedKeyUsageValue) value).purposeOid()))
                .toArray(ASN1Encodable[]::new);
        return new DERSequence(purposes).getEncoded(ASN1Encoding.DER);
    }

    private static byte[] encodeKeyUsage(OidFilterSpec filter) throws IOException {
        int highestBit = filter.values().stream()
                .mapToInt(value -> ((KeyUsageValue) value).bitIndex())
                .max().orElseThrow();
        byte[] bits = new byte[highestBit / 8 + 1];
        for (var value : filter.values()) {
            int bitIndex = ((KeyUsageValue) value).bitIndex();
            bits[bitIndex / 8] |= (byte) (0x80 >>> (bitIndex % 8));
        }
        int unusedBits = bits.length * 8 - highestBit - 1;
        return new DERBitString(bits, unusedBits).getEncoded(ASN1Encoding.DER);
    }
}
