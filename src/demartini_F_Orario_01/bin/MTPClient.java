package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.packages.MTPPacket;

import java.io.IOException;

/**
 * The type Mtp client.
 */
public class MTPClient extends MTP {

    /**
     * Instantiates a new Mtp client.
     *
     * @param clientPort the server port
     */
    public MTPClient(int clientPort) {
        super(clientPort);
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
