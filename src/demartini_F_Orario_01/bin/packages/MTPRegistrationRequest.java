package demartini_F_Orario_01.bin.packages;

import demartini_F_Orario_01.bin.PacketOperationCode;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class MTPRegistrationRequest extends MTPPacket {

    private final String name;

    public MTPRegistrationRequest(byte[] dataByte) {
        super(PacketOperationCode.REGISTRATION_REQUEST, dataByte.length, dataByte);
        name = new String(dataByte);
    }

    public MTPRegistrationRequest(String name) {
        super(PacketOperationCode.REGISTRATION_REQUEST);
        this.name = name;
        super.setDataByte(name.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public byte[] getDataByte() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(operationCode.getOperationCode());
        outputStream.writeBytes(
                ByteBuffer.allocate(Integer.BYTES).putInt(dataLength).array()
        );
        outputStream.writeBytes(name.getBytes());

        return outputStream.toByteArray();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MTPRegistrationRequest{" +
                "\n\toperationCode=" + operationCode +
                ",\n\tdataLength=" + dataLength +
                ",\n\tname='" + name + '\'' +
                "\n}";
    }
}
