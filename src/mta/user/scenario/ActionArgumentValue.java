package mta.user.scenario;

import java.util.Objects;

public class ActionArgumentValue implements ActionArgument {

    private String value;

    public ActionArgumentValue(String value) {
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
        if (!(o instanceof ActionArgumentValue that)) {
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
        return "ActionArgumentValue{"
                + "value='" + value + '\''
                + '}';
    }
}
