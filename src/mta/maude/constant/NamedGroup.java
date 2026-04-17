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
        return switch (this) {
            case SECP160K1 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP160K1;
            case SECP160R1 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP160R1;
            case SECP160R2 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP160R2;
            case SECP192K1 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP192K1;
            case SECP192R1 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP192R1;
            case SECP224K1 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP224K1;
            case SECP224R1 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP224R1;
            case SECP256K1 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP256K1;
            case SECP256R1 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP256R1;
            case SECP384R1 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP384R1;
            case SECP521R1 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP521R1;
            case FFDHE2048 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.FFDHE2048;
            case FFDHE3072 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.FFDHE3072;
            case FFDHE4096 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.FFDHE4096;
            case FFDHE6144 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.FFDHE6144;
            case FFDHE8192 -> de.rub.nds.tlsattacker.core.constants.NamedGroup.FFDHE8192;
            default -> throw new AssertionError();
        };
    }

    public String maudeTerm() {
        return switch (this) {
            case SECP160K1 -> "secp160k1";
            case SECP160R1 -> "secp160r1";
            case SECP160R2 -> "secp160r2";
            case SECP192K1 -> "secp192k1";
            case SECP192R1 -> "secp192r1";
            case SECP224K1 -> "secp224k1";
            case SECP224R1 -> "secp224r1";
            case SECP256K1 -> "secp256k1";
            case SECP256R1 -> "secp256r1";
            case SECP384R1 -> "secp384r1";
            case SECP521R1 -> "secp521r1";
            case FFDHE2048 -> "ffdhe2048";
            case FFDHE3072 -> "ffdhe3072";
            case FFDHE4096 -> "ffdhe4096";
            case FFDHE6144 -> "ffdhe6144";
            case FFDHE8192 -> "ffdhe8192";
            default -> throw new IllegalArgumentException("Unknown message size");
        };
    }
}
