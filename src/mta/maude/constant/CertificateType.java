package mta.maude.constant;

public enum CertificateType {
    RSA_SIGN, DSS_SIGN, RSA_FIXED_DH, DSS_FIXED_DH, ECDSA_SIGN, RSA_FIXED_ECDH, ECDSA_FIXED_ECDH;

    public static String title(){
        return "CertificateType";
    }

    public de.rub.nds.tlsattacker.core.constants.ClientCertificateType transform(){
        switch(this){
            case RSA_SIGN: return de.rub.nds.tlsattacker.core.constants.ClientCertificateType.RSA_SIGN;
            case DSS_SIGN: return de.rub.nds.tlsattacker.core.constants.ClientCertificateType.DSS_SIGN;
            case RSA_FIXED_DH: return de.rub.nds.tlsattacker.core.constants.ClientCertificateType.RSA_FIXED_DH;
            case DSS_FIXED_DH: return de.rub.nds.tlsattacker.core.constants.ClientCertificateType.DSS_FIXED_DH;
            case ECDSA_SIGN: return de.rub.nds.tlsattacker.core.constants.ClientCertificateType.ECDSA_SIGN;
            case RSA_FIXED_ECDH: return de.rub.nds.tlsattacker.core.constants.ClientCertificateType.RSA_FIXED_ECDH;
            case ECDSA_FIXED_ECDH: return de.rub.nds.tlsattacker.core.constants.ClientCertificateType.ECDSA_FIXED_ECDH;
            default: throw new IllegalArgumentException("Unknown CertificateType: " + this);
        }
    }
}
