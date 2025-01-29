package mode;

import runner.MaudeRunner2;

import java.io.IOException;
import java.net.*;

public class VisualMode implements RunningMode {

    private enum NetworkState {
        INIT, SEND_READY, RECV_READY, RECV_NODE_INFO, RECV_REQ_INFO,
        SEND_RESULT, RECV_COMPLETE, RECV_TERMINATE
    };

    private String ip;
    private int port;
    private String recvIp;
    private int recvPort;
    private DatagramSocket sendSocket;
    private DatagramSocket recvSocket;

    private byte[] TLSLibraryIP;
    private byte[] TLSLibraryPort;
    private byte[] requirementInfo;
    private int requirementLength;

    public VisualMode(String ip, int port, String recvIp, int recvPort) {
        this.ip = ip;
        this.port = port;
        this.recvIp = recvIp;
        this.recvPort = recvPort;

        this.TLSLibraryIP = new byte[4];
        this.TLSLibraryPort = new byte[2];
        this.requirementInfo = new byte[40];
        this.requirementLength = 0;
        try {
            this.sendSocket = new DatagramSocket();
            this.recvSocket = new DatagramSocket(recvPort, InetAddress.getByName(recvIp));
        } catch (SocketException e) {
            System.out.println("Socket create error: " + e.getMessage());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
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
                    if(result){
                        runMaudeVerification();
                        mode = NetworkState.RECV_REQ_INFO;
                    }
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
                    mode = NetworkState.RECV_READY;
                    break;
            }
        }
        System.out.println("System terminates");
    }

    private boolean sendReady() {
        byte[] data = {0x01, 0x00};
        try{
            DatagramPacket packet = new DatagramPacket(data, data.length,  InetAddress.getByName(ip), port);
            sendSocket.send(packet);
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
        System.out.println("Waiting for recv ready...");
        try{
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            recvSocket.receive(packet);
            System.out.println("receive ready");
            byte[] received_buffer = packet.getData();
            return received_buffer[0] == 0x02 && received_buffer[1] == 0x00;
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
        }
        return false;
    }

    private boolean recvNodeInfo(){
        byte[] buffer = new byte[1024];
        System.out.println("Waiting for recv node...");
        try{
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            recvSocket.receive(packet);
            byte[] received_buffer = packet.getData();
            if(received_buffer[0] == 0x03) {
                TLSLibraryIP[0] = received_buffer[1];
                TLSLibraryIP[1] = received_buffer[2];
                TLSLibraryIP[2] = received_buffer[3];
                TLSLibraryIP[3] = received_buffer[4];

                TLSLibraryPort[0] = received_buffer[5];
                TLSLibraryPort[1] = received_buffer[6];
                System.out.println("receive node info");
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
        System.out.println("Waiting for recv requirement info...");
        try{
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            recvSocket.receive(packet);
            byte[] received_buffer = packet.getData();
            if(received_buffer[0] == 0x04) {
                for(int i = 1; received_buffer[i] != (byte) 0xff ; i++){
                    requirementInfo[i-1] = received_buffer[i];
                    requirementLength++;
                }
                System.out.println("receive requirement info");
                return true;
            }
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
            return false;
        }
        return false;
    }

    private boolean sendResult(){
        byte[] data = {0x05, 0x01, 0x00};
        try{
            InetAddress serverAddress = InetAddress.getByName(this.ip);
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);
            sendSocket.send(packet);
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
            recvSocket.receive(packet);
            byte[] received_buffer = packet.getData();
            return received_buffer[0] == 0x06 && received_buffer[1] == 0x00;
        } catch (IOException e) {
            System.out.println("IO exception: " + e.getMessage());
            return false;
        }
    }

    private void runMaudeVerification(){
        MaudeRunner2 runner = new MaudeRunner2(requirementInfo, requirementLength);
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
