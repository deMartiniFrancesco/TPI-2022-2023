package demartini_F_TFTP.bin;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


class TFTP {

    private InetAddress ipTarget;
    private int port;
    private DatagramSocket socket;


    public TFTP(InetAddress ipTarget, int port) {
        try {
            this.ipTarget = ipTarget;
            this.port = port;
            socket = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void sendPacket(TftpPacket packet) {
        try {
            socket.send(new DatagramPacket(
                    packet.bytePacket,
                    packet.bytePacket.length,
                    ipTarget,
                    port
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("packet = " + packet);
    }

    public void receivePacket() {
        byte[] receiveBuff = new byte[512];
        try {
            socket.receive(new DatagramPacket(
                    receiveBuff,
                    receiveBuff.length
            ));
            System.out.println("receiveBuff = " + new String(receiveBuff, 0, receiveBuff.length));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("TFTP.receivePacket");
    }
}