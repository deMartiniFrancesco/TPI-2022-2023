package demartini_F_Orario_01.bin.packages.data;

import demartini_F_Orario_01.bin.PacketOperationCode;
import demartini_F_Orario_01.bin.packages.MTPPacket;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class MTPDataRequest extends MTPPacket {

    private final String request;

    public MTPDataRequest(PacketOperationCode operationCode, String request) {
        super(operationCode);
        this.request = request;
        super.setDataByte(request.getBytes(StandardCharsets.UTF_8));
    }

    public MTPDataRequest(PacketOperationCode operationCode, byte[] bytePacket) {
        super(operationCode, bytePacket.length, bytePacket);
        request = new String(bytePacket);
    }

    @Override
    public byte[] getDataByte() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(operationCode.getOperationCode());
        outputStream.writeBytes(ByteBuffer.allocate(Integer.BYTES).putInt(dataLength).array());
        outputStream.writeBytes(request.getBytes(StandardCharsets.UTF_8));

        return outputStream.toByteArray();
    }

    @Override
    public String toString() {
        return "MTPDataRequest{" +
                "\n\toperationCode=" + operationCode +
                ",\n\tdataLength=" + dataLength +
                ",\n\trequest='" + request + '\'' +
                "\n}";
    }
}
