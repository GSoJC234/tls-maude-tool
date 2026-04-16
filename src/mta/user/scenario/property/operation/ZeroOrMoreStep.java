package mta.user.scenario.property.operation;

import java.util.Objects;

public class ZeroOrMoreStep implements PropertyRelationOperation {

    @Override
    public boolean equals(Object o) {
        return o instanceof ZeroOrMoreStep;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ZeroOrMoreStep.class);
    }

    @Override
    public String toString() {
        return "ZeroOrMoreStep";
    }
}
