package mta.user.common;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public interface ScenarioExpression {

    Set<String> parameterRefs();

    ScenarioExpression substitute(Map<String, UserTerm> bindings);

    record RawMaude(String text) implements ScenarioExpression {
        public RawMaude {
            Objects.requireNonNull(text, "text must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return Collections.emptySet();
        }

        @Override
        public ScenarioExpression substitute(Map<String, UserTerm> bindings) {
            return this;
        }
    }

    record Step(StepExpression expression) implements ScenarioExpression {
        public Step {
            Objects.requireNonNull(expression, "expression must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return expression.parameterRefs();
        }

        @Override
        public ScenarioExpression substitute(Map<String, UserTerm> bindings) {
            return new Step(expression.substitute(bindings));
        }
    }

    record Star(ScenarioExpression expression) implements ScenarioExpression {
        public Star {
            Objects.requireNonNull(expression, "expression must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return expression.parameterRefs();
        }

        @Override
        public ScenarioExpression substitute(Map<String, UserTerm> bindings) {
            return new Star(expression.substitute(bindings));
        }
    }

    record Sequence(ScenarioExpression left, ScenarioExpression right) implements ScenarioExpression {
        public Sequence {
            Objects.requireNonNull(left, "left must not be null");
            Objects.requireNonNull(right, "right must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return refs(left, right);
        }

        @Override
        public ScenarioExpression substitute(Map<String, UserTerm> bindings) {
            return new Sequence(left.substitute(bindings), right.substitute(bindings));
        }
    }

    record Choice(ScenarioExpression left, ScenarioExpression right) implements ScenarioExpression {
        public Choice {
            Objects.requireNonNull(left, "left must not be null");
            Objects.requireNonNull(right, "right must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return refs(left, right);
        }

        @Override
        public ScenarioExpression substitute(Map<String, UserTerm> bindings) {
            return new Choice(left.substitute(bindings), right.substitute(bindings));
        }
    }

    private static Set<String> refs(ScenarioExpression left, ScenarioExpression right) {
        Set<String> refs = new LinkedHashSet<String>();
        refs.addAll(left.parameterRefs());
        refs.addAll(right.parameterRefs());
        return Collections.unmodifiableSet(refs);
    }
}
