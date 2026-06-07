package mta.user.common;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public interface ActionExpression {

    Set<String> parameterRefs();

    ActionExpression substitute(Map<String, UserTerm> bindings);

    record RawMaude(String text) implements ActionExpression {
        public RawMaude {
            Objects.requireNonNull(text, "text must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return Collections.emptySet();
        }

        @Override
        public ActionExpression substitute(Map<String, UserTerm> bindings) {
            return this;
        }
    }

    record Atom(String field, UserTerm value) implements ActionExpression {
        public Atom {
            Objects.requireNonNull(field, "field must not be null");
            Objects.requireNonNull(value, "value must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return value.parameterRefs();
        }

        @Override
        public ActionExpression substitute(Map<String, UserTerm> bindings) {
            return new Atom(field, value.substitute(bindings));
        }
    }

    record Not(ActionExpression expression) implements ActionExpression {
        public Not {
            Objects.requireNonNull(expression, "expression must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return expression.parameterRefs();
        }

        @Override
        public ActionExpression substitute(Map<String, UserTerm> bindings) {
            return new Not(expression.substitute(bindings));
        }
    }

    record Binary(Operator operator, ActionExpression left, ActionExpression right) implements ActionExpression {
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
        public ActionExpression substitute(Map<String, UserTerm> bindings) {
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
