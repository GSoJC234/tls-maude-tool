package mta.user.profile;

import java.util.Locale;

public enum TestRole {
    Target,
    Tester;

    public static TestRole fromValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value.trim().toUpperCase(Locale.ROOT)){
            case "TARGET" -> Target;
            case "TESTER" -> Tester;
            default -> throw new IllegalArgumentException("Unknown role: " + value);
        };
    }
}
