package mta.visualizer.formal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import mta.visualizer.Visualizer;
import mta.visualizer.formal.antlr.FormalVisualLexer;
import mta.visualizer.formal.antlr.FormalVisualParser;
import mta.visualizer.formal.antlr.FormalVisualTransformVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FormalVisualizer implements Visualizer {

    private String title;
    private List<String> nodeId;

    public FormalVisualizer(String title, List<String> nodeId) {
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
        FormalVisualLexer lexer = new FormalVisualLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FormalVisualParser parser = new FormalVisualParser(tokens);

        // 필요시 에러 핸들러 조정
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override public void syntaxError(Recognizer<?, ?> r, Object off, int line, int charPos, String msg, RecognitionException e) {
                throw new RuntimeException("line " + line + ":" + charPos + " " + msg, e);
            }
        });

        ParseTree tree = parser.file();
        FormalVisualTransformVisitor visitor = new FormalVisualTransformVisitor(title, nodeId);
        return visitor.visit(tree);
    }
}
