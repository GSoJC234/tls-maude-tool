package mta.user.profile;

import java.util.Locale;

public enum TLSRole {
    Client,
    Server,
    Mitm;

    public static TLSRole fromValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value.trim().toUpperCase(Locale.ROOT)){
            case "CLIENT" -> Client;
            case "SERVER" -> Server;
            case "MITM" -> Mitm;
            default -> throw new IllegalArgumentException("Unknown role: " + value);
        };
    }
}   