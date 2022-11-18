package demartini_F_TFTP.bin.packages;

import demartini_F_TFTP.bin.PacketType;
import demartini_F_TFTP.bin.TftpPacket;
import demartini_F_TFTP.bin.Utility;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class TftpDataPacket extends TftpPacket {

    private final int block;
    private final byte[] data;

    public TftpDataPacket(int block, byte[] data) {
        super(PacketType.DATA);
        this.block = block;
        this.data = data;
    }

    public TftpDataPacket(byte[] bytePacket) {
        super(bytePacket);
        block = ByteBuffer.wrap(Utility.toIntegerLength(Arrays.copyOfRange(bytePacket, 2, 4))).getInt();
        data = Utility.trim(Arrays.copyOfRange(bytePacket, 4, bytePacket.length));
    }

    public int getBlock() {
        return block;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "MTSRegistrationSuccess{" +
                "\n\tpacketType=" + packetType +
                ",\n\tblock=" + block +
                ",\n\tdata=" + Arrays.toString(data) +
                ",\n\tbytePacket=" + Arrays.toString(bytePacket) +
                "\n}";
    }
}
