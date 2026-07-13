package mta.scenario;

import com.fasterxml.jackson.databind.ObjectMapper;
import mta.config.Config;
import mta.config.Node;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import mta.scenario.antlr.ScenarioLexer;
import mta.scenario.antlr.ScenarioParser;
import mta.scenario.antlr.ScenarioTransformVisitor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

public class ScenarioRunner {

    private Path testerConfigPath;
    private Path outputDir;
    private Path tlsAttackerConfigPath;
    private String scenario;
    private List<Node> nodes;

    public ScenarioRunner(String sceanrio, Path testerConfigPath, Path outputDir) {
        this.scenario = sceanrio;
        this.testerConfigPath = testerConfigPath;
        URL url = ScenarioRunner.class.getResource("/tls-attacker/default_config.xml");
        if (url == null) throw new RuntimeException("default_config.xml not found!");
        try {
            this.tlsAttackerConfigPath = Path.of(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        this.outputDir = outputDir;
    }
    public ScenarioRunner(Path sceanrioPath, Path testerConfigPath, Path tlsAttackerConfigPath, Path outputDir) {
        this.scenario = readFile(sceanrioPath);
        this.testerConfigPath = testerConfigPath;
        this.tlsAttackerConfigPath = tlsAttackerConfigPath;
        this.outputDir = outputDir;
    }

    public ScenarioRunner(
            Path scenarioPath,
            String mode,
            String ip,
            int port,
            String caCertificatePath,
            String certificatePath,
            String privateKeyPath,
            Path tlsAttackerConfigPath,
            Path outputDir) {
        this.scenario = readFile(scenarioPath);
        this.nodes = List.of(buildNode(mode, ip, port, caCertificatePath, certificatePath, privateKeyPath));
        this.tlsAttackerConfigPath = tlsAttackerConfigPath;
        this.outputDir = outputDir;
    }


    private String readFile(Path scenarioPath) {
        try {
            return Files.readString(scenarioPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void execute() {
        // Logger Out Path
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        System.setProperty("logFilePath", outputDir.toString());
        ctx.reconfigure();

        List<Node> nodeList = nodes != null ? nodes : getConfig().getNodes();
        String transformedScenario = scenarioTransform(scenario, nodeList);
        ScenarioExecutor executor = new ScenarioExecutor(transformedScenario, tlsAttackerConfigPath.toString());
        executor.execute();
    }

    private Node buildNode(
            String mode,
            String ip,
            int port,
            String caCertificatePath,
            String certificatePath,
            String privateKeyPath) {
        String normalizedMode = mode.trim().toLowerCase(Locale.ROOT);
        if (!normalizedMode.equals("client") && !normalizedMode.equals("server")) {
            throw new IllegalArgumentException("mode must be client or server: " + mode);
        }

        Node node = new Node();
        node.setMode(normalizedMode);
        node.setId(normalizedMode.equals("server") ? "N2 . SI" : "N1 . CI");
        node.setIp(ip);
        node.setPort(port);
        node.setCaCertificatePath(caCertificatePath);
        node.setCertificatePath(certificatePath);
        node.setPrivateKeyPath(privateKeyPath);
        return node;
    }

    private Config getConfig() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(testerConfigPath.toFile(), Config.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON mta.config file: " + testerConfigPath, e);
        }
    }

    private String scenarioTransform(String input, List<Node> nodeList) {
        CharStream charStream = CharStreams.fromString(input);
        ScenarioLexer lexer = new ScenarioLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ScenarioParser parser = new ScenarioParser(tokens);

        ParseTree tree = parser.program();

        ScenarioTransformVisitor visitor = new ScenarioTransformVisitor();
        visitor.setNodes(nodeList);

        return visitor.visit(tree);
    }

}
