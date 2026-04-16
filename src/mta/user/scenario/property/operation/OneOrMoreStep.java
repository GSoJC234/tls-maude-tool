package mta.user.scenario.property.operation;

import java.util.Objects;

public class OneOrMoreStep implements PropertyRelationOperation {

    @Override
    public boolean equals(Object o) {
        return o instanceof OneOrMoreStep;
    }

    @Override
    public int hashCode() {
        return Objects.hash(OneOrMoreStep.class);
    }

    @Override
    public String toString() {
        return "OneOrMoreStep";
    }
}
