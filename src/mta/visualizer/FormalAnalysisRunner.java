package mta.visualizer;

import mta.visualizer.info.Configuration;
import mta.visualizer.info.InfoRecord;
import mta.visualizer.info.Requirement;
import mta.scenario.ScenarioRunner;
import mta.visualizer.formal.FormalVisualizer;
import mta.visualizer.real.RealVisualizer;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FormalAnalysisRunner {

    private String resultDirectory;
    private boolean analysisResult;
    private final String FORMAL_EXTENSION = ".fsc";
    private final String REAL_EXTENSION = ".rsc";

    private static Path extractedDbPath;

    private final Map<Integer, Boolean> isServerTarget = Map.ofEntries(
            Map.entry(1, true),
            Map.entry(2, false),
            Map.entry(3, false),
            Map.entry(4, false),
            Map.entry(5, false),
            Map.entry(6, false),
            Map.entry(7, true),
            Map.entry(8, true),
            Map.entry(9, true),
            Map.entry(10, false),
            Map.entry(11, true),
            Map.entry(12, true),
            Map.entry(13, true),
            Map.entry(14, true),
            Map.entry(15, true),
            Map.entry(16, true),
            Map.entry(17, true),
            Map.entry(18, true),
            Map.entry(19, false),
            Map.entry(20, true),
            Map.entry(21, true),
            Map.entry(22, false),
            Map.entry(23, true),
            Map.entry(24, false),
            Map.entry(25, false),
            Map.entry(26, false),
            Map.entry(27, true),
            Map.entry(28, true),
            Map.entry(29, true),
            Map.entry(30, false),
            Map.entry(31, false),
            Map.entry(32, false),
            Map.entry(33, true),
            Map.entry(34, false),
            Map.entry(35, false),
            Map.entry(36, true),
            Map.entry(37, false),
            Map.entry(38, true),
            Map.entry(39, true),
            Map.entry(40, true),
            Map.entry(41, true),
            Map.entry(42, true),
            Map.entry(43, false),
            Map.entry(44, false),
            Map.entry(45, false),
            Map.entry(46, false),
            Map.entry(47, true),
            Map.entry(48, false),
            Map.entry(49, true),
            Map.entry(50, true),
            Map.entry(51, false),
            Map.entry(52, false),
            Map.entry(53, false),
            Map.entry(54, false),
            Map.entry(55, true),
            Map.entry(56, true),
            Map.entry(57, true),
            Map.entry(58, true),
            Map.entry(59, true),
            Map.entry(60, true),
            Map.entry(61, true),
            Map.entry(62, true),
            Map.entry(63, true),
            Map.entry(64, false),
            Map.entry(65, false),
            Map.entry(66, false),
            Map.entry(67, false),
            Map.entry(68, false)
    );

    public FormalAnalysisRunner(String resultDirectory) {
        this.resultDirectory = resultDirectory;
    }

    public void run(InfoRecord info){
        // Step 1. Validate and load scenario counts for each requirement.
        // For every requirement and its corresponding configuration, check whether
        // matching scenarios exist in the scenario database. If any requirement has
        // zero matching scenarios, terminate the process by throwing an exception.
        Requirement requirement = info.getRequirement();
        Map<Byte, Configuration> configurations = info.getConfigurations();
        Map<Integer, Integer> scenarioCount = loadScenarioCounts(requirement, configurations);

        // Step 2. Execute all required scenarios.
        // For each (requirement, scenario index) pair, run the formal tester
        // (Maude TLS Attacker via ScenarioRunner) and the actual TLS target library.
        // The execution order depends on whether the requirement expects a client-side
        // or server-side tester, and both processes are coordinated to interact properly.
        executeScenarios(info, scenarioCount, configurations);

        // Step 3. Parse and visualize the execution results.
        // For each executed scenario, generate both formal (.fsc) and real (.rsc)
        // result files following the naming format: requirement_N_M_timestamp.fsc/.rsc.
        // The visualizer components convert the raw scenario data into JSON files,
        // which are saved into the specified result directory.
        visualizeResults(scenarioCount, configurations);

        analysisResult = true;
    }

    private Map<Integer, Integer> loadScenarioCounts(Requirement req, Map<Byte, Configuration> configs) {
        int total = req.getRequirementsCount();
        Map<Integer, Integer> result = new LinkedHashMap<>();
        for (int i = 0; i < total; i++) {
            int reqIdx = req.getRequirementIdx(i);
            Configuration cfg = configs.get((byte) reqIdx);

            int count = getScenarioCount(reqIdx, cfg);
            if (count == 0) {
                analysisResult = false;
                throw new IllegalStateException("No matched scenario found for requirement " + reqIdx);
            }
            result.put(reqIdx, count);
        }
        return result;
    }

    private void executeScenarios(InfoRecord info, Map<Integer, Integer> scenarioCount,
                                  Map<Byte, Configuration> configurations) {

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (Map.Entry<Integer, Integer> entry : scenarioCount.entrySet()) {
            int reqIdx = entry.getKey();
            int scenCount = entry.getValue();
            Configuration cfg = configurations.get((byte) reqIdx);

            for (int scenIdx = 0; scenIdx < 1; scenIdx++) {
                URL logDirectory = FormalAnalysisRunner.class.getClassLoader()
                        .getResource("log");
                if (logDirectory == null) {
                    throw new RuntimeException("Unable to access log directory: log");
                }
                Path logPath = Path.of(logDirectory.getPath(), "requirement_" + Integer.toString(reqIdx) +  "_" + Integer.toString(scenIdx) + ".log");

                String formalScenario = getTesterScenario(reqIdx, scenIdx, configurations.get((byte) reqIdx));
                String targetCommand = info.getTarget().getCommand();

                Future<?> task1;
                Future<?> task2;
                if (!isServerTarget.get(reqIdx)) {
                    task1 = executor.submit(() -> runMaudeTLSAttacker(formalScenario, logPath, isServerTarget.get(reqIdx)));
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    task2 = executor.submit(() -> runTLSLibrary(targetCommand));
                } else {
                    task1 = executor.submit(() -> runTLSLibrary(targetCommand));
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    task2 = executor.submit(() -> runMaudeTLSAttacker(formalScenario, logPath, isServerTarget.get(reqIdx)));

                }
                try {
                    task2.get();
                    task1.get();
                } catch (Exception e) {
                    throw new RuntimeException("Scenario execution failed (reqIdx=" +
                            reqIdx + ", scenario=" + scenIdx + ")", e);
                }
                try {
                    Thread.sleep(10000); // for port resource deallocation
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        executor.shutdown();
    }

    private void visualizeResults(Map<Integer, Integer> scenarioCount,
                                  Map<Byte, Configuration> configurations) {

        for (Map.Entry<Integer, Integer> entry : scenarioCount.entrySet()) {
            int reqIdx = entry.getKey();
            int scenCount = entry.getValue();
            Configuration cfg = configurations.get((byte) reqIdx);

            FormalVisualizer fv = new FormalVisualizer("requirement", List.of("client", "server"));
            RealVisualizer rv = new RealVisualizer("requirement", List.of("client", "server"));

            for (int scenIdx = 0; scenIdx < 1; scenIdx++) {
                System.out.println("Visualizer Scenario: " + scenIdx);
                String formal = getVisualScenario(reqIdx, scenIdx, cfg).replace("\n", "").replace("\t", "");
                fv.makeJSON(formal, resultDirectory + "/" + buildFileName(reqIdx, scenIdx, true));

                String real = readRealScenario(reqIdx, scenIdx);
                rv.makeJSON(real, resultDirectory + "/" + buildFileName(reqIdx, scenIdx, false));
            }
        }
    }


    private String readRealScenario(int reqIdx, int scenIdx) {
        String resourceName = "log/requirement_" + reqIdx + "_" + scenIdx + ".log";
        URL resource = FormalAnalysisRunner.class.getClassLoader().getResource(resourceName);
        if (resource == null) {
            throw new RuntimeException("Scenario log not found: " + resourceName);
        }

        try (InputStream is = resource.openStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read scenario file: " + resourceName, e);
        }
    }

    private String buildFileName(int reqIdx, int scenIdx, boolean isFormal) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String currentTime = now.format(formatter);

        StringBuilder fileName = new StringBuilder();
        fileName.append("requirement");
        fileName.append("_");
        fileName.append(reqIdx);
        fileName.append("_");
        fileName.append(scenIdx);
        fileName.append("_");
        fileName.append(currentTime);
        fileName.append(isFormal ? FORMAL_EXTENSION : REAL_EXTENSION);

        return fileName.toString();
    }

    // We assume that wolfssl is only library tested
    private void runTLSLibrary(String command) {
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", command)
                    .redirectErrorStream(true);

            pb.directory(new File("/home/jaehun/git/test_target/wolfssl-v5.8.2/examples/server"));
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[TLS] " + line);
                }
            }

            int exitCode = process.waitFor();
            System.out.println("exitCode: " + exitCode);
        } catch (Exception e) {
            throw new RuntimeException("Failed to run TLS library", e);
        }
    }

    private void runMaudeTLSAttacker(String formalScenario, Path logPath, boolean isClientTester) {
        String configFileName = isClientTester ? "scenario_client_config.json" : "scenario_server_config.json";
        URL configUrl = FormalAnalysisRunner.class.getClassLoader().getResource("config" + "/" + configFileName);
        if (configUrl == null) {
            throw new RuntimeException("Config file not found: " + configFileName);
        }

        ScenarioRunner scenarioRunner = new ScenarioRunner(formalScenario, Path.of(configUrl.getPath()), logPath);
        scenarioRunner.execute();
    }

    private String getTesterScenario(int requirementIdx, int scenarioNum, Configuration configuration) {
        try (Connection conn = openScenarioDb()) {
            String sql =
                    "SELECT TesterScenario FROM ScenarioResult WHERE " +
                            "Requirement = ? AND " +
                            "Scenario = ? AND " +
                            "CipherSuites = ? AND " +
                            "NamedGroups = ? AND " +
                            "CompressionMethods = ? AND " +
                            "SignatureAndHashAlgorithms = ? AND " +
                            "CertificateTypes = ? AND " +
                            "CertificateRequest = ? AND " +
                            "KeyShareGroups = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, requirementIdx);
                pstmt.setInt(2, scenarioNum);
                pstmt.setString(3, bytesToCommaSeparatedInts(configuration.getConfigurationValues("cipherSuites")));
                pstmt.setString(4, bytesToCommaSeparatedInts(configuration.getConfigurationValues("namedGroups")));
                pstmt.setString(5, bytesToCommaSeparatedInts(configuration.getConfigurationValues("compressionMethods")));
                pstmt.setString(6, bytesToCommaSeparatedInts(configuration.getConfigurationValues("signatureAndHashAlgorithms")));
                pstmt.setString(7, bytesToCommaSeparatedInts(configuration.getConfigurationValues("certificateTypes")));
                pstmt.setString(8, bytesToCommaSeparatedInts(configuration.getConfigurationValues("certificateRequests")));
                pstmt.setString(9, bytesToCommaSeparatedInts(configuration.getConfigurationValues("keyShareGroups")));


                ResultSet rs = pstmt.executeQuery();
                return rs.getString("TesterScenario");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    private String getVisualScenario(int requirementIdx, int scenarioNum, Configuration configuration) {
        try (Connection conn = openScenarioDb()) {

            String sql =
                    "SELECT VisualScenario FROM ScenarioResult WHERE " +
                            "Requirement = ? AND " +
                            "Scenario = ? AND " +
                            "CipherSuites = ? AND " +
                            "NamedGroups = ? AND " +
                            "CompressionMethods = ? AND " +
                            "SignatureAndHashAlgorithms = ? AND " +
                            "CertificateTypes = ? AND " +
                            "CertificateRequest = ? AND " +
                            "KeyShareGroups = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, requirementIdx);
                pstmt.setInt(2, scenarioNum);
                pstmt.setString(3, bytesToCommaSeparatedInts(configuration.getConfigurationValues("cipherSuites")));
                pstmt.setString(4, bytesToCommaSeparatedInts(configuration.getConfigurationValues("namedGroups")));
                pstmt.setString(5, bytesToCommaSeparatedInts(configuration.getConfigurationValues("compressionMethods")));
                pstmt.setString(6, bytesToCommaSeparatedInts(configuration.getConfigurationValues("signatureAndHashAlgorithms")));
                pstmt.setString(7, bytesToCommaSeparatedInts(configuration.getConfigurationValues("certificateTypes")));
                pstmt.setString(8, bytesToCommaSeparatedInts(configuration.getConfigurationValues("certificateRequests")));
                pstmt.setString(9, bytesToCommaSeparatedInts(configuration.getConfigurationValues("keyShareGroups")));

                ResultSet rs = pstmt.executeQuery();
                return rs.getString("VisualScenario");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getScenarioCount(int requirementIdx, Configuration configuration) {
        try (Connection conn = openScenarioDb()) {

            String sql =
                    "SELECT COUNT(*) FROM ScenarioResult WHERE " +
                            "Requirement = ? AND " +
                            "CipherSuites = ? AND " +
                            "NamedGroups = ? AND " +
                            "CompressionMethods = ? AND " +
                            "SignatureAndHashAlgorithms = ? AND " +
                            "CertificateTypes = ? AND " +
                            "CertificateRequest = ? AND " +
                            "KeyShareGroups = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, requirementIdx);
                pstmt.setString(2, bytesToCommaSeparatedInts(configuration.getConfigurationValues("cipherSuites")));
                pstmt.setString(3, bytesToCommaSeparatedInts(configuration.getConfigurationValues("namedGroups")));
                pstmt.setString(4, bytesToCommaSeparatedInts(configuration.getConfigurationValues("compressionMethods")));
                pstmt.setString(5, bytesToCommaSeparatedInts(configuration.getConfigurationValues("signatureAndHashAlgorithms")));
                pstmt.setString(6, bytesToCommaSeparatedInts(configuration.getConfigurationValues("certificateTypes")));
                pstmt.setString(7, bytesToCommaSeparatedInts(configuration.getConfigurationValues("certificateRequests")));
                pstmt.setString(8, bytesToCommaSeparatedInts(configuration.getConfigurationValues("keyShareGroups")));

                ResultSet rs = pstmt.executeQuery();
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private static synchronized Path getExtractedDbPath() throws IOException {
        if (extractedDbPath != null) {
            return extractedDbPath;
        }
        Path temp = Files.createTempFile("tls_scenarios", ".db");
        temp.toFile().deleteOnExit();
        try (InputStream in = FormalAnalysisRunner.class.getResourceAsStream("/db/tls_scenarios.db")) {
            if (in == null) {
                throw new RuntimeException("DB file not found in resources!");
            }
            Files.copy(in, temp, StandardCopyOption.REPLACE_EXISTING);
        }
        extractedDbPath = temp;
        return temp;
    }

    private Connection openScenarioDb() throws Exception {
        Path dbPath = getExtractedDbPath();
        String dbUrl = "jdbc:sqlite:" + dbPath.toAbsolutePath();
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(dbUrl);
    }

    private String bytesToCommaSeparatedInts(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            // byte → unsigned integer (0~255)
            int unsigned = bytes[i] & 0xFF;
            sb.append(unsigned);
            if (i < bytes.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public boolean analysisSuccess(){
        return analysisResult;
    }
}
