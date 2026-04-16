package mta.user.behavior;

import mta.user.behavior.antlr.BehaviorSpecLexer;
import mta.user.behavior.antlr.BehaviorSpecParser;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.io.IOException;
import java.nio.file.Path;

public class BehaviorSpecLoader {

    private static final BaseErrorListener THROWING_ERROR_LISTENER = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                                String msg, RecognitionException e) {
            throw new IllegalArgumentException("Failed to parse behavior spec at line "
                    + line + ":" + charPositionInLine + " " + msg, e);
        }
    };

    public BehaviorSpec loadBehaviorSpec(String behaviorSpecPath) {
        return loadBehaviorSpec(Path.of(behaviorSpecPath));
    }

    public BehaviorSpec loadBehaviorSpec(Path behaviorSpecPath) {
        try {
            return parse(CharStreams.fromPath(behaviorSpecPath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read behavior spec: " + behaviorSpecPath, e);
        }
    }

    public BehaviorSpec loadBehaviorSpecFromString(String behaviorSpecContent) {
        return parse(CharStreams.fromString(behaviorSpecContent));
    }

    private BehaviorSpec parse(CharStream input) {
        BehaviorSpecLexer lexer = new BehaviorSpecLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(THROWING_ERROR_LISTENER);

        BehaviorSpecParser parser = new BehaviorSpecParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(THROWING_ERROR_LISTENER);

        return (BehaviorSpec) new BehaviorSpecBuildingVisitor().visit(parser.behaviorSpec());
    }
}
