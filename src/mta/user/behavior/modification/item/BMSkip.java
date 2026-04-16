package mta.user.behavior.modification.item;

import java.util.Objects;

public class BMSkip implements BehaviorModificationItem {

    @Override
    public boolean equals(Object o) {
        return o instanceof BMSkip;
    }

    @Override
    public int hashCode() {
        return Objects.hash(BMSkip.class);
    }

    @Override
    public String toString() {
        return "BMSkip{}";
    }
}
