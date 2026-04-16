package mta.user.behavior.condition;

import java.util.Objects;

public class BehaviorConditionTerm extends BehaviorConditionExpr {
    private String field;
    private String value;

    public BehaviorConditionTerm(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }
    public String getValue() {
        return value;
    }
    public void setField(String field) {
        this.field = field;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BehaviorConditionTerm that)) {
            return false;
        }
        return Objects.equals(field, that.field)
                && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, value);
    }

    @Override
    public String toString() {
        return "BehaviorConditionTerm{"
                + "field='" + field + '\''
                + ", value='" + value + '\''
                + '}';
    }
}
