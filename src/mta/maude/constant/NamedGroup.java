package mta.maude.constant;

public enum NamedGroup {
    NONE,
    SECP160K1,
    SECP160R1,
    SECP160R2,
    SECP192K1,
    SECP192R1,
    SECP224K1,
    SECP224R1,
    SECP256K1,
    SECP256R1,
    SECP384R1,
    SECP521R1,
    FFDHE2048,
    FFDHE3072,
    FFDHE4096,
    FFDHE6144,
    FFDHE8192;

    public static String title(){
        return "NamedGroup";
    }

    public de.rub.nds.tlsattacker.core.constants.NamedGroup transform(){
        switch (this){
            case SECP160K1: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP160K1;
            case SECP160R1: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP160R1;
            case SECP160R2: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP160R2;
            case SECP192K1: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP192K1;
            case SECP192R1: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP192R1;
            case SECP224K1: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP224K1;
            case SECP224R1: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP224R1;
            case SECP256K1: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP256K1;
            case SECP256R1: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP256R1;
            case SECP384R1: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP384R1;
            case SECP521R1: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP521R1;
            case FFDHE2048: return de.rub.nds.tlsattacker.core.constants.NamedGroup.FFDHE2048;
            case FFDHE3072: return de.rub.nds.tlsattacker.core.constants.NamedGroup.FFDHE3072;
            case FFDHE4096: return de.rub.nds.tlsattacker.core.constants.NamedGroup.FFDHE4096;
            case FFDHE6144: return de.rub.nds.tlsattacker.core.constants.NamedGroup.FFDHE6144;
            case FFDHE8192: return de.rub.nds.tlsattacker.core.constants.NamedGroup.FFDHE8192;
            default: throw new AssertionError();
        }
    }
}
