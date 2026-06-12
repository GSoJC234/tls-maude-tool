package mta.main;

import mta.maude.MaudeRunner;
import mta.maude.module.GeneratedTestModuleRenderer;
import mta.maude.module.GeneratedTestModuleSpec;
import mta.maude.module.UserScenarioModuleBuilder;
import mta.maude.result.GeneratedScenarioResultExtractor;
import mta.scenario.ScenarioRunner;
import mta.visualizer.VisualizerConnector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    private static final String CONFIG_BASE_PATH = "resources/mta.config";
    private static final String DEFAULT_MAUDE_EXECUTABLE = "maude/maude/maude-3.5.1/maude";
    private static final String DEFAULT_USER_OUTPUT_DIR = "generated/user-scenario";

    public static void main(String[] args) {
        if (args.length == 0 || args[0].trim().isEmpty()) {
            System.err.println("Usage: --generate [options] | --generate-user [options] | --run [options] | --visual [options]");
            System.exit(1);
        }

        String mode = args[0];
        switch (mode) {
            case "--generate" ->  generateScenario(args);
            case "--generate-user" -> generateUserScenario(args);
            case "--run" ->  runScenario(args);
            case "--visual" -> runVisualizer(args);
            default -> {
                System.err.println("Unknown mode: " + mode);
                System.exit(1);
            }
        }

    }

    private static void generateScenario(String[] args) {
        if (isUserGenerateArguments(args)) {
            generateUserScenario(args);
            return;
        }
        if (args.length < 6) {
            System.err.println("Usage:");
            System.err.println("  --generate [tls profile path] [behavior spec path] [scenario spec path] [output dir?] [maude executable path?]");
            System.err.println("  --generate [maude executable path] [module path] [requirement TLS version] [requirement index] [output file directory]");
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

    private static boolean isUserGenerateArguments(String[] args) {
        return args.length >= 4
                && Files.exists(Path.of(args[1]))
                && Files.exists(Path.of(args[2]))
                && Files.exists(Path.of(args[3]));
    }

    private static void generateUserScenario(String[] args) {
        if (args.length < 4) {
            System.err.println("Usage: --generate-user [tls profile path] [behavior spec path] [scenario spec path] [output dir?] [maude executable path?]");
            System.exit(1);
        }

        Path profilePath = Path.of(args[1]);
        Path behaviorPath = Path.of(args[2]);
        Path scenarioPath = Path.of(args[3]);
        Path outputDir = args.length >= 5 ? Path.of(args[4]) : Path.of(DEFAULT_USER_OUTPUT_DIR);
        String maudePath = args.length >= 6 ? args[5] : DEFAULT_MAUDE_EXECUTABLE;

        UserScenarioModuleBuilder builder = new UserScenarioModuleBuilder();
        GeneratedTestModuleSpec spec = builder.fromFiles(profilePath, behaviorPath, scenarioPath);
        GeneratedTestModuleRenderer renderer = new GeneratedTestModuleRenderer();

        Path modulePath = outputDir.resolve("generated-scenario.maude");
        Path commandPath = outputDir.resolve("reduction-command.maude");
        Path logPath = outputDir.resolve("maude.log");

        try {
            Files.createDirectories(outputDir);
            Files.writeString(modulePath, renderer.render(spec));
            Files.writeString(commandPath, renderer.renderReductionCommand(spec) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write generated user scenario module under " + outputDir, e);
        }

        System.out.println("   User Maude analysis:");
        System.out.println("   TLS profile path : " + profilePath);
        System.out.println("   Behavior spec path : " + behaviorPath);
        System.out.println("   Scenario spec path : " + scenarioPath);
        System.out.println("   Maude executable path : " + maudePath);
        System.out.println("   Generated module path : " + modulePath);
        System.out.println("   Reduction command path : " + commandPath);
        System.out.println("   Output log path : " + logPath);

        MaudeRunner maudeRunner = new MaudeRunner(maudePath);
        maudeRunner.execute(modulePath, spec.getModuleName(), spec.toRunManifest(), logPath);

        GeneratedScenarioResultExtractor extractor = new GeneratedScenarioResultExtractor();
        List<Path> testerScenarioPaths = extractor.extractTesterScenarioFiles(logPath, outputDir);
        System.out.println("   Generated tester scenario files : " + testerScenarioPaths.size());
        for (Path testerScenarioPath : testerScenarioPaths) {
            System.out.println("     " + testerScenarioPath);
        }
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
