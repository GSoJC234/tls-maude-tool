package mta.maude.constant;

public enum AlertLevel {
    FATAL, WARNING, UNDEFINED;

    public static String title(){
        return "AlertLevel";
    }

    public de.rub.nds.tlsattacker.core.constants.AlertLevel transform(){
        return switch (this) {
            case FATAL -> de.rub.nds.tlsattacker.core.constants.AlertLevel.FATAL;
            case WARNING -> de.rub.nds.tlsattacker.core.constants.AlertLevel.WARNING;
            case UNDEFINED -> de.rub.nds.tlsattacker.core.constants.AlertLevel.UNDEFINED;
            default -> throw new IllegalArgumentException("Unknown alert level: " + this);
        };
    }

    public static AlertLevel transform(de.rub.nds.tlsattacker.core.constants.AlertLevel alertLevel) {
        return switch (alertLevel) {
            case FATAL -> AlertLevel.FATAL;
            case WARNING -> AlertLevel.WARNING;
            case UNDEFINED -> AlertLevel.UNDEFINED;
            default -> throw new IllegalArgumentException("Unknown alert level: " + alertLevel);
        };
    }

    public String maudeTerm() {
        return switch (this) {
            case FATAL -> "fatal";
            case WARNING -> "warning";
            default -> throw new IllegalArgumentException("Unknown alert level: " + this);
        };
    }
}
