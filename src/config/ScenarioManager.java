package config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScenarioManager {
    private final int scenarioNumber;
    private final boolean isClientTester;
    private final Path scenarioPath;
    private String scenarioContent;

    public ScenarioManager(int scenarioNumber, boolean isClientTester) {
        this.scenarioNumber = scenarioNumber;
        this.isClientTester = isClientTester;
        this.scenarioPath = Paths.get("resources", "test_scenario", getModeDir(), "scenario" + scenarioNumber);
        loadScenarioContent();
    }

    private void loadScenarioContent() {
        try {
            this.scenarioContent = Files.readString(scenarioPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read scenario file: " + scenarioPath, e);
        }
    }

    private String getModeDir() {
        return isClientTester ? "client" : "server";
    }

    /**
     * Extracts the configuration section between 'TargetConfiguration' and 'TestStart'.
     */
    public Map<String, String> getConfiguration() {
        Map<String, String> configMap = new HashMap<>();
        int start = scenarioContent.indexOf("TargetConfiguration");
        int end = scenarioContent.indexOf("ScenarioStart");

        if (start == -1 || end == -1 || end <= start) {
            return configMap;  // Return empty if structure is invalid
        }

        String configSection = scenarioContent
                .substring(start + "TargetConfiguration".length(), end)
                .replaceAll("\\r?\\n", " "); // remove line breaks

        Pattern pattern = Pattern.compile("\\[([^->\\[\\]]+)->\\s*(.*?)\\]");
        Matcher matcher = pattern.matcher(configSection);

        while (matcher.find()) {
            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();
            configMap.put(key, value);
        }

        return configMap;
    }

    /**
     * Extracts the test script between 'TestStart' and 'TestEnd'.
     */
    public String getScenario() {
        int start = scenarioContent.indexOf("ScenarioStart");
        int end = scenarioContent.indexOf("ScenarioEnd");

        if (start == -1 || end == -1 || end <= start) {
            return "";
        }

        return scenarioContent.substring(start + "ScenarioStart".length(), end).trim();
    }
}