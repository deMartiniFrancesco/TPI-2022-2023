package demartini_F_Orario_01.bin.packages;

import demartini_F_Orario_01.bin.PacketOperationCode;

import java.util.Arrays;

public abstract class MTPPacket {

    protected final PacketOperationCode operationCode;
    protected int dataLength;
    protected byte[] dataByte;

    public MTPPacket(PacketOperationCode packetOperationCode) {
        this.operationCode = packetOperationCode;
    }

    public MTPPacket(
            PacketOperationCode operationCode,
            int dataLength,
            byte[] dataByte
    ) {
      this.operationCode = operationCode;
      this.dataLength = dataLength;
      this.dataByte = dataByte;
    }

    public PacketOperationCode getOperationCode() {
        return operationCode;
    }

    public int getDataLength() {
        return dataLength;
    }

    public byte[] getDataByte() {
        return dataByte;
    }

    protected void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

  @Override
  public String toString() {
    return "MTPPacket{" +
            "\n\toperationCode=" + operationCode +
            ",\n\tdataLength=" + dataLength +
            ",\n\tdataByte=" + Arrays.toString(dataByte) +
            "\n}";
  }
}
