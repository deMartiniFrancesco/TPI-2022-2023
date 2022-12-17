package demartini_F_Orario_01.bin.data;

import java.io.DataInputStream;

public class ServerDataListener implements Runnable {

    private DataInputStream inputStream;
    private final DataReceivedListener listener;

    public ServerDataListener(DataInputStream inputStream, DataReceivedListener listener) {
        this.inputStream = inputStream;
        this.listener = listener;
    }

    public void run() {
        try {
            while (true) {
                int dataAvailable;
                if ((dataAvailable = inputStream.available()) > 0) {
                    System.out.println(dataAvailable);
                    listener.onDataReceived(new DataReceivedEvent(dataAvailable));
                }
            }
        } catch (Exception ignored) {
        }
    }
}