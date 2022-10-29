package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.packages.MTPPacket;

import java.io.IOException;
import java.net.InetAddress;

/**
 * The type Mtp client.
 */
public class MTPClient extends MTP {

    /**
     * Instantiates a new Mtp client.
     *
     * @param serverPort the server port
     */
    public MTPClient(int serverPort) {
        super(serverPort);
    }

    @Override
    public void connect(InetAddress targetAddress, int targetPort) {
        super.connect(targetAddress, targetPort);
        try {
            setUpStreams(activeConnectionSocket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPacket(MTPPacket packet) {
        if (isConnected) {
            System.out.println("MTPClient.sendPacket");
            try {
                outputStream.write(packet.getBytePacket());
                outputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Not connection! Use .connect() before send packet");
        }
    }

}