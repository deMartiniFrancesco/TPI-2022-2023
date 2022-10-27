package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.connections.ConnectionReceivedEvent;
import demartini_F_Orario_01.bin.packages.MTPPacket;
import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationError;
import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationRequest;
import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationSuccess;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
                int dataType = inputStream.readByte();
                System.out.println("dataString = " + dataString);
                PacketOperationCode type = PacketOperationCode.findByValue(dataType);
                if (type != null) {
                    return switch (type) {
                        case REQ_REGISTRAZIONE -> new MTPRegistrationRequest(receiveData);
                        case REG_SUCCESS -> new MTPRegistrationSuccess(receiveData);
                        case REG_ERROR -> new MTPRegistrationError(receiveData);
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