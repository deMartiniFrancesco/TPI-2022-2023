package demartini_F_Orario_01.bin.events.connections;

import java.util.EventListener;

/**
 * The interface Connection received listener.
 */
@FunctionalInterface
public interface ConnectionReceivedListener extends EventListener {
    /**
     * On connection received.
     *
     * @param event the event
     */
    void onConnectionReceived(ConnectionReceivedEvent event);
}
