package mta.maude.constant;

public enum CurveType {
    NAMED_CURVE;

    public static String title(){
        return "CurveType";
    }

    public de.rub.nds.tlsattacker.core.constants.EllipticCurveType transform() {
        switch (this) {
            case NAMED_CURVE: return de.rub.nds.tlsattacker.core.constants.EllipticCurveType.NAMED_CURVE;
            default: throw new IllegalArgumentException("Unsupported CurveType");
        }
    }

    public String maudeTerm() {
        switch (this) {
            case NAMED_CURVE: return "namedcurve";
            default: throw new IllegalArgumentException("Unsupported CurveType");
        }
    }
}
