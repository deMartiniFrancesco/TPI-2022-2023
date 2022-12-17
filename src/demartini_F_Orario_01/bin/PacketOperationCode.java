package demartini_F_Orario_01.bin;

public enum PacketOperationCode {
    PROFESSOR_REQUEST(1),
    CLASSROOM_REQUEST(2),
    CLASS_REQUEST(3),
    PROFESSOR_REQUEST_NOW(11),
    CLASSROOM_REQUEST_NOW(12),
    CLASS_REQUEST_NOW(13),
    DATA(21),
    END_CONNECTION(22),
    ERROR(23),
    REGISTRATION_REQUEST(31),
    REGISTRATION_SUCCESS(32);

    private final int operationCode;

    PacketOperationCode(int packetTypeInt) {
        operationCode = packetTypeInt;
    }

    public static PacketOperationCode findByValue(int intType) {
        for (PacketOperationCode packetOperationCode : values()) {
            if (packetOperationCode.getOperationCode() == intType) {
                return packetOperationCode;
            }
        }
        return null;
    }

    public int getOperationCode() {
        return operationCode;
    }
}
