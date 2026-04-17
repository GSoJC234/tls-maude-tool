package mta.user.scenario;

import java.util.Objects;

public class ActionArgumentOneOf implements ActionArgument {

    private String constantId;

    public ActionArgumentOneOf(String constantId) {
        this.constantId = constantId;
    }

    public String getConstantId() {
        return constantId;
    }

    public void setConstantId(String constantId) {
        this.constantId = constantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionArgumentOneOf that)) {
            return false;
        }
        return Objects.equals(constantId, that.constantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(constantId);
    }

    @Override
    public String toString() {
        return "ActionArgumentOneOf{"
                + "constantId='" + constantId + '\''
                + '}';
    }
}
