package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.packages.MTPPacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * The type Mtp client.
 */
public class MTPClient extends MTP {

    /**
     * The Input stream.
     */
    private DataInputStream inputStream = null;
    /**
     * The Output stream.
     */
    private DataOutputStream outputStream = null;

    /**
     * Instantiates a new Mtp client.
     *
     * @param clientPort the server port
     */
    public MTPClient(int clientPort) {
        super(clientPort);
    }

    /**
     * Connect.
     *
     * @param targetAddress the target address
     * @param targetPort    the target port
     */
    public void connect(InetAddress targetAddress, int targetPort) {
        try {
            activeConnectionSocket = new Socket(targetAddress, targetPort);
            inputStream = new DataInputStream(activeConnectionSocket.getInputStream());
            outputStream = new DataOutputStream(activeConnectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        isConnected = true;
    }

    /**
     * Send packet.
     *
     * @param packet the packet
     */
    public void sendPacket(MTPPacket packet) {
        if (isConnected) {
            System.out.println("MTPClient.sendPacket");
            try {
                outputStream.write(packet.getDataByte());
                outputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Not connection! Use .connect() before send packet");
        }
    }
}
