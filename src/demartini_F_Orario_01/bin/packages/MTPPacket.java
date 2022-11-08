package demartini_F_Orario_01.bin.packages;

import demartini_F_Orario_01.bin.PacketOperationCode;
import java.util.Arrays;

public abstract class MTPPacket {

  protected final PacketOperationCode operationCode;
  protected int length;
  protected byte[] dataByte;

  public MTPPacket(PacketOperationCode packetOperationCode) {
    this.operationCode = packetOperationCode;
  }

  public MTPPacket(
    byte[] dataByte,
    int length,
    PacketOperationCode operationCode
  ) {
    this.dataByte = dataByte;
    this.length = length;
    this.operationCode = operationCode;
  }

  public PacketOperationCode getOperationCode() {
    return operationCode;
  }

  public int getLength() {
    return length;
  }

  public byte[] getDataByte() {
    return dataByte;
  }

  protected void setLength(int length) {
    this.length = length;
  }

  @Override
  public String toString() {
    return (
      "MTSPacket{" +
      "packetType=" +
      operationCode +
      ", bytePacket=" +
      Arrays.toString(dataByte) +
      '}'
    );
  }
}
