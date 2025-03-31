package scenario;

import config.ConfigData;
import config.NodeInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScenarioGenerator implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger();

    private String scenario = "";
    private final ConfigData configData;
    private final String requirementDirectory;
    private final String currentDirectory;

    public ScenarioGenerator(ConfigData configData) {
        this.configData = configData;
        this.currentDirectory = Paths.get("").toAbsolutePath().toString();
        this.requirementDirectory = this.currentDirectory + "/maude/requirements/";
    }

    @Override
    public void run() {
        String maudePath = "/Users/gsojc234/tools/maude-3.5/maude";
        String command = maudePath + " " + requirementDirectory + "requirement" + configData.getRequirement() + ".maude";
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
        pre_defined_functions.add("checkConnection");
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
        pre_defined_functions.add("getCertificate");
        pre_defined_functions.add("getPublicKeyFromCertificate");
        pre_defined_functions.add("getRandom");
        pre_defined_functions.add("getHandshakeBody");
        pre_defined_functions.add("getRSAPreMasterSecret");
        pre_defined_functions.add("calculateMasterSecret");
        pre_defined_functions.add("buildKeyShareEntry");
        pre_defined_functions.add("addKeyShareExtension");
        pre_defined_functions.add("addSupportedVersionExtension");
        pre_defined_functions.add("addSignatureAndHashAlgorithmExtension");
        pre_defined_functions.add("addSupportedGroupExtension");
        pre_defined_functions.add("updateContext");
        pre_defined_functions.add("send");
        pre_defined_functions.add("recv");
        pre_defined_functions.add("buildClientHello");
        pre_defined_functions.add("buildServerHello");
        pre_defined_functions.add("buildEncryptedExtension");
        pre_defined_functions.add("buildEmptyCertificate");
        pre_defined_functions.add("buildCertificateVerify");
        pre_defined_functions.add("buildChangeCipher");
        pre_defined_functions.add("buildFinished");
        pre_defined_functions.add("buildRecord");
        pre_defined_functions.add("genCertificatePrivateKey");
        pre_defined_functions.add("genCertificate");
        pre_defined_functions.add("changeCertificate");
        pre_defined_functions.add("reEncryptRSAClientKeyExchange");
        pre_defined_functions.add("buildInvalidPaddingRSAClientKeyExchange");
        pre_defined_functions.add("changeVerifyData");
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
        regex = "(N\\d+)\\s+\\.\\s+(\\w+)";
        result = result.replaceAll(regex, "\"$1 . $2\"");

        // 7. c[T] -> session.constant(T)
        regex = "c\\[\\s*(.*?)\\s*\\]";
        result = result.replaceAll(regex, "session.constant($1)");

        // 8. Constant T Mapping
        result = result.replaceAll("handshake", "ProtocolMessageType.HANDSHAKE");
        result = result.replaceAll("alert", "ProtocolMessageType.ALERT");
        result = result.replaceAll("change-cipher-spec", "ProtocolMessageType.CHANGE_CIPHER_SPEC");

        result = result.replaceAll("TLS-11", "ProtocolVersion.TLS11");
        result = result.replaceAll("TLS-12", "ProtocolVersion.TLS12");
        result = result.replaceAll("TLS-13", "ProtocolVersion.TLS13");

        result = result.replaceAll("client-hello", "HandshakeMessageType.CLIENT_HELLO");
        result = result.replaceAll("server-hello-done", "HandshakeMessageType.SERVER_HELLO_DONE");
        result = result.replaceAll("server-hello", "HandshakeMessageType.SERVER_HELLO");
        result = result.replaceAll("encrypted-extension", "HandshakeMessageType.ENCRYPTED_EXTENSION");
        result = result.replaceAll("certificate-request", "HandshakeMessageType.CERTIFICATE_REQUEST");
        result = result.replaceAll("certificate-verify", "HandshakeMessageType.CERTIFICATE_VERIFY");
        result = result.replaceAll("certificate", "HandshakeMessageType.CERTIFICATE");
        result = result.replaceAll("server-key-exchange", "HandshakeMessageType.SERVER_KEY_EXCHANGE");
        result = result.replaceAll("client-key-exchange", "HandshakeMessageType.CLIENT_KEY_EXCHANGE");
        result = result.replaceAll("finished", "HandshakeMessageType.FINISHED");

        result = result.replaceAll("TLS-AES-128-CCM-SHA256", "CipherSuite.TLS_AES_128_CCM_SHA256");
        result = result.replaceAll("TLS-RSA-WITH-AES-128-CBC-SHA256", "CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA_256");
        result = result.replaceAll("no-compression", "CompressionMethod.NO_COMPRESSION");
        result = result.replaceAll("ecdsa-secp256r1-sha256", "SignatureAndHashAlgorithm.ECDSA_SHA256");
        result = result.replaceAll("dsa-sha256", "SignatureAndHashAlgorithm.DSA_SHA256");
        result = result.replaceAll("secp256r1", "NamedGroup.SECP256R1");
        result = result.replaceAll("fatal", "AlertLevel.FATAL");
        result = result.replaceAll("unexpected-message", "AlertDescription.UNEXPECTED_MESSAGE");
        result = result.replaceAll("decode-error", "AlertDescription.DECODE_ERROR");
        result = result.replaceAll("bad-record-mac", "AlertDescription.BAD_RECORD_MAC");


        // 9. change connect or accept to include target IP address and port.
        result = convertConnectCommand(result, "accept");
        result = convertConnectCommand(result, "connect");

        // 10. change pre-defined constructor

        result = result.replaceAll("prvkey-path", "\"" + this.currentDirectory + "/" + configData.getPrivateKeyPath() + "\"");
        result = result.replaceAll("cert-path", "\"" + this.currentDirectory + "/" + configData.getCertificatePath() + "\"");
        return result;
    }

    private String convertConnectCommand(String scenario, String keyword) {
        Pattern connectPattern = Pattern.compile(keyword + "\\(\"(.+?)\"\\)");
        Matcher matcher = connectPattern.matcher(scenario);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String id = matcher.group(1).trim(); // ID 추출 후 공백 제거
            NodeInfo info = configData.getNodeInfo().get(id); // ID 기반으로 IP, PORT 조회

            if (info != null) {
                String replacement = String.format("%s(\"%s\", \"%s\", %d)", keyword, info.getId(), info.getIp(), info.getPort());
                matcher.appendReplacement(result, replacement); // 문자열 변경
            }
        }
        matcher.appendTail(result);

        return result.toString();
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
