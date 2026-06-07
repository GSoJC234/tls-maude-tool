package mta.maude.module.renderer;

import mta.user.common.ScenarioExpression;
import mta.user.scenario.ScenarioSpec;

public class ScenarioPropertyRenderer {

    private final StepExpressionRenderer stepExpressionRenderer;

    public ScenarioPropertyRenderer() {
        this(new StepExpressionRenderer());
    }

    public ScenarioPropertyRenderer(StepExpressionRenderer stepExpressionRenderer) {
        this.stepExpressionRenderer = stepExpressionRenderer;
    }

    public String renderEq(String opName, ScenarioSpec scenarioSpec) {
        return "  op "
                + opName
                + " : -> ScenarioProperty ."
                + System.lineSeparator()
                + "  eq "
                + opName
                + " = "
                + render(scenarioSpec)
                + " .";
    }

    public String render(ScenarioSpec scenarioSpec) {
        if (scenarioSpec.getCurrentScenarioProperty() == null) {
            throw new IllegalArgumentException("ScenarioSpec has no ScenarioProperty");
        }
        return render(scenarioSpec.getCurrentScenarioProperty());
    }

    public String render(ScenarioExpression expression) {
        if (expression instanceof ScenarioExpression.RawMaude rawMaude) {
            return rawMaude.text();
        }
        if (expression instanceof ScenarioExpression.Step step) {
            return stepExpressionRenderer.render(step.expression());
        }
        if (expression instanceof ScenarioExpression.Star star) {
            return renderOperand(star.expression()) + " *";
        }
        if (expression instanceof ScenarioExpression.Sequence sequence) {
            return renderOperand(sequence.left())
                    + " ; "
                    + renderOperand(sequence.right());
        }
        if (expression instanceof ScenarioExpression.Choice choice) {
            return renderOperand(choice.left())
                    + " | "
                    + renderOperand(choice.right());
        }
        throw new IllegalArgumentException("Unsupported scenario expression: " + expression);
    }

    private String renderOperand(ScenarioExpression expression) {
        if (expression instanceof ScenarioExpression.Step) {
            return render(expression);
        }
        return "(" + render(expression) + ")";
    }
}
