package mta.maude.module.renderer;

import mta.user.common.ActionExpression;
import mta.user.common.UserTerm;

public class ActionExpressionRenderer {

    private final BehaviorValueLowerer behaviorValueLowerer;

    public ActionExpressionRenderer() {
        this(new MaudeUserTermRenderer());
    }

    public ActionExpressionRenderer(MaudeUserTermRenderer termRenderer) {
        this.behaviorValueLowerer = new BehaviorValueLowerer(termRenderer);
    }

    public String render(ActionExpression expression) {
        if (expression instanceof ActionExpression.RawMaude rawMaude) {
            return rawMaude.text();
        }
        if (expression instanceof ActionExpression.Atom atom) {
            return renderAtom(atom);
        }
        if (expression instanceof ActionExpression.Not not) {
            return "not " + renderOperand(not.expression());
        }
        if (expression instanceof ActionExpression.Binary binary) {
            return renderOperand(binary.left())
                    + " "
                    + binary.operator().maudeOperator()
                    + " "
                    + renderOperand(binary.right());
        }
        throw new IllegalArgumentException("Unsupported action expression: " + expression);
    }

    private String renderOperand(ActionExpression expression) {
        if (expression instanceof ActionExpression.Atom) {
            return render(expression);
        }
        return "(" + render(expression) + ")";
    }

    private String renderAtom(ActionExpression.Atom atom) {
        String field = atom.field();
        UserTerm value = atom.value();
        return switch (field) {
            case "ruleLabel" -> "ruleLabel(" + behaviorValueLowerer.renderRuleLabel(value) + ")";
            case "node" -> "appliedNode(" + behaviorValueLowerer.renderNode(value) + ")";
            case "event", "eventType" -> "eventType(" + behaviorValueLowerer.renderEventType(value) + ")";
            default -> "featureMap("
                    + behaviorValueLowerer.renderFeatureAttribute(field)
                    + " |-> "
                    + behaviorValueLowerer.renderFeatureValue(field, value)
                    + ")";
        };
    }
}
