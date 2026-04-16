package mta.user.scenario.property;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ConnectionStep {

    private String referenceId;
    private final Map<String, String> argumentBindings;
    private PropertyRelation propertyRelation;

    public ConnectionStep() {
        this.argumentBindings = new LinkedHashMap<String, String>();
    }

    public PropertyRelation getPropertyRelation() {
        return propertyRelation;
    }
    public void setPropertyRelation(PropertyRelation propertyRelation) {
        this.propertyRelation = propertyRelation;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Map<String, String> getArgumentBindings() {
        return Collections.unmodifiableMap(argumentBindings);
    }

    public void setArgumentBindings(Map<String, String> argumentBindings) {
        this.argumentBindings.clear();
        if (argumentBindings != null) {
            this.argumentBindings.putAll(argumentBindings);
        }
    }

    public void addArgumentBinding(String parameter, String value) {
        if (parameter != null && value != null) {
            this.argumentBindings.put(parameter, value);
        }
    }

    public boolean hasArgumentBindings() {
        return !argumentBindings.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConnectionStep that)) {
            return false;
        }
        return Objects.equals(propertyRelation, that.propertyRelation)
                && Objects.equals(referenceId, that.referenceId)
                && Objects.equals(argumentBindings, that.argumentBindings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyRelation, referenceId, argumentBindings);
    }

    @Override
    public String toString() {
        return "ScenarioStep{"
                + "propertyRelation=" + propertyRelation
                + ", referenceId='" + referenceId + '\''
                + ", argumentBindings=" + argumentBindings
                + '}';
    }
}
