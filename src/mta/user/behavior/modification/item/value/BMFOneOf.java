package mta.user.behavior.modification.item.value;

import java.util.Objects;

public class BMFOneOf implements BehaviorModificationFunctionCall {

    private String parameter;

    public BMFOneOf() {
    }

    public BMFOneOf(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BMFOneOf bmfOneOf)) {
            return false;
        }
        return Objects.equals(parameter, bmfOneOf.parameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameter);
    }

    @Override
    public String toString() {
        return "BMFOneOf{"
                + "parameter='" + parameter + '\''
                + '}';
    }
}
