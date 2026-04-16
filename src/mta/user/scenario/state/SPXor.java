package mta.user.scenario.state;

import java.util.Objects;

public class SPXor implements StatePropositionOperator {

    @Override
    public boolean equals(Object o) {
        return o instanceof SPXor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(SPXor.class);
    }

    @Override
    public String toString() {
        return "SPXor";
    }
}
