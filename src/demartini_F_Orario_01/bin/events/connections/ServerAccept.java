package demartini_F_Orario_01.bin.events.connections;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerAccept implements Runnable {

    private final ServerSocket serverSocket;
    private final ConnectionReceivedListener listener;

    public ServerAccept(ServerSocket serverSocket, ConnectionReceivedListener receivedListener) {
        this.serverSocket = serverSocket;
        this.listener = receivedListener;
    }

    @Override
    public void run() {
        try {
            while(true) {
                listener.onConnectionReceived(new ConnectionReceivedEvent(serverSocket.accept()));
            }
        } catch (IOException e) {
            // planned exception here.
        }
    }
}
