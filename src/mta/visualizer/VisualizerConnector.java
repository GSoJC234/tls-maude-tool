package mta.visualizer;

import mta.visualizer.info.InfoRecord;

import java.util.*;

public class VisualizerConnector {

    private final int VREADY_SIZE = 2;
    private final int INFO_SIZE = 1024;
    private final int VCOMPLETE_SIZE = 2;

    private String sendIp;
    private int sendPort;
    private int receivePort;
    private String sharedDirectory;
    private UDPConnector connector;

    public VisualizerConnector(String sendIp, int sendPort, int receivePort, String sharedDirectory) {
        this.sendIp = sendIp;
        this.sendPort = sendPort;
        this.receivePort = receivePort;
        this.sharedDirectory = sharedDirectory;
        this.connector = new UDPConnector();
    }

    public void connect() {
        byte[] MReady = new byte []{0x01, 0x00};
        connector.send(MReady, sendIp, sendPort);

        byte[] VReady = connector.receive(receivePort, VREADY_SIZE);
        if (VReady[0] != 0x02 || VReady[1] != 0x00) {
            System.out.println("V_Ready receive error: " + Arrays.toString(VReady));
            return;
        }
        System.out.println("V_Ready receive: " + Arrays.toString(VReady));

        while (true){
            InfoRecord infoRecord = new InfoRecord();

            byte[] target_info = connector.receive(receivePort, INFO_SIZE);
            System.out.println("Target_Info receive[byte]: " + Arrays.toString(target_info));
            if (target_info[0] == 0x08 && target_info[1] == 0x00) {
                System.out.println("M_Terminate received!");
                break;
            }

            infoRecord.parseTargetInfo(target_info, 0);
            if (!infoRecord.getTarget().isValid()) {
                System.out.println("Target_Info receive error: " + infoRecord.getTarget());
                return;
            }
            System.out.println("Target_Info receive: \n" + infoRecord.getTarget().toString());


            byte[] requirement_info = connector.receive(receivePort, INFO_SIZE);
            System.out.println("Requirement_Info receive[byte]: " + Arrays.toString(requirement_info));
            infoRecord.parseRequirement(requirement_info, 0);
            if (!infoRecord.getRequirement().isValid()) {
                System.out.println("Requirement_Info receive error: " + infoRecord.getRequirement());
                return;
            }
            System.out.println("Requirement_Info receive: \n" + infoRecord.getRequirement().toString());

            byte[] configuration_info = connector.receive(receivePort, INFO_SIZE);
            System.out.println("Configurtion_Info receive[byte]: " + Arrays.toString(configuration_info));
            infoRecord.parseConfiguration(configuration_info, 0);
            System.out.println("Configuration_Info receive: \n" + infoRecord.getConfigurations());

            FormalAnalysisRunner formalAnalysisRunner = new FormalAnalysisRunner(sharedDirectory);
            try{
                formalAnalysisRunner.run(infoRecord);
            } catch (RuntimeException e) {
                System.out.println("Error!!!!! " + e.toString());
                e.printStackTrace();
            }

            byte[] MResult;
            if (formalAnalysisRunner.analysisSuccess()){
                System.out.println("Formal Analysis Success!");
                MResult = new byte[]{0x06, 0x01};
            } else {
                System.out.println("Formal Analysis Failed!");
                MResult = new byte[]{0x06, 0x00};
            }
            connector.send(MResult, sendIp, sendPort);

            byte[] VComplete = connector.receive(receivePort, VCOMPLETE_SIZE);
            if (VComplete[0] != 0x07 || VComplete[1] != 0x00) {
                System.out.println("V_Complete receive error: " + Arrays.toString(VComplete));
                return;
            }
        }
    }
}
