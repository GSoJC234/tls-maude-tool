package mta.user.scenario.state;

import java.util.Objects;

public class SPOr implements StatePropositionOperator {

    @Override
    public boolean equals(Object o) {
        return o instanceof SPOr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(SPOr.class);
    }

    @Override
    public String toString() {
        return "SPOr";
    }
}
