package scenario;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.*;

public class ScenarioGenerator implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();

    private String currentDir;
    private String maudePath = "/Users/gsojc234/tools/maude-3.5/maude";
    private String requirementDirectory;
    private int requirementNum = 0;
    private String scenario = "";

    public ScenarioGenerator(int requirementNum) {
        this.requirementNum = requirementNum;
        this.currentDir = Paths.get("").toAbsolutePath().toString();
        this.requirementDirectory = this.currentDir + "/maude/requirements/";
    }

    @Override
    public void run() {
        String command = maudePath + " " + requirementDirectory + "requirement" + requirementNum + ".maude";
        LOGGER.info("Command for Maude process = {}", command);
        String result = executeMaudeCommand(command);
        if(result.equals(" ;")){
            LOGGER.info("Maude execution failed");
            return;
        }
        LOGGER.info("Maude execution results = {}", result);

        scenario = transformResult(result);
        LOGGER.info("Generated java code = {}", scenario);
    }

    public String getScenario() {
        return scenario;
    }

    private String transformResult(String result) {
        String regex = null;

        // 1. v[N] -> vN
        regex = "v\\[(\\d+)]";
        result = result.replaceAll(regex, "v$1");

        // 2. v0 := ... -> Variable v0 = ...
        regex = "(v\\d+)\\s*:=\\s*(.*?);";
        result = result.replaceAll(regex, "Variable $1 = $2;");

        // 3. (Variable v0 = ...) -> Variable v0 = ...
        regex = "\\((Variable \\w+ = .*?)\\)";
        result = result.replaceAll(regex, "$1");

        // 4. pre-defined function -> session . pre-defined function
        Set<String> pre_defined_functions = new HashSet<>();
        pre_defined_functions.add("connect");
        pre_defined_functions.add("accept");
        pre_defined_functions.add("assertEqual");
        pre_defined_functions.add("getContentType");
        pre_defined_functions.add("getRecordVersion");
        pre_defined_functions.add("getHandshakeMessageType");
        pre_defined_functions.add("getProtocolVersion");
        pre_defined_functions.add("getCipherSuite");
        pre_defined_functions.add("getCompressionMethod");
        pre_defined_functions.add("getSupportedVersion");
        pre_defined_functions.add("getSignatureAndHashAlgorithm");
        pre_defined_functions.add("getKeyShareEntries");
        pre_defined_functions.add("getNamedGroupFromKeyShares");
        pre_defined_functions.add("getNamedGroup");
        pre_defined_functions.add("getAlertLevel");
        pre_defined_functions.add("getAlertDescription");
        pre_defined_functions.add("buildKeyShareEntry");
        pre_defined_functions.add("addKeyShareExtension");
        pre_defined_functions.add("addSupportedVersionExtension");
        pre_defined_functions.add("addSignatureAndHashAlgorithmExtension");
        pre_defined_functions.add("addSupportedGroupExtension");
        pre_defined_functions.add("updateDigest");
        pre_defined_functions.add("send");
        pre_defined_functions.add("recv");
        pre_defined_functions.add("buildClientHello");
        pre_defined_functions.add("buildServerHello");
        pre_defined_functions.add("buildEncryptedExtension");
        pre_defined_functions.add("buildEmptyCertificate");
        pre_defined_functions.add("buildCertificateVerify");
        pre_defined_functions.add("buildFinished");
        pre_defined_functions.add("buildRecord");
        pre_defined_functions.add("encrypt");
        pre_defined_functions.add("decrypt");

        for (String entry : pre_defined_functions) {
            regex = "\\b" + entry + "\\((.*?)\\)";
            result = result.replaceAll(regex, "session." + entry + "($1)");
        }
        // 5. generateNonce
        regex = "generateRandom\\(nonce\\((N\\d+\\s\\.\\s\\w+),\\s\\d+\\)\\)";
        result = result.replaceAll(regex, "session.constant(Random.NONCE)");
        regex = "generateRandom\\(noNonce\\)";
        result = result.replaceAll(regex, "session.constant(Random.EMPTY)");

        // 6. N1 . CI -> "N1 . CI"
        regex = "(N\\d+)\\s\\.\\s(\\w+)";
        result = result.replaceAll(regex, "\"$1 . $2\"");

        // 7. c[T] -> session.constant(T)
        regex = "c\\[\\s*(.*?)\\s*\\]";
        result = result.replaceAll(regex, "session.constant($1)");

        // 8. Constant T Mapping
        result = result.replaceAll("handshake", "ProtocolMessageType.HANDSHAKE");
        result = result.replaceAll("alert", "ProtocolMessageType.ALERT");

        result = result.replaceAll("TLS-11", "ProtocolVersion.TLS11");
        result = result.replaceAll("TLS-12", "ProtocolVersion.TLS12");
        result = result.replaceAll("TLS-13", "ProtocolVersion.TLS13");

        result = result.replaceAll("client-hello", "HandshakeMessageType.CLIENT_HELLO");
        result = result.replaceAll("server-hello", "HandshakeMessageType.SERVER_HELLO");
        result = result.replaceAll("encrypted-extension", "HandshakeMessageType.ENCRYPTED_EXTENSION");
        result = result.replaceAll("certificate-request", "HandshakeMessageType.CERTIFICATE_REQUEST");
        result = result.replaceAll("certificate-verify", "HandshakeMessageType.CERTIFICATE_VERIFY");
        result = result.replaceAll("certificate", "HandshakeMessageType.CERTIFICATE");
        result = result.replaceAll("server-key-exchange", "HandshakeMessageType.SERVER_KEY_EXCHANGE");
        result = result.replaceAll("server-hello-done", "HandshakeMessageType.SERVER_HELLO_DONE");
        result = result.replaceAll("client-key-exchange", "HandshakeMessageType.CLIENT_KEY_EXCHANGE");
        result = result.replaceAll("finished", "HandshakeMessageType.FINISHED");

        result = result.replaceAll("TLS-AES-128-CCM-SHA256", "CipherSuite.TLS_AES_128_CCM_SHA256");
        result = result.replaceAll("no-compression", "CompressionMethod.NO_COMPRESSION");
        result = result.replaceAll("ecdsa-secp256r1-sha256", "SignatureAndHashAlgorithm.ECDSA_SHA256");
        result = result.replaceAll("dsa-sha256", "SignatureAndHashAlgorithm.DSA_SHA256");
        result = result.replaceAll("secp256r1", "NamedGroup.SECP256R1");
        result = result.replaceAll("fatal", "AlertLevel.FATAL");
        result = result.replaceAll("unexpected-message", "AlertDescription.UNEXPECTED_MESSAGE");
        result = result.replaceAll("decode-error", "AlertDescription.DECODE_ERROR");

        return result;
    }

    private String executeMaudeCommand(String command) {
        StringBuilder builder = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            boolean record = false;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.equals("scenarioEnd")){
                    record = false;
                }
                if (record) {
                    builder.append(line);
                }
                if(line.equals("scenarioStart")){
                    record = true;
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return builder.toString() + " ;";
    }
}
