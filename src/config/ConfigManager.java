package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.Config;
import config.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigManager {

    private Config config;
    private Node testerNode;
    private Node targetNode;

    public void loadFromJson(String jsonFilePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.config = mapper.readValue(new File(jsonFilePath), Config.class);
            initializeNodes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON config file: " + jsonFilePath, e);
        }
    }

    private void initializeNodes() {
        for (Node node : config.getNodes()) {
            if ("tester".equalsIgnoreCase(node.getRole())) {
                testerNode = node;
            } else if ("target".equalsIgnoreCase(node.getRole())) {
                targetNode = node;
            }
        }

        if (testerNode == null || targetNode == null) {
            throw new IllegalStateException("Both tester and target nodes must be defined.");
        }
    }

    public String getTesterIp() {
        return testerNode.getIp();
    }

    public int getTesterPort() {
        return testerNode.getPort();
    }

    public String getTesterId() {
        return testerNode.getId();
    }

    public String getTesterPrivateKeyPath() {
        return testerNode.getPrivateKeyPath();
    }

    public String getTesterCertificatePath() {
        return testerNode.getCertificatePath();
    }

    public String getTargetId() {
        return targetNode.getId();
    }

    public String getTargetIp() {
        return targetNode.getIp();
    }

    public int getTargetPort() {
        return targetNode.getPort();
    }

    public String getTargetRole() {
        return targetNode.getTarget();  // "openssl", "wolfssl" 등
    }

    public String getTargetPath() {
        return targetNode.getTargetPath();
    }

    public String getTargetOutputPath() {
        return targetNode.getOutputPath();
    }

    public String getTesterOutputPath() {
        return testerNode.getOutputPath();
    }

    public String getTargetCACertificatePath() {
        return targetNode.getCertificatePath();
    }

    public String getTesterCACertificatePath() {
        return testerNode.getCertificatePath();
    }

    public boolean isClientTarget() {
        return "client".equalsIgnoreCase(targetNode.getMode());
    }

    public boolean isClientTester() {
        return "client".equalsIgnoreCase(testerNode.getMode());
    }

    public List<Integer> getScenarioNumbers() {
        return config.getScenario();
    }

    public Map<String, String> getTargetConfiguration(int scenarioNum) {
        ScenarioManager scenarioManager = new ScenarioManager(scenarioNum, isClientTester());
        return scenarioManager.getConfiguration();
    }

    public String getScenarioContent(int scenarioNum) {
        ScenarioManager scenarioManager = new ScenarioManager(scenarioNum, isClientTester());
        return scenarioManager.getScenario();
    }
}