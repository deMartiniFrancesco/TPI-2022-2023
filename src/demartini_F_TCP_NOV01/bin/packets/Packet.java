package demartini_F_TCP_NOV01.bin.packets;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public abstract class Packet {

    protected final int operationCode;
    protected int length;
    protected byte[] dataByte;

    public Packet(
            int operationCode,
            int length,
            byte[] dataByte
    ) {
        this.operationCode = operationCode;
        this.length = length;
        this.dataByte = dataByte;
    }

    public byte[] getDataByte() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write((byte) operationCode);
        outputStream.writeBytes(
                ByteBuffer.allocate(2).putShort((short) length).array()
        );

        return outputStream.toByteArray();
    }

    @Override
    public String toString() {
        return "Packet{" +
                "\n\toperationCode=" + operationCode +
                ",\n\tlength=" + length +
                ",\n\tdataByte=" + Arrays.toString(dataByte) +
                "\n}";
    }
}
