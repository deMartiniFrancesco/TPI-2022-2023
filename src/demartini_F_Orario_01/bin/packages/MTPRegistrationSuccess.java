package demartini_F_Orario_01.bin.packages;

import demartini_F_Orario_01.bin.PacketOperationCode;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class MTPRegistrationSuccess extends MTPPacket {

    private final int uuid;

    public MTPRegistrationSuccess(int uuid) {
        super(PacketOperationCode.REGISTRATION_SUCCESS);
        this.uuid = uuid;
        super.setDataByte(ByteBuffer.allocate(Integer.BYTES).putInt(uuid).array());
    }

    public MTPRegistrationSuccess(byte[] dataByte) {
        super(PacketOperationCode.REGISTRATION_SUCCESS, Integer.BYTES, dataByte);
        uuid = ByteBuffer.wrap(dataByte).getInt();
    }

    @Override
    public byte[] getDataByte() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(operationCode.getOperationCode());
        outputStream.writeBytes(
                ByteBuffer.allocate(Integer.BYTES).putInt(uuid).array()
        );

        return outputStream.toByteArray();
    }

    @Override
    public String toString() {
        return "MTPRegistrationSuccess{" +
                "\n\toperationCode=" + operationCode +
                ",\n\tdataLength=" + dataLength +
                ",\n\tuuid=" + uuid +
                "\n}";
    }
}
