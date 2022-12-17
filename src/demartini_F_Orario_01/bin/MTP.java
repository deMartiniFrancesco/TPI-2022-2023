package demartini_F_Orario_01.bin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The type Mtp.
 */
public abstract class MTP {

    /**
     * The Executor service.
     */
    protected final ExecutorService connectionExecutorService = new ThreadPoolExecutor(
            0, Integer.MAX_VALUE,
            0L, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>());
    /**
     * The Port.
     */
    protected final int port;
    /**
     * The Active connection socket.
     */
    protected Socket activeConnectionSocket;
    /**
     * The Listener.
     */
    protected ServerSocket listener;
    /**
     * The Is connected.
     */
    protected boolean isConnected = false;

    /**
     * Instantiates a new Mtp.
     *
     * @param port the port
     */
    protected MTP(int port) {
        this.port = port;
    }

    /**
     * Close if not null.
     */
    protected void closeIfNotNull() {
        if (listener != null) {
            try {
                listener.close();
            } catch (IOException e) {
                // this will almost certainly throw an exception, but it is intended.
            }
        }
    }

    /**
     * Create listening socket server socket.
     *
     * @return the server socket
     */
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
