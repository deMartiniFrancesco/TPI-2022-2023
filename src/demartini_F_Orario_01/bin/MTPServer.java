package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.connections.ConnectionReceivedEvent;
import demartini_F_Orario_01.bin.packages.MTPPacket;
import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationError;
import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationRequest;
import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationSuccess;

import java.io.IOException;

/**
 * The type Mtp server.
 */
public class MTPServer extends MTP {

    /**
     * Instantiates a new Mtp server.
     *
     * @param serverPort the server port
     */
    public MTPServer(int serverPort) {
        super(serverPort);
    }


    @Override
    protected void peerToPeer(ConnectionReceivedEvent event) {
        super.peerToPeer(event);
        isConnected = true;
        System.out.println(receivePacket().toString());
    }

    /**
     * Receive packet mtp packet.
     *
     * @return the mtp packet
     */
    public MTPPacket receivePacket() {
        if (isConnected) {
            try {
                byte[] receiveBuff = inputStream.readAllBytes();
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
        }
        return null;
    }


}