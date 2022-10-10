package demartini_F_Orario_01.bin.packages.registration;

import demartini_F_Orario_01.bin.PacketOperationCode;
import demartini_F_Orario_01.bin.packages.MTSPacket;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class MTSRegistrationError extends MTSPacket {

    public MTSRegistrationError() {
        super(PacketOperationCode.REG_ERROR);
        super.bytePacket = getBytePacket();
    }

    public MTSRegistrationError(byte[] bytePacket) {
        super(Arrays.copyOfRange(bytePacket, 0, 1 + Integer.BYTES));
    }

    @Override
    public byte[] getBytePacket(){
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
