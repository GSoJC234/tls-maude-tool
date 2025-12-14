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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ScenarioRunner {

    private Path testerConfigPath;
    private Path outputDir;
    private Path tlsAttackerConfigPath;
    private String scenario;

    public ScenarioRunner(String sceanrio, Path testerConfigPath, Path outputDir) {
        this.scenario = scenario;
        this.testerConfigPath = testerConfigPath;
        this.tlsAttackerConfigPath = Path.of(ScenarioRunner.class.getResource("default_config.xml").getPath());
        this.outputDir = outputDir;
    }
    public ScenarioRunner(Path sceanrioPath, Path testerConfigPath, Path tlsAttackerConfigPath, Path outputDir) {
        this.scenario = readFile(sceanrioPath);
        this.testerConfigPath = testerConfigPath;
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

        Config config = getConfig();
        String transformedScenario = scenarioTransform(scenario, config.getNodes());
        ScenarioExecutor executor = new ScenarioExecutor(transformedScenario, tlsAttackerConfigPath.toString());
        executor.execute();
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
