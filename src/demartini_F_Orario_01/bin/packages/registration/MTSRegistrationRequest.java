package demartini_F_Orario_01.bin.packages.registration;

import demartini_F_Orario_01.bin.PacketOperationCode;
import demartini_F_Orario_01.bin.Utility;
import demartini_F_Orario_01.bin.packages.MTSPacket;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class MTSRegistrationRequest extends MTSPacket {
    private final String name;

    public MTSRegistrationRequest(String name) {
        super(PacketOperationCode.REQ_REGISTRAZIONE);
        this.name = name;
        super.bytePacket = getBytePacket();
    }

    public MTSRegistrationRequest(byte[] bytePacket) {
        super(Utility.trim(bytePacket));
        name = new String(Arrays.copyOfRange(super.bytePacket, 1 , super.bytePacket.length));
    }

    @Override
    public byte[] getBytePacket(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(operationCode.getOperationCode());
        outputStream.writeBytes(name.getBytes());

        return outputStream.toByteArray();
    }

    @Override
    public String toString() {
        return "MTSRegistrationRequest{" +
                "\n\toperationCode=" + operationCode +
                ",\n\tname='" + name + '\'' +
                ",\n\tbytePacket=" + Arrays.toString(bytePacket) +
                "\n}";
    }
}
