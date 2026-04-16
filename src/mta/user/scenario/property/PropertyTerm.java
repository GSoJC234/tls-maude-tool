package mta.user.scenario.property;

import java.util.Objects;

public class PropertyTerm extends PropertyRelation {
    private String statePropositionId;
    private String actionPropositionId;

    private PropertyTerm(String statePropositionId, String actionPropositionId) {
        if ((statePropositionId == null) == (actionPropositionId == null)) {
            throw new IllegalArgumentException("Property term should be either statePropostionId or actionPropositionId");
        }
        this.statePropositionId = statePropositionId;
        this.actionPropositionId = actionPropositionId;
    }

    public static PropertyTerm stateProposition(String statePropositionId) {
        return new PropertyTerm(statePropositionId, null);
    }

    public static PropertyTerm actionProposition(String actionPropositionId) {
        return new PropertyTerm(null, actionPropositionId);
    }

    public String getStatePropositionId() {
        return statePropositionId;
    }

    public String getActionPropositionId() {
        return actionPropositionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PropertyTerm that)) {
            return false;
        }
        return Objects.equals(statePropositionId, that.statePropositionId)
                && Objects.equals(actionPropositionId, that.actionPropositionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statePropositionId, actionPropositionId);
    }

    @Override
    public String toString() {
        return "PropertyTerm{"
                + "statePropositionId='" + statePropositionId + '\''
                + ", actionPropositionId='" + actionPropositionId + '\''
                + '}';
    }
}
