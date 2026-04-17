package mta.maude.module.section;

import mta.maude.module.ModuleSectionRenderer;
import mta.user.scenario.ActionArgument;
import mta.user.scenario.ActionArgumentOneOf;
import mta.user.scenario.ActionArgumentValue;
import mta.user.scenario.ActionProposition;
import mta.user.scenario.ScenarioSpec;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class ActionPropositionsSectionRenderer implements ModuleSectionRenderer {

    @Override
    public String placeholder() {
        return "actionPropositions";
    }

    @Override
    public String render(ScenarioSpec scenarioSpec) {
        if (scenarioSpec.getActionPropositions().isEmpty()) {
            return "  --- no action propositions declared";
        }

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        int index = 0;
        for (Map.Entry<String, ActionProposition> entry : scenarioSpec.getActionPropositions().entrySet()) {
            joiner.add("  op " + entry.getKey() + " : -> ActionProposition .");
            joiner.add("  eq " + entry.getKey() + " = " + renderActionInvocation(entry.getValue()) + " .");
            if (index < scenarioSpec.getActionPropositions().size() - 1) {
                joiner.add("");
            }
            index++;
        }
        return joiner.toString();
    }

    private String renderActionInvocation(ActionProposition actionProposition) {
        StringJoiner joiner = new StringJoiner(", ", "apply(", ")");
        joiner.add("'" + actionProposition.getActionId());
        List<ActionArgument> arguments = actionProposition.getArguments();
        for (ActionArgument argument : arguments) {
            joiner.add(renderArgument(argument));
        }
        joiner.add("behaviorSpecSet");
        return joiner.toString();
    }

    private String renderArgument(ActionArgument argument) {
        if (argument instanceof ActionArgumentValue valueArgument) {
            return "'" + valueArgument.getValue();
        }
        if (argument instanceof ActionArgumentOneOf oneOfArgument) {
            return "oneOf(" + oneOfArgument.getConstantId() + ")";
        }
        throw new IllegalArgumentException("Unsupported action argument: " + argument);
    }
}
