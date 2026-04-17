package mta.maude.constant;

public enum MessageSize {
    VALID, SMALLER, LARGER, MAXSIZE, MINSIZE;

    public static String title(){
        return "MessageSize";
    }

    public String maudeTerm() {
        return switch (this) {
            case VALID -> "valid";
            case SMALLER -> "smaller";
            case LARGER -> "larger";
            case MAXSIZE -> "maxSize";
            case MINSIZE -> "minSize";
            default -> throw new IllegalArgumentException("Unknown message size");
        };
    }
}
