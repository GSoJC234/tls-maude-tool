package mta.maude.constant;

public enum CompressionMethod {
    NO_COMPRESSION,
    DEFLATE,
    NULL,
    LZS;

    public static String title(){
        return "CompressionMethod";
    }

    public de.rub.nds.tlsattacker.core.constants.CompressionMethod transform() {
        switch (this) {
            case LZS: return de.rub.nds.tlsattacker.core.constants.CompressionMethod.LZS;
            case DEFLATE: return de.rub.nds.tlsattacker.core.constants.CompressionMethod.DEFLATE;
            case NULL: return de.rub.nds.tlsattacker.core.constants.CompressionMethod.NULL;
            case NO_COMPRESSION: return de.rub.nds.tlsattacker.core.constants.CompressionMethod.NULL;
            default: throw new IllegalArgumentException("Unknown compression method: " + this);
        }
    }
}
