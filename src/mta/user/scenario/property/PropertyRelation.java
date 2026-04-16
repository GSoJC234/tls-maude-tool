package mta.user.scenario.property;

import mta.user.scenario.property.operation.PropertyRelationOperation;

import java.util.Objects;

public class PropertyRelation {
    private PropertyRelation leftRelation;
    private PropertyRelation rightRelation;
    private PropertyRelationOperation operation;

    public PropertyRelation getLeftRelation() {
        return leftRelation;
    }
    public void setLeftRelation(PropertyRelation leftRelation) {
        this.leftRelation = leftRelation;
    }
    public PropertyRelation getRightRelation() {
        return rightRelation;
    }
    public void setRightRelation(PropertyRelation rightRelation) {
        this.rightRelation = rightRelation;
    }
    public PropertyRelationOperation getOperation() {
        return this.operation;
    }
    public void setOperation(PropertyRelationOperation operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PropertyRelation that)) {
            return false;
        }
        return Objects.equals(leftRelation, that.leftRelation)
                && Objects.equals(rightRelation, that.rightRelation)
                && Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftRelation, rightRelation, operation);
    }

    @Override
    public String toString() {
        return "PropertyRelation{"
                + "leftRelation=" + leftRelation
                + ", rightRelation=" + rightRelation
                + ", operation=" + operation
                + '}';
    }
}
