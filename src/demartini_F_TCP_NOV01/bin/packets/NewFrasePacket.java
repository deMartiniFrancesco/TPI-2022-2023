package demartini_F_TCP_NOV01.bin.packets;

public class NewFrasePacket extends Packet {

    private static final int OP_CODE = 25;

    public NewFrasePacket(String frase) {

        super(OP_CODE, frase.getBytes().length, frase.getBytes());
    }
}
