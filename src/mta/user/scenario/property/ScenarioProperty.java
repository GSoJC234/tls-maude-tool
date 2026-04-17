package mta.user.scenario.property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ScenarioProperty {

    private String connectorId;
    private final List<NodeBinding> nodeBindings;
    private final List<String> linkIds;
    private final List<ConnectionStep> steps;

    public ScenarioProperty() {
        this.nodeBindings = new ArrayList<NodeBinding>();
        this.linkIds = new ArrayList<String>();
        this.steps = new ArrayList<ConnectionStep>();
    }

    public String getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(String connectorId) {
        this.connectorId = connectorId;
    }

    public List<NodeBinding> getNodeBindings() {
        return Collections.unmodifiableList(nodeBindings);
    }

    public void setNodeBindings(List<NodeBinding> nodeBindings) {
        this.nodeBindings.clear();
        if (nodeBindings != null) {
            this.nodeBindings.addAll(nodeBindings);
        }
    }

    public void addNodeBinding(NodeBinding nodeBinding) {
        if (nodeBinding != null) {
            this.nodeBindings.add(nodeBinding);
        }
    }

    public List<String> getLinkIds() {
        return Collections.unmodifiableList(linkIds);
    }

    public void setLinkIds(List<String> linkIds) {
        this.linkIds.clear();
        if (linkIds != null) {
            this.linkIds.addAll(linkIds);
        }
    }

    public void addLinkId(String linkId) {
        if (linkId != null) {
            this.linkIds.add(linkId);
        }
    }

    public List<ConnectionStep> getSteps() {
        return Collections.unmodifiableList(steps);
    }

    public void setSteps(List<ConnectionStep> steps) {
        this.steps.clear();
        if (steps != null) {
            this.steps.addAll(steps);
        }
    }

    public void addStep(ConnectionStep step) {
        if (step != null) {
            this.steps.add(step);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScenarioProperty that)) {
            return false;
        }
        return Objects.equals(connectorId, that.connectorId)
                && Objects.equals(nodeBindings, that.nodeBindings)
                && Objects.equals(linkIds, that.linkIds)
                && Objects.equals(steps, that.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectorId, nodeBindings, linkIds, steps);
    }

    @Override
    public String toString() {
        return "ScenarioProperty{"
                + "connectorId='" + connectorId + '\''
                + ", nodeBindings=" + nodeBindings
                + ", linkIds=" + linkIds
                + ", steps=" + steps
                + '}';
    }
}
