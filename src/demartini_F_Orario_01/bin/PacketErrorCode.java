package demartini_F_Orario_01.bin;

public enum PacketErrorCode {

    DEFAULT(0, "Error."),
    NONEXISTENT_ARGUMENT(1, "Non existent argument in the packet."),
    REGISTRATION_ERROR(2, "The registration Fail."),
    TIMEOUT_ERROR(3, "Timeout error, try to re-connect."),
    MALFORMED_PACKET(4, "Packet invalid or malformed.");

    private final int errorCode;
    private final String message;

    PacketErrorCode(int errorInt, String message) {
        errorCode = errorInt;
        this.message = message;
    }

    public static PacketErrorCode findByValue(int intType) {
        for (PacketErrorCode packetErrorCode : values()) {
            if (packetErrorCode.getErrorCode() == intType) {
                return packetErrorCode;
            }
        }
        return DEFAULT;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
