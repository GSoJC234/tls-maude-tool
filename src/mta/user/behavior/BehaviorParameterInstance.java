package mta.user.behavior;

import mta.user.common.UserTerm;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class BehaviorParameterInstance {

    private final Map<String, UserTerm> bindings = new LinkedHashMap<String, UserTerm>();

    public Map<String, UserTerm> getBindings() {
        return Collections.unmodifiableMap(bindings);
    }

    public void addBinding(String parameterName, UserTerm value) {
        if (parameterName == null || value == null) {
            return;
        }
        if (bindings.containsKey(parameterName)) {
            throw new IllegalArgumentException("Duplicate parameter binding: " + parameterName);
        }
        bindings.put(parameterName, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BehaviorParameterInstance that)) {
            return false;
        }
        return Objects.equals(bindings, that.bindings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bindings);
    }

    @Override
    public String toString() {
        return "BehaviorParameterInstance{"
                + "bindings=" + bindings
                + '}';
    }
}
