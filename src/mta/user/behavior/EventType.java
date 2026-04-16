package mta.user.behavior;

import java.util.Locale;

public enum EventType {
    SEND,
    RECEIVE;

    public static EventType fromValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value.trim().toUpperCase(Locale.ROOT)) {
            case "SEND" -> SEND;
            case "RECEIVE" -> RECEIVE;
            default -> throw new IllegalArgumentException("Unsupported behavior event type: " + value);
        };
    }
}