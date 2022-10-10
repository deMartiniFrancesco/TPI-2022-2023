package demartini_F_TFTP.bin;

import java.util.Arrays;

public abstract class TftpPacket {

    protected final PacketType packetType;
    public byte[] bytePacket;

    public TftpPacket(PacketType packetType) {
        this.packetType = packetType;
    }

    public TftpPacket(byte[] bytePacket){
        this.bytePacket = bytePacket;
        packetType = PacketType.findByValue(bytePacket[1]);
    }

    public byte[] getBytePacket(){
        return new byte[0];
    }


    @Override
    public String toString() {
        return "MTSPacket{" +
                "packetType=" + packetType +
                ", bytePacket=" + Arrays.toString(bytePacket) +
                '}';
    }
}
