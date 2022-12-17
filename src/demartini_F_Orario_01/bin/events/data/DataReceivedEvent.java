package demartini_F_Orario_01.bin.events.data;

import demartini_F_Orario_01.bin.events.connections.ConnectionReceivedEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;

public record DataReceivedEvent(int dataAvailable,
                                ConnectionReceivedEvent connection) {

    public DataInputStream getDataInput() throws IOException {
        return new DataInputStream(connection.getInput());
    }

    public DataOutputStream getDataOutput() throws IOException {
        return new DataOutputStream(connection.getOutput());
    }

    public InetAddress getAddress() {
        return connection.socket().getInetAddress();
    }

    public int getPort() {
        return connection.socket().getPort();
    }


}
