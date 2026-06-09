package mta.maude.module.renderer;

import mta.user.common.ScenarioExpression;
import mta.user.scenario.ScenarioSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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
            return renderSequence(sequence);
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

    private String renderSequence(ScenarioExpression expression) {
        List<ScenarioExpression> terms = new ArrayList<ScenarioExpression>();
        collectSequenceTerms(expression, terms);
        StringJoiner joiner = new StringJoiner(" ; ");
        for (ScenarioExpression term : terms) {
            joiner.add(renderSequenceTerm(term));
        }
        return joiner.toString();
    }

    private void collectSequenceTerms(ScenarioExpression expression, List<ScenarioExpression> terms) {
        if (expression instanceof ScenarioExpression.Sequence sequence) {
            collectSequenceTerms(sequence.left(), terms);
            collectSequenceTerms(sequence.right(), terms);
            return;
        }
        terms.add(expression);
    }

    private String renderSequenceTerm(ScenarioExpression expression) {
        if (expression instanceof ScenarioExpression.Step || expression instanceof ScenarioExpression.Star) {
            return render(expression);
        }
        return "(" + render(expression) + ")";
    }
}
