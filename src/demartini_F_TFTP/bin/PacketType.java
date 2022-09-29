package demartini_F_TFTP.bin;

public enum PacketType {
    RRQ(1),
    WRQ(2),
    DATA(3),
    ACK(4),
    ERROR(5);

    private final byte[] packetTypeBytes = new byte[2];

    PacketType(int packetTypeInt) {

        packetTypeBytes[0] = (byte) packetTypeInt;
        packetTypeBytes[1] = (byte) (packetTypeInt >>> 8);
    }

    public byte[] getBytes() {
        return packetTypeBytes;
    }
}
