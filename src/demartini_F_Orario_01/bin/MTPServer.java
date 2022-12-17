package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.events.connections.ConnectionReceivedEvent;
import demartini_F_Orario_01.bin.events.connections.ServerAccept;
import demartini_F_Orario_01.bin.events.data.DataReceivedEvent;
import demartini_F_Orario_01.bin.events.data.ServerDataListener;
import demartini_F_Orario_01.bin.packages.MTPDataRequest;
import demartini_F_Orario_01.bin.packages.MTPError;
import demartini_F_Orario_01.bin.packages.MTPRegistrationRequest;
import demartini_F_Orario_01.bin.packages.MTPRegistrationSuccess;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MTPServer extends MTP {

    private final ExecutorService dataExecutorService = Executors.newCachedThreadPool();

    private final Set<ConnectionReceivedEvent> connectionReceived = new HashSet<>();


    public MTPServer(int serverPort) {
        super(serverPort);
    }


    public void startListening() {
        System.out.println("Start Listening...");
        closeIfNotNull();
        listener = createListeningSocket();
        connectionExecutorService.execute(new ServerAccept(listener, this::connectionReceive));
    }

    private void connectionReceive(ConnectionReceivedEvent event) {
        if (!connectionReceived.contains(event)) {
            System.out.printf(
                    "Connection accept: %s:%s%n",
                    event.getAddress(),
                    event.getPort()
            );
            connectionReceived.add(event);
            dataExecutorService.execute(new ServerDataListener(event, this::receivePacket));
        } else {
            System.out.println("Error");
        }
    }

    public void receivePacket(DataReceivedEvent event) {
        if (connectionReceived.contains(event.connection())) {
            try {
                int dataType = event.getDataInput().readByte();
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
                                event.getDataInput().readNBytes(event.getDataInput().readInt())
                        );
                        case REGISTRATION_REQUEST ->
                                new MTPRegistrationRequest(event.getDataInput().readNBytes(event.getDataInput().readInt()));
                        case REGISTRATION_SUCCESS ->
                                new MTPRegistrationSuccess(event.getDataInput().readNBytes(Integer.BYTES));
                        case ERROR -> new MTPError(event.getDataInput().readNBytes(Integer.BYTES));
                        default -> null;
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
