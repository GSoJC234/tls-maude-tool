package mta.user.behavior.modification.item;

import java.util.Objects;

public class BMDelay implements BehaviorModificationItem {

    private String target;

    public BMDelay() {
    }

    public BMDelay(String target) {
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
        if (!(o instanceof BMDelay bmDelay)) {
            return false;
        }
        return Objects.equals(target, bmDelay.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target);
    }

    @Override
    public String toString() {
        return "BMDelay{"
                + "target='" + target + '\''
                + '}';
    }
}
