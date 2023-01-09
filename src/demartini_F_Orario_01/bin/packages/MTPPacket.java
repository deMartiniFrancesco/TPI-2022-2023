package demartini_F_Orario_01.bin.packages;

import demartini_F_Orario_01.bin.PacketOperationCode;

import java.util.Arrays;

public abstract class MTPPacket {

    protected final PacketOperationCode operationCode;
    protected int dataLength;
    private byte[] dataByte;

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

    public byte[] getDataByte() {
        return dataByte;
    }

    public void setDataByte(byte[] dataByte) {
        this.dataByte = dataByte;
        this.dataLength = dataByte.length;
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
