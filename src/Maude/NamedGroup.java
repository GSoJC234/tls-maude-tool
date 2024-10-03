package Maude;

public enum NamedGroup {
    NONE, SECP256R1;

    public de.rub.nds.tlsattacker.core.constants.NamedGroup transform(){
        switch (this){
            case SECP256R1: return de.rub.nds.tlsattacker.core.constants.NamedGroup.SECP256R1;
            default: throw new AssertionError();
        }
    }
}
