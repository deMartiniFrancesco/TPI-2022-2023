package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.errors.MTPException;
import demartini_F_Orario_01.bin.events.connections.ConnectionReceivedEvent;
import demartini_F_Orario_01.bin.events.connections.ServerAccept;
import demartini_F_Orario_01.bin.events.data.DataReceivedEvent;
import demartini_F_Orario_01.bin.events.data.ServerDataListener;
import demartini_F_Orario_01.bin.packages.*;
import demartini_F_Orario_01.bin.providers.RegistrationProviders;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MTPServer extends MTP {

    private final ExecutorService dataExecutorService = Executors.newCachedThreadPool();

    private final Set<ConnectionReceivedEvent> connectionsReceived = new HashSet<>();


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
        if (!connectionsReceived.contains(event)) {
            System.out.printf("Connection accept: %s:%s%n", event.getAddress(), event.getPort());
            connectionsReceived.add(event);
            dataExecutorService.execute(new ServerDataListener(event, this::receivePacket));
        } else {
            System.out.println("Error");
        }
    }

    public void receivePacket(DataReceivedEvent event) {
        if (connectionsReceived.contains(event.connection())) {
            try {
                int dataType = event.getDataInput().readByte();
                PacketOperationCode type = PacketOperationCode.findByValue(dataType);
                responsePacket(switch (type) {
                    case PROFESSOR_REQUEST, CLASSROOM_REQUEST, CLASS_REQUEST, CLASSROOM_REQUEST_NOW, CLASS_REQUEST_NOW ->
                            new MTPDataRequest(type, event.getDataInput().readNBytes(event.getDataInput().readInt()));
                    case REGISTRATION_REQUEST ->
                            new MTPRegistrationRequest(event.getDataInput().readNBytes(event.getDataInput().readInt()));
                    case REGISTRATION_SUCCESS ->
                            new MTPRegistrationSuccess(event.getDataInput().readNBytes(Integer.BYTES));
                    case ERROR -> new MTPError(event.getDataInput().readNBytes(Integer.BYTES));
                    default -> null;
                }, event);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void responsePacket(MTPPacket packet, DataReceivedEvent event) {
        try {
            if (packet == null) {
                throw new MTPException(PacketErrorCode.MALFORMED_PACKET);
            }
            switch (packet.getOperationCode()) {
                case REGISTRATION_REQUEST -> RegistrationProviders.evaluateRequest((MTPRegistrationRequest) packet);


                default -> throw new MTPException(PacketErrorCode.DEFAULT);
            }
        } catch (MTPException error) {
            sendPacket(new MTPError(error.getCode()), event);
        }
    }


    public static void sendPacket(MTPPacket packet, DataReceivedEvent event) {
        try {
            event.getDataOutput().write(packet.getDataByte());
            event.getDataOutput().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("packet = " + packet);
    }


}
