package mta.maude.module.section;

import mta.maude.module.ModuleSectionRenderer;
import mta.user.behavior.BehaviorSpec;
import mta.user.scenario.ActionArgument;
import mta.user.scenario.ActionArgumentOneOf;
import mta.user.scenario.ActionArgumentValue;
import mta.user.scenario.ActionProposition;
import mta.user.scenario.ScenarioSpec;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class ConcreteBehaviorInstanceSectionRenderer implements ModuleSectionRenderer {
    @Override
    public String placeholder() {
        return "concreteBehaviorInstances";
    }

    @Override
    public String render(ScenarioSpec scenarioSpec) {
        Map<String, List<ActionProposition>> actionPropositionsByBehaviorId =
                groupActionPropositionsByBehaviorId(scenarioSpec);
        if (scenarioSpec.getBehaviorSpecs().isEmpty() || actionPropositionsByBehaviorId.isEmpty()) {
            return "  --- no concrete behavior instances loaded";
        }

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        boolean renderedAny = false;
        for (BehaviorSpec behaviorSpec : scenarioSpec.getBehaviorSpecs()) {
            List<ActionProposition> actionPropositions =
                    actionPropositionsByBehaviorId.get(behaviorSpec.getBehaviorId());
            if (actionPropositions == null || actionPropositions.isEmpty()) {
                continue;
            }

            String instanceName = "concrete" + behaviorSpec.getBehaviorId();
            joiner.add("  op " + instanceName + " : -> Set{BehaviorInstance} .");
            joiner.add("  eq " + instanceName + " = "
                    + renderConcreteBehaviorInstances(behaviorSpec, actionPropositions)
                    + " .");
            renderedAny = true;
        }
        return renderedAny ? joiner.toString() : "  --- no concrete behavior instances loaded";
    }

    private Map<String, List<ActionProposition>> groupActionPropositionsByBehaviorId(ScenarioSpec scenarioSpec) {
        Map<String, List<ActionProposition>> grouped = new LinkedHashMap<String, List<ActionProposition>>();
        for (ActionProposition actionProposition : scenarioSpec.getActionPropositions().values()) {
            grouped.computeIfAbsent(
                    actionProposition.getActionId(),
                    ignored -> new ArrayList<ActionProposition>()
            ).add(actionProposition);
        }
        return grouped;
    }

    private String renderConcreteBehaviorInstances(BehaviorSpec behaviorSpec,
                                                   List<ActionProposition> actionPropositions) {
        StringJoiner joiner = new StringJoiner(", ");
        for (ActionProposition actionProposition : actionPropositions) {
            joiner.add(renderConcreteBehaviorInstance(behaviorSpec, actionProposition));
        }
        return joiner.toString();
    }

    private String renderConcreteBehaviorInstance(BehaviorSpec behaviorSpec,
                                                  ActionProposition actionProposition) {
        List<String> parameters = behaviorSpec.getParameters();
        List<ActionArgument> arguments = actionProposition.getArguments();
        if (parameters.size() != arguments.size()) {
            throw new IllegalArgumentException(
                    "Behavior action argument count mismatch for "
                            + behaviorSpec.getBehaviorId()
                            + ": expected "
                            + parameters.size()
                            + ", got "
                            + arguments.size()
            );
        }

        return "apply(param"
                + behaviorSpec.getBehaviorId()
                + ", "
                + renderParameterMap(parameters, arguments)
                + ")";
    }

    private String renderParameterMap(List<String> parameters, List<ActionArgument> arguments) {
        if (parameters.isEmpty()) {
            return "empty";
        }

        List<String> mappings = new ArrayList<String>();
        for (int index = 0; index < parameters.size(); index++) {
            mappings.add(renderParameter(parameters.get(index))
                    + " <- "
                    + renderArgument(arguments.get(index)));
        }
        if (mappings.size() == 1) {
            return mappings.get(0);
        }

        StringJoiner joiner = new StringJoiner(", ");
        for (int index = 0; index < mappings.size(); index++) {
            String mapping = mappings.get(index);
            if (index == 0) {
                joiner.add("(" + mapping + ")");
            } else {
                joiner.add(mapping);
            }
        }
        return joiner.toString();
    }

    private String renderParameter(String parameter) {
        return "p('" + parameter + ")";
    }

    private String renderArgument(ActionArgument argument) {
        if (argument instanceof ActionArgumentValue value) {
            return "v[[" + value.getValue() + "]]";
        }
        if (argument instanceof ActionArgumentOneOf oneOf) {
            throw new IllegalArgumentException(
                    "oneOf arguments are not supported in concrete behavior instances: "
                            + oneOf.getConstantId()
            );
        }
        throw new IllegalArgumentException("Unsupported action argument: " + argument);
    }
}
