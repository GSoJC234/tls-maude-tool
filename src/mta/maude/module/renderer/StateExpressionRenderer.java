package mta.maude.module.renderer;

import mta.user.common.StateExpression;
import mta.user.common.UserTerm;

public class StateExpressionRenderer {

    private final MaudeUserTermRenderer termRenderer;

    public StateExpressionRenderer() {
        this(new MaudeUserTermRenderer());
    }

    public StateExpressionRenderer(MaudeUserTermRenderer termRenderer) {
        this.termRenderer = termRenderer;
    }

    public String render(StateExpression expression) {
        if (expression instanceof StateExpression.Atom atom) {
            return renderObject(atom.object())
                    + " . "
                    + termRenderer.renderAttribute(new UserTerm.Atom(atom.attribute()))
                    + " = "
                    + termRenderer.renderAttributeValue(atom.value());
        }
        if (expression instanceof StateExpression.Not not) {
            return "not " + renderOperand(not.expression());
        }
        if (expression instanceof StateExpression.Binary binary) {
            return renderOperand(binary.left())
                    + " "
                    + binary.operator().maudeOperator()
                    + " "
                    + renderOperand(binary.right());
        }
        throw new IllegalArgumentException("Unsupported state expression: " + expression);
    }

    private String renderOperand(StateExpression expression) {
        if (expression instanceof StateExpression.Atom) {
            return render(expression);
        }
        return "(" + render(expression) + ")";
    }

    private String renderObject(UserTerm object) {
        if (object instanceof UserTerm.Atom atom) {
            return switch (atom.text()) {
                case "client" -> "N1 . CI";
                case "server" -> "N2 . SI";
                default -> atom.text();
            };
        }
        return termRenderer.renderTerm(object);
    }
}
