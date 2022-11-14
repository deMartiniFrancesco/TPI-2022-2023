package demartini_F_TCP_NOV01.bin.packets;

public class NumFrasiPacket extends Packet {

    private static final int OP_CODE = 22;

    public NumFrasiPacket() {
        super(OP_CODE, 0, new byte[0]);
    }
}
