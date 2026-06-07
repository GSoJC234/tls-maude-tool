package mta.user.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public interface UserTerm {

    Set<String> parameterRefs();

    UserTerm substitute(Map<String, UserTerm> bindings);

    String source();

    record Atom(String text) implements UserTerm {
        public Atom {
            Objects.requireNonNull(text, "text must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return Collections.emptySet();
        }

        @Override
        public UserTerm substitute(Map<String, UserTerm> bindings) {
            return this;
        }

        @Override
        public String source() {
            return text;
        }
    }

    record StringLiteral(String value) implements UserTerm {
        public StringLiteral {
            Objects.requireNonNull(value, "value must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return Collections.emptySet();
        }

        @Override
        public UserTerm substitute(Map<String, UserTerm> bindings) {
            return this;
        }

        @Override
        public String source() {
            return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
        }
    }

    record RawMaude(String text) implements UserTerm {
        public RawMaude {
            Objects.requireNonNull(text, "text must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return Collections.emptySet();
        }

        @Override
        public UserTerm substitute(Map<String, UserTerm> bindings) {
            return this;
        }

        @Override
        public String source() {
            return "maude(\"" + text.replace("\\", "\\\\").replace("\"", "\\\"") + "\")";
        }
    }

    record Parameter(String name) implements UserTerm {
        public Parameter {
            Objects.requireNonNull(name, "name must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return Set.of(name);
        }

        @Override
        public UserTerm substitute(Map<String, UserTerm> bindings) {
            UserTerm replacement = bindings.get(name);
            if (replacement == null) {
                throw new IllegalArgumentException("Missing parameter binding: " + name);
            }
            return replacement;
        }

        @Override
        public String source() {
            return "$" + name;
        }
    }

    record Call(String name, List<UserTerm> arguments) implements UserTerm {
        public Call {
            Objects.requireNonNull(name, "name must not be null");
            arguments = immutableCopy(arguments);
        }

        @Override
        public Set<String> parameterRefs() {
            return collectParameterRefs(arguments);
        }

        @Override
        public UserTerm substitute(Map<String, UserTerm> bindings) {
            return new Call(name, substituteAll(arguments, bindings));
        }

        @Override
        public String source() {
            StringJoiner joiner = new StringJoiner(", ");
            for (UserTerm argument : arguments) {
                joiner.add(argument.source());
            }
            return name + "(" + joiner + ")";
        }
    }

    record ListTerm(List<UserTerm> values) implements UserTerm {
        public ListTerm {
            values = immutableCopy(values);
        }

        @Override
        public Set<String> parameterRefs() {
            return collectParameterRefs(values);
        }

        @Override
        public UserTerm substitute(Map<String, UserTerm> bindings) {
            return new ListTerm(substituteAll(values, bindings));
        }

        @Override
        public String source() {
            StringJoiner joiner = new StringJoiner(", ", "[", "]");
            for (UserTerm value : values) {
                joiner.add(value.source());
            }
            return joiner.toString();
        }
    }

    record BraceTerm(List<UserTerm> values) implements UserTerm {
        public BraceTerm {
            values = immutableCopy(values);
        }

        @Override
        public Set<String> parameterRefs() {
            return collectParameterRefs(values);
        }

        @Override
        public UserTerm substitute(Map<String, UserTerm> bindings) {
            return new BraceTerm(substituteAll(values, bindings));
        }

        @Override
        public String source() {
            StringJoiner joiner = new StringJoiner(", ", "{", "}");
            for (UserTerm value : values) {
                joiner.add(value.source());
            }
            return joiner.toString();
        }
    }

    record Indexed(String name, UserTerm index) implements UserTerm {
        public Indexed {
            Objects.requireNonNull(name, "name must not be null");
            Objects.requireNonNull(index, "index must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return index.parameterRefs();
        }

        @Override
        public UserTerm substitute(Map<String, UserTerm> bindings) {
            return new Indexed(name, index.substitute(bindings));
        }

        @Override
        public String source() {
            return name + "[" + index.source() + "]";
        }
    }

    record Dotted(List<UserTerm> parts) implements UserTerm {
        public Dotted {
            parts = immutableCopy(parts);
            if (parts.size() < 2) {
                throw new IllegalArgumentException("Dotted term requires at least two parts");
            }
        }

        @Override
        public Set<String> parameterRefs() {
            return collectParameterRefs(parts);
        }

        @Override
        public UserTerm substitute(Map<String, UserTerm> bindings) {
            return new Dotted(substituteAll(parts, bindings));
        }

        @Override
        public String source() {
            StringJoiner joiner = new StringJoiner(" . ");
            for (UserTerm part : parts) {
                joiner.add(part.source());
            }
            return joiner.toString();
        }
    }

    private static List<UserTerm> immutableCopy(List<UserTerm> values) {
        if (values == null || values.isEmpty()) {
            return List.of();
        }
        return List.copyOf(values);
    }

    private static List<UserTerm> substituteAll(List<UserTerm> values, Map<String, UserTerm> bindings) {
        List<UserTerm> substituted = new ArrayList<UserTerm>();
        for (UserTerm value : values) {
            substituted.add(value.substitute(bindings));
        }
        return substituted;
    }

    private static Set<String> collectParameterRefs(List<UserTerm> values) {
        Set<String> refs = new LinkedHashSet<String>();
        for (UserTerm value : values) {
            refs.addAll(value.parameterRefs());
        }
        return Collections.unmodifiableSet(refs);
    }
}
