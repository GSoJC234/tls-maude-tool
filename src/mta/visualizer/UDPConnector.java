package mta.visualizer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPConnector {

    private DatagramSocket sendSocket = null;
    private DatagramSocket receiveSocket = null;

    public void send(byte[] data, String ip, int port) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            if (sendSocket == null) {
                sendSocket = new DatagramSocket();
            }
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            sendSocket.send(packet);
        } catch (SecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] receive(int port, int maxSize) {
        try {
            if (receiveSocket == null || receiveSocket.getLocalPort() != port) {
                receiveSocket = new DatagramSocket(port);
            }
            byte[] buffer = new byte[maxSize];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            receiveSocket.receive(packet);
            return packet.getData();
        } catch (SecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
