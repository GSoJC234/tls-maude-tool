package mta.main;

import mta.maude.MaudeRunner;
import mta.scenario.ScenarioRunner;
import mta.user.scenario.ScenarioSpec;
import mta.user.scenario.ScenarioSpecLoader;
import mta.user.scenario.ScenarioSpecParser;
import mta.visualizer.VisualizerConnector;

import java.nio.file.Path;

public class Main {

    private static final String CONFIG_BASE_PATH = "resources/mta.config";

    public static void main(String[] args) {
        if (args.length == 0 || args[0].trim().isEmpty()) {
            System.err.println("Usage: --generate [options] | -- run [options] | --visual [options]");
            System.exit(1);
        }

        String mode = args[0];
        switch (mode) {
            case "--generate" ->  generateScenario(args);
            case "--run" ->  runScenario(args);
            case "--visual" -> runVisualizer(args);
        }

    }

    private static void generateScenario(String[] args) {
        if (args.length < 4) {
            System.err.println("Usage: --generate [maude executable path] [module path] [scenario_spec path] [output directory]");
            System.exit(1);
        }

        String maudePath = args[1];
        String modulePath = args[2];
        String scenarioPath = args[3];
        String outputDir = args[4];

        System.out.println("   Maude analysis:");
        System.out.println("   Maude executable path : " + maudePath);
        System.out.println("   Maude module path : " + modulePath);
        System.out.println("   Scenario spec path: " + scenarioPath);
        System.out.println("   Output directory: " + outputDir);

        ScenarioSpecLoader loader = new ScenarioSpecLoader();
        ScenarioSpec scenSpec = loader.loadTLSProfile(scenarioPath);

        MaudeRunner maudeRunner = new MaudeRunner(maudePath);
        maudeRunner.execute(Path.of(modulePath), scenSpec, Path.of(outputDir));
    }

    private static void runScenario(String[] args) {
        if (args.length < 5) {
            System.err.println("Usage: --run [scenario path] [tester configuration path] [tls-attacker configuration path] [output file path]");
        }
        String scenarioPath = args[1];
        String testerConfigPath = args[2];
        String tlsAttackerConfigPath = args[3];
        String outputPath = args[4];

        System.out.println("   Run formal mta.scenario");
        System.out.println("   Scenario path : " + scenarioPath);
        System.out.println("   Configuration path : " + testerConfigPath);
        System.out.println("   TLS attacker configuration path : " + tlsAttackerConfigPath);
        System.out.println("   Output path : " + outputPath);

        ScenarioRunner scenarioRunner = new ScenarioRunner(Path.of(scenarioPath), Path.of(testerConfigPath), Path.of(tlsAttackerConfigPath), Path.of(outputPath));
        scenarioRunner.execute();
    }

    private static void runVisualizer(String[] args) {
        // --visual 127.0.0.1 19001 19002 /home/jaehun/visualizer/TLS_system/TLS/Result
        System.out.println("Usage: --visual [sendIp] [sendPort] [receivePort] [sharedFolderPath]");

        String targetIp = args[1];
        int targetPort = Integer.parseInt(args[2]);
        int receivePort = Integer.parseInt(args[3]);
        String sharedFolderPath = args[4];

        System.out.println("Send to " + targetIp + ":" + targetPort);
        System.out.println("Receive at port " + receivePort);
        System.out.println("Shared folder path " + sharedFolderPath);

        VisualizerConnector visualizerConnector = new VisualizerConnector(targetIp, targetPort, receivePort, sharedFolderPath);
        visualizerConnector.connect();

    }
}