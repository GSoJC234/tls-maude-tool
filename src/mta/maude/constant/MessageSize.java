package mta.maude.constant;

public enum MessageSize {
    VALID, SMALLER, LARGER, MAXSIZE, MINSIZE;

    public static String title(){
        return "MessageSize";
    }
}
