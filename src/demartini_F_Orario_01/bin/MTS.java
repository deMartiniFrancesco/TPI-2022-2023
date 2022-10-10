package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.packages.MTSPacket;
import demartini_F_Orario_01.bin.packages.registration.MTSRegistrationError;
import demartini_F_Orario_01.bin.packages.registration.MTSRegistrationRequest;
import demartini_F_Orario_01.bin.packages.registration.MTSRegistrationSuccess;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;


class MTS {

    private InetAddress ipTarget;
    private int port;
    private DatagramSocket socket;


    public MTS(InetAddress ipTarget, int port) {
        try {
            this.ipTarget = ipTarget;
            this.port = port;
            socket = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void sendPacket(MTSPacket packet) {
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

    public MTSPacket receivePacket() {
        byte[] receiveBuff = new byte[32];
        try {
            socket.receive(new DatagramPacket(
                    receiveBuff,
                    receiveBuff.length
            ));

            PacketOperationCode type = PacketOperationCode.findByValue(receiveBuff[0]);
            ;
            if (type != null) {
                System.out.println("MTS.receivePacket");
                return switch (type) {
                    case REQ_REGISTRAZIONE -> new MTSRegistrationRequest(receiveBuff);
                    case REG_SUCCESS -> new MTSRegistrationSuccess(receiveBuff);
                    case REG_ERROR -> new MTSRegistrationError(receiveBuff);
                    default -> null;
                };
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("receiveBuff = " + Arrays.toString(receiveBuff));
        return null;
    }
}