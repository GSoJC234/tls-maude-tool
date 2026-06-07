package mta.user.common;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public interface StateExpression {

    Set<String> parameterRefs();

    StateExpression substitute(Map<String, UserTerm> bindings);

    record Atom(UserTerm object, String attribute, UserTerm value) implements StateExpression {
        public Atom {
            Objects.requireNonNull(object, "object must not be null");
            Objects.requireNonNull(attribute, "attribute must not be null");
            Objects.requireNonNull(value, "value must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            Set<String> refs = new LinkedHashSet<String>();
            refs.addAll(object.parameterRefs());
            refs.addAll(value.parameterRefs());
            return Collections.unmodifiableSet(refs);
        }

        @Override
        public StateExpression substitute(Map<String, UserTerm> bindings) {
            return new Atom(object.substitute(bindings), attribute, value.substitute(bindings));
        }
    }

    record Not(StateExpression expression) implements StateExpression {
        public Not {
            Objects.requireNonNull(expression, "expression must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return expression.parameterRefs();
        }

        @Override
        public StateExpression substitute(Map<String, UserTerm> bindings) {
            return new Not(expression.substitute(bindings));
        }
    }

    record Binary(Operator operator, StateExpression left, StateExpression right) implements StateExpression {
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
        public StateExpression substitute(Map<String, UserTerm> bindings) {
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
