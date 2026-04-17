package mta.maude.module.section;

import mta.maude.module.ModuleSectionRenderer;
import mta.user.scenario.ScenarioSpec;

import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class ConstantsSectionRenderer implements ModuleSectionRenderer {

    @Override
    public String placeholder() {
        return "constants";
    }

    @Override
    public String render(ScenarioSpec scenarioSpec) {
        if (scenarioSpec.getConstants().isEmpty()) {
            return "  --- no constants declared";
        }

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (Map.Entry<String, Set<String>> entry : scenarioSpec.getConstants().entrySet()) {
            joiner.add("  op " + entry.getKey() + " : -> QidSet .");
            joiner.add("  eq " + entry.getKey() + " = " + renderConstantSet(entry.getValue()) + " .");
            joiner.add("");
        }
        return joiner.toString();
    }

    private String renderConstantSet(Set<String> values) {
        StringJoiner joiner = new StringJoiner(", ", "resolve(", ")");
        for (String value : values) {
            joiner.add(renderConstantValue(value));
        }
        return joiner.toString();
    }

    private String renderConstantValue(String value) {
        return "'" + value;
    }
}
