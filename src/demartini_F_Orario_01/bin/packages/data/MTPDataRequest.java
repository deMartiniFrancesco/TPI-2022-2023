package demartini_F_Orario_01.bin.packages.data;

import demartini_F_Orario_01.bin.PacketOperationCode;
import demartini_F_Orario_01.bin.packages.MTPPacket;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class MTPDataRequest extends MTPPacket {

    private final int uuid;

    public MTPDataRequest(PacketOperationCode operationCode, int uuid) {
        super(operationCode);
        this.uuid = uuid;
        super.setDataByte(getDataByte());
    }

//  public MTPDataRequest(byte[] bytePacket) {
//    super(Utility.trim(bytePacket));
//    uuid =
//      new BigInteger(Arrays.copyOfRange(super.dataByte, 1, 1 + Integer.BYTES))
//        .intValue();
//  }

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
        return "MTPDataRequest{" +
                "\n\toperationCode=" + operationCode +
                ",\n\tdataLength=" + dataLength +
                ",\n\tuuid=" + uuid +
                "\n}";
    }
}
