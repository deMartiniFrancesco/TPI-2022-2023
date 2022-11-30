package demartini_F_Orario_01.bin.connections;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * The type Connection received event.
 */
public class ConnectionReceivedEvent {

    private final Socket accepted;

    /**
     * Instantiates a new Connection received event.
     *
     * @param sock the sock
     */
    public ConnectionReceivedEvent(Socket sock) {
        this.accepted = sock;
    }

    /**
     * Gets socket.
     *
     * @return the socket
     */
    public Socket getSocket() {
        return accepted;
    }

    /**
     * Gets output.
     *
     * @return the output
     * @throws IOException the io exception
     */
    public OutputStream getOutput() throws IOException {
        return accepted.getOutputStream();
    }

    /**
     * Gets input.
     *
     * @return the input
     * @throws IOException the io exception
     */
    public InputStream getInput() throws IOException {
        return accepted.getInputStream();
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public InetAddress getAddress() {
        return accepted.getInetAddress();
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public int getPort() {
        return accepted.getPort();
    }
}
