package mta.user.behavior;

import mta.user.behavior.condition.BehaviorCondition;
import mta.user.behavior.modification.BehaviorModification;
import mta.user.behavior.modification.item.BehaviorModificationItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BehaviorSpec {

    private String behaviorId;
    private final List<String> parameters;
    private EventType eventType;
    private BehaviorCondition conditions;
    private BehaviorModification modifications;

    public BehaviorSpec() {
        this.parameters = new ArrayList<String>();
        this.conditions = new BehaviorCondition();
        this.modifications = new BehaviorModification();
    }

    public String getBehaviorId() {
        return behaviorId;
    }

    public void setBehaviorId(String behaviorId) {
        this.behaviorId = behaviorId;
    }

    public List<String> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public void setParameters(List<String> parameters) {
        this.parameters.clear();
        if (parameters != null) {
            this.parameters.addAll(parameters);
        }
    }

    public void addParameter(String parameter) {
        if (parameter != null) {
            this.parameters.add(parameter);
        }
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = EventType.fromValue(eventType);
    }

    public BehaviorCondition getConditions() {
        return conditions;
    }

    public void setConditions(BehaviorCondition conditions) {
        this.conditions = conditions != null ? conditions : new BehaviorCondition();
    }

    public void setConditionExpression(mta.user.behavior.condition.BehaviorConditionExpr expr) {
        this.conditions.setExpression(expr);
    }

    public BehaviorModification getModifications() {
        return modifications;
    }

    public void setModifications(BehaviorModification modifications) {
        this.modifications = modifications != null ? modifications : new BehaviorModification();
    }

    public void addModification(BehaviorModificationItem modification) {
        if (modification != null) {
            this.modifications.add(modification);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BehaviorSpec that)) {
            return false;
        }
        return Objects.equals(behaviorId, that.behaviorId)
                && Objects.equals(parameters, that.parameters)
                && eventType == that.eventType
                && Objects.equals(conditions, that.conditions)
                && Objects.equals(modifications, that.modifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(behaviorId, parameters, eventType, conditions, modifications);
    }

    @Override
    public String toString() {
        return "BehaviorSpec{"
                + "behaviorId='" + behaviorId + '\''
                + ", parameters=" + parameters
                + ", eventType=" + eventType
                + ", conditions=" + conditions
                + ", modifications=" + modifications
                + '}';
    }
}
