package mode;

import runner.MaudeRunner;

import java.io.IOException;
import java.net.*;

public class VisualMode implements RunningMode {

    private enum NetworkState {
        INIT, SEND_READY, RECV_READY, RECV_NODE_INFO, RECV_REQ_INFO,
        SEND_RESULT, RECV_COMPLETE, RECV_TERMINATE
    };

    private String ip;
    private int port;
    private DatagramSocket socket;

    private byte[] targetIP;
    private byte[] targetPort;
    private byte[] requirementInfo;
    private int requirementLength;

    public VisualMode(String ip, int port){
        this.ip = ip;
        this.port = port;
        this.targetIP = new byte[4];
        this.targetPort = new byte[2];
        this.requirementInfo = new byte[40];
        this.requirementLength = 0;
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("Socket create error: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        boolean result = true;
        NetworkState mode = NetworkState.INIT;
        while (result){
            switch(mode){
                case INIT:
                    result = sendReady();
                    mode = NetworkState.SEND_READY;
                    break;
                case SEND_READY:
                    result = recvReady();
                    mode = NetworkState.RECV_READY;
                    break;
                case RECV_READY:
                    result = recvNodeInfo();
                    mode = NetworkState.RECV_NODE_INFO;
                    break;
                case RECV_NODE_INFO:
                    result = recvReqInfo();
                    runMaudeVerification();
                    MaudeRunner runner = new MaudeRunner(requirementInfo, requirementLength);
                    runner.run();
                    mode = NetworkState.RECV_REQ_INFO;
                    break;
                case RECV_REQ_INFO:
                    result = sendResult();
                    mode = NetworkState.SEND_RESULT;
                    break;
                case SEND_RESULT:
                    result = recvComplete();
                    mode = NetworkState.RECV_COMPLETE;
                    break;
                case RECV_COMPLETE:
                    mode = NetworkState.RECV_NODE_INFO;
                    break;
            }
        }
    }

    private boolean sendReady() {
        byte[] data = {0x01, 0x00};
        try{
            InetAddress serverAddress = InetAddress.getByName(this.ip);
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);
            socket.send(packet);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
            return false;
        }
        System.out.println("Send ready message");
        return true;
    }

    private boolean recvReady(){
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        System.out.println("Waiting for recv ready...");
        try{
            socket.receive(packet);
            return packet.getLength() > 0 && packet.getData()[0] == 0x02
                    && packet.getData()[1] == 0x00;
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
        }
        return false;
    }

    private boolean recvNodeInfo(){
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        System.out.println("Waiting for recv node...");
        try{
            socket.receive(packet);
            if(packet.getLength() > 7 && packet.getData()[0] == 0x03) {
                byte[] received_buffer = packet.getData();
                targetIP[0] = received_buffer[1];
                targetIP[1] = received_buffer[2];
                targetIP[2] = received_buffer[3];
                targetIP[3] = received_buffer[4];

                targetPort[0] = received_buffer[5];
                targetPort[1] = received_buffer[6];
                return true;
            }
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean recvReqInfo(){
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        System.out.println("Waiting for recv requirement info...");
        try{
          socket.receive(packet);
          if(packet.getLength() > 7 && packet.getData()[0] == 0x04) {
              byte[] received_buffer = packet.getData();
              for(int i = 1; received_buffer[i] != (byte) 0xff ; i++){
                  requirementInfo[i] = received_buffer[i];
                  requirementLength++;
              }
              return true;
          }
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean sendResult(){
        byte[] data = {0x05, 0x00, 0x00};
        try{
            InetAddress serverAddress = InetAddress.getByName(this.ip);
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);
            socket.send(packet);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
            return false;
        }
        System.out.println("Send ready message");
        return true;
    }

    private boolean recvComplete(){
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        System.out.println("Waiting for recv complete...");
        try{
            socket.receive(packet);
            return packet.getLength() > 2 && packet.getData()[0] == 0x06;
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
            return false;
        }
    }

    private void runMaudeVerification(){
        MaudeRunner runner = new MaudeRunner(requirementInfo, requirementLength);
        Thread thread = new Thread(runner);
        thread.start();
        try {
            System.out.println("Waiting for maude verification...");
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception: " + e.getMessage());
        }
        System.out.println("Maude verification complete");
    }
}
