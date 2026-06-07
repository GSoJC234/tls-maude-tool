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
        BehaviorDeviationSpecification specification = loadBehaviorDeviationSpecification(behaviorSpecPath);
        if (specification.getBehaviorSpecs().isEmpty()) {
            throw new IllegalArgumentException("Behavior deviation specification has no behavior specs: "
                    + behaviorSpecPath);
        }
        return specification.getBehaviorSpecs().get(0);
    }

    public BehaviorDeviationSpecification loadBehaviorDeviationSpecification(String behaviorSpecPath) {
        return loadBehaviorDeviationSpecification(Path.of(behaviorSpecPath));
    }

    public BehaviorDeviationSpecification loadBehaviorDeviationSpecification(Path behaviorSpecPath) {
        try {
            return parse(CharStreams.fromPath(behaviorSpecPath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read behavior spec: " + behaviorSpecPath, e);
        }
    }

    public BehaviorSpec loadBehaviorSpecFromString(String behaviorSpecContent) {
        BehaviorDeviationSpecification specification =
                loadBehaviorDeviationSpecificationFromString(behaviorSpecContent);
        if (specification.getBehaviorSpecs().isEmpty()) {
            throw new IllegalArgumentException("Behavior deviation specification has no behavior specs");
        }
        return specification.getBehaviorSpecs().get(0);
    }

    public BehaviorDeviationSpecification loadBehaviorDeviationSpecificationFromString(String behaviorSpecContent) {
        return parse(CharStreams.fromString(behaviorSpecContent));
    }

    private BehaviorDeviationSpecification parse(CharStream input) {
        BehaviorSpecLexer lexer = new BehaviorSpecLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(THROWING_ERROR_LISTENER);

        BehaviorSpecParser parser = new BehaviorSpecParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(THROWING_ERROR_LISTENER);

        return (BehaviorDeviationSpecification) new BehaviorSpecBuildingVisitor()
                .visit(parser.behaviorDeviationSpecification());
    }
}
