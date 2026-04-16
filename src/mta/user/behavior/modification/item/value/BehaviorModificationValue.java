package mta.user.behavior.modification.item.value;

import java.util.Objects;

public class BehaviorModificationValue implements BehaviorModificationOperand {

    private String value;

    public BehaviorModificationValue() {
    }

    public BehaviorModificationValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BehaviorModificationValue that)) {
            return false;
        }
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BehaviorModificationValue{"
                + "value='" + value + '\''
                + '}';
    }
}
