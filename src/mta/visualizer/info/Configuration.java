package mta.visualizer.info;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Configuration {

    private Map<String, byte[]> configurations;
    public Configuration() {
        this.configurations = new LinkedHashMap<String, byte[]>();
    }

    public void addConfigurationValues(byte configurationType, byte[] configurationValues){
        System.out.println("Configuration Type: " + configurationType);

        switch (configurationType){
            case 0x00: configurations.put("cipherSuites", normalizeRandomValue("cipherSuites", configurationValues)); break;
            case 0x01: configurations.put("namedGroups", normalizeRandomValue("namedGroups", configurationValues)); break;
            case 0x02: configurations.put("compressionMethods", normalizeRandomValue("compressionMethods", configurationValues)); break;
            case 0x03: configurations.put("signatureAndHashAlgorithms", normalizeRandomValue("signatureAndHashAlgorithms", configurationValues)); break;
            case 0x04: configurations.put("certificateTypes", normalizeRandomValue("certificateTypes", configurationValues)); break;
            case 0x05: configurations.put("certificateRequests", normalizeRandomValue("certificateRequests", configurationValues)); break;
            case 0x06: configurations.put("keyShareGroups", normalizeRandomValue("keyShareGroups", configurationValues)); break;
            case 0x07: configurations.put("versions", normalizeRandomValue("versions", configurationValues)); break;
            case 0x08: configurations.put("certificatePath", configurationValues); break;
            case 0x09: configurations.put("privateKeyPath", configurationValues); break;
            default: throw new IllegalArgumentException("Unknown configuration type: " + configurationType);
        }
    }

    private byte[] normalizeRandomValue(String type, byte[] values) {
        int length = values.length;
        for(int idx = 0 ; idx < length; idx++) {
            if(values[idx] == (byte) 0xff) {
                values[idx] = randomValue(type, values[idx]);
            }
        }
        return values;
    }

    private byte randomValue(String type, byte defaultValue) {
        Random random = new Random();
        if (type.equals("cipherSuites")) {
            return (byte) random.nextInt(84);
        } else if (type.equals("namedGroups")) {
            return (byte) random.nextInt(16);
        } else if (type.equals("compressionMethods")) {
            return (byte) random.nextInt(3);
        } else if (type.equals("signatureAndHashAlgorithms")) {
            return (byte) random.nextInt(34);
        } else if (type.equals("certificateTypes")) {
            return (byte) random.nextInt(8);
        } else if (type.equals("certificateRequests")) {
            return (byte) random.nextInt(3);
        } else if (type.equals("keyShareGroups")) {
            return (byte) random.nextInt(17);
        }
        return defaultValue;
    }

    public byte[] getConfigurationValues(String configurationType){
        return configurations.get(configurationType);
    }

    public boolean isValid(){
        return true;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, byte[]> entry : configurations.entrySet()){
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(new String(entry.getValue(), StandardCharsets.UTF_8));
        }
        return sb.toString();
    }
}
