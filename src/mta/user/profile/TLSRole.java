package mta.user.profile;

import java.util.Locale;

public enum TLSRole {
    CLIENT,
    SERVER,
    MITM;

    public static TLSRole fromValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value.trim().toUpperCase(Locale.ROOT)){
            case "CLIENT" -> CLIENT;
            case "SERVER" -> SERVER;
            case "MITM" -> MITM;
            default -> throw new IllegalArgumentException("Unknown role: " + value);
        };
    }
}   