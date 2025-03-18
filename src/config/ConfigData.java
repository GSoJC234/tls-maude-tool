package config;

import java.util.HashMap;
import java.util.Map;

public class ConfigData {
    private int requirement;
    private Map<String, NodeInfo> nodeInfo;
    private String certificate;
    private String privateKey;

    public ConfigData(int requirement) {
        this.requirement = requirement;
        this.nodeInfo = new HashMap<>();
    }

    public int getRequirement() { return requirement; }
    public Map<String, NodeInfo> getNodeInfo() { return nodeInfo; }
    public void addNodes(String id, NodeInfo server) { nodeInfo.put(id, server); }
    public String getCertificatePath() { return certificate; }
    public void setCertificatePath(String certificate) { this.certificate = certificate; }
    public String getPrivateKeyPath() { return privateKey; }
    public void setPrivateKeyPath(String privateKey) { this.privateKey = privateKey; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Requirement: " + requirement + "\n");
        for (NodeInfo server : nodeInfo.values()) {
            sb.append(server).append("\n");
        }
        if (certificate != null) {
            sb.append("Certificate: ").append(certificate).append("\n");
        }
        if (privateKey != null) {
            sb.append("PrivateKey: ").append(privateKey).append("\n");
        }
        return sb.toString();
    }
}
