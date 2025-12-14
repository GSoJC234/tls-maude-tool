package mta.visualizer.info;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class InfoRecord {

    private Map<Byte, Configuration> configurations = null;
    private Requirement requirement = null;
    private Target target = null;

    public InfoRecord(){
        configurations = new LinkedHashMap<Byte, Configuration>();
        requirement = new Requirement();
        target = new Target();
    }

    public int parseTargetInfo(byte[] info, int idx) {
        if (info[idx++] != 0x03) {
            throw new RuntimeException("Invalid Target_Info");
        }

        byte[] ip = Arrays.copyOfRange(info, idx, idx + 4);
        idx += 4;
        String stringIp = String.format("%d.%d.%d.%d", ip[3] & 0xFF, ip[2] & 0xFF, ip[1] & 0xFF, ip[0] & 0xFF);
        target.setIp(stringIp);

        byte[] port = Arrays.copyOfRange(info, idx, idx + 2);
        idx += 2;
        int intPort = ((port[1] & 0xFF) << 8) | (port[0] & 0xFF);
        target.setPort(intPort);

        int commandSize = info[idx++] & 0xFF;
        byte[] command = Arrays.copyOfRange(info, idx, idx + commandSize);
        idx += commandSize;
        String commandString = new String(command, StandardCharsets.US_ASCII);
        target.setCommand(commandString);

        int timeDelaySize = info[idx++] & 0xFF;
        byte[] timeDelay = Arrays.copyOfRange(info, idx, idx + timeDelaySize);
        idx += timeDelaySize;
        int intTimeDelay = 0;
        if (timeDelaySize != 0) {
            intTimeDelay = ((timeDelay[1] & 0xFF) << 8) | (timeDelay[0] & 0xFF);
        }
        target.setTimeDelay(intTimeDelay);

        int remoteIdSize = info[idx++] & 0xFF;
        byte[] remoteId = Arrays.copyOfRange(info, idx, idx + remoteIdSize);
        idx += remoteIdSize;
        String remoteIdString = new String(remoteId, StandardCharsets.US_ASCII);
        target.setRemoteId(remoteIdString);

        int remotePasswordSize = info[idx++] & 0xFF;
        byte[] remotePassword = Arrays.copyOfRange(info, idx, idx + remotePasswordSize);
        idx += remotePasswordSize;
        String remotePasswordString = new String(remotePassword, StandardCharsets.US_ASCII);
        target.setRemotePassword(remotePasswordString);

        return idx + 1; // NULL
    }

    public int parseRequirement(byte[] info, int idx) {
        if (info[idx++] != 0x04) {
            throw new RuntimeException("Invalid Requirement_Info");
        }
        int requirementSize = info[idx++] & 0xFF;
        for (int i = 0; i < requirementSize; i++) {
            byte requirementIdx = info[idx++];
            requirement.addRequirement(requirementIdx);
        }
        return idx + 1; // NULL
    }

    public int parseConfiguration(byte[] info, int idx) {
        if (info[idx++] != 0x05) {
            throw new RuntimeException("Invalid Configuration_Info");
        }
        byte requirementSize = info[idx++];
        for (int i = 0; i < requirementSize; i++){
            byte requirementIdx = info[idx++];
            int configurationSize = info[idx++] & 0xFF;
            Configuration configuration = new Configuration();
            for(int k = 0; k < configurationSize; k++){
                byte configurationType = info[idx++];
                int configurationLength = info[idx++] & 0xFF;
                byte[] configurationValues = Arrays.copyOfRange(info, idx, idx + configurationLength);
                idx += configurationLength;
                configuration.addConfigurationValues(configurationType, configurationValues);
            }
            configurations.put(requirementIdx, configuration);
        }
        return idx + 1; // NULL
    }

    public Configuration getConfiguration(int idx) {
        return configurations.get((byte)idx);
    }

    public Map<Byte, Configuration> getConfigurations( ) { return configurations; }

    public Requirement getRequirement() {
        return requirement;
    }

    public Target getTarget() {
        return target;
    }
}
