package mta.user.scenario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ActionProposition {

    private final List<String> arguments;
    private String actionId;

    public ActionProposition() {
        this.arguments = new ArrayList<String>();
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionId() {
        return this.actionId;
    }

    public List<String> getArguments() {
        return Collections.unmodifiableList(arguments);
    }

    public void setArguments(List<String> arguments) {
        this.arguments.clear();
        if (arguments != null) {
            this.arguments.addAll(arguments);
        }
    }

    public void addArgument(String argument) {
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
