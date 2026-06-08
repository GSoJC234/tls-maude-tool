package mta.maude;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class MaudeRunner {

    private String maudeExecutable = null;

    private static final String RESULT_FILE_NAME = "requirement";
    private static final String LOG_EXTENSION = ".log";
    private static final String SCENARIO_EXTENSION = ".tester_scenario";
    private static final String CONFIG_EXTENSION = ".target_scenario";
    private static final String VISUAL_EXTENSION = ".visual";

    public MaudeRunner(String maudeExecutable) {
        this.maudeExecutable = maudeExecutable;
    }

    public void execute(Path modulePath, MaudeRunManifest manifest, Path outputLogPath) {
        execute(modulePath, null, manifest, outputLogPath);
    }

    public void execute(Path modulePath, String moduleName, MaudeRunManifest manifest, Path outputLogPath) {
        ProcessBuilder maudePb = new ProcessBuilder(maudeExecutable, modulePath.toAbsolutePath().toString());
        maudePb.redirectOutput(outputLogPath.toFile());
        maudePb.redirectErrorStream(true);

        try {
            Path parent = outputLogPath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Process maudeProcess = maudePb.start();
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(maudeProcess.getOutputStream()))) {
                if (moduleName != null && !moduleName.isBlank()) {
                    writer.write("select " + moduleName + " .");
                    writer.newLine();
                }
                writer.write(manifest.toReductionCommand());
                writer.newLine();
                writer.flush();
            }
            int exitCode = maudeProcess.waitFor();
            System.out.println("Maude exit code: " + exitCode);

            if (exitCode != 0) {
                throw new RuntimeException("Maude exited with code " + exitCode
                        + " (see " + outputLogPath + ")");
            }
            if (hasMaudeError(outputLogPath)) {
                throw new RuntimeException("Maude reported an error (see " + outputLogPath + ")");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // echo "red runScenario(system, scen1) ." | mta.maude requirements/rfc8446.mta.maude > requirement1.log
    public void execute(Path modulePath, int requirementIdx, int requirementTLSVersion, Path outputDirPath) {
        ProcessBuilder maudePb = new ProcessBuilder(maudeExecutable, modulePath.toAbsolutePath().toString());
        maudePb.redirectOutput(outputDirPath.resolve(RESULT_FILE_NAME + requirementIdx + LOG_EXTENSION).toFile());
        maudePb.redirectErrorStream(true);

        String systemVersion = requirementTLSVersion == 3 ? "system3" : "system2";

        try {
            Files.createDirectories(outputDirPath);
            Process maudeProcess = maudePb.start();
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(maudeProcess.getOutputStream()))) {
                writer.write("red runScenario(" + systemVersion + ", scen" + requirementIdx + ") .\n");
                writer.flush();
            }
            int exitCode = maudeProcess.waitFor();
            System.out.println("Maude exit code: " + exitCode);

            if (exitCode != 0) {
                throw new RuntimeException("Maude exited with code " + exitCode +
                        " (see " + outputDirPath.resolve(RESULT_FILE_NAME + requirementIdx + LOG_EXTENSION) + ")");
            }

            parseMaudeResult(requirementIdx, outputDirPath);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseMaudeResult(int requirementIdx, Path outputDirPath) {
        try {
            int scenIdx = 0, cfgIdx = 0, visIdx = 0;
            BufferedWriter out = null;
            String line;

            BufferedReader br = Files.newBufferedReader(outputDirPath.resolve(RESULT_FILE_NAME + requirementIdx + LOG_EXTENSION));
            while ((line = br.readLine()) != null) {
                switch (line) {
                    case "ScenarioStart" -> {
                        if (out != null) out.close();
                        out = Files.newBufferedWriter(outputDirPath.resolve(RESULT_FILE_NAME + requirementIdx + "_" + (scenIdx++) + SCENARIO_EXTENSION));
                    }
                    case "ConfigStart" -> {
                        if (out != null) out.close();
                        out = Files.newBufferedWriter(outputDirPath.resolve(RESULT_FILE_NAME + requirementIdx + "_" + (cfgIdx++) + CONFIG_EXTENSION));
                    }
                    case "VisualStart" -> {
                        if (out != null) out.close();
                        out = Files.newBufferedWriter(outputDirPath.resolve(RESULT_FILE_NAME + requirementIdx + "_" + (visIdx++) + VISUAL_EXTENSION));
                    }
                    case "Bye.", ") (" -> {
                        if (out != null) out.close();
                    }
                    default -> {
                        if (out != null) {
                            out.write(normalizeScenarioLine(line));
                            out.newLine();
                        }
                    }
                }
            }
            if (out != null) out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String normalizeScenarioLine(String line) {
        String trimmed = line.trim();
        if (trimmed.startsWith("close(") && !trimmed.endsWith(";")) {
            return line + " ;";
        }
        return line;
    }

    private boolean hasMaudeError(Path outputLogPath) throws IOException {
        String output = Files.readString(outputLogPath);
        return output.contains("Error:")
                || output.contains("bad token")
                || output.contains("no parse")
                || output.contains("not declared")
                || output.contains("ambiguous")
                || output.matches("(?s).*module .*does not exist.*");
    }
}
