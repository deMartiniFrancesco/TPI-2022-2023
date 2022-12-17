package demartini_F_Orario_01.bin.events.data;

import demartini_F_Orario_01.bin.events.connections.ConnectionReceivedEvent;

public record DataReceivedEvent(int dataAvailable,
                                ConnectionReceivedEvent connection) {


}
