package mta.user.scenario.property;

import java.util.Objects;

public class NodeBinding {

    private String nodeId;
    private String profileAlias;

    public NodeBinding() {
    }

    public NodeBinding(String nodeId, String profileAlias) {
        this.nodeId = nodeId;
        this.profileAlias = profileAlias;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getProfileAlias() {
        return profileAlias;
    }

    public void setProfileAlias(String profileAlias) {
        this.profileAlias = profileAlias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NodeBinding that)) {
            return false;
        }
        return Objects.equals(nodeId, that.nodeId)
                && Objects.equals(profileAlias, that.profileAlias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, profileAlias);
    }

    @Override
    public String toString() {
        return "NodeBinding{"
                + "nodeId='" + nodeId + '\''
                + ", profileAlias='" + profileAlias + '\''
                + '}';
    }
}
