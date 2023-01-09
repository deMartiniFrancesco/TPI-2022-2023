package demartini_F_Orario_01.bin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class MTP {

    protected final ExecutorService connectionExecutorService = new ThreadPoolExecutor(
            0, Integer.MAX_VALUE,
            0L, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>());
    protected final int port;
    protected Socket activeConnectionSocket;
    protected ServerSocket listener;
    protected boolean isConnected = false;

    protected MTP(int port) {
        this.port = port;
    }

    protected void closeIfNotNull() {
        if (listener != null) {
            try {
                listener.close();
            } catch (IOException e) {
                // this will almost certainly throw an exception, but it is intended.
            }
        }
    }

    protected ServerSocket createListeningSocket() {
        ServerSocket temp = null;
        try {
            temp = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
