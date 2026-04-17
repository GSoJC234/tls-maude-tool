package mta.user.scenario;

import java.util.Objects;

public class NodeLink {
    private String nodeId1;
    private String nodeId2;

    public NodeLink(String nodeId1, String nodeId2) {
        this.nodeId1 = nodeId1;
        this.nodeId2 = nodeId2;
    }

    public String getNodeId1() {
        return nodeId1;
    }

    public String getNodeId2() {
        return nodeId2;
    }

    public void setNodeId1(String nodeId1) {
        this.nodeId1 = nodeId1;
    }

    public void setNodeId2(String nodeId2) {
        this.nodeId2 = nodeId2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NodeLink nodeLink)) {
            return false;
        }
        return Objects.equals(nodeId1, nodeLink.nodeId1)
                && Objects.equals(nodeId2, nodeLink.nodeId2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId1, nodeId2);
    }

    @Override
    public String toString() {
        return "NodeLink{"
                + "nodeId1='" + nodeId1 + '\''
                + ", nodeId2='" + nodeId2 + '\''
                + '}';
    }
}
