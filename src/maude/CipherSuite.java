package maude;

public enum CipherSuite {
    TLS_ECDHE_ECDSA_WITH_AES_128_CCM,
    TLS_ECDHE_ECDSA_WITH_AES_256_CCM;

    public de.rub.nds.tlsattacker.core.constants.CipherSuite transform(){
        switch(this){
            case TLS_ECDHE_ECDSA_WITH_AES_128_CCM: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM;
            case TLS_ECDHE_ECDSA_WITH_AES_256_CCM: return de.rub.nds.tlsattacker.core.constants.CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM;
            default: throw new IllegalArgumentException("Unknown cipher suite: " + this);
        }
    }
}
