package demartini_F_Orario_01.bin.events.connections;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public record ConnectionReceivedEvent(Socket socket) {
    public OutputStream getOutput() throws IOException {
        return socket.getOutputStream();
    }

    public InputStream getInput() throws IOException {
        return socket.getInputStream();
    }

    public InetAddress getAddress() {
        return socket.getInetAddress();
    }

    public int getPort() {
        return socket.getPort();
    }
}
