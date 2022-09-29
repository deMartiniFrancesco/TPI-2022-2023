package demartini_F_TFTP.bin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;


class TFTP {

    private InetAddress ip;
    private int port;
    private DatagramSocket socket;
    private final PacketType packetType;
    private final PacketMode packetMode;
    private String fileName;

    public TFTP(String ipTarget, int port, PacketType packetType, PacketMode packetMode, String filename) {
        try {
            ip = InetAddress.getByName(ipTarget);
            this.port = port;
            socket = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        this.packetType = packetType;
        this.packetMode = packetMode;
        this.fileName = filename;
    }

    public TFTP(String ipTarget, int port, PacketType packetType, PacketMode packetMode) {
        try {
            ip = InetAddress.getByName(ipTarget);
            this.port = port;
            socket = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        this.packetType = packetType;
        this.packetMode = packetMode;
    }

    public void sendPacket() {
        if (fileName != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(512);
            outputStream.writeBytes(packetType.getBytes());
            outputStream.writeBytes(fileName.getBytes());
            outputStream.write(0);
            outputStream.writeBytes(packetMode.getBytes());
            outputStream.write(0);

            byte[] sendBuf = outputStream.toByteArray();

            try {
                socket.send(new DatagramPacket(sendBuf, sendBuf.length, ip, port));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("sendBuf = " + Arrays.toString(outputStream.toByteArray()));
        }
    }

    public void receivePacket() {
        byte[] receiveBuff = new byte[512];
        try {
            socket.receive(new DatagramPacket(
                    receiveBuff,
                    receiveBuff.length,
                    socket.getInetAddress(),
                    socket.getLocalPort()
            ));
            System.out.println("TFTP.receivePacket");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("receiveBuff = " + new String(receiveBuff));


    }


}

class TFTPTest {
    public static void main(String[] args) {

        System.out.println("Start");

        TFTP tftp = new TFTP("172.16.1.99", 69, PacketType.RRQ, PacketMode.OCTET, "filecorto");

        tftp.sendPacket();
        tftp.receivePacket();

        System.out.println("End");

    }
}