package mta.user.behavior.modification.item;

import java.util.Objects;

public class BMNoCheck implements BehaviorModificationItem {

    private String target;

    public BMNoCheck() {
    }

    public BMNoCheck(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BMNoCheck bmNoCheck)) {
            return false;
        }
        return Objects.equals(target, bmNoCheck.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target);
    }

    @Override
    public String toString() {
        return "BMNoCheck{"
                + "target='" + target + '\''
                + '}';
    }
}
