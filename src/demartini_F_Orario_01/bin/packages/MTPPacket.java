package demartini_F_Orario_01.bin.packages;

import demartini_F_Orario_01.bin.PacketOperationCode;

import java.util.Arrays;

/**
 * The type Mtp packet.
 */
public abstract class MTPPacket {

    /**
     * The Operation code.
     */
    protected final PacketOperationCode operationCode;
    /**
     * The Data length.
     */
    protected int dataLength;
    private byte[] dataByte;

    /**
     * Instantiates a new Mtp packet.
     *
     * @param packetOperationCode the packet operation code
     */
    public MTPPacket(PacketOperationCode packetOperationCode) {
        this.operationCode = packetOperationCode;
    }

    /**
     * Instantiates a new Mtp packet.
     *
     * @param operationCode the operation code
     * @param dataLength    the data length
     * @param dataByte      the data byte
     */
    public MTPPacket(
            PacketOperationCode operationCode,
            int dataLength,
            byte[] dataByte
    ) {
        this.operationCode = operationCode;
        this.dataLength = dataLength;
        this.dataByte = dataByte;
    }

    /**
     * Gets operation code.
     *
     * @return the operation code
     */
    public PacketOperationCode getOperationCode() {
        return operationCode;
    }

    /**
     * Get data byte byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getDataByte() {
        return dataByte;
    }

    /**
     * Sets data byte.
     *
     * @param dataByte the data byte
     */
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
