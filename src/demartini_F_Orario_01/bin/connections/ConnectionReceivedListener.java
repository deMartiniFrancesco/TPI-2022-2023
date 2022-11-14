package demartini_F_Orario_01.bin.connections;

import java.util.EventListener;

@FunctionalInterface
public interface ConnectionReceivedListener extends EventListener {
    void onConnectionReceived(ConnectionReceivedEvent event);
}
