package mta.user.behavior.modification.item;

import java.util.Objects;

import mta.user.behavior.modification.item.value.BehaviorModificationOperand;

public class BMSet implements BehaviorModificationItem {

    private String target;
    private BehaviorModificationOperand value;

    public BMSet() {
    }

    public BMSet(String target, BehaviorModificationOperand value) {
        this.target = target;
        this.value = value;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public BehaviorModificationOperand getValue() {
        return value;
    }

    public void setValue(BehaviorModificationOperand value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BMSet bmSet)) {
            return false;
        }
        return Objects.equals(target, bmSet.target) && Objects.equals(value, bmSet.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, value);
    }

    @Override
    public String toString() {
        return "BMSet{"
                + "target='" + target + '\''
                + ", value='" + value + '\''
                + '}';
    }
}
