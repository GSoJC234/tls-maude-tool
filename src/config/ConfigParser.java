package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigParser {
    public static ConfigData parseConfigFile(String filePath) {
        ConfigData configData = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int requirement = 0;
            String currentId = null;
            String ip = null;
            int port = 0;
            String certificate = null;
            String privateKey = null;

            Pattern reqPattern = Pattern.compile("req:\\s*(\\d+)");
            Pattern idPattern = Pattern.compile("id:\\s*(.+)");
            Pattern ipPattern = Pattern.compile("ip:\\s*([0-9\\.]+)");
            Pattern portPattern = Pattern.compile("port:\\s*(\\d+)");
            Pattern certPattern = Pattern.compile("certificate:\\s*\"(.+)\"");
            Pattern keyPattern = Pattern.compile("privateKey:\\s*\"(.+)\"");

            while ((line = br.readLine()) != null) {
                line = line.trim();

                Matcher reqMatcher = reqPattern.matcher(line);
                Matcher idMatcher = idPattern.matcher(line);
                Matcher ipMatcher = ipPattern.matcher(line);
                Matcher portMatcher = portPattern.matcher(line);
                Matcher certMatcher = certPattern.matcher(line);
                Matcher keyMatcher = keyPattern.matcher(line);

                if (reqMatcher.matches()) {
                    requirement = Integer.parseInt(reqMatcher.group(1));
                    configData = new ConfigData(requirement);
                } else if (idMatcher.matches()) {
                    if (currentId != null && ip != null) {
                        configData.addNodes(currentId, new NodeInfo(currentId, ip, port));
                    }
                    currentId = idMatcher.group(1).trim();
                    ip = null;
                    port = 0;
                } else if (ipMatcher.matches()) {
                    ip = ipMatcher.group(1);
                } else if (portMatcher.matches()) {
                    port = Integer.parseInt(portMatcher.group(1));
                } else if (certMatcher.matches()) {
                    certificate = certMatcher.group(1);
                } else if (keyMatcher.matches()) {
                    privateKey = keyMatcher.group(1);
                }
            }

            if (currentId != null && ip != null) {
                configData.addNodes(currentId, new NodeInfo(currentId, ip, port));
            }

            configData.setCertificatePath(certificate);
            configData.setPrivateKeyPath(privateKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configData;
    }
}