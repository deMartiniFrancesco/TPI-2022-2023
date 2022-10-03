package demartini_F_TFTP.bin.packages;

import demartini_F_TFTP.bin.PacketMode;
import demartini_F_TFTP.bin.PacketType;
import demartini_F_TFTP.bin.TftpPacket;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class TftpFileRequestPackage extends TftpPacket {
    private final PacketMode packetMode;
    private final String fileName;

    public TftpFileRequestPackage(PacketMode packetMode, String fileName) {
        super(PacketType.RRQ);
        this.packetMode = packetMode;
        this.fileName = fileName;
        super.bytePacket = getBytePacket();
    }

    @Override
    public byte[] getBytePacket(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(512);
        outputStream.write(0);
        outputStream.write(packetType.getPacketType());
        outputStream.writeBytes(fileName.getBytes());
        outputStream.write(0);
        outputStream.writeBytes(packetMode.getBytes());
        outputStream.write(0);

        return outputStream.toByteArray();
    }


    @Override
    public String toString() {
        return "TftpFileRequest{" +
                "fileName='" + fileName + '\'' +
                ", bytePacket=" + Arrays.toString(bytePacket) +
                ", packetType=" + packetType +
                ", packetMode=" + packetMode +
                '}';
    }
}
