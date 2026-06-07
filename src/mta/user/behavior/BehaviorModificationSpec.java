package mta.user.behavior;

import mta.user.common.UserTerm;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public interface BehaviorModificationSpec {

    Set<String> parameterRefs();

    BehaviorModificationSpec substitute(Map<String, UserTerm> bindings);

    record RawMaude(String text) implements BehaviorModificationSpec {
        public RawMaude {
            Objects.requireNonNull(text, "text must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return Collections.emptySet();
        }

        @Override
        public BehaviorModificationSpec substitute(Map<String, UserTerm> bindings) {
            return this;
        }
    }

    record SetMessage(UserTerm field, UserTerm value) implements BehaviorModificationSpec {
        public SetMessage {
            Objects.requireNonNull(field, "field must not be null");
            Objects.requireNonNull(value, "value must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return refs(field, value);
        }

        @Override
        public BehaviorModificationSpec substitute(Map<String, UserTerm> bindings) {
            return new SetMessage(field.substitute(bindings), value.substitute(bindings));
        }
    }

    record SetFeature(UserTerm field, UserTerm value) implements BehaviorModificationSpec {
        public SetFeature {
            Objects.requireNonNull(field, "field must not be null");
            Objects.requireNonNull(value, "value must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return refs(field, value);
        }

        @Override
        public BehaviorModificationSpec substitute(Map<String, UserTerm> bindings) {
            return new SetFeature(field.substitute(bindings), value.substitute(bindings));
        }
    }

    record AddMessage(UserTerm field, UserTerm value) implements BehaviorModificationSpec {
        public AddMessage {
            Objects.requireNonNull(field, "field must not be null");
            Objects.requireNonNull(value, "value must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return refs(field, value);
        }

        @Override
        public BehaviorModificationSpec substitute(Map<String, UserTerm> bindings) {
            return new AddMessage(field.substitute(bindings), value.substitute(bindings));
        }
    }

    record RemoveMessage(UserTerm field) implements BehaviorModificationSpec {
        public RemoveMessage {
            Objects.requireNonNull(field, "field must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return field.parameterRefs();
        }

        @Override
        public BehaviorModificationSpec substitute(Map<String, UserTerm> bindings) {
            return new RemoveMessage(field.substitute(bindings));
        }
    }

    record NoCheck(UserTerm label) implements BehaviorModificationSpec {
        public NoCheck {
            Objects.requireNonNull(label, "label must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return label.parameterRefs();
        }

        @Override
        public BehaviorModificationSpec substitute(Map<String, UserTerm> bindings) {
            return new NoCheck(label.substitute(bindings));
        }
    }

    record Skip() implements BehaviorModificationSpec {
        @Override
        public Set<String> parameterRefs() {
            return Collections.emptySet();
        }

        @Override
        public BehaviorModificationSpec substitute(Map<String, UserTerm> bindings) {
            return this;
        }
    }

    record Delay(UserTerm handshakeType) implements BehaviorModificationSpec {
        public Delay {
            Objects.requireNonNull(handshakeType, "handshakeType must not be null");
        }

        @Override
        public Set<String> parameterRefs() {
            return handshakeType.parameterRefs();
        }

        @Override
        public BehaviorModificationSpec substitute(Map<String, UserTerm> bindings) {
            return new Delay(handshakeType.substitute(bindings));
        }
    }

    private static Set<String> refs(UserTerm first, UserTerm second) {
        Set<String> refs = new LinkedHashSet<String>();
        refs.addAll(first.parameterRefs());
        refs.addAll(second.parameterRefs());
        return Collections.unmodifiableSet(refs);
    }
}
