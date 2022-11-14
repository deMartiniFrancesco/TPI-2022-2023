package demartini_F_Orario_01.bin.connections;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerAccept implements Runnable {

    private final ServerSocket serv;
    private final ConnectionReceivedListener listener;

    public ServerAccept(ServerSocket sock, ConnectionReceivedListener con) {
        this.serv = sock;
        this.listener = con;
    }

    @Override
    public void run() {
        try {
            listener.onConnectionReceived(new ConnectionReceivedEvent(serv.accept()));
        } catch (IOException e) {
            // planned exception here.
        }
    }
}
