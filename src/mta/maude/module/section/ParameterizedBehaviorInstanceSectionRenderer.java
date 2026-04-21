package mta.maude.module.section;

import mta.maude.module.ModuleSectionRenderer;
import mta.user.behavior.BehaviorSpec;
import mta.user.behavior.condition.BehaviorCondition;
import mta.user.behavior.condition.BehaviorConditionExpr;
import mta.user.behavior.condition.BehaviorConditionOpr;
import mta.user.behavior.condition.BehaviorConditionTerm;
import mta.user.behavior.modification.BehaviorModification;
import mta.user.behavior.modification.item.BMDelay;
import mta.user.behavior.modification.item.BMNoCheck;
import mta.user.behavior.modification.item.BMDelete;
import mta.user.behavior.modification.item.BMSet;
import mta.user.behavior.modification.item.BMSkip;
import mta.user.behavior.modification.item.BehaviorModificationItem;
import mta.user.behavior.modification.BehaviorModificationValue;
import mta.user.scenario.ScenarioSpec;

import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

public class ParameterizedBehaviorInstanceSectionRenderer implements ModuleSectionRenderer {

    @Override
    public String placeholder() {
        return "parameterizedBehaviorInstances";
    }

    @Override
    public String render(ScenarioSpec scenarioSpec) {
        if (scenarioSpec.getBehaviorSpecs().isEmpty()) {
            return "  --- no parameterized behavior instances loaded";
        }

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (BehaviorSpec behaviorSpec : scenarioSpec.getBehaviorSpecs()) {
            String instanceName = "param" + behaviorSpec.getBehaviorId();
            joiner.add("  op " + instanceName + " : -> BehaviorInstance .");
            joiner.add("  eq " + instanceName + " = "
                    + "{"
                    + renderBehaviorId(behaviorSpec)
                    + ", "
                    + renderEventType(behaviorSpec)
                    + ", "
                    + renderCondition(behaviorSpec.getConditions(), behaviorSpec.getParameters())
                    + ", "
                    + renderModification(behaviorSpec.getModifications(), behaviorSpec.getParameters())
                    + " } .");
        }
        return joiner.toString();
    }

    private String renderBehaviorId(BehaviorSpec behaviorSpec) {
        return "l('" + behaviorSpec.getBehaviorId() + ")";
    }

    private String renderEventType(BehaviorSpec behaviorSpec) {
        return behaviorSpec.getEventType().name().toLowerCase(Locale.ROOT);
    }

    private String renderCondition(BehaviorCondition condition, List<String> parameters) {
        if (condition == null || condition.getExpr() == null) {
            return "none";
        }
        return renderConditionExpr(condition.getExpr(), parameters);
    }

    private String renderConditionExpr(BehaviorConditionExpr expression, List<String> parameters) {
        if (expression instanceof BehaviorConditionTerm term) {
            return renderConditionTerm(term, parameters);
        }
        if (expression.getOperator() == BehaviorConditionOpr.not) {
            return "not " + renderConditionOperand(expression.getLeftExpression(), parameters);
        }
        return renderConditionOperand(expression.getLeftExpression(), parameters)
                + " "
                + renderConditionOperator(expression.getOperator())
                + " "
                + renderConditionOperand(expression.getRightExpression(), parameters);
    }

    private String renderConditionOperand(BehaviorConditionExpr expression, List<String> parameters) {
        if (expression instanceof BehaviorConditionTerm term) {
            return renderConditionTerm(term, parameters);
        }
        return "(" + renderConditionExpr(expression, parameters) + ")";
    }

    private String renderConditionTerm(BehaviorConditionTerm term, List<String> parameters) {
        return "fieldValue(" + renderIdentifier(term.getField(), parameters) + ") = value(" + renderIdentifier(term.getValue(), parameters) + ")";
    }

    private String renderConditionOperator(BehaviorConditionOpr operator) {
        if (operator == BehaviorConditionOpr.and) {
            return "and";
        }
        if (operator == BehaviorConditionOpr.or) {
            return "or";
        }
        if (operator == BehaviorConditionOpr.xor) {
            return "xor";
        }
        throw new IllegalArgumentException("Unsupported condition operator: " + operator);
    }

    private String renderModification(BehaviorModification modification, List<String> parameters) {
        if (modification == null || modification.getBehaviorModificationItems().isEmpty()) {
            return "none";
        }

        StringJoiner joiner = new StringJoiner(" ");
        for (BehaviorModificationItem item : modification.getBehaviorModificationItems()) {
            joiner.add(renderModificationItem(item, parameters));
        }
        return joiner.toString();
    }

    private String renderModificationItem(BehaviorModificationItem item, List<String> parameters) {
        if (item instanceof BMSet set) {
            return "set(" + renderIdentifier(set.getTarget(), parameters) + ", " + renderOperand(set.getValue(), parameters) + ")";
        }
        if (item instanceof BMDelete remove) {
            return "delete(" + renderIdentifier(remove.getTarget(), parameters) + ")";
        }
        if (item instanceof BMNoCheck noCheck) {
            return "noCheck(" + renderIdentifier(noCheck.getTarget(), parameters) + ")";
        }
        if (item instanceof BMDelay delay) {
            return "delay(" + renderIdentifier(delay.getTarget(), parameters) + ")";
        }
        if (item instanceof BMSkip) {
            return "skip";
        }
        throw new IllegalArgumentException("Unsupported behavior modification item: " + item);
    }

    private String renderOperand(BehaviorModificationValue value, List<String> parameters) {
        return renderIdentifier(value.getValue(), parameters);
    }

    private String renderIdentifier(String reference, List<String> parameters) {
        if (parameters.contains(reference)) {
            return "p('" + reference + ")";
        }
        return reference;
    }
}
