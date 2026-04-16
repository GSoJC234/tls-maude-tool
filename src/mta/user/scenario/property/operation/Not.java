package mta.user.scenario.property.operation;

import java.util.Objects;

public class Not implements PropertyRelationOperation {

    @Override
    public boolean equals(Object o) {
        return o instanceof Not;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Not.class);
    }

    @Override
    public String toString() {
        return "Not";
    }
}
