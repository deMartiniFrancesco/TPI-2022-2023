package demartini_F_Orario_01.bin.packages.data;

import demartini_F_Orario_01.bin.PacketOperationCode;
import demartini_F_Orario_01.bin.Utility;
import demartini_F_Orario_01.bin.packages.MTSPacket;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class MTSDataRequest extends MTSPacket {
    private final int uuid;


    public MTSDataRequest(PacketOperationCode operationCode, int uuid) {
        super(operationCode);
        this.uuid = uuid;
        super.bytePacket = getBytePacket();
    }

    public MTSDataRequest(byte[] bytePacket) {
        super(Utility.trim(bytePacket));
        uuid = new BigInteger(Arrays.copyOfRange(super.bytePacket, 1 ,  1 + Integer.BYTES)).intValue();
    }

    @Override
    public byte[] getBytePacket() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(operationCode.getOperationCode());
        outputStream.writeBytes(ByteBuffer.allocate(Integer.BYTES).putInt(uuid).array());

        return outputStream.toByteArray();
    }

    @Override
    public String toString() {
        return "MTSDataRequest{" +
                "\n\toperationCode=" + operationCode +
                ",\n\tuuid=" + uuid +
                ",\n\tbytePacket=" + Arrays.toString(bytePacket) +
                "\n}";
    }
}
