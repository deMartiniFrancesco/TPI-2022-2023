package demartini_F_Orario_01.bin.errors;

import demartini_F_Orario_01.bin.PacketErrorCode;

public class MTPException extends Exception {
    private final PacketErrorCode code;

    public MTPException(PacketErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public MTPException(PacketErrorCode code, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code;
    }

    public PacketErrorCode getCode() {
        return code;
    }
}
