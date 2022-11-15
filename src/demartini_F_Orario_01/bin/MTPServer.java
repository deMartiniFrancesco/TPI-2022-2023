package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.connections.ConnectionReceivedEvent;
import demartini_F_Orario_01.bin.packages.MTPPacket;
import demartini_F_Orario_01.bin.packages.data.MTPDataRequest;
import demartini_F_Orario_01.bin.packages.registration.MTPError;
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
        System.out.println(receivePacket());
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
                PacketOperationCode type = PacketOperationCode.findByValue(dataType);
                System.out.println("type = " + type);
                if (type != null) {
                    return switch (type) {
                        case PROFESSOR_REQUEST,
                                CLASSROOM_REQUEST,
                                CLASS_REQUEST,
                                CLASSROOM_REQUEST_NOW,
                                CLASS_REQUEST_NOW -> new MTPDataRequest(
                                type,
                                inputStream.readNBytes(inputStream.readInt())
                        );
                        case REGISTRATION_REQUEST ->
                                new MTPRegistrationRequest(inputStream.readNBytes(inputStream.readInt()));
                        case REGISTRATION_SUCCESS -> new MTPRegistrationSuccess(inputStream.readNBytes(Integer.BYTES));
                        case ERROR -> new MTPError(inputStream.readNBytes(Integer.BYTES));
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
