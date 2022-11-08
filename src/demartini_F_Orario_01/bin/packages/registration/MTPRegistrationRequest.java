package demartini_F_Orario_01.bin.packages.registration;

import demartini_F_Orario_01.bin.PacketOperationCode;
import demartini_F_Orario_01.bin.Utility;
import demartini_F_Orario_01.bin.packages.MTPPacket;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class MTPRegistrationRequest extends MTPPacket {

  private final String name;

  public MTPRegistrationRequest(String name) {
    super(PacketOperationCode.REQ_REGISTRAZIONE);
    this.name = name;
    super.dataByte = getDataByte();
  }

  public MTPRegistrationRequest(byte[] bytePacket) {
    super(Utility.trim(bytePacket));
    name =
      new String(Arrays.copyOfRange(super.dataByte, 1, super.dataByte.length));
  }

  @Override
  public byte[] getDataByte() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    outputStream.write(operationCode.getOperationCode());
    outputStream.writeBytes(name.getBytes());

    return outputStream.toByteArray();
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return (
      "MTSRegistrationRequest{" +
      "\n\toperationCode=" +
      operationCode +
      ",\n\tname='" +
      name +
      '\'' +
      ",\n\tbytePacket=" +
      Arrays.toString(dataByte) +
      "\n}"
    );
  }
}
