package demartini_F_Orario_01.bin;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {
        MTP server;
        try {
            server = new MTP(new DatagramSocket(1234));
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        while (true){
            server.responsePacket(server.receivePacket());
        }
    }
}
