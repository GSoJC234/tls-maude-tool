package maude;

public enum SignatureAndHashAlgorithm {
    NONE,
    RSA_NONE,
    RSA_MD5,
    RSA_SHA1,
    RSA_SHA224,
    RSA_SHA256,
    RSA_SHA384,
    RSA_SHA512,
    DSA_NONE,
    DSA_MD5,
    DSA_SHA1,
    DSA_SHA224,
    DSA_SHA256,
    DSA_SHA384,
    DSA_SHA512,
    ECDSA_NONE,
    ECDSA_MD5,
    ECDSA_SHA1,
    ECDSA_SHA224,
    ECDSA_SHA256,
    ECDSA_SHA384,
    ECDSA_SHA512;

    public de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm transform(){
        switch (this){
            case RSA_NONE: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_NONE;
            case RSA_MD5: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_MD5;
            case RSA_SHA1: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA1;
            case RSA_SHA224: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA224;
            case RSA_SHA256: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA256;
            case RSA_SHA384: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA384;
            case RSA_SHA512: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA512;
            case DSA_NONE: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_NONE;
            case DSA_MD5: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_MD5;
            case DSA_SHA1: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_SHA1;
            case DSA_SHA224: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_SHA224;
            case DSA_SHA256: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_SHA256;
            case DSA_SHA384: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_SHA384;
            case DSA_SHA512: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_SHA512;
            case ECDSA_NONE: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_NONE;
            case ECDSA_MD5: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_MD5;
            case ECDSA_SHA1: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_SHA1;
            case ECDSA_SHA224: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_SHA224;
            case ECDSA_SHA256: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_SHA256;
            case ECDSA_SHA384: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_SHA384;
            case ECDSA_SHA512: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_SHA512;
            default: throw new AssertionError();
        }
    }

    public static maude.SignatureAndHashAlgorithm transform(de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm signatureAndHashAlgorithm){
        switch (signatureAndHashAlgorithm){
            case RSA_NONE: return maude.SignatureAndHashAlgorithm.RSA_NONE;
            case RSA_MD5: return maude.SignatureAndHashAlgorithm.RSA_MD5;
            case RSA_SHA1: return maude.SignatureAndHashAlgorithm.RSA_SHA1;
            case RSA_SHA224: return maude.SignatureAndHashAlgorithm.RSA_SHA224;
            case RSA_SHA256: return maude.SignatureAndHashAlgorithm.RSA_SHA256;
            case RSA_SHA384: return maude.SignatureAndHashAlgorithm.RSA_SHA384;
            case RSA_SHA512: return maude.SignatureAndHashAlgorithm.RSA_SHA512;
            case DSA_NONE: return maude.SignatureAndHashAlgorithm.DSA_NONE;
            case DSA_MD5: return maude.SignatureAndHashAlgorithm.DSA_MD5;
            case DSA_SHA1: return maude.SignatureAndHashAlgorithm.DSA_SHA1;
            case DSA_SHA224: return maude.SignatureAndHashAlgorithm.DSA_SHA224;
            case DSA_SHA256: return maude.SignatureAndHashAlgorithm.DSA_SHA256;
            case DSA_SHA384: return maude.SignatureAndHashAlgorithm.DSA_SHA384;
            case DSA_SHA512: return maude.SignatureAndHashAlgorithm.DSA_SHA512;
            case ECDSA_NONE: return maude.SignatureAndHashAlgorithm.ECDSA_NONE;
            case ECDSA_MD5: return maude.SignatureAndHashAlgorithm.ECDSA_MD5;
            case ECDSA_SHA1: return maude.SignatureAndHashAlgorithm.ECDSA_SHA1;
            case ECDSA_SHA224: return maude.SignatureAndHashAlgorithm.ECDSA_SHA224;
            case ECDSA_SHA256: return maude.SignatureAndHashAlgorithm.ECDSA_SHA256;
            case ECDSA_SHA384: return maude.SignatureAndHashAlgorithm.ECDSA_SHA384;
            case ECDSA_SHA512: return maude.SignatureAndHashAlgorithm.ECDSA_SHA512;
            default: throw new AssertionError();
        }
    }
}
