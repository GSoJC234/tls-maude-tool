package main;

import config.ConfigData;
import config.ConfigParser;
import config.NodeInfo;
import scenario.ScenarioExecutor;
import scenario.ScenarioGenerator;

import java.nio.file.Paths;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        Main main = new Main();
        String requirement = args[0];
        String requirementPath = Paths.get("").toAbsolutePath().toString() + "/resources/config/" + requirement + ".cfg";
        ConfigData data = ConfigParser.parseConfigFile(requirementPath);

        ScenarioGenerator generator = new ScenarioGenerator(data);
        generator.run();
        String scenario = generator.getScenario();
        ScenarioExecutor executor = new ScenarioExecutor(scenario);
        executor.setNodeInfo(data.getNodeInfo());
        executor.execute();
    }
}