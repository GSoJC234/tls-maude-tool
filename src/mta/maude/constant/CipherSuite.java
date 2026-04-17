package mta.maude.constant;

public enum CipherSuite {
    TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA,
    TLS_DHE_RSA_WITH_AES_256_CBC_SHA,
    TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
    TLS_DH_anon_WITH_AES_128_CBC_SHA,
    TLS_RSA_WITH_AES_256_CBC_SHA,
    TLS_RSA_WITH_AES_128_CBC_SHA,
    TLS_RSA_WITH_NULL_MD5,
    TLS_RSA_WITH_NULL_SHA,
    TLS_PSK_WITH_AES_256_CBC_SHA,
    TLS_PSK_WITH_AES_128_CBC_SHA256,
    TLS_PSK_WITH_AES_256_CBC_SHA384,
    TLS_PSK_WITH_AES_128_CBC_SHA,
    TLS_PSK_WITH_NULL_SHA256,
    TLS_PSK_WITH_NULL_SHA384,
    TLS_PSK_WITH_NULL_SHA,
    /* ECC suites, first byte is 0xC0 (ECC_BYTE) */
    TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
    TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
    TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
    TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
    TLS_ECDHE_RSA_WITH_RC4_128_SHA,
    TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
    TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA,
    TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA,
    TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,
    TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256,
    TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384,
    TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384,
    TLS_ECDHE_ECDSA_WITH_NULL_SHA,
    TLS_ECDHE_PSK_WITH_NULL_SHA256,
    TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256,
    /* static ECDH */
    TLS_ECDH_RSA_WITH_AES_256_CBC_SHA,
    TLS_ECDH_RSA_WITH_AES_128_CBC_SHA,
    TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA,
    TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA,
    TLS_ECDH_RSA_WITH_RC4_128_SHA,
    TLS_ECDH_ECDSA_WITH_RC4_128_SHA,
    TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA,
    TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA,
    TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256,
    TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256,
    TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384,
    TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384,
    /* SHA256 */
    TLS_DHE_RSA_WITH_AES_256_CBC_SHA256,
    TLS_DHE_RSA_WITH_AES_128_CBC_SHA256,
    TLS_RSA_WITH_AES_256_CBC_SHA256,
    TLS_RSA_WITH_AES_128_CBC_SHA256,
    TLS_RSA_WITH_NULL_SHA256,
    TLS_DHE_PSK_WITH_AES_128_CBC_SHA256,
    TLS_DHE_PSK_WITH_NULL_SHA256,
    /* SHA384 */
    TLS_DHE_PSK_WITH_AES_256_CBC_SHA384,
    TLS_DHE_PSK_WITH_NULL_SHA384,
    /* AES-GCM */
    TLS_RSA_WITH_AES_128_GCM_SHA256,
    TLS_RSA_WITH_AES_256_GCM_SHA384,
    TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
    TLS_DHE_RSA_WITH_AES_256_GCM_SHA384,
    TLS_DH_anon_WITH_AES_256_GCM_SHA384,
    TLS_PSK_WITH_AES_128_GCM_SHA256,
    TLS_PSK_WITH_AES_256_GCM_SHA384,
    TLS_DHE_PSK_WITH_AES_128_GCM_SHA256,
    TLS_DHE_PSK_WITH_AES_256_GCM_SHA384,
    /* ECC AES-GCM, first byte is 0xC0 (ECC_BYTE) */
    TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
    TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,
    TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256,
    TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384,
    TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
    TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
    TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256,
    TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384,
    /* AES-CCM, first byte is 0xC0 but isn't ECC,
     * also, in some of the other AES-CCM suites
     * there will be second byte number conflicts
     * with non-ECC AES-GCM */
    TLS_RSA_WITH_AES_128_CCM_8,
    TLS_RSA_WITH_AES_256_CCM_8,
    TLS_ECDHE_ECDSA_WITH_AES_128_CCM,
    TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8,
    TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8,
    TLS_PSK_WITH_AES_128_CCM,
    TLS_PSK_WITH_AES_256_CCM,
    TLS_PSK_WITH_AES_128_CCM_8,
    TLS_PSK_WITH_AES_256_CCM_8,
    TLS_DHE_PSK_WITH_AES_128_CCM,
    TLS_DHE_PSK_WITH_AES_256_CCM,
    TLS_AES_128_CCM_SHA256,
    TLS_AES_128_CCM_8_SHA256,
    TLS_AES_128_GCM_SHA256,
    TLS_AES_256_GCM_SHA384;

    public static String title(){
        return "CipherSuite";
    }

    public de.rub.nds.tlsattacker.core.constants.CipherSuite transform(){
        return switch (this) {
            case TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA;
            case TLS_DHE_RSA_WITH_AES_256_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA;
            case TLS_DHE_RSA_WITH_AES_128_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA;
            case TLS_DH_anon_WITH_AES_128_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA;
            case TLS_RSA_WITH_AES_256_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA;
            case TLS_RSA_WITH_AES_128_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA;
            case TLS_RSA_WITH_NULL_MD5 -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_NULL_MD5;
            case TLS_RSA_WITH_NULL_SHA -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_NULL_SHA;
            case TLS_PSK_WITH_AES_256_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA;
            case TLS_PSK_WITH_AES_128_CBC_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256;
            case TLS_PSK_WITH_AES_256_CBC_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384;
            case TLS_PSK_WITH_AES_128_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA;
            case TLS_PSK_WITH_NULL_SHA256 -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_NULL_SHA256;
            case TLS_PSK_WITH_NULL_SHA384 -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_NULL_SHA384;
            case TLS_PSK_WITH_NULL_SHA -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_NULL_SHA;
            case TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA;
            case TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA;
            case TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA;
            case TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA;
            case TLS_ECDHE_RSA_WITH_RC4_128_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA;
            case TLS_ECDHE_ECDSA_WITH_RC4_128_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA;
            case TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA;
            case TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA;
            case TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256;
            case TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256;
            case TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384;
            case TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384;
            case TLS_ECDHE_ECDSA_WITH_NULL_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA;
            case TLS_ECDHE_PSK_WITH_NULL_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256;
            case TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256;
            case TLS_ECDH_RSA_WITH_AES_256_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA;
            case TLS_ECDH_RSA_WITH_AES_128_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA;
            case TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA;
            case TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA;
            case TLS_ECDH_RSA_WITH_RC4_128_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA;
            case TLS_ECDH_ECDSA_WITH_RC4_128_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA;
            case TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA;
            case TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA;
            case TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256;
            case TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256;
            case TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384;
            case TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384;
            case TLS_DHE_RSA_WITH_AES_256_CBC_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256;
            case TLS_DHE_RSA_WITH_AES_128_CBC_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA256;
            case TLS_RSA_WITH_AES_256_CBC_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA256;
            case TLS_RSA_WITH_AES_128_CBC_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256;
            case TLS_RSA_WITH_NULL_SHA256 -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_NULL_SHA256;
            case TLS_DHE_PSK_WITH_AES_128_CBC_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256;
            case TLS_DHE_PSK_WITH_NULL_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
            case TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384;
            case TLS_DHE_PSK_WITH_NULL_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384;
            case TLS_RSA_WITH_AES_128_GCM_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256;
            case TLS_RSA_WITH_AES_256_GCM_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384;
            case TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256;
            case TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384;
            case TLS_DH_anon_WITH_AES_256_GCM_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384;
            case TLS_PSK_WITH_AES_128_GCM_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256;
            case TLS_PSK_WITH_AES_256_GCM_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384;
            case TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256;
            case TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384;
            case TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256;
            case TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384;
            case TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256;
            case TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384;
            case TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256;
            case TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384;
            case TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256;
            case TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384;
            case TLS_RSA_WITH_AES_128_CCM_8 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_128_CCM_8;
            case TLS_RSA_WITH_AES_256_CCM_8 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_256_CCM_8;
            case TLS_ECDHE_ECDSA_WITH_AES_128_CCM ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM;
            case TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8;
            case TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8;
            case TLS_PSK_WITH_AES_128_CCM -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_128_CCM;
            case TLS_PSK_WITH_AES_256_CCM -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_256_CCM;
            case TLS_PSK_WITH_AES_128_CCM_8 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_128_CCM_8;
            case TLS_PSK_WITH_AES_256_CCM_8 ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_256_CCM_8;
            case TLS_DHE_PSK_WITH_AES_128_CCM ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM;
            case TLS_DHE_PSK_WITH_AES_256_CCM ->
                    de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM;
            case TLS_AES_128_CCM_SHA256 -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_AES_128_CCM_SHA256;
            case TLS_AES_128_CCM_8_SHA256 -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_AES_128_CCM_8_SHA256;
            case TLS_AES_128_GCM_SHA256 -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_AES_128_GCM_SHA256;
            case TLS_AES_256_GCM_SHA384 -> de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_AES_256_GCM_SHA384;
            default -> throw new IllegalArgumentException("Unknown cipher suite: " + this);
        };
    }

    public String maudeTerm(){
        return switch (this) {
            case TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA -> "TLS-DHE-RSA-WITH-3DES-EDE-CBC-SHA";
            case TLS_DHE_RSA_WITH_AES_256_CBC_SHA -> "TLS-DHE-RSA-WITH-AES-256-CBC-SHA";
            case TLS_DHE_RSA_WITH_AES_128_CBC_SHA -> "TLS-DHE-RSA-WITH-AES-128-CBC-SHA";
            case TLS_DH_anon_WITH_AES_128_CBC_SHA -> "TLS-DH-anon-WITH-AES-128-CBC-SHA";
            case TLS_RSA_WITH_AES_256_CBC_SHA -> "TLS-RSA-WITH-AES-256-CBC-SHA";
            case TLS_RSA_WITH_AES_128_CBC_SHA -> "TLS-RSA-WITH-AES-128-CBC-SHA";
            case TLS_RSA_WITH_NULL_MD5 -> "TLS-RSA-WITH-NULL-MD5";
            case TLS_RSA_WITH_NULL_SHA -> "TLS-RSA-WITH-NULL-SHA";
            case TLS_PSK_WITH_AES_256_CBC_SHA -> "TLS-PSK-WITH-AES-256-CBC-SHA";
            case TLS_PSK_WITH_AES_128_CBC_SHA256 -> "TLS-PSK-WITH-AES-128-CBC-SHA256";
            case TLS_PSK_WITH_AES_256_CBC_SHA384 -> "TLS-PSK-WITH-AES-256-CBC-SHA384";
            case TLS_PSK_WITH_AES_128_CBC_SHA -> "TLS-PSK-WITH-AES-128-CBC-SHA";
            case TLS_PSK_WITH_NULL_SHA256 -> "TLS-PSK-WITH-NULL-SHA256";
            case TLS_PSK_WITH_NULL_SHA384 -> "TLS-PSK-WITH-NULL-SHA384";
            case TLS_PSK_WITH_NULL_SHA -> "TLS-PSK-WITH-NULL-SHA";
            case TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA -> "TLS-ECDHE-RSA-WITH-AES-256-CBC-SHA";
            case TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA -> "TLS-ECDHE-RSA-WITH-AES-128-CBC-SHA";
            case TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA -> "TLS-ECDHE-ECDSA-WITH-AES-256-CBC-SHA";
            case TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA -> "TLS-ECDHE-ECDSA-WITH-AES-128-CBC-SHA";
            case TLS_ECDHE_RSA_WITH_RC4_128_SHA -> "TLS-ECDHE-RSA-WITH-RC4-128-SHA";
            case TLS_ECDHE_ECDSA_WITH_RC4_128_SHA -> "TLS-ECDHE-ECDSA-WITH-RC4-128-SHA";
            case TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA -> "TLS-ECDHE-RSA-WITH-3DES-EDE-CBC-SHA";
            case TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA -> "TLS-ECDHE-ECDSA-WITH-3DES-EDE-CBC-SHA";
            case TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 -> "TLS-ECDHE-RSA-WITH-AES-128-CBC-SHA256";
            case TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 -> "TLS-ECDHE-ECDSA-WITH-AES-128-CBC-SHA256";
            case TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 -> "TLS-ECDHE-RSA-WITH-AES-256-CBC-SHA384";
            case TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 -> "TLS-ECDHE-ECDSA-WITH-AES-256-CBC-SHA384";
            case TLS_ECDHE_ECDSA_WITH_NULL_SHA -> "TLS-ECDHE-ECDSA-WITH-NULL-SHA";
            case TLS_ECDHE_PSK_WITH_NULL_SHA256 -> "TLS-ECDHE-PSK-WITH-NULL-SHA256";
            case TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 -> "TLS-ECDHE-PSK-WITH-AES-128-CBC-SHA256";
            case TLS_ECDH_RSA_WITH_AES_256_CBC_SHA -> "TLS-ECDH-RSA-WITH-AES-256-CBC-SHA";
            case TLS_ECDH_RSA_WITH_AES_128_CBC_SHA -> "TLS-ECDH-RSA-WITH-AES-128-CBC-SHA";
            case TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA -> "TLS-ECDH-ECDSA-WITH-AES-256-CBC-SHA";
            case TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA -> "TLS-ECDH-ECDSA-WITH-AES-128-CBC-SHA";
            case TLS_ECDH_RSA_WITH_RC4_128_SHA -> "TLS-ECDH-RSA-WITH-RC4-128-SHA";
            case TLS_ECDH_ECDSA_WITH_RC4_128_SHA -> "TLS-ECDH-ECDSA-WITH-RC4-128-SHA";
            case TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA -> "TLS-ECDH-RSA-WITH-3DES-EDE-CBC-SHA";
            case TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA -> "TLS-ECDH-ECDSA-WITH-3DES-EDE-CBC-SHA";
            case TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 -> "TLS-ECDH-RSA-WITH-AES-128-CBC-SHA256";
            case TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 -> "TLS-ECDH-ECDSA-WITH-AES-128-CBC-SHA256";
            case TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 -> "TLS-ECDH-RSA-WITH-AES-256-CBC-SHA384";
            case TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 -> "TLS-ECDH-ECDSA-WITH-AES-256-CBC-SHA384";
            case TLS_DHE_RSA_WITH_AES_256_CBC_SHA256 -> "TLS-DHE-RSA-WITH-AES-256-CBC-SHA256";
            case TLS_DHE_RSA_WITH_AES_128_CBC_SHA256 -> "TLS-DHE-RSA-WITH-AES-128-CBC-SHA256";
            case TLS_RSA_WITH_AES_256_CBC_SHA256 -> "TLS-RSA-WITH-AES-256-CBC-SHA256";
            case TLS_RSA_WITH_AES_128_CBC_SHA256 -> "TLS-RSA-WITH-AES-128-CBC-SHA256";
            case TLS_RSA_WITH_NULL_SHA256 -> "TLS-RSA-WITH-NULL-SHA256";
            case TLS_DHE_PSK_WITH_AES_128_CBC_SHA256 -> "TLS-DHE-PSK-WITH-AES-128-CBC-SHA256";
            case TLS_DHE_PSK_WITH_NULL_SHA256 -> "TLS-DHE-PSK-WITH-NULL-SHA256";
            case TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 -> "TLS-DHE-PSK-WITH-AES-256-CBC-SHA384";
            case TLS_DHE_PSK_WITH_NULL_SHA384 -> "TLS-DHE-PSK-WITH-NULL-SHA384";
            case TLS_RSA_WITH_AES_128_GCM_SHA256 -> "TLS-RSA-WITH-AES-128-GCM-SHA256";
            case TLS_RSA_WITH_AES_256_GCM_SHA384 -> "TLS-RSA-WITH-AES-256-GCM-SHA384";
            case TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 -> "TLS-DHE-RSA-WITH-AES-128-GCM-SHA256";
            case TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 -> "TLS-DHE-RSA-WITH-AES-256-GCM-SHA384";
            case TLS_DH_anon_WITH_AES_256_GCM_SHA384 -> "TLS-DH-anon-WITH-AES-256-GCM-SHA384";
            case TLS_PSK_WITH_AES_128_GCM_SHA256 -> "TLS-PSK-WITH-AES-128-GCM-SHA256";
            case TLS_PSK_WITH_AES_256_GCM_SHA384 -> "TLS-PSK-WITH-AES-256-GCM-SHA384";
            case TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 -> "TLS-DHE-PSK-WITH-AES-128-GCM-SHA256";
            case TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 -> "TLS-DHE-PSK-WITH-AES-256-GCM-SHA384";
            case TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 -> "TLS-ECDHE-ECDSA-WITH-AES-128-GCM-SHA256";
            case TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 -> "TLS-ECDHE-ECDSA-WITH-AES-256-GCM-SHA384";
            case TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 -> "TLS-ECDH-ECDSA-WITH-AES-128-GCM-SHA256";
            case TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 -> "TLS-ECDH-ECDSA-WITH-AES-256-GCM-SHA384";
            case TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 -> "TLS-ECDHE-RSA-WITH-AES-128-GCM-SHA256";
            case TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 -> "TLS-ECDHE-RSA-WITH-AES-256-GCM-SHA384";
            case TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 -> "TLS-ECDH-RSA-WITH-AES-128-GCM-SHA256";
            case TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 -> "TLS-ECDH-RSA-WITH-AES-256-GCM-SHA384";
            case TLS_RSA_WITH_AES_128_CCM_8 -> "TLS-RSA-WITH-AES-128-CCM-8";
            case TLS_RSA_WITH_AES_256_CCM_8 -> "TLS-RSA-WITH-AES-256-CCM-8";
            case TLS_ECDHE_ECDSA_WITH_AES_128_CCM -> "TLS-ECDHE-ECDSA-WITH-AES-128-CCM";
            case TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 -> "TLS-ECDHE-ECDSA-WITH-AES-128-CCM-8";
            case TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 -> "TLS-ECDHE-ECDSA-WITH-AES-256-CCM-8";
            case TLS_PSK_WITH_AES_128_CCM -> "TLS-PSK-WITH-AES-128-CCM";
            case TLS_PSK_WITH_AES_256_CCM -> "TLS-PSK-WITH-AES-256-CCM";
            case TLS_PSK_WITH_AES_128_CCM_8 -> "TLS-PSK-WITH-AES-128-CCM-8";
            case TLS_PSK_WITH_AES_256_CCM_8 -> "TLS-PSK-WITH-AES-256-CCM-8";
            case TLS_DHE_PSK_WITH_AES_128_CCM -> "TLS-DHE-PSK-WITH-AES-128-CCM";
            case TLS_DHE_PSK_WITH_AES_256_CCM -> "TLS-DHE-PSK-WITH-AES-256-CCM";
            case TLS_AES_128_CCM_SHA256 -> "TLS-AES-128-CCM-SHA256";
            case TLS_AES_128_CCM_8_SHA256 -> "TLS-AES-128-CCM-8-SHA256";
            case TLS_AES_128_GCM_SHA256 -> "TLS-AES-128-GCM-SHA256";
            case TLS_AES_256_GCM_SHA384 -> "TLS-AES-256-GCM-SHA384";
            default -> throw new IllegalArgumentException("Unknown cipher suite: " + this);
        };
    }
}