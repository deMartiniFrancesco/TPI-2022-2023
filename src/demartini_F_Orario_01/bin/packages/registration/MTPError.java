package demartini_F_Orario_01.bin.packages.registration;

import demartini_F_Orario_01.bin.PacketOperationCode;
import demartini_F_Orario_01.bin.packages.MTPPacket;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class MTPError extends MTPPacket {

  public MTPError() {
    super(PacketOperationCode.REG_ERROR);
    super.dataByte = getDataByte();
  }

  @Override
  public int getLength() {
    return 2;
  }

  @Override
  public byte[] getDataByte() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    outputStream.write(operationCode.getOperationCode());
    outputStream.writeBytes(new byte[4]);

    return outputStream.toByteArray();
  }

  @Override
  public String toString() {
    return (
      "MTSRegistrationError{" +
      "\n\toperationCode=" +
      operationCode +
      ",\n\tbytePacket=" +
      Arrays.toString(dataByte) +
      "\n}"
    );
  }
}
