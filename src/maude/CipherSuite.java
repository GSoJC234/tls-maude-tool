package maude;

public enum CipherSuite {
    // TLS 1.2
    TLS_ECDHE_ECDSA_WITH_AES_128_CCM,
    TLS_ECDHE_ECDSA_WITH_AES_256_CCM,
    TLS_RSA_WITH_AES_128_CBC_SHA_256,

    // TLS 1.3
    TLS_AES_128_CCM_SHA256;

    public de.rub.nds.tlsattacker.core.constants.CipherSuite transform(){
        switch(this){
            case TLS_ECDHE_ECDSA_WITH_AES_128_CCM: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM;
            case TLS_ECDHE_ECDSA_WITH_AES_256_CCM: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM;
            case TLS_AES_128_CCM_SHA256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_AES_128_CCM_SHA256;
            case TLS_RSA_WITH_AES_128_CBC_SHA_256: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256;
            default: throw new IllegalArgumentException("Unknown cipher suite: " + this);
        }
    }
}
