package mta.maude.module.section;

import mta.maude.module.ModuleSectionRenderer;
import mta.user.scenario.NodeLink;
import mta.user.scenario.ScenarioSpec;
import mta.user.scenario.property.ConnectionStep;
import mta.user.scenario.property.NodeBinding;
import mta.user.scenario.property.PropertyRelation;
import mta.user.scenario.property.PropertyTerm;
import mta.user.scenario.property.ScenarioProperty;
import mta.user.scenario.property.operation.Not;
import mta.user.scenario.property.operation.OneOrMoreRepetition;
import mta.user.scenario.property.operation.OneOrMoreStep;
import mta.user.scenario.property.operation.OneStep;
import mta.user.scenario.property.operation.Or;
import mta.user.scenario.property.operation.ZeroOrMoreRepetition;
import mta.user.scenario.property.operation.ZeroOrMoreStep;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ScenarioPropertiesSectionRenderer implements ModuleSectionRenderer {

    @Override
    public String placeholder() {
        return "scenarioProperties";
    }

    @Override
    public String render(ScenarioSpec scenarioSpec) {
        if (scenarioSpec.getScenarioProperties().isEmpty()) {
            return "  --- no scenario properties declared";
        }

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        List<ScenarioProperty> scenarioProperties = scenarioSpec.getScenarioProperties();
        for (int index = 0; index < scenarioProperties.size(); index++) {
            ScenarioProperty scenarioProperty = scenarioProperties.get(index);
            String propertyId = buildName("scenarioProperty", index, scenarioProperties.size());
            String contextId = buildName("scenarioContext", index, scenarioProperties.size());

            joiner.add("  --- scenario property");
            joiner.add("  op " + propertyId + " : -> ScenarioProperty .");
            joiner.add("  eq " + propertyId + " = "
                    + renderScenarioProperty(scenarioProperty)
                    + " .");
            joiner.add("");
            joiner.add("  --- scenario context");
            joiner.add("  op " + contextId + " : -> ScenarioContext .");
            joiner.add("  eq " + contextId + " = "
                    + renderScenarioContext(scenarioSpec, scenarioProperty)
                    + " .");
            if (index < scenarioProperties.size() - 1) {
                joiner.add("");
            }
        }
        return joiner.toString();
    }

    private String buildName(String baseName, int index, int size) {
        return size == 1 ? baseName : baseName + (index + 1);
    }

    private String renderScenarioProperty(ScenarioProperty scenarioProperty) {
        if (scenarioProperty.getSteps().isEmpty()) {
            throw new IllegalArgumentException("Scenario property must contain at least one step");
        }
        ConnectionStep rootStep = scenarioProperty.getSteps().get(0);
        return renderPropertyRelation(rootStep.getPropertyRelation());
    }

    private String renderPropertyRelation(PropertyRelation relation) {
        if (relation instanceof PropertyTerm term) {
            return renderPropertyTerm(term);
        }
        if (relation.getOperation() instanceof Not) {
            return "not " + renderPropertyOperand(relation.getLeftRelation());
        }
        if (relation.getOperation() instanceof ZeroOrMoreRepetition) {
            return renderPropertyOperand(relation.getLeftRelation()) + " *";
        }
        if (relation.getOperation() instanceof OneOrMoreRepetition) {
            return renderPropertyOperand(relation.getLeftRelation()) + " +";
        }

        return renderPropertyOperand(relation.getLeftRelation())
                + " "
                + renderPropertyOperator(relation)
                + " "
                + renderPropertyOperand(relation.getRightRelation());
    }

    private String renderPropertyOperand(PropertyRelation relation) {
        if (relation instanceof PropertyTerm) {
            return renderPropertyRelation(relation);
        }
        return "(" + renderPropertyRelation(relation) + ")";
    }

    private String renderPropertyTerm(PropertyTerm term) {
        if (term.getStatePropositionId() != null) {
            return term.getStatePropositionId();
        }
        return "act(l('" + term.getActionPropositionId() + "))";
    }

    private String renderPropertyOperator(PropertyRelation relation) {
        if (relation.getOperation() instanceof ZeroOrMoreStep) {
            return "->*";
        }
        if (relation.getOperation() instanceof OneOrMoreStep) {
            return "->+";
        }
        if (relation.getOperation() instanceof OneStep) {
            return "->";
        }
        if (relation.getOperation() instanceof Or) {
            return "or";
        }
        throw new IllegalArgumentException("Unsupported property relation operator: " + relation.getOperation());
    }

    private String renderScenarioContext(ScenarioSpec scenarioSpec, ScenarioProperty scenarioProperty) {
        StringJoiner nodeJoiner = new StringJoiner(" ");
        for (NodeBinding nodeBinding : scenarioProperty.getNodeBindings()) {
            nodeJoiner.add("(" + nodeBinding.getNodeId() + " <- " + nodeBinding.getProfileAlias() + ")");
        }

        List<String> links = renderLinks(scenarioSpec, scenarioProperty);
        if (links.isEmpty()) {
            return "{" + nodeJoiner + "}";
        }

        StringJoiner linkJoiner = new StringJoiner(" ");
        for (String link : links) {
            linkJoiner.add(link);
        }
        return "{" + nodeJoiner + ", " + linkJoiner + "}";
    }

    private List<String> renderLinks(ScenarioSpec scenarioSpec, ScenarioProperty scenarioProperty) {
        List<String> renderedLinks = new ArrayList<String>();
        if (!scenarioProperty.getLinkIds().isEmpty()) {
            for (String linkId : scenarioProperty.getLinkIds()) {
                NodeLink nodeLink = scenarioSpec.getNodeLink(linkId);
                if (nodeLink == null) {
                    throw new IllegalArgumentException("Unknown link id in scenario property: " + linkId);
                }
                renderedLinks.add("link(" + nodeLink.getNodeId1() + ", " + nodeLink.getNodeId2() + ")");
            }
            return renderedLinks;
        }

        for (NodeLink nodeLink : scenarioSpec.getNodeLinks().values()) {
            renderedLinks.add("link(" + nodeLink.getNodeId1() + ", " + nodeLink.getNodeId2() + ")");
        }
        return renderedLinks;
    }
}
