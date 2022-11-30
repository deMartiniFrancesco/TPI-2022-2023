package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.connections.ConnectionReceivedEvent;
import demartini_F_Orario_01.bin.connections.ServerAccept;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Mtp.
 */
public abstract class MTP {

    /**
     * The Executor service.
     */
    protected final ExecutorService executorService = Executors.newCachedThreadPool();
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
     * The Input stream.
     */
    protected DataInputStream inputStream = null;
    /**
     * The Output stream.
     */
    protected DataOutputStream outputStream = null;

    /**
     * Instantiates a new Mtp.
     *
     * @param port the port
     */
    protected MTP(int port) {
        this.port = port;
        startListening();
    }

    /**
     * Start listening.
     */
    public void startListening() {
        if (!isConnected) {
            System.out.println("MTP.startListening");
            closeIfNotNull();
            listener = createListeningSocket();
            executorService.execute(new ServerAccept(listener, this::connectionReceive));
        }
    }

    /**
     * Connection receive.
     *
     * @param event the event
     */
    protected void connectionReceive(ConnectionReceivedEvent event) {
        if (!isConnected) {
            this.activeConnectionSocket = event.getSocket();
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
        } else {
            System.out.println("Error");
            //            sendError(socket);
        }
    }

    /**
     * Connect.
     *
     * @param targetAddress the target address
     * @param targetPort    the target port
     */
    public void connect(InetAddress targetAddress, int targetPort) {
        isConnected = true;
        try {
            activeConnectionSocket = new Socket(targetAddress, targetPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            listener.close();
        } catch (IOException e) {
            // this will almost certainly throw an exception, but it is intended.
        }
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
