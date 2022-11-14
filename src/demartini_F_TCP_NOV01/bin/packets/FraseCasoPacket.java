package demartini_F_TCP_NOV01.bin.packets;

public class FraseCasoPacket extends Packet {

    private static final int OP_CODE = 21;

    public FraseCasoPacket() {
        super(OP_CODE, 0, new byte[0]);
    }
}
