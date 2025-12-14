package mta.visualizer.real;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import mta.visualizer.Visualizer;
import mta.visualizer.real.antlr.RealVisualLexer;
import mta.visualizer.real.antlr.RealVisualParser;
import mta.visualizer.real.antlr.RealVisualTransformVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RealVisualizer implements Visualizer {

    private String title;
    private List<String> nodeId;

    public RealVisualizer(String title, List<String> nodeId) {
        this.title = title;
        this.nodeId = nodeId;
    }

    public void makeJSON(String content, String outputFilePath) {
        try {
            Object astJson = parseToJsonObject(content);
            ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

            String json = om.writeValueAsString(astJson);
            Files.writeString(Paths.get(outputFilePath), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Object parseToJsonObject(String source) throws IOException {
        CharStream input = CharStreams.fromString(source);
        RealVisualLexer lexer = new RealVisualLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RealVisualParser parser = new RealVisualParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?, ?> r, Object off, int line, int charPos, String msg, RecognitionException e) {
                throw new RuntimeException("line " + line + ":" + charPos + " " + msg, e);
            }
        });

        ParseTree tree = parser.file();
        RealVisualTransformVisitor visitor = new RealVisualTransformVisitor(title, nodeId);
        return visitor.visit(tree);
    }
}
