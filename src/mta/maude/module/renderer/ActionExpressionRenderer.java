package mta.maude.module.renderer;

import mta.user.common.ActionExpression;
import mta.user.common.UserTerm;

public class ActionExpressionRenderer {

    private final BehaviorValueToMaudeConverter behaviorValueToMaudeConverter;

    public ActionExpressionRenderer() {
        this(new MaudeUserTermRenderer());
    }

    public ActionExpressionRenderer(MaudeUserTermRenderer termRenderer) {
        this.behaviorValueToMaudeConverter = new BehaviorValueToMaudeConverter(termRenderer);
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
            case "label", "ruleLabel" -> "ruleLabel(" + behaviorValueToMaudeConverter.renderRuleLabel(value) + ")";
            case "ruleMsgType" -> "ruleMsgType(" + behaviorValueToMaudeConverter.renderRuleMessageType(value) + ")";
            case "node" -> "appliedNode(" + behaviorValueToMaudeConverter.renderNode(value) + ")";
            case "event", "eventType" -> "eventType(" + behaviorValueToMaudeConverter.renderEventType(value) + ")";
            default -> "featureMap("
                    + behaviorValueToMaudeConverter.renderFeatureAttribute(field)
                    + " |-> "
                    + behaviorValueToMaudeConverter.renderFeatureValue(field, value)
                    + ")";
        };
    }
}
