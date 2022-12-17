package demartini_F_Orario_01.bin.main;

import demartini_F_Orario_01.bin.MTPServer;
import demartini_F_Orario_01.bin.packages.MTPPacket;
import demartini_F_Orario_01.bin.packages.MTPRegistrationRequest;
import demartini_F_Orario_01.bin.providers.RegistrationProviders;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * The type Server.
 */
public class Server {

    /**
     * The Target packet.
     */
    static DatagramPacket targetPacket;
    /**
     * The Server.
     */
    static DatagramSocket server;
    /**
     * The Server address.
     */
    static InetAddress serverAddress;
    /**
     * The Server port.
     */
    static int serverPort;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        MTPServer mtpServer = new MTPServer(1234);
        mtpServer.startListening();
    }

    /**
     * Response packet.
     *
     * @param packet the packet
     */
    public static void responsePacket(MTPPacket packet) {
        if (targetPacket == null || packet == null) {
            throw new RuntimeException();
        }
        setServerAddress(targetPacket.getAddress());
        setServerPort(targetPacket.getPort());

        MTPPacket response =
                switch (packet.getOperationCode()) {
                    case REGISTRATION_REQUEST -> RegistrationProviders.evaluateRequest(
                            (MTPRegistrationRequest) packet
                    );
                    default -> null;
                };

        if (response == null) {
            System.out.println("MTP.responsePacket FAIL!\n" + "packet - " + packet);
            return;
        }
        sendPacket(response);
    }

    /**
     * Sets server address.
     *
     * @param serverAddress the server address
     */
    public static void setServerAddress(InetAddress serverAddress) {
        Server.serverAddress = serverAddress;
    }

    /**
     * Sets server port.
     *
     * @param serverPort the server port
     */
    public static void setServerPort(int serverPort) {
        Server.serverPort = serverPort;
    }

    /**
     * Send packet.
     *
     * @param packet the packet
     */
    public static void sendPacket(MTPPacket packet) {
        try {
            server.send(
                    new DatagramPacket(
                            packet.getDataByte(),
                            packet.getDataByte().length,
                            serverAddress,
                            serverPort
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("packet = " + packet);
    }
}
