package demartini_F_Orario_01.bin.events.data;

import demartini_F_Orario_01.bin.events.connections.ConnectionReceivedEvent;

public class ServerDataListener implements Runnable {

    private final ConnectionReceivedEvent receivedEvent;
    private final DataReceivedListener listener;

    public ServerDataListener(ConnectionReceivedEvent receivedEvent, DataReceivedListener listener) {
        this.receivedEvent = receivedEvent;
        this.listener = listener;
    }

    public void run() {
        try {
            while (receivedEvent.socket().isConnected()) {
                int dataAvailable;
                if ((dataAvailable = receivedEvent.getInput().available()) > 0) {
                    listener.onDataReceived(new DataReceivedEvent(dataAvailable, receivedEvent));
                }
            }
        } catch (Exception ignored) {
        }
    }

}