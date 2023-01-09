package demartini_F_TCP_VER.bin.packets;

import demartini_F_TCP_NOV01.bin.packets.Packet;

public class BenvenutoPacket extends Packet {

    private static final int OP_CODE = 20;

    public BenvenutoPacket() {
        super(OP_CODE, 0, new byte[0]);
    }
}
