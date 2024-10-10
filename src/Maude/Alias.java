package Maude;

public enum Alias {
    SERVER, CLIENT;

    @Override
    public String toString() {
        switch (this) {
            case SERVER: return "server";
            case CLIENT: return "client";
            default: return "unknown";
        }
    }
}
