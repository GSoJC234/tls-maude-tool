package mta.user.scenario.state;

import mta.user.behavior.condition.BehaviorCondition;

public class StatePropositionTerm extends StatePropositionExpression {
    private String nodeId;
    private BehaviorCondition condition;

    public StatePropositionTerm(String nodeId, BehaviorCondition condition) {
        this.nodeId = nodeId;
        this.condition = condition;
    }

    public String getNodeId() {
        return nodeId;
    }
    public BehaviorCondition getCondition() {
        return condition;
    }
    public void setCondition(BehaviorCondition condition) {
        this.condition = condition;
    }
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
