package mta.maude.constant;

public enum AlertLevel {
    FATAL, WARNING, UNDEFINED;

    public static String title(){
        return "AlertLevel";
    }

    public de.rub.nds.tlsattacker.core.constants.AlertLevel transform(){
        switch(this){
            case FATAL: return de.rub.nds.tlsattacker.core.constants.AlertLevel.FATAL;
            case WARNING: return de.rub.nds.tlsattacker.core.constants.AlertLevel.WARNING;
            case UNDEFINED: return de.rub.nds.tlsattacker.core.constants.AlertLevel.UNDEFINED;
            default: throw new AssertionError();
        }
    }

    public static AlertLevel transform(de.rub.nds.tlsattacker.core.constants.AlertLevel alertLevel) {
        switch (alertLevel){
            case FATAL: return AlertLevel.FATAL;
            case WARNING: return AlertLevel.WARNING;
            case UNDEFINED: return AlertLevel.UNDEFINED;
            default: throw new AssertionError();
        }
    }
}
