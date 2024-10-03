package Maude;

public enum ECPointFormat {
    UNCOMPRESSED;

    public de.rub.nds.tlsattacker.core.constants.ECPointFormat transform(){
        switch (this){
            case UNCOMPRESSED: return de.rub.nds.tlsattacker.core.constants.ECPointFormat.UNCOMPRESSED;
            default: throw new AssertionError();
        }
    }
}
