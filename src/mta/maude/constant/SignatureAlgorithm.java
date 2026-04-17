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
        return switch (this) {
            case RSA_NONE -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_NONE;
            case RSA_MD5 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_MD5;
            case RSA_SHA1 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA1;
            case RSA_SHA224 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA224;
            case RSA_SHA256 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA256;
            case RSA_SHA384 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA384;
            case RSA_SHA512 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_SHA512;
            case RSA_PSS_PSS_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_PSS_SHA256;
            case RSA_PSS_PSS_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_PSS_SHA384;
            case RSA_PSS_PSS_SHA512 ->
                    de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_PSS_SHA512;
            case RSA_PSS_RSAE_SHA256 ->
                    de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_RSAE_SHA256;
            case RSA_PSS_RSAE_SHA384 ->
                    de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_RSAE_SHA384;
            case RSA_PSS_RSAE_SHA512 ->
                    de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.RSA_PSS_RSAE_SHA512;
            case DSA_NONE -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_NONE;
            case DSA_MD5 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_MD5;
            case DSA_SHA1 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_SHA1;
            case DSA_SHA224 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_SHA224;
            case DSA_SHA256 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_SHA256;
            case DSA_SHA384 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_SHA384;
            case DSA_SHA512 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.DSA_SHA512;
            case ECDSA_NONE -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_NONE;
            case ECDSA_MD5 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_MD5;
            case ECDSA_SHA1 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_SHA1;
            case ECDSA_SHA224 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_SHA224;
            case ECDSA_SHA256 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_SHA256;
            case ECDSA_SHA384 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_SHA384;
            case ECDSA_SHA512 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ECDSA_SHA512;
            case ANON_SHA -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_SHA1;
            case ANON_SHA224 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_SHA224;
            case ANON_SHA256 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_SHA256;
            case ANON_SHA384 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_SHA384;
            case ANON_SHA512 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_SHA512;
            case ANON_MD5 -> de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm.ANONYMOUS_MD5;
            default -> throw new IllegalArgumentException("Unknown Signature Algorithms : " + this);
        };
    }

    public static SignatureAlgorithm transform(de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm signatureAndHashAlgorithm){
        return switch (signatureAndHashAlgorithm) {
            case RSA_NONE -> SignatureAlgorithm.RSA_NONE;
            case RSA_MD5 -> SignatureAlgorithm.RSA_MD5;
            case RSA_SHA1 -> SignatureAlgorithm.RSA_SHA1;
            case RSA_SHA224 -> SignatureAlgorithm.RSA_SHA224;
            case RSA_SHA256 -> SignatureAlgorithm.RSA_SHA256;
            case RSA_SHA384 -> SignatureAlgorithm.RSA_SHA384;
            case RSA_SHA512 -> SignatureAlgorithm.RSA_SHA512;
            case RSA_PSS_PSS_SHA256 -> SignatureAlgorithm.RSA_PSS_PSS_SHA256;
            case RSA_PSS_PSS_SHA384 -> SignatureAlgorithm.RSA_PSS_PSS_SHA384;
            case RSA_PSS_PSS_SHA512 -> SignatureAlgorithm.RSA_PSS_PSS_SHA512;
            case RSA_PSS_RSAE_SHA256 -> SignatureAlgorithm.RSA_PSS_RSAE_SHA256;
            case RSA_PSS_RSAE_SHA384 -> SignatureAlgorithm.RSA_PSS_RSAE_SHA384;
            case RSA_PSS_RSAE_SHA512 -> SignatureAlgorithm.RSA_PSS_RSAE_SHA512;
            case DSA_NONE -> SignatureAlgorithm.DSA_NONE;
            case DSA_MD5 -> SignatureAlgorithm.DSA_MD5;
            case DSA_SHA1 -> SignatureAlgorithm.DSA_SHA1;
            case DSA_SHA224 -> SignatureAlgorithm.DSA_SHA224;
            case DSA_SHA256 -> SignatureAlgorithm.DSA_SHA256;
            case DSA_SHA384 -> SignatureAlgorithm.DSA_SHA384;
            case DSA_SHA512 -> SignatureAlgorithm.DSA_SHA512;
            case ECDSA_NONE -> SignatureAlgorithm.ECDSA_NONE;
            case ECDSA_MD5 -> SignatureAlgorithm.ECDSA_MD5;
            case ECDSA_SHA1 -> SignatureAlgorithm.ECDSA_SHA1;
            case ECDSA_SHA224 -> SignatureAlgorithm.ECDSA_SHA224;
            case ECDSA_SHA256 -> SignatureAlgorithm.ECDSA_SHA256;
            case ECDSA_SHA384 -> SignatureAlgorithm.ECDSA_SHA384;
            case ECDSA_SHA512 -> SignatureAlgorithm.ECDSA_SHA512;
            case ANONYMOUS_SHA1 -> SignatureAlgorithm.ANON_SHA;
            case ANONYMOUS_SHA224 -> SignatureAlgorithm.ANON_SHA224;
            case ANONYMOUS_SHA384 -> SignatureAlgorithm.ANON_SHA384;
            case ANONYMOUS_SHA512 -> SignatureAlgorithm.ANON_SHA512;
            case ANONYMOUS_MD5 -> SignatureAlgorithm.ANON_MD5;
            default -> throw new IllegalArgumentException("Unknown Signature Algorithms : " + signatureAndHashAlgorithm);
        };
    }
    public String maudeTerm(){
        return switch (this) {
            case RSA_MD5 -> "{rsa,md5}";
            case RSA_SHA1 -> "{rsa,sha1}";
            case RSA_SHA224 -> "{rsa,sha224}";
            case RSA_SHA256 -> "{rsa,sha256}";
            case RSA_SHA384 -> "{rsa,sha384}";
            case RSA_SHA512 -> "{rsa,sha512}";
            case DSA_MD5 -> "{dsa,md5}";
            case DSA_SHA1 -> "{dsa,sha1}";
            case DSA_SHA224 -> "{dsa,sha224}";
            case DSA_SHA256 -> "{dsa,sha256}";
            case DSA_SHA384 -> "{dsa,sha384}";
            case DSA_SHA512 -> "{dsa,sha512}";
            case ECDSA_MD5 -> "{ecdsa,md5}";
            case ECDSA_SHA1 -> "{ecdsa,sha1}";
            case ECDSA_SHA224 -> "{ecdsa,sha224}";
            case ECDSA_SHA256 -> "{ecdsa,sha256}";
            case ECDSA_SHA384 -> "{ecdsa,sha384}";
            case ECDSA_SHA512 -> "{ecdsa,sha512}";
            case ANON_MD5 -> "{anon,md5}";
            case ANON_SHA -> "{anon,sha1}";
            case ANON_SHA224 -> "{anon,sha224}";
            case ANON_SHA256 -> "{anon,sha256}";
            case ANON_SHA384 -> "{anon,sha384}";
            case ANON_SHA512 -> "{anon,sha512}";
            default -> throw new IllegalArgumentException("Unknown Signature Algorithms : " + this);
        };
    }
    
}
