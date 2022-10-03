package demartini_F_TFTP.bin;

public enum PacketType {
    RRQ(1),
    WRQ(2),
    DATA(3),
    ACK(4),
    ERROR(5);

    private final int packetType;

    PacketType(int packetTypeInt) {

        packetType = packetTypeInt;
    }

    public int getPacketType() {
        return packetType;
    }

    public static PacketType findByValue(int intType){
        for(PacketType packetType : values()){
            if( packetType.getPacketType() == intType){
                return packetType;
            }
        }
        return null;
    }
}
