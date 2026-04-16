package mta.user.profile;

import mta.user.profile.antlr.TLSProfileLexer;
import mta.user.profile.antlr.TLSProfileParser;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.io.IOException;
import java.nio.file.Path;

public class TLSProfileLoader {

    private static final BaseErrorListener THROWING_ERROR_LISTENER = new BaseErrorListener() {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                                String msg, RecognitionException e) {
            throw new IllegalArgumentException("Failed to parse TLS profile at line "
                    + line + ":" + charPositionInLine + " " + msg, e);
        }
    };

    public TLSProfile loadTLSProfile(String profilePath) {
        return loadTLSProfile(Path.of(profilePath));
    }

    public TLSProfile loadTLSProfile(Path profilePath) {
        try {
            return parse(CharStreams.fromPath(profilePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read TLS profile: " + profilePath, e);
        }
    }

    public TLSProfile loadTLSProfileFromString(String profileContent) {
        return parse(CharStreams.fromString(profileContent));
    }

    private TLSProfile parse(CharStream input) {
        TLSProfileLexer lexer = new TLSProfileLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(THROWING_ERROR_LISTENER);

        TLSProfileParser parser = new TLSProfileParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(THROWING_ERROR_LISTENER);

        return new TLSProfileBuildingVisitor().visit(parser.profile());
    }
}
