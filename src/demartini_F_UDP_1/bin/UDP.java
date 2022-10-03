package demartini_F_UDP_1.bin;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDP {
    public static void main(String[] args) throws IOException {

        System.out.println("Start");

        InetAddress ip1 = InetAddress.getByName("172.16.1.99");
        int port = 7;
        try (DatagramSocket socket = new DatagramSocket()) {
            DatagramPacket p_send, p_rece;
            String mex = "meprova";

            byte[] sendBuf = mex.getBytes();
            p_send = new DatagramPacket(sendBuf, sendBuf.length, ip1, port);

            byte[] recBuf = new byte[256];
            p_rece = new DatagramPacket(recBuf, recBuf.length);

            socket.send(p_send);
            socket.receive(p_rece);
            String rec = new String(recBuf, 0, p_rece.getLength());

            System.out.println(rec);
        }
        System.out.println("End");

    }
}