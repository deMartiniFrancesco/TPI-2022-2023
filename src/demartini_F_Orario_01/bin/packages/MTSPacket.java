package demartini_F_Orario_01.bin.packages;

import demartini_F_Orario_01.bin.PacketOperationCode;

import java.util.Arrays;

public abstract class MTSPacket {

    protected final PacketOperationCode operationCode;
    public byte[] bytePacket;

    public MTSPacket(PacketOperationCode packetOperationCode) {
        this.operationCode = packetOperationCode;
    }

    public MTSPacket(byte[] bytePacket){
        this.bytePacket = bytePacket;
        operationCode = PacketOperationCode.findByValue(bytePacket[0]);
    }

    public byte[] getBytePacket(){
        return new byte[0];
    }


    @Override
    public String toString() {
        return "MTSPacket{" +
                "packetType=" + operationCode +
                ", bytePacket=" + Arrays.toString(bytePacket) +
                '}';
    }
}
