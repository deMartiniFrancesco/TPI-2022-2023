package demartini_F_UDP_2.bin;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

class UDP {

    private InetAddress ip;
    private int port;
    private DatagramSocket socket;
    private DatagramPacket p_send;

    public UDP(String ipTarget, int port) {
        try {
            ip = InetAddress.getByName(ipTarget);
            this.port = port;
            socket = new DatagramSocket();
        } catch (IOException e) {
            System.exit(1);
        }
    }

    public void sendPacket() {
        int number_int = 5;

        int[] intArray = new int[number_int];
        for (int i = 0; i < number_int; i++) {
            intArray[i] = i + 1;
        }

        byte[] sendBuf = intsToBytes(intArray);

        try {
            p_send = new DatagramPacket(sendBuf, sendBuf.length, ip, port);
            socket.send(p_send);
        } catch (IOException ignored) {
        }
    }

    public void recevePacket() {
        byte[] recBuf = new byte[p_send.getLength()];
        try {
            DatagramPacket p_rece = new DatagramPacket(recBuf, recBuf.length);
            socket.receive(p_rece);
        } catch (IOException ignored) {
            return;
        }

        int[] intsRecive = bytesToInts(recBuf);
        System.out.println("intsRecive = " + Arrays.toString(intsRecive));
    }


    private byte[] intsToBytes(int[] ints) {
        ByteBuffer bb = ByteBuffer.allocate(ints.length * 4);
        IntBuffer ib = bb.asIntBuffer();
        for (int i : ints) ib.put(i);
        return bb.array();
    }

    private int[] bytesToInts(byte[] bytes) {
        int[] ints = new int[bytes.length / 4];
        ByteBuffer.wrap(bytes).asIntBuffer().get(ints);
        return ints;
    }
}

class UDPTest {
    public static void main(String[] args) {

        System.out.println("Start");

        UDP udp = new UDP("172.16.1.99", 7);

        udp.sendPacket();
        udp.recevePacket();

        System.out.println("End");

    }
}