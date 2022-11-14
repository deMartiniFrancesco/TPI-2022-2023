package demartini_F_Orario_01.bin;

public enum PacketErrorCode {
    INEXISTENT_ARGUMENT(1),
    REGISTRATION_ERROR(2),
    TIMEOUT_ERROR(3);

    private final int errorCode;

    PacketErrorCode(int errorInt) {
        errorCode = errorInt;
    }

    public static PacketErrorCode findByValue(int intType) {
        for (PacketErrorCode packetErrorCode : values()) {
            if (packetErrorCode.getErrorCode() == intType) {
                return packetErrorCode;
            }
        }
        return null;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
