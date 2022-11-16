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

public abstract class MTP {

    protected final ExecutorService executorService = Executors.newCachedThreadPool();
    protected final int port;
    protected Socket activeConnectionSocket;
    protected ServerSocket listener;
    protected boolean isConnected = false;
    protected DataInputStream inputStream = null;
    protected DataOutputStream outputStream = null;

    protected MTP(int port) {
        this.port = port;
        startListening();
    }

    public void startListening() {
        if (!isConnected) {
            System.out.println("MTP.startListening");
            closeIfNotNull();
            listener = createListeningSocket();
            executorService.execute(new ServerAccept(listener, this::connectionReceive));
        }
    }

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
