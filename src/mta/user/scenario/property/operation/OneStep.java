package mta.user.scenario.property.operation;

import java.util.Objects;

public class OneStep implements PropertyRelationOperation {

    @Override
    public boolean equals(Object o) {
        return o instanceof OneStep;
    }

    @Override
    public int hashCode() {
        return Objects.hash(OneStep.class);
    }

    @Override
    public String toString() {
        return "OneStep";
    }
}
