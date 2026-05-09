package mta.main;

import mta.maude.MaudeRunner;
import mta.scenario.ScenarioRunner;
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
        if (args.length < 6) {
            System.err.println("Usage: --generate [maude executable path] [module path] [requirement TLS version] [requirement index] [output file directory]");
            System.exit(1);
        }

        String maudePath = args[1];
        String modulePath = args[2];
        int requirementTLSVersion = Integer.parseInt(args[3]);
        int requirementIdx = Integer.parseInt(args[4]);
        String outputDir = args[5];

        System.out.println("   Maude analysis:");
        System.out.println("   Maude executable path : " + maudePath);
        System.out.println("   Maude module path : " + modulePath);
        System.out.println("   Requirement TLS version(2, 3) : " + requirementTLSVersion);
        System.out.println("   Requirement index : " + requirementIdx);
        System.out.println("   Output directory: " + outputDir);

        MaudeRunner maudeRunner = new MaudeRunner(maudePath);
        maudeRunner.execute(Path.of(modulePath), requirementIdx, requirementTLSVersion, Path.of(outputDir));
    }

    private static void runScenario(String[] args) {
        if (args.length < 10) {
            System.err.println("Usage: --run [scenario path] [mode] [ip] [port] [ca certificate path] [certificate path] [private key path] [tls-attacker configuration path] [output file path]");
            System.exit(1);
        }
        String scenarioPath = args[1];
        String testerMode = args[2];
        String testerIp = args[3];
        int testerPort = Integer.parseInt(args[4]);
        String caCertificatePath = args[5];
        String certificatePath = args[6];
        String privateKeyPath = args[7];
        String tlsAttackerConfigPath = args[8];
        String outputPath = args[9];

        System.out.println("   Run formal mta.scenario");
        System.out.println("   Scenario path : " + scenarioPath);
        System.out.println("   Mode : " + testerMode);
        System.out.println("   IP : " + testerIp);
        System.out.println("   Port : " + testerPort);
        System.out.println("   CA certificate path : " + caCertificatePath);
        System.out.println("   Certificate path : " + certificatePath);
        System.out.println("   Private key path : " + privateKeyPath);
        System.out.println("   TLS attacker configuration path : " + tlsAttackerConfigPath);
        System.out.println("   Output path : " + outputPath);

        ScenarioRunner scenarioRunner = new ScenarioRunner(
                Path.of(scenarioPath),
                testerMode,
                testerIp,
                testerPort,
                caCertificatePath,
                certificatePath,
                privateKeyPath,
                Path.of(tlsAttackerConfigPath),
                Path.of(outputPath));
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
