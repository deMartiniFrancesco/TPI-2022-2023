package demartini_F_Orario_01.bin.packages.registration;

import demartini_F_Orario_01.bin.PacketOperationCode;
import demartini_F_Orario_01.bin.packages.MTPPacket;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class MTPRegistrationError extends MTPPacket {

    public MTPRegistrationError() {
        super(PacketOperationCode.REG_ERROR);
        super.bytePacket = getBytePacket();
    }

    public MTPRegistrationError(byte[] bytePacket) {
        super(Arrays.copyOfRange(bytePacket, 0, 1 + Integer.BYTES));
    }

    @Override
    public byte[] getBytePacket() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(operationCode.getOperationCode());
        outputStream.writeBytes(new byte[4]);

        return outputStream.toByteArray();
    }

    @Override
    public String toString() {
        return "MTSRegistrationError{" +
                "\n\toperationCode=" + operationCode +
                ",\n\tbytePacket=" + Arrays.toString(bytePacket) +
                "\n}";
    }

}
