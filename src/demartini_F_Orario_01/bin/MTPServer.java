package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.connections.ConnectionReceivedEvent;
import demartini_F_Orario_01.bin.data.DataReceivedEvent;
import demartini_F_Orario_01.bin.data.ServerDataListener;
import demartini_F_Orario_01.bin.packages.MTPDataRequest;
import demartini_F_Orario_01.bin.packages.MTPError;
import demartini_F_Orario_01.bin.packages.MTPRegistrationRequest;
import demartini_F_Orario_01.bin.packages.MTPRegistrationSuccess;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Mtp server.
 */
public class MTPServer extends MTP {


    protected final ExecutorService dataExecutorService = Executors.newCachedThreadPool();


    /**
     * Instantiates a new Mtp server.
     *
     * @param serverPort the server port
     */
    public MTPServer(int serverPort) {
        super(serverPort);
    }

    @Override
    protected void connectionReceive(ConnectionReceivedEvent event) {
        if (!isConnected) {
            this.activeConnectionSocket = event.socket();
            try {
                inputStream = new DataInputStream(event.getInput());
                outputStream = new DataOutputStream(event.getOutput());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.printf(
                    "Connection accept: %s:%s%n",
                    event.getAddress(),
                    event.getPort()
            );
            isConnected = true;
            dataExecutorService.execute(new ServerDataListener(inputStream, this::receivePacket));
        } else {
            System.out.println("Error");
        }
    }

    /**
     * Receive packet mtp packet.
     */
    public void receivePacket(DataReceivedEvent event) {
        if (isConnected) {
            try {
                int dataType = inputStream.readByte();
                PacketOperationCode type = PacketOperationCode.findByValue(dataType);
                System.out.println("type = " + type);
                if (type != null) {
                    System.out.println(switch (type) {
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
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
