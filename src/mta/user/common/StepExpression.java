package mta.user.common;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public interface StepExpression {

    Set<String> parameterRefs();

    StepExpression substitute(Map<String, UserTerm> bindings);

    record AnyStep() implements StepExpression {
        @Override
        public Set<String> parameterRefs() {
            return Collections.emptySet();
        }

        @Override
        public StepExpression substitute(Map<String, UserTerm> bindings) {
            return this;
        }
    }

    record Action(ActionExpression expression) implements StepExpression {
        public Action {
            Objects.requireNonNull(expression, "expression must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return expression.parameterRefs();
        }

        @Override
        public StepExpression substitute(Map<String, UserTerm> bindings) {
            return new Action(expression.substitute(bindings));
        }
    }

    record State(StateExpression expression) implements StepExpression {
        public State {
            Objects.requireNonNull(expression, "expression must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return expression.parameterRefs();
        }

        @Override
        public StepExpression substitute(Map<String, UserTerm> bindings) {
            return new State(expression.substitute(bindings));
        }
    }

    record Not(StepExpression expression) implements StepExpression {
        public Not {
            Objects.requireNonNull(expression, "expression must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return expression.parameterRefs();
        }

        @Override
        public StepExpression substitute(Map<String, UserTerm> bindings) {
            return new Not(expression.substitute(bindings));
        }
    }

    record Binary(Operator operator, StepExpression left, StepExpression right) implements StepExpression {
        public Binary {
            Objects.requireNonNull(operator, "operator must not be null");
            Objects.requireNonNull(left, "left must not be null");
            Objects.requireNonNull(right, "right must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            Set<String> refs = new LinkedHashSet<String>();
            refs.addAll(left.parameterRefs());
            refs.addAll(right.parameterRefs());
            return Collections.unmodifiableSet(refs);
        }

        @Override
        public StepExpression substitute(Map<String, UserTerm> bindings) {
            return new Binary(operator, left.substitute(bindings), right.substitute(bindings));
        }
    }

    enum Operator {
        AND("and"),
        OR("or");

        private final String maudeOperator;

        Operator(String maudeOperator) {
            this.maudeOperator = maudeOperator;
        }

        public String maudeOperator() {
            return maudeOperator;
        }
    }
}
