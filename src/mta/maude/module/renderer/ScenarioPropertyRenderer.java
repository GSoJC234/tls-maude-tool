package mta.maude.module.renderer;

import mta.user.common.ScenarioExpression;
import mta.user.scenario.ScenarioSpec;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class ScenarioPropertyRenderer {

    private static final char[] HEX = "0123456789abcdef".toCharArray();

    private final StepExpressionRenderer stepExpressionRenderer;

    public ScenarioPropertyRenderer() {
        this(new StepExpressionRenderer());
    }

    public ScenarioPropertyRenderer(StepExpressionRenderer stepExpressionRenderer) {
        this.stepExpressionRenderer = stepExpressionRenderer;
    }

    public String renderEq(String opName, ScenarioSpec scenarioSpec) {
        if (opName == null || opName.isBlank()) {
            throw new IllegalArgumentException("ScenarioProperty runtime op name must not be blank");
        }
        scenarioSpec.validate();

        Map<String, StepExpressionRenderer.PropositionBinding> internalNames =
                buildInternalNames(scenarioSpec);
        StringJoiner definitions = new StringJoiner(System.lineSeparator());
        for (ScenarioSpec.PropositionDeclaration declaration : scenarioSpec.getPropositionDeclarations()) {
            String internalName = internalNames.get(declaration.name()).maudeName();
            if (declaration instanceof ScenarioSpec.StatePropositionDeclaration state) {
                definitions.add(renderDefinition(
                        internalName,
                        "StateProposition",
                        stepExpressionRenderer.renderState(state.expression())
                ));
            } else if (declaration instanceof ScenarioSpec.ActionPropositionDeclaration action) {
                definitions.add(renderDefinition(
                        internalName,
                        "ActionProposition",
                        stepExpressionRenderer.renderAction(action.expression())
                ));
            } else {
                throw new IllegalArgumentException("Unsupported proposition declaration: " + declaration);
            }
        }

        String runtimeValue = render(scenarioSpec.getCurrentScenarioProperty(), internalNames);
        if (scenarioSpec.getCurrentScenarioPropertyName() != null) {
            String namedPropertyOp = internalOpName(
                    "Property",
                    scenarioSpec.getCurrentScenarioPropertyName()
            );
            definitions.add(renderDefinition(namedPropertyOp, "ScenarioProperty", runtimeValue));
            runtimeValue = namedPropertyOp;
        }
        definitions.add(renderDefinition(opName, "ScenarioProperty", runtimeValue));
        return definitions.toString();
    }

    public String render(ScenarioSpec scenarioSpec) {
        scenarioSpec.validate();
        return render(scenarioSpec.getCurrentScenarioProperty(), buildInternalNames(scenarioSpec));
    }

    public String render(ScenarioExpression expression) {
        return render(expression, Map.of());
    }

    private String render(
            ScenarioExpression expression,
            Map<String, StepExpressionRenderer.PropositionBinding> internalNames) {
        if (expression instanceof ScenarioExpression.RawMaude rawMaude) {
            return rawMaude.text();
        }
        if (expression instanceof ScenarioExpression.Reference reference) {
            StepExpressionRenderer.PropositionBinding binding = internalNames.get(reference.name());
            if (binding == null) {
                throw new IllegalArgumentException("Unresolved proposition reference '"
                        + reference.name()
                        + "'");
            }
            return binding.maudeName();
        }
        if (expression instanceof ScenarioExpression.Step step) {
            return stepExpressionRenderer.render(step.expression(), internalNames);
        }
        if (expression instanceof ScenarioExpression.Star star) {
            if (star.expression() instanceof ScenarioExpression.Step) {
                return "(" + render(star.expression(), internalNames) + ") *";
            }
            return renderOperand(star.expression(), internalNames) + " *";
        }
        if (expression instanceof ScenarioExpression.Sequence sequence) {
            return renderSequence(sequence, internalNames);
        }
        if (expression instanceof ScenarioExpression.Choice choice) {
            return renderOperand(choice.left(), internalNames)
                    + " | "
                    + renderOperand(choice.right(), internalNames);
        }
        throw new IllegalArgumentException("Unsupported scenario expression: " + expression);
    }

    private Map<String, StepExpressionRenderer.PropositionBinding> buildInternalNames(
            ScenarioSpec scenarioSpec) {
        Map<String, StepExpressionRenderer.PropositionBinding> names =
                new LinkedHashMap<String, StepExpressionRenderer.PropositionBinding>();
        for (ScenarioSpec.PropositionDeclaration declaration : scenarioSpec.getPropositionDeclarations()) {
            String internalKind;
            StepExpressionRenderer.PropositionKind propositionKind;
            if (declaration instanceof ScenarioSpec.StatePropositionDeclaration) {
                internalKind = "State";
                propositionKind = StepExpressionRenderer.PropositionKind.STATE;
            } else if (declaration instanceof ScenarioSpec.ActionPropositionDeclaration) {
                internalKind = "Action";
                propositionKind = StepExpressionRenderer.PropositionKind.ACTION;
            } else {
                throw new IllegalArgumentException("Unsupported proposition declaration: " + declaration);
            }
            names.put(
                    declaration.name(),
                    new StepExpressionRenderer.PropositionBinding(
                            internalOpName(internalKind, declaration.name()),
                            propositionKind));
        }
        return names;
    }

    private String renderOperand(
            ScenarioExpression expression,
            Map<String, StepExpressionRenderer.PropositionBinding> internalNames) {
        if (expression instanceof ScenarioExpression.Step
                || expression instanceof ScenarioExpression.Reference) {
            return render(expression, internalNames);
        }
        return "(" + render(expression, internalNames) + ")";
    }

    private String renderSequence(
            ScenarioExpression expression,
            Map<String, StepExpressionRenderer.PropositionBinding> internalNames) {
        List<ScenarioExpression> terms = new ArrayList<ScenarioExpression>();
        collectSequenceTerms(expression, terms);
        StringJoiner joiner = new StringJoiner(" ; ");
        for (ScenarioExpression term : terms) {
            joiner.add(renderSequenceTerm(term, internalNames));
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

    private String renderSequenceTerm(
            ScenarioExpression expression,
            Map<String, StepExpressionRenderer.PropositionBinding> internalNames) {
        if (expression instanceof ScenarioExpression.Step
                || expression instanceof ScenarioExpression.Reference
                || expression instanceof ScenarioExpression.Star) {
            return render(expression, internalNames);
        }
        return "(" + render(expression, internalNames) + ")";
    }

    private String renderDefinition(String opName, String sort, String value) {
        return "  op "
                + opName
                + " : -> "
                + sort
                + " ."
                + System.lineSeparator()
                + "  eq "
                + opName
                + " = "
                + value
                + " .";
    }

    private static String internalOpName(String kind, String userName) {
        byte[] bytes = userName.getBytes(StandardCharsets.UTF_8);
        StringBuilder encodedName = new StringBuilder(bytes.length * 2);
        for (byte value : bytes) {
            int unsigned = value & 0xff;
            encodedName.append(HEX[unsigned >>> 4]);
            encodedName.append(HEX[unsigned & 0x0f]);
        }
        return "mtaScenarioDsl" + kind + "X" + encodedName;
    }
}
