package demartini_F_TFTP.bin;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

class TFTP {

    private InetAddress ip;
    private int port;
    private DatagramSocket socket;
    private DatagramPacket p_send;

    public TFTP(String ipTarget, int port) {
        try {
            ip = InetAddress.getByName(ipTarget);
            this.port = port;
            socket = new DatagramSocket();
        } catch (IOException e) {
            System.exit(1);
        }
    }

    public void sendPacket() {

        byte[] sendBuf = new byte[500];

        



    }

    public void recevePacket() {

    }


}

class TFTPTest {
    public static void main(String[] args) {

        System.out.println("Start");

        TFTP tftp = new TFTP("172.16.1.99", 69);

        tftp.sendPacket();
        tftp.recevePacket();

        System.out.println("End");

    }
}