package mta.user.scenario.property.operation;

import java.util.Objects;

public class OneOrMoreRepetition implements PropertyRelationOperation {

    @Override
    public boolean equals(Object o) {
        return o instanceof OneOrMoreRepetition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(OneOrMoreRepetition.class);
    }

    @Override
    public String toString() {
        return "OneOrMoreRepetition";
    }
}
