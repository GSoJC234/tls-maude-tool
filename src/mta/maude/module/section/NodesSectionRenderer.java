package mta.maude.module.section;

import mta.maude.module.ModuleSectionRenderer;
import mta.user.scenario.ScenarioSpec;

import java.util.StringJoiner;

public class NodesSectionRenderer implements ModuleSectionRenderer {

    @Override
    public String placeholder() {
        return "nodes";
    }

    @Override
    public String render(ScenarioSpec scenarioSpec) {
        if (scenarioSpec.getNodeIds().isEmpty()) {
            return "  --- no nodes declared";
        }

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (String nodeId : scenarioSpec.getNodeIds()) {
            joiner.add("  op " + nodeId + " : -> TId [ctor] .");
        }
        return joiner.toString();
    }
}
