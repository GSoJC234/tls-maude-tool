package mta.maude.constant;

public enum CompressionMethod {
    NO_COMPRESSION,
    DEFLATE,
    NULL,
    LZS,
    INVALID_COMPRESSION;

    public static String title(){
        return "CompressionMethod";
    }

    public de.rub.nds.tlsattacker.core.constants.CompressionMethod transform() {
        return switch (this) {
            case LZS -> de.rub.nds.tlsattacker.core.constants.CompressionMethod.LZS;
            case DEFLATE -> de.rub.nds.tlsattacker.core.constants.CompressionMethod.DEFLATE;
            case NULL -> de.rub.nds.tlsattacker.core.constants.CompressionMethod.NULL;
            case NO_COMPRESSION -> de.rub.nds.tlsattacker.core.constants.CompressionMethod.NULL;
            default -> throw new IllegalArgumentException("Unknown compression method: " + this);
        };
    }

    public byte wireValue() {
        return this == INVALID_COMPRESSION ? (byte) 0x7f : transform().getValue();
    }

    public String maudeTerm(){
        return switch (this) {
            case LZS -> "zlib-compression";
            case DEFLATE -> "zlib-compression";
            case NULL -> "no-compression";
            case NO_COMPRESSION -> "no-compression";
            case INVALID_COMPRESSION -> "invalid-compression";
            default -> throw new IllegalArgumentException("Unknown compression method: " + this);
        };
    }
}
