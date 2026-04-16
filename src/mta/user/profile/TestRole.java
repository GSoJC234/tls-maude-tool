package mta.user.profile;

import java.util.Locale;

public enum TestRole {
    TARGET,
    TESTER;

    public static TestRole fromValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value.trim().toUpperCase(Locale.ROOT)){
            case "TARGET" -> TARGET;
            case "TESTER" -> TESTER;
            default -> throw new IllegalArgumentException("Unknown role: " + value);
        };
    }
}