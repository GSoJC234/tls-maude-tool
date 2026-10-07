package mta.scenario.antlr;

import mta.scenario.antlr.ScenarioLexer;
import mta.scenario.antlr.ScenarioParser;
import mta.scenario.antlr.ScenarioTransformVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/** Standalone regression test for lowering the buildEarlyData Scen operation. */
public final class BuildEarlyDataScenarioTest {
    private BuildEarlyDataScenarioTest() {}

    public static void main(String[] args) {
        String scenario = "v[8] := buildEarlyData(N2 . SI, v[7]);";
        ScenarioLexer lexer = new ScenarioLexer(CharStreams.fromString(scenario));
        ScenarioParser parser = new ScenarioParser(new CommonTokenStream(lexer));
        String generatedJava = new ScenarioTransformVisitor().visit(parser.program());

        String expected = "Variable v8 = session.buildEarlyData(\"N2 . SI\", v7);\n";
        if (parser.getNumberOfSyntaxErrors() != 0 || !expected.equals(generatedJava)) {
            throw new AssertionError(
                    "Unexpected buildEarlyData transformation: " + generatedJava);
        }
    }
}
