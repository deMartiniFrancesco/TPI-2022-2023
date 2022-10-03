package demartini_F_TFTP.bin.packages;

import demartini_F_TFTP.bin.PacketType;
import demartini_F_TFTP.bin.TftpPacket;

public class TftpDataPacket extends TftpPacket {

    private final PacketType packetType = PacketType.DATA;
    private final int block;
    public TftpDataPacket(PacketType packetType, int block) {
        super(packetType);
        this.block = block;
    }

    public TftpDataPacket(byte[] bytePacket) {
        super(bytePacket);
        block = 


    }
}
