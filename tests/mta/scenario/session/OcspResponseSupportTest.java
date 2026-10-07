package mta.scenario.session;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import mta.maude.constant.OcspResponseSpec;
import mta.scenario.TargetExecutionMetadata;
import mta.scenario.TargetExecutionMetadataLoader;
import mta.scenario.antlr.ScenarioLexer;
import mta.scenario.antlr.ScenarioParser;
import mta.scenario.antlr.ScenarioTransformVisitor;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/** Standalone regression test; run with the same classpath used to compile tls-maude-tool. */
public final class OcspResponseSupportTest {
    private OcspResponseSupportTest() {}

    public static void main(String[] args) throws Exception {
        Path workspace = Files.createTempDirectory("mta-ocsp-test-");
        Path fixtures = Files.createDirectory(workspace.resolve("ocsp"));
        byte[] response = {0x30, 0x03, 0x0a, 0x01, 0x01};
        Files.write(fixtures.resolve("malformed.der"), response);

        Path profile = workspace.resolve("tlsprofile.dsl");
        Files.writeString(profile, """
                TLSProfiles:
                  tester:
                    TestRole: Tester
                    TLSRole: Client
                    CACertificateType: ecdsa
                    CertificateType: ecdsa
                    PrivateKeyType: ecdsa
                    CertificateSignatureAlgorithm: {ecdsa,sha256}
                    OCSPResponseFixtureDirectory: "ocsp"
                  target:
                    TestRole: Target
                    TLSRole: Server
                    LibraryPath: "/bin/true"
                    CACertificateType: ecdsa
                    CertificateType: ecdsa
                    PrivateKeyType: ecdsa
                    CertificateSignatureAlgorithm: {ecdsa,sha256}
                """);
        TargetExecutionMetadata metadata = new TargetExecutionMetadataLoader().load(profile);
        if (!metadata.ocspResponseFixtureDirectory().equals(fixtures.toString())) {
            throw new AssertionError("Fixture directory did not resolve relative to TLSProfile");
        }
        OcspResponseSpec spec = OcspResponseSpec.MALFORMED;
        if (!Arrays.equals(response,
                OcspResponseFixtureResolver.load(metadata.ocspResponseFixtureDirectory(), spec))) {
            throw new AssertionError("OCSP fixture bytes changed during resolution");
        }

        Map<String, String> cases = Map.of(
                "good-leaf", "GOOD_LEAF",
                "valid-for-leaf", "VALID_FOR_LEAF",
                "wrong-certificate", "WRONG_CERTIFICATE",
                "malformed", "MALFORMED");
        if (OcspResponseSpec.values().length != cases.size()) {
            throw new AssertionError("The OCSP fixture enum must contain exactly the four supported cases");
        }
        for (Map.Entry<String, String> entry : cases.entrySet()) {
            String scenario = "addCertificateEntryStatusRequestExtension("
                    + "N2 . SI, v[7], 0, c[" + entry.getKey() + "]);";
            String expectedJava = "session.addCertificateEntryStatusRequestExtension("
                    + "\"N2 . SI\", v7, 0, session.constant(OcspResponseSpec."
                    + entry.getValue() + "));\n";
            if (!transform(scenario).equals(expectedJava)) {
                throw new AssertionError("OCSP fixture ID did not transform correctly: " + entry.getKey());
            }
            if (!OcspResponseSpec.fromFixtureId(entry.getKey()).name().equals(entry.getValue())) {
                throw new AssertionError("OCSP fixture enum mapping is inconsistent: " + entry.getKey());
            }
        }
        expectSyntaxError("addCertificateEntryStatusRequestExtension(N2 . SI, v[7], 0, good-leaf);");
        expectSyntaxError("addCertificateEntryStatusRequestExtension("
                + "N2 . SI, v[7], 0, ocspResponse(\"good-leaf\"));");
        expectSyntaxError("addCertificateEntryStatusRequestExtension(N2 . SI, v[7], 0, c[missing]);");
        expectSyntaxError("addCertificateEntryStatusRequestExtension("
                + "N2 . SI, v[7], 0, c[malformed-request]);");

        expectIllegalArgument(() -> OcspResponseSpec.fromFixtureId("missing"));
        expectIllegalArgument(() -> OcspResponseFixtureResolver.load("", spec));
        expectIllegalArgument(() -> OcspResponseFixtureResolver.load(
                metadata.ocspResponseFixtureDirectory(), OcspResponseSpec.GOOD_LEAF));
    }

    private static String transform(String scenario) {
        ScenarioLexer lexer = new ScenarioLexer(CharStreams.fromString(scenario));
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        BaseErrorListener failOnError = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                    int line, int charPositionInLine, String message, RecognitionException cause) {
                throw new AssertionError("Scenario syntax error: " + message, cause);
            }
        };
        lexer.addErrorListener(failOnError);
        ScenarioParser parser = new ScenarioParser(new CommonTokenStream(lexer));
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        parser.addErrorListener(failOnError);
        return new ScenarioTransformVisitor().visit(parser.program());
    }

    private static void expectIllegalArgument(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("Expected IllegalArgumentException");
    }

    private static void expectSyntaxError(String scenario) {
        try {
            transform(scenario);
        } catch (AssertionError expected) {
            if (expected.getMessage() != null
                    && expected.getMessage().startsWith("Scenario syntax error:")) {
                return;
            }
            throw expected;
        }
        throw new AssertionError("Expected a scenario syntax error: " + scenario);
    }
}
