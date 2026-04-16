package mta.user.behavior.modification.item;

import java.util.Objects;

import mta.user.behavior.modification.item.value.BehaviorModificationOperand;

public class BMAdd implements BehaviorModificationItem {

    private String target;
    private BehaviorModificationOperand value;

    public BMAdd() {
    }

    public BMAdd(String target, BehaviorModificationOperand value) {
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
        if (!(o instanceof BMAdd bmAdd)) {
            return false;
        }
        return Objects.equals(target, bmAdd.target) && Objects.equals(value, bmAdd.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, value);
    }

    @Override
    public String toString() {
        return "BMAdd{"
                + "target='" + target + '\''
                + ", value='" + value + '\''
                + '}';
    }
}
