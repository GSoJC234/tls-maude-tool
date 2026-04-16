package mta.user.scenario.state;

import java.util.Objects;

public class SPNot implements StatePropositionOperator {

    @Override
    public boolean equals(Object o) {
        return o instanceof SPNot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(SPNot.class);
    }

    @Override
    public String toString() {
        return "SPNot";
    }
}
