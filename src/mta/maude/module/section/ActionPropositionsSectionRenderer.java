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
            joiner.add("  eq " + entry.getKey() + " = act(l('" + entry.getValue().getActionId() + ")) .");
            if (index < scenarioSpec.getActionPropositions().size() - 1) {
                joiner.add("");
            }
            index++;
        }
        return joiner.toString();
    }
}
