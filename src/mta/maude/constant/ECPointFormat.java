package mta.maude.constant;

public enum ECPointFormat {
    UNCOMPRESSED;

    public de.rub.nds.tlsattacker.core.constants.ECPointFormat transform(){
        switch (this){
            case UNCOMPRESSED: return de.rub.nds.tlsattacker.core.constants.ECPointFormat.UNCOMPRESSED;
            default: throw new IllegalArgumentException("Unsupported ECPointFormat");
        }
    }

    public String maudeTerm() {
        switch (this){
            case UNCOMPRESSED: return "uncompressed";
            default: throw new IllegalArgumentException("Unsupported ECPointFormat");
        }
    }
}
