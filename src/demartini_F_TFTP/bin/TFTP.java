package demartini_F_TFTP.bin;

import demartini_F_TFTP.bin.packages.TftpDataPacket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;


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

    public TftpPacket receivePacket() {
        byte[] receiveBuff = new byte[512];
        try {
            socket.receive(new DatagramPacket(
                    receiveBuff,
                    receiveBuff.length
            ));

            PacketType type = PacketType.findByValue(
                    ByteBuffer.wrap(
                            Utility.toIntegerLength(Arrays.copyOfRange(receiveBuff, 0, 2)
                            )
                    ).getInt());
            ;
            if (type != null) {
                System.out.println("TFTP.receivePacket");
                return switch (type) {
                    case DATA -> new TftpDataPacket(receiveBuff);
                    default -> null;
                };
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}