package main;

import config.ConfigManager;

import java.nio.file.Paths;

public class Main {

    private static final String CONFIG_BASE_PATH = "resources/config";

    public static void main(String[] args) {
        if (args.length == 0 || args[0].trim().isEmpty()) {
            System.err.println("Usage: java Main <configName>");
            System.exit(1);
        }

        String configFile = args[0].trim() + ".json";
        String configPath = Paths.get("")
                .toAbsolutePath()
                .resolve(CONFIG_BASE_PATH)
                .resolve(configFile)
                .toString();

        ConfigManager manager = new ConfigManager();
        manager.loadFromJson(configPath);

        try {
            new RunnerEngine(manager).run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}