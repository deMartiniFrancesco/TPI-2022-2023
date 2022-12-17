package demartini_F_Orario_01.bin.connections;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * The type Connection received event.
 */
public record ConnectionReceivedEvent(Socket socket) {
    /**
     * Gets output.
     *
     * @return the output
     * @throws IOException the io exception
     */
    public OutputStream getOutput() throws IOException {
        return socket.getOutputStream();
    }

    /**
     * Gets input.
     *
     * @return the input
     * @throws IOException the io exception
     */
    public InputStream getInput() throws IOException {
        return socket.getInputStream();
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public InetAddress getAddress() {
        return socket.getInetAddress();
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public int getPort() {
        return socket.getPort();
    }
}
