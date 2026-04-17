package mta.user.scenario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ActionProposition {

    private final List<ActionArgument> arguments;
    private String actionId;

    public ActionProposition() {
        this.arguments = new ArrayList<ActionArgument>();
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionId() {
        return this.actionId;
    }

    public List<ActionArgument> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    public void setArguments(List<ActionArgument> arguments) {
        this.arguments.clear();
        if (arguments != null) {
            this.arguments.addAll(arguments);
        }
    }

    public void addArgument(ActionArgument argument) {
        if (argument != null) {
            this.arguments.add(argument);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionProposition that)) {
            return false;
        }
        return Objects.equals(actionId, that.actionId)
                && Objects.equals(arguments, that.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionId, arguments);
    }

    @Override
    public String toString() {
        return "ActionProposition{"
                + "actionId='" + actionId + '\''
                + ", "
                + "arguments=" + arguments
                + '}';
    }
}
