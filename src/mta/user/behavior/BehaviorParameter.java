package mta.user.behavior;

import mta.user.common.UserTerm;

import java.util.Objects;

public record BehaviorParameter(String name, UserTerm typeExpression) {
    public BehaviorParameter {
        Objects.requireNonNull(name, "name must not be null");
    }

    public boolean hasType() {
        return typeExpression != null;
    }
}
