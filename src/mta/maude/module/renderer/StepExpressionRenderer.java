package mta.maude.module.renderer;

import mta.user.common.ActionExpression;
import mta.user.common.StateExpression;
import mta.user.common.StepExpression;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class StepExpressionRenderer {

    private static final int MAX_NORMALIZED_CLAUSES = 1024;

    public enum PropositionKind {
        STATE,
        ACTION
    }

    public record PropositionBinding(String maudeName, PropositionKind kind) {
        public PropositionBinding {
            if (maudeName == null || maudeName.isBlank()) {
                throw new IllegalArgumentException("Maude proposition name must not be blank");
            }
            Objects.requireNonNull(kind, "Proposition kind must not be null");
        }
    }

    private record Literal(PropositionKind kind, String value, boolean negated) {}

    private record Clause(List<Literal> stateLiterals, List<Literal> actionLiterals) {
        private Clause {
            stateLiterals = List.copyOf(stateLiterals);
            actionLiterals = List.copyOf(actionLiterals);
        }
    }

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
        return render(expression, Map.of());
    }

    public String render(StepExpression expression,
                         Map<String, PropositionBinding> propositionBindings) {
        EnumSet<PropositionKind> kinds = collectKinds(expression, propositionBindings);
        if (kinds.size() <= 1) {
            return renderHomogeneous(expression, propositionBindings);
        }
        return renderNormalizedMixed(expression, propositionBindings);
    }

    public String renderAction(ActionExpression expression) {
        return actionExpressionRenderer.render(expression);
    }

    public String renderState(StateExpression expression) {
        return stateExpressionRenderer.render(expression);
    }

    private EnumSet<PropositionKind> collectKinds(
            StepExpression expression,
            Map<String, PropositionBinding> propositionBindings) {
        if (expression instanceof StepExpression.AnyStep) {
            return EnumSet.noneOf(PropositionKind.class);
        }
        if (expression instanceof StepExpression.Reference reference) {
            return EnumSet.of(resolveBinding(reference, propositionBindings).kind());
        }
        if (expression instanceof StepExpression.Action) {
            return EnumSet.of(PropositionKind.ACTION);
        }
        if (expression instanceof StepExpression.State) {
            return EnumSet.of(PropositionKind.STATE);
        }
        if (expression instanceof StepExpression.Not not) {
            return collectKinds(not.expression(), propositionBindings);
        }
        if (expression instanceof StepExpression.Binary binary) {
            EnumSet<PropositionKind> kinds = collectKinds(binary.left(), propositionBindings);
            kinds.addAll(collectKinds(binary.right(), propositionBindings));
            return kinds;
        }
        throw new IllegalArgumentException("Unsupported step expression: " + expression);
    }

    private String renderHomogeneous(
            StepExpression expression,
            Map<String, PropositionBinding> propositionBindings) {
        if (expression instanceof StepExpression.AnyStep) {
            return "anyStep";
        }
        if (expression instanceof StepExpression.Reference reference) {
            return resolveBinding(reference, propositionBindings).maudeName();
        }
        if (expression instanceof StepExpression.Action action) {
            return actionExpressionRenderer.render(action.expression());
        }
        if (expression instanceof StepExpression.State state) {
            return stateExpressionRenderer.render(state.expression());
        }
        if (expression instanceof StepExpression.Not not) {
            return "not " + renderHomogeneousOperand(not.expression(), propositionBindings);
        }
        if (expression instanceof StepExpression.Binary binary) {
            return renderHomogeneousOperand(binary.left(), propositionBindings)
                    + " "
                    + binary.operator().maudeOperator()
                    + " "
                    + renderHomogeneousOperand(binary.right(), propositionBindings);
        }
        throw new IllegalArgumentException("Unsupported step expression: " + expression);
    }

    private String renderHomogeneousOperand(
            StepExpression expression,
            Map<String, PropositionBinding> propositionBindings) {
        if (expression instanceof StepExpression.Action
                || expression instanceof StepExpression.State
                || expression instanceof StepExpression.Reference
                || expression instanceof StepExpression.AnyStep) {
            return renderHomogeneous(expression, propositionBindings);
        }
        return "(" + renderHomogeneous(expression, propositionBindings) + ")";
    }

    private String renderNormalizedMixed(
            StepExpression expression,
            Map<String, PropositionBinding> propositionBindings) {
        List<Clause> clauses = normalize(expression, false, propositionBindings);
        StringJoiner choice = new StringJoiner(" or ");
        for (Clause clause : clauses) {
            choice.add(renderClause(clause));
        }
        return choice.toString();
    }

    private List<Clause> normalize(
            StepExpression expression,
            boolean negated,
            Map<String, PropositionBinding> propositionBindings) {
        if (expression instanceof StepExpression.AnyStep) {
            throw new IllegalArgumentException(
                    "anyStep cannot be combined in a Boolean step expression");
        }
        if (expression instanceof StepExpression.Reference reference) {
            PropositionBinding binding = resolveBinding(reference, propositionBindings);
            return singletonClause(new Literal(binding.kind(), binding.maudeName(), negated));
        }
        if (expression instanceof StepExpression.Action action) {
            return singletonClause(new Literal(
                    PropositionKind.ACTION,
                    actionExpressionRenderer.render(action.expression()),
                    negated));
        }
        if (expression instanceof StepExpression.State state) {
            return singletonClause(new Literal(
                    PropositionKind.STATE,
                    stateExpressionRenderer.render(state.expression()),
                    negated));
        }
        if (expression instanceof StepExpression.Not not) {
            return normalize(not.expression(), !negated, propositionBindings);
        }
        if (expression instanceof StepExpression.Binary binary) {
            boolean conjunction = binary.operator() == StepExpression.Operator.AND;
            if (negated) {
                conjunction = !conjunction;
            }
            List<Clause> left = normalize(binary.left(), negated, propositionBindings);
            List<Clause> right = normalize(binary.right(), negated, propositionBindings);
            return conjunction ? conjoin(left, right) : disjoin(left, right);
        }
        throw new IllegalArgumentException("Unsupported step expression: " + expression);
    }

    private List<Clause> singletonClause(Literal literal) {
        if (literal.kind() == PropositionKind.STATE) {
            return List.of(new Clause(List.of(literal), List.of()));
        }
        return List.of(new Clause(List.of(), List.of(literal)));
    }

    private List<Clause> disjoin(List<Clause> left, List<Clause> right) {
        ensureClauseLimit((long) left.size() + right.size());
        List<Clause> result = new ArrayList<Clause>(left.size() + right.size());
        result.addAll(left);
        result.addAll(right);
        return result;
    }

    private List<Clause> conjoin(List<Clause> left, List<Clause> right) {
        ensureClauseLimit((long) left.size() * right.size());
        List<Clause> result = new ArrayList<Clause>(left.size() * right.size());
        for (Clause leftClause : left) {
            for (Clause rightClause : right) {
                List<Literal> states = new ArrayList<Literal>(
                        leftClause.stateLiterals().size() + rightClause.stateLiterals().size());
                states.addAll(leftClause.stateLiterals());
                states.addAll(rightClause.stateLiterals());
                List<Literal> actions = new ArrayList<Literal>(
                        leftClause.actionLiterals().size() + rightClause.actionLiterals().size());
                actions.addAll(leftClause.actionLiterals());
                actions.addAll(rightClause.actionLiterals());
                result.add(new Clause(states, actions));
            }
        }
        return result;
    }

    private void ensureClauseLimit(long clauseCount) {
        if (clauseCount > MAX_NORMALIZED_CLAUSES) {
            throw new IllegalArgumentException(
                    "Mixed proposition Boolean expression expands beyond "
                            + MAX_NORMALIZED_CLAUSES
                            + " normalized clauses");
        }
    }

    private String renderClause(Clause clause) {
        if (clause.stateLiterals().isEmpty()) {
            return renderConjunction(clause.actionLiterals());
        }
        if (clause.actionLiterals().isEmpty()) {
            return renderConjunction(clause.stateLiterals());
        }
        return groupConjunction(clause.stateLiterals())
                + " and "
                + groupConjunction(clause.actionLiterals());
    }

    private String groupConjunction(List<Literal> literals) {
        String rendered = renderConjunction(literals);
        return literals.size() == 1 ? rendered : "(" + rendered + ")";
    }

    private String renderConjunction(List<Literal> literals) {
        StringJoiner conjunction = new StringJoiner(" and ");
        for (Literal literal : literals) {
            conjunction.add(renderLiteral(literal));
        }
        return conjunction.toString();
    }

    private String renderLiteral(Literal literal) {
        return literal.negated() ? "not " + literal.value() : literal.value();
    }

    private PropositionBinding resolveBinding(
            StepExpression.Reference reference,
            Map<String, PropositionBinding> propositionBindings) {
        PropositionBinding binding = propositionBindings.get(reference.name());
        if (binding == null) {
            throw new IllegalArgumentException(
                    "Unresolved proposition reference '" + reference.name() + "'");
        }
        return binding;
    }
}
