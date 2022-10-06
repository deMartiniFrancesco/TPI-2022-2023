package demartini_F_TFTP.bin;

public enum PacketErrorCode {

    NOT_DEFINED("Not defined, see error message (if any)."),
    NOT_FOUND("File not found."),
    ACCESS_VIOLATION("Access violation."),
    ALLOCATION_EXCEEDED("Disk full or allocation exceeded."),
    ILLEGAL_TFTP_OPERATION("Illegal TFTP operation."),
    UNKNOWN_TRANSFER_ID("Unknown transfer ID."),
    FILE_ALREADY_EXIST("File already exists."),
    NO_SUCH_USER("No such user.");

    private final String meaning;

    PacketErrorCode(String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning() {
        return meaning;
    }

    public PacketErrorCode getByIndex(int indexCode) {
        return values()[indexCode];
    }
}
