package maude;

public enum AlertDescription {
    UNEXPECTED_MESSAGE,
    DECODE_ERROR,
    BAD_RECORD_MAC;

    public de.rub.nds.tlsattacker.core.constants.AlertDescription transform(){
        switch(this){
            case UNEXPECTED_MESSAGE: return de.rub.nds.tlsattacker.core.constants.AlertDescription.UNEXPECTED_MESSAGE;
            case DECODE_ERROR: return de.rub.nds.tlsattacker.core.constants.AlertDescription.DECODE_ERROR;
            case BAD_RECORD_MAC: return de.rub.nds.tlsattacker.core.constants.AlertDescription.BAD_RECORD_MAC;
            default: throw new IllegalArgumentException("Unknown alert description: " + this);
        }
    }
}
