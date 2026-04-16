package mta.user.scenario.property.operation;

import java.util.Objects;

public class ZeroOrMoreRepetition implements PropertyRelationOperation {

    @Override
    public boolean equals(Object o) {
        return o instanceof ZeroOrMoreRepetition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ZeroOrMoreRepetition.class);
    }

    @Override
    public String toString() {
        return "ZeroOrMoreRepetition";
    }
}
