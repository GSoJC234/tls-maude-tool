package mta.user.scenario;

import mta.user.common.ActionExpression;
import mta.user.common.ScenarioExpression;
import mta.user.common.StateExpression;
import mta.user.common.StepExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ScenarioSpec {

    private final List<PropositionDeclaration> propositionDeclarations =
            new ArrayList<PropositionDeclaration>();
    private final Map<String, String> declarationKinds = new LinkedHashMap<String, String>();
    private String currentScenarioPropertyName;
    private ScenarioExpression currentScenarioProperty;

    public interface PropositionDeclaration {
        String name();
    }

    public record StatePropositionDeclaration(String name, StateExpression expression)
            implements PropositionDeclaration {
        public StatePropositionDeclaration {
            requireName(name);
            Objects.requireNonNull(expression, "expression must not be null");
        }
    }

    public record ActionPropositionDeclaration(String name, ActionExpression expression)
            implements PropositionDeclaration {
        public ActionPropositionDeclaration {
            requireName(name);
            Objects.requireNonNull(expression, "expression must not be null");
        }
    }

    public List<PropositionDeclaration> getPropositionDeclarations() {
        return Collections.unmodifiableList(propositionDeclarations);
    }

    public void addStateProposition(String name, StateExpression expression) {
        ensureBeforeScenarioProperty("StateProposition");
        registerName(name, "StateProposition");
        propositionDeclarations.add(new StatePropositionDeclaration(name, expression));
    }

    public void addActionProposition(String name, ActionExpression expression) {
        ensureBeforeScenarioProperty("ActionProposition");
        registerName(name, "ActionProposition");
        propositionDeclarations.add(new ActionPropositionDeclaration(name, expression));
    }

    public String getCurrentScenarioPropertyName() {
        return currentScenarioPropertyName;
    }

    public ScenarioExpression getCurrentScenarioProperty() {
        return currentScenarioProperty;
    }

    public void setCurrentScenarioProperty(ScenarioExpression currentScenarioProperty) {
        this.currentScenarioPropertyName = null;
        this.currentScenarioProperty = currentScenarioProperty;
    }

    public void declareScenarioProperty(String name, ScenarioExpression expression) {
        if (name != null) {
            registerName(name, "ScenarioProperty");
        }
        if (currentScenarioProperty != null) {
            throw new IllegalArgumentException(
                    "Scenario spec must contain exactly one executable ScenarioProperty; found multiple");
        }
        currentScenarioPropertyName = name;
        currentScenarioProperty = Objects.requireNonNull(expression, "expression must not be null");
    }

    public void validate() {
        if (currentScenarioProperty == null) {
            throw new IllegalArgumentException(
                    "Scenario spec must contain exactly one executable ScenarioProperty; found none");
        }

        Set<String> references = new LinkedHashSet<String>();
        collectReferences(currentScenarioProperty, references);
        for (String reference : references) {
            if (!isStateOrActionProposition(reference)) {
                throw new IllegalArgumentException("Undefined State/Action proposition reference '"
                        + reference
                        + "' in ScenarioProperty");
            }
        }
    }

    private boolean isStateOrActionProposition(String name) {
        String kind = declarationKinds.get(name);
        return "StateProposition".equals(kind) || "ActionProposition".equals(kind);
    }

    private void ensureBeforeScenarioProperty(String kind) {
        if (currentScenarioProperty != null) {
            throw new IllegalArgumentException(kind + " declarations must appear before ScenarioProperty");
        }
    }

    private void registerName(String name, String kind) {
        requireName(name);
        if (("StateProposition".equals(kind) || "ActionProposition".equals(kind))
                && ("any".equals(name) || "anyStep".equals(name))) {
            throw new IllegalArgumentException("State/Action proposition name '"
                    + name
                    + "' is reserved for the any-step scenario operator");
        }
        String previousKind = declarationKinds.putIfAbsent(name, kind);
        if (previousKind != null) {
            throw new IllegalArgumentException("Duplicate proposition name '"
                    + name
                    + "': already declared as "
                    + previousKind
                    + "; cannot redeclare as "
                    + kind);
        }
    }

    private static void collectReferences(ScenarioExpression expression, Set<String> references) {
        if (expression instanceof ScenarioExpression.Reference reference) {
            references.add(reference.name());
            return;
        }
        if (expression instanceof ScenarioExpression.Star star) {
            collectReferences(star.expression(), references);
            return;
        }
        if (expression instanceof ScenarioExpression.Step step) {
            collectReferences(step.expression(), references);
            return;
        }
        if (expression instanceof ScenarioExpression.Sequence sequence) {
            collectReferences(sequence.left(), references);
            collectReferences(sequence.right(), references);
            return;
        }
        if (expression instanceof ScenarioExpression.Choice choice) {
            collectReferences(choice.left(), references);
            collectReferences(choice.right(), references);
        }
    }

    private static void collectReferences(StepExpression expression, Set<String> references) {
        if (expression instanceof StepExpression.Reference reference) {
            references.add(reference.name());
            return;
        }
        if (expression instanceof StepExpression.Not not) {
            collectReferences(not.expression(), references);
            return;
        }
        if (expression instanceof StepExpression.Binary binary) {
            collectReferences(binary.left(), references);
            collectReferences(binary.right(), references);
        }
    }

    private static void requireName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Proposition name must not be blank");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScenarioSpec that)) {
            return false;
        }
        return Objects.equals(propositionDeclarations, that.propositionDeclarations)
                && Objects.equals(currentScenarioPropertyName, that.currentScenarioPropertyName)
                && Objects.equals(currentScenarioProperty, that.currentScenarioProperty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propositionDeclarations, currentScenarioPropertyName, currentScenarioProperty);
    }

    @Override
    public String toString() {
        return "ScenarioSpec{"
                + "propositionDeclarations=" + propositionDeclarations
                + ", currentScenarioPropertyName='" + currentScenarioPropertyName + '\''
                + ", currentScenarioProperty=" + currentScenarioProperty
                + '}';
    }
}
