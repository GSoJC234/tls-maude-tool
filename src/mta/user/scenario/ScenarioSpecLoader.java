package mta.user.scenario;

import mta.user.scenario.antlr.ScenarioSpecLexer;
import mta.user.scenario.antlr.ScenarioSpecParser;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.io.IOException;
import java.nio.file.Path;

public class ScenarioSpecLoader {

    private static final BaseErrorListener THROWING_ERROR_LISTENER = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                                String msg, RecognitionException e) {
            throw new IllegalArgumentException("Failed to parse scenario spec at line "
                    + line + ":" + charPositionInLine + " " + msg, e);
        }
    };

    public ScenarioSpec loadScenarioSpec(String scenarioSpecPath) {
        return loadScenarioSpec(Path.of(scenarioSpecPath));
    }

    public ScenarioSpec loadScenarioSpec(Path scenarioSpecPath) {
        try {
            return parse(CharStreams.fromPath(scenarioSpecPath), scenarioSpecPath.toAbsolutePath().normalize().getParent());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read scenario spec: " + scenarioSpecPath, e);
        }
    }

    public ScenarioSpec loadScenarioSpecFromString(String scenarioSpecContent) {
        return parse(CharStreams.fromString(scenarioSpecContent), Path.of(".").toAbsolutePath().normalize());
    }

    private ScenarioSpec parse(CharStream input, Path baseDirectory) {
        ScenarioSpecLexer lexer = new ScenarioSpecLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(THROWING_ERROR_LISTENER);

        ScenarioSpecParser parser = new ScenarioSpecParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(THROWING_ERROR_LISTENER);

        return (ScenarioSpec) new ScenarioSpecBuildingVisitor(baseDirectory).visit(parser.scenarioSpec());
    }
}
