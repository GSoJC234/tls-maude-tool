package maude;

public enum AlertLevel {
    FATAL, WARNING, UNDEFINED;

    public de.rub.nds.tlsattacker.core.constants.AlertLevel transform(){
        switch(this){
            case FATAL: return de.rub.nds.tlsattacker.core.constants.AlertLevel.FATAL;
            case WARNING: return de.rub.nds.tlsattacker.core.constants.AlertLevel.WARNING;
            case UNDEFINED: return de.rub.nds.tlsattacker.core.constants.AlertLevel.UNDEFINED;
            default: throw new AssertionError();
        }
    }

    public static maude.AlertLevel transform(de.rub.nds.tlsattacker.core.constants.AlertLevel alertLevel) {
        switch (alertLevel){
            case FATAL: return maude.AlertLevel.FATAL;
            case WARNING: return maude.AlertLevel.WARNING;
            case UNDEFINED: return maude.AlertLevel.UNDEFINED;
            default: throw new AssertionError();
        }
    }
}
