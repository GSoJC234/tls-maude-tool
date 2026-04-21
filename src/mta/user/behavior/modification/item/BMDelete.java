package mta.user.behavior.modification.item;

import java.util.Objects;

public class BMDelete implements BehaviorModificationItem {

    private String target;

    public BMDelete() {
    }

    public BMDelete(String target) {
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
        if (!(o instanceof BMDelete bmRemove)) {
            return false;
        }
        return Objects.equals(target, bmRemove.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target);
    }

    @Override
    public String toString() {
        return "BMRemove{"
                + "target='" + target + '\''
                + '}';
    }
}
