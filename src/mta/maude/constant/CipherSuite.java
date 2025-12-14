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
        switch(this){
            case TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA;
            case TLS_DHE_RSA_WITH_AES_256_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA;
            case TLS_DHE_RSA_WITH_AES_128_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA;
            case TLS_DH_anon_WITH_AES_128_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA;
            case TLS_RSA_WITH_AES_256_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA;
            case TLS_RSA_WITH_AES_128_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA;
            case TLS_RSA_WITH_NULL_MD5: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_NULL_MD5;
            case TLS_RSA_WITH_NULL_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_NULL_SHA;
            case TLS_PSK_WITH_AES_256_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA;
            case TLS_PSK_WITH_AES_128_CBC_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA;
            case TLS_PSK_WITH_AES_256_CBC_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384;
            case TLS_PSK_WITH_AES_128_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA;
            case TLS_PSK_WITH_NULL_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_NULL_SHA256;
            case TLS_PSK_WITH_NULL_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_NULL_SHA384;
            case TLS_PSK_WITH_NULL_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_NULL_SHA;
            case TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA;
            case TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA;
            case TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA;
            case TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA;
            case TLS_ECDHE_RSA_WITH_RC4_128_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA;
            case TLS_ECDHE_ECDSA_WITH_RC4_128_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA;
            case TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA;
            case TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA;
            case TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256;
            case TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256;
            case TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384;
            case TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384;
            case TLS_ECDHE_ECDSA_WITH_NULL_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA;
            case TLS_ECDHE_PSK_WITH_NULL_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256;
            case TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256;
            case TLS_ECDH_RSA_WITH_AES_256_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA;
            case TLS_ECDH_RSA_WITH_AES_128_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA;
            case TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA;
            case TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA;
            case TLS_ECDH_RSA_WITH_RC4_128_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA;
            case TLS_ECDH_ECDSA_WITH_RC4_128_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA;
            case TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA;
            case TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA;
            case TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256;
            case TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256;
            case TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384;
            case TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384;
            case TLS_DHE_RSA_WITH_AES_256_CBC_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256;
            case TLS_DHE_RSA_WITH_AES_128_CBC_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA256;
            case TLS_RSA_WITH_AES_256_CBC_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA256;
            case TLS_RSA_WITH_AES_128_CBC_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256;
            case TLS_RSA_WITH_NULL_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_NULL_SHA256;
            case TLS_DHE_PSK_WITH_AES_128_CBC_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256;
            case TLS_DHE_PSK_WITH_NULL_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
            case TLS_DHE_PSK_WITH_AES_256_CBC_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384;
            case TLS_DHE_PSK_WITH_NULL_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384;
            case TLS_RSA_WITH_AES_128_GCM_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256;
            case TLS_RSA_WITH_AES_256_GCM_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384;
            case TLS_DHE_RSA_WITH_AES_128_GCM_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256;
            case TLS_DHE_RSA_WITH_AES_256_GCM_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384;
            case TLS_DH_anon_WITH_AES_256_GCM_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384;
            case TLS_PSK_WITH_AES_128_GCM_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256;
            case TLS_PSK_WITH_AES_256_GCM_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384;
            case TLS_DHE_PSK_WITH_AES_128_GCM_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256;
            case TLS_DHE_PSK_WITH_AES_256_GCM_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384;
            case TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256;
            case TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384;
            case TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256;
            case TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384;
            case TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256;
            case TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384;
            case TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256;
            case TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384;
            case TLS_RSA_WITH_AES_128_CCM_8: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_128_CCM_8;
            case TLS_RSA_WITH_AES_256_CCM_8: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_256_CCM_8;
            case TLS_ECDHE_ECDSA_WITH_AES_128_CCM: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM;
            case TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8;
            case TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8;
            case TLS_PSK_WITH_AES_128_CCM: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_128_CCM;
            case TLS_PSK_WITH_AES_256_CCM: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_256_CCM;
            case TLS_PSK_WITH_AES_128_CCM_8: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_128_CCM_8;
            case TLS_PSK_WITH_AES_256_CCM_8: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_PSK_WITH_AES_256_CCM_8;
            case TLS_DHE_PSK_WITH_AES_128_CCM: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM;
            case TLS_DHE_PSK_WITH_AES_256_CCM: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM;
            case TLS_AES_128_CCM_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_AES_128_CCM_SHA256;
            case TLS_AES_128_CCM_8_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_AES_128_CCM_8_SHA256;
            case TLS_AES_128_GCM_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_AES_128_GCM_SHA256;
            case TLS_AES_256_GCM_SHA384: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_AES_256_GCM_SHA384;
            default: throw new IllegalArgumentException("Unknown cipher suite: " + this);
        }
    }
}