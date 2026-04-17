package mta.maude.module.section;

import mta.maude.module.ModuleSectionRenderer;
import mta.user.behavior.condition.BehaviorCondition;
import mta.user.behavior.condition.BehaviorConditionExpr;
import mta.user.behavior.condition.BehaviorConditionOpr;
import mta.user.behavior.condition.BehaviorConditionTerm;
import mta.user.scenario.ScenarioSpec;
import mta.user.scenario.state.SPAnd;
import mta.user.scenario.state.SPNot;
import mta.user.scenario.state.SPOr;
import mta.user.scenario.state.SPXor;
import mta.user.scenario.state.StateProposition;
import mta.user.scenario.state.StatePropositionExpression;
import mta.user.scenario.state.StatePropositionTerm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class StatePropositionsSectionRenderer implements ModuleSectionRenderer {

    @Override
    public String placeholder() {
        return "statePropositions";
    }

    @Override
    public String render(ScenarioSpec scenarioSpec) {
        if (scenarioSpec.getStatePropositions().isEmpty()) {
            return "  --- no state propositions declared";
        }

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        List<Map.Entry<String, StateProposition>> entries =
                new ArrayList<Map.Entry<String, StateProposition>>(scenarioSpec.getStatePropositions().entrySet());
        for (int index = 0; index < entries.size(); index++) {
            Map.Entry<String, StateProposition> entry = entries.get(index);
            String propositionId = entry.getKey();
            joiner.add("  op " + propositionId + " : -> StateProposition .");
            addStateProposition(propositionId, (StatePropositionExpression) entry.getValue(), joiner);
            if (index < entries.size() - 1) {
                joiner.add("");
            }
        }
        return joiner.toString();
    }

    public void addStateProposition(String propositionId,
                                    StatePropositionExpression expression,
                                    StringJoiner joiner) {
        joiner.add((propositionId.isEmpty() ? "" : "  eq " + propositionId + " = ")
                + renderStateProposition(expression)
                + " .");
    }

    private String renderStateProposition(StatePropositionExpression expression) {
        if (expression instanceof StatePropositionTerm term) {
            return renderStatePropositionTerm(term);
        }

        if (expression.getOperator() instanceof SPNot) {
            return "not " + renderStatePropositionOperand(expression.getLeftExpression());
        }

        return renderStatePropositionOperand(expression.getLeftExpression())
                + " "
                + renderStateOperator(expression)
                + " "
                + renderStatePropositionOperand(expression.getRightExpression());
    }

    private String renderStatePropositionOperand(StatePropositionExpression expression) {
        if (expression instanceof StatePropositionTerm term) {
            return renderStatePropositionTerm(term);
        }
        return "(" + renderStateProposition(expression) + ")";
    }

    private String renderStatePropositionTerm(StatePropositionTerm term) {
        return "(" + term.getNodeId() + " | " + renderCondition(term.getCondition()) + ")";
    }

    private String renderCondition(BehaviorCondition condition) {
        if (condition == null || condition.getExpr() == null) {
            return "";
        }
        return renderConditionExpr(condition.getExpr());
    }

    private String renderConditionExpr(BehaviorConditionExpr expression) {
        if (expression instanceof BehaviorConditionTerm term) {
            return renderConditionTerm(term);
        }
        if (expression.getOperator() == BehaviorConditionOpr.not) {
            return "not " + renderConditionOperand(expression.getLeftExpression());
        }
        return renderConditionOperand(expression.getLeftExpression())
                + " "
                + renderConditionOperator(expression.getOperator())
                + " "
                + renderConditionOperand(expression.getRightExpression());
    }

    private String renderConditionOperand(BehaviorConditionExpr expression) {
        if (expression instanceof BehaviorConditionTerm term) {
            return renderConditionTerm(term);
        }
        return "(" + renderConditionExpr(expression) + ")";
    }

    private String renderConditionTerm(BehaviorConditionTerm term) {
        return "'" + term.getField() + " = " + renderConditionValue(term.getValue());
    }

    private String renderConditionValue(String value) {
        return "v[[" + value + "]]";
    }

    private String renderStateOperator(StatePropositionExpression expression) {
        if (expression.getOperator() instanceof SPAnd) {
            return "and";
        }
        if (expression.getOperator() instanceof SPOr) {
            return "or";
        }
        if (expression.getOperator() instanceof SPXor) {
            return "xor";
        }
        throw new IllegalArgumentException("Unsupported state proposition operator: " + expression.getOperator());
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
}
