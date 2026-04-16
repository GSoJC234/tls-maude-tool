package mta.maude.constant;

public enum SignatureAlgorithm {
    NONE,
    RSA_NONE,
    RSA_MD5,
    RSA_SHA1,
    RSA_SHA224,
    RSA_SHA256,
    RSA_SHA384,
    RSA_SHA512,
    RSA_PSS_RSAE_SHA256,
    RSA_PSS_RSAE_SHA384,
    RSA_PSS_RSAE_SHA512,
    RSA_PSS_PSS_SHA256,
    RSA_PSS_PSS_SHA384,
    RSA_PSS_PSS_SHA512,
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
    ECDSA_SHA512,
    ANON_SHA,
    ANON_SHA224,
    ANON_SHA256,
    ANON_SHA384,
    ANON_SHA512,
    ANON_MD5;

    public static String title(){
        return "SignatureAndHashAlgorithm";
    }

    public de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm transform(){
        switch (this){
            case RSA_NONE: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_NONE;
            case RSA_MD5: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_MD5;
            case RSA_SHA1: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA1;
            case RSA_SHA224: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA224;
            case RSA_SHA256: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA256;
            case RSA_SHA384: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA384;
            case RSA_SHA512: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA512;
            case RSA_PSS_PSS_SHA256: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_PSS_SHA256;
            case RSA_PSS_PSS_SHA384: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_PSS_SHA384;
            case RSA_PSS_PSS_SHA512: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_PSS_SHA512;
            case RSA_PSS_RSAE_SHA256: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_RSAE_SHA256;
            case RSA_PSS_RSAE_SHA384: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_RSAE_SHA384;
            case RSA_PSS_RSAE_SHA512: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_RSAE_SHA512;
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
            case ANON_SHA: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_SHA1;
            case ANON_SHA224: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_SHA224;
            case ANON_SHA256: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_SHA256;
            case ANON_SHA384: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_SHA384;
            case ANON_SHA512: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_SHA512;
            case ANON_MD5: return de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_MD5;
            default: throw new AssertionError();
        }
    }

    public static SignatureAlgorithm transform(de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm signatureAndHashAlgorithm){
        switch (signatureAndHashAlgorithm){
            case RSA_NONE: return SignatureAlgorithm.RSA_NONE;
            case RSA_MD5: return SignatureAlgorithm.RSA_MD5;
            case RSA_SHA1: return SignatureAlgorithm.RSA_SHA1;
            case RSA_SHA224: return SignatureAlgorithm.RSA_SHA224;
            case RSA_SHA256: return SignatureAlgorithm.RSA_SHA256;
            case RSA_SHA384: return SignatureAlgorithm.RSA_SHA384;
            case RSA_SHA512: return SignatureAlgorithm.RSA_SHA512;
            case RSA_PSS_PSS_SHA256: return SignatureAlgorithm.RSA_PSS_PSS_SHA256;
            case RSA_PSS_PSS_SHA384: return SignatureAlgorithm.RSA_PSS_PSS_SHA384;
            case RSA_PSS_PSS_SHA512: return SignatureAlgorithm.RSA_PSS_PSS_SHA512;
            case RSA_PSS_RSAE_SHA256: return SignatureAlgorithm.RSA_PSS_RSAE_SHA256;
            case RSA_PSS_RSAE_SHA384: return SignatureAlgorithm.RSA_PSS_RSAE_SHA384;
            case RSA_PSS_RSAE_SHA512: return SignatureAlgorithm.RSA_PSS_RSAE_SHA512;
            case DSA_NONE: return SignatureAlgorithm.DSA_NONE;
            case DSA_MD5: return SignatureAlgorithm.DSA_MD5;
            case DSA_SHA1: return SignatureAlgorithm.DSA_SHA1;
            case DSA_SHA224: return SignatureAlgorithm.DSA_SHA224;
            case DSA_SHA256: return SignatureAlgorithm.DSA_SHA256;
            case DSA_SHA384: return SignatureAlgorithm.DSA_SHA384;
            case DSA_SHA512: return SignatureAlgorithm.DSA_SHA512;
            case ECDSA_NONE: return SignatureAlgorithm.ECDSA_NONE;
            case ECDSA_MD5: return SignatureAlgorithm.ECDSA_MD5;
            case ECDSA_SHA1: return SignatureAlgorithm.ECDSA_SHA1;
            case ECDSA_SHA224: return SignatureAlgorithm.ECDSA_SHA224;
            case ECDSA_SHA256: return SignatureAlgorithm.ECDSA_SHA256;
            case ECDSA_SHA384: return SignatureAlgorithm.ECDSA_SHA384;
            case ECDSA_SHA512: return SignatureAlgorithm.ECDSA_SHA512;
            case ANONYMOUS_SHA1: return SignatureAlgorithm.ANON_SHA;
            case ANONYMOUS_SHA224: return SignatureAlgorithm.ANON_SHA224;
            case ANONYMOUS_SHA384: return SignatureAlgorithm.ANON_SHA384;
            case ANONYMOUS_SHA512: return SignatureAlgorithm.ANON_SHA512;
            case ANONYMOUS_MD5: return SignatureAlgorithm.ANON_MD5;
            default: throw new AssertionError();
        }
    }
}
