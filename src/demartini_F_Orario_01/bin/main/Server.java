package demartini_F_Orario_01.bin.main;

import demartini_F_Orario_01.bin.MTPServer;
import demartini_F_Orario_01.bin.packages.MTPPacket;
import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationRequest;
import demartini_F_Orario_01.bin.providers.RegistrationProviders;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    static DatagramPacket targetPacket;
    static DatagramSocket server;
    static InetAddress serverAddress;
    static int serverPort;

    public static void main(String[] args) {

        MTPServer mtpServer = new MTPServer(1234);
    }


    public static void responsePacket(MTPPacket packet) {
        if (targetPacket == null || packet == null) {
            throw new RuntimeException();
        }
        setServerAddress(targetPacket.getAddress());
        setServerPort(targetPacket.getPort());

        MTPPacket response = switch (packet.getOperationCode()) {
            case REQ_REGISTRAZIONE -> RegistrationProviders.evaluateRequest((MTPRegistrationRequest) packet);
            default -> null;
        };

        if (response == null) {
            System.out.println("MTP.responsePacket FAIL!\n" +
                    "packet - " + packet
            );
            return;
        }
        sendPacket(response);
    }

    public static void setServerAddress(InetAddress serverAddress) {
        Server.serverAddress = serverAddress;
    }

    public static void setServerPort(int serverPort) {
        Server.serverPort = serverPort;
    }

    public static void sendPacket(MTPPacket packet) {
        try {
            server.send(new DatagramPacket(
                    packet.getDataByte(),
                    packet.getDataByte().length,
                    serverAddress,
                    serverPort
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("packet = " + packet);
    }

}
