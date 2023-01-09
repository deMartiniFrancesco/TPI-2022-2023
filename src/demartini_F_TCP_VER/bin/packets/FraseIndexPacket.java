package demartini_F_TCP_VER.bin.packets;

import demartini_F_TCP_NOV01.bin.packets.Packet;
import demartini_F_TCP_NOV01.bin.packets.Utility;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class FraseIndexPacket extends Packet {

    private static final int OP_CODE = 24;

    public FraseIndexPacket(int index) {
        super(OP_CODE, 2, Utility.intTo2Byte(index));
    }
}
