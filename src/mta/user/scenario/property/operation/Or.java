package mta.user.scenario.property.operation;

import java.util.Objects;

public class Or implements PropertyRelationOperation {

    @Override
    public boolean equals(Object o) {
        return o instanceof Or;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Or.class);
    }

    @Override
    public String toString() {
        return "Or";
    }
}
