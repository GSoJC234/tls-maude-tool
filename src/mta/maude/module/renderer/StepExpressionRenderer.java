package mta.maude.module.renderer;

import mta.user.common.StepExpression;

public class StepExpressionRenderer {

    private final ActionExpressionRenderer actionExpressionRenderer;
    private final StateExpressionRenderer stateExpressionRenderer;

    public StepExpressionRenderer() {
        this(new ActionExpressionRenderer(), new StateExpressionRenderer());
    }

    public StepExpressionRenderer(ActionExpressionRenderer actionExpressionRenderer,
                                  StateExpressionRenderer stateExpressionRenderer) {
        this.actionExpressionRenderer = actionExpressionRenderer;
        this.stateExpressionRenderer = stateExpressionRenderer;
    }

    public String render(StepExpression expression) {
        if (expression instanceof StepExpression.AnyStep) {
            return "anyStep";
        }
        if (expression instanceof StepExpression.Action action) {
            return actionExpressionRenderer.render(action.expression());
        }
        if (expression instanceof StepExpression.State state) {
            return stateExpressionRenderer.render(state.expression());
        }
        if (expression instanceof StepExpression.Not not) {
            return "not " + renderOperand(not.expression());
        }
        if (expression instanceof StepExpression.Binary binary) {
            return renderOperand(binary.left())
                    + " "
                    + binary.operator().maudeOperator()
                    + " "
                    + renderOperand(binary.right());
        }
        throw new IllegalArgumentException("Unsupported step expression: " + expression);
    }

    private String renderOperand(StepExpression expression) {
        if (expression instanceof StepExpression.Action
                || expression instanceof StepExpression.State
                || expression instanceof StepExpression.AnyStep) {
            return render(expression);
        }
        return "(" + render(expression) + ")";
    }
}
