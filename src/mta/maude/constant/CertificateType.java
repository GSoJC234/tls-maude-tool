package mta.maude.constant;

public enum CertificateType {
    RSA_SIGN, DSS_SIGN, RSA_FIXED_DH, DSS_FIXED_DH, ECDSA_SIGN, RSA_FIXED_ECDH, ECDSA_FIXED_ECDH;

    public static String title(){
        return "CertificateType";
    }

    public de.rub.nds.tlsattacker.core.constants.ClientCertificateType transform(){
        return switch (this) {
            case RSA_SIGN -> de.rub.nds.tlsattacker.core.constants.ClientCertificateType.RSA_SIGN;
            case DSS_SIGN -> de.rub.nds.tlsattacker.core.constants.ClientCertificateType.DSS_SIGN;
            case RSA_FIXED_DH -> de.rub.nds.tlsattacker.core.constants.ClientCertificateType.RSA_FIXED_DH;
            case DSS_FIXED_DH -> de.rub.nds.tlsattacker.core.constants.ClientCertificateType.DSS_FIXED_DH;
            case ECDSA_SIGN -> de.rub.nds.tlsattacker.core.constants.ClientCertificateType.ECDSA_SIGN;
            case RSA_FIXED_ECDH -> de.rub.nds.tlsattacker.core.constants.ClientCertificateType.RSA_FIXED_ECDH;
            case ECDSA_FIXED_ECDH -> de.rub.nds.tlsattacker.core.constants.ClientCertificateType.ECDSA_FIXED_ECDH;
            default -> throw new IllegalArgumentException("Unknown CertificateType: " + this);
        };
    }

    public String maudeTerm() {
        return switch (this) {
            case RSA_SIGN -> "rsa-sign";
            case DSS_SIGN -> "dsa-sign";
            case RSA_FIXED_DH -> "rsa-fixed-dh";
            case DSS_FIXED_DH -> "dss-fixed-dh";
            case ECDSA_SIGN -> "ecdsa-sign";
            case RSA_FIXED_ECDH -> "rsa-fixed-ecdh";
            case ECDSA_FIXED_ECDH -> "ecdsa-fixed-ecdh";
            default -> throw new IllegalArgumentException("Unknown CertificateType: " + this);
        };
    }
}
