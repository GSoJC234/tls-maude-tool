package maude;

public enum AlertDescription {
    UNEXPECTED_MESSAGE;

    public de.rub.nds.tlsattacker.core.constants.AlertDescription transform(){
        switch(this){
            case UNEXPECTED_MESSAGE: return de.rub.nds.tlsattacker.core.constants.AlertDescription.UNEXPECTED_MESSAGE;
            default: throw new IllegalArgumentException("Unknown alert description: " + this);
        }
    }
}
