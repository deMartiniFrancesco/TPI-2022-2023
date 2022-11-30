package demartini_F_Orario_01.bin;

/**
 * The enum Packet error code.
 */
public enum PacketErrorCode {
    /**
     * Nonexistent argument packet error code.
     */
    NONEXISTENT_ARGUMENT(1),
    /**
     * Registration error packet error code.
     */
    REGISTRATION_ERROR(2),
    /**
     * Timeout error packet error code.
     */
    TIMEOUT_ERROR(3);

    private final int errorCode;

    PacketErrorCode(int errorInt) {
        errorCode = errorInt;
    }

    /**
     * Find by value packet error code.
     *
     * @param intType the int type
     * @return the packet error code
     */
    public static PacketErrorCode findByValue(int intType) {
        for (PacketErrorCode packetErrorCode : values()) {
            if (packetErrorCode.getErrorCode() == intType) {
                return packetErrorCode;
            }
        }
        return null;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }
}
