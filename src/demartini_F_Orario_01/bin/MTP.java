package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.packages.MTPPacket;
import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationError;
import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationRequest;
import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationSuccess;
import demartini_F_Orario_01.bin.providers.RegistrationProviders;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


class MTP {

    private InetAddress ipTarget;
    private int port;
    private DatagramSocket socket;

    private DatagramPacket targetPacket;

    public MTP(InetAddress ipTarget, int port) {
        try {
            this.ipTarget = ipTarget;
            this.port = port;
            socket = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public MTP(DatagramSocket socket) {
        this.socket = socket;
    }

    public void setIpTarget(InetAddress ipTarget) {
        this.ipTarget = ipTarget;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void sendPacket(MTPPacket packet) {
        try {
            socket.send(new DatagramPacket(
                    packet.getBytePacket(),
                    packet.getBytePacket().length,
                    ipTarget,
                    port
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("packet = " + packet);
    }

    public MTPPacket receivePacket() {
        byte[] receiveBuff = new byte[32];
        try {
            targetPacket = new DatagramPacket(
                    receiveBuff,
                    receiveBuff.length
            );
            socket.receive(targetPacket);
            System.out.printf("Client address: %s, port: %s%n", targetPacket.getAddress(), targetPacket.getPort());

            PacketOperationCode type = PacketOperationCode.findByValue(receiveBuff[0]);
            if (type != null) {
                return switch (type) {
                    case REQ_REGISTRAZIONE -> new MTPRegistrationRequest(receiveBuff);
                    case REG_SUCCESS -> new MTPRegistrationSuccess(receiveBuff);
                    case REG_ERROR -> new MTPRegistrationError(receiveBuff);
                    default -> null;
                };
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void responsePacket(MTPPacket packet) {
        if (targetPacket == null){
            throw new RuntimeException();
        }
        setIpTarget(targetPacket.getAddress());
        setPort(targetPacket.getPort());

        MTPPacket response = switch (packet.getOperationCode()){
            case REQ_REGISTRAZIONE -> RegistrationProviders.evaluateRequest((MTPRegistrationRequest) packet);
            default -> null;
        };

        if (response == null){
            System.out.println("MTP.responsePacket FAIL!\n" +
                    "packet - " + packet + "\n"
            );
            return;
        }
        targetPacket = null;
        sendPacket(response);
    }



}