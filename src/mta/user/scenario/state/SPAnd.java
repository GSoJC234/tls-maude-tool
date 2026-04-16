package mta.user.scenario.state;

import java.util.Objects;

public class SPAnd implements StatePropositionOperator {

    @Override
    public boolean equals(Object o) {
        return o instanceof SPAnd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(SPAnd.class);
    }

    @Override
    public String toString() {
        return "SPAnd";
    }
}
