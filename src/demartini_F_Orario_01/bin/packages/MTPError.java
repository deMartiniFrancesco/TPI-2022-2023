package demartini_F_Orario_01.bin.packages;

import demartini_F_Orario_01.bin.PacketErrorCode;
import demartini_F_Orario_01.bin.PacketOperationCode;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class MTPError extends MTPPacket {

    private final PacketErrorCode errorCode;

    public MTPError(PacketErrorCode errorCode) {
        super(PacketOperationCode.ERROR);
        this.errorCode = errorCode;
        super.setDataByte(ByteBuffer.allocate(Integer.BYTES).putInt(errorCode.getErrorCode()).array());
    }

    public MTPError(byte[] dataByte) {
        super(PacketOperationCode.ERROR, Integer.BYTES, dataByte);
        errorCode = PacketErrorCode.findByValue(ByteBuffer.wrap(dataByte).getInt());
    }

    @Override
    public byte[] getDataByte() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(operationCode.getOperationCode());
        outputStream.writeBytes(ByteBuffer.allocate(Integer.BYTES).putInt(errorCode.getErrorCode()).array());

        return outputStream.toByteArray();
    }

    @Override
    public String toString() {
        return "MTPError{" +
                "\n\toperationCode=" + operationCode +
                ",\n\tdataLength=" + dataLength +
                ",\n\terrorCode=" + errorCode +
                "\n}";
    }
}
