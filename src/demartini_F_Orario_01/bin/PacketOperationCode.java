package demartini_F_Orario_01.bin;

/**
 * The enum Packet operation code.
 */
public enum PacketOperationCode {
    /**
     * Professor request packet operation code.
     */
    PROFESSOR_REQUEST(1),
    /**
     * Classroom request packet operation code.
     */
    CLASSROOM_REQUEST(2),
    /**
     * Class request packet operation code.
     */
    CLASS_REQUEST(3),
    /**
     * Professor request now packet operation code.
     */
    PROFESSOR_REQUEST_NOW(11),
    /**
     * Classroom request now packet operation code.
     */
    CLASSROOM_REQUEST_NOW(12),
    /**
     * Class request now packet operation code.
     */
    CLASS_REQUEST_NOW(13),
    /**
     * Data packet operation code.
     */
    DATA(21),
    /**
     * End connection packet operation code.
     */
    END_CONNECTION(22),
    /**
     * Error packet operation code.
     */
    ERROR(23),
    /**
     * Registration request packet operation code.
     */
    REGISTRATION_REQUEST(31),
    /**
     * Registration success packet operation code.
     */
    REGISTRATION_SUCCESS(32);

    private final int operationCode;

    PacketOperationCode(int packetTypeInt) {
        operationCode = packetTypeInt;
    }

    /**
     * Find by value packet operation code.
     *
     * @param intType the int type
     * @return the packet operation code
     */
    public static PacketOperationCode findByValue(int intType) {
        for (PacketOperationCode packetOperationCode : values()) {
            if (packetOperationCode.getOperationCode() == intType) {
                return packetOperationCode;
            }
        }
        return null;
    }

    /**
     * Gets operation code.
     *
     * @return the operation code
     */
    public int getOperationCode() {
        return operationCode;
    }
}
