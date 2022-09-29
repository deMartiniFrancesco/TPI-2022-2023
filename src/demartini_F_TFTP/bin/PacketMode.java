package demartini_F_TFTP.bin;

public enum PacketMode {
    NETASCII("netascii"),
    OCTET("octet"),
    MAIL("mail");

    private final byte[] modeBytes;

    PacketMode(String modeBytes) {
        this.modeBytes = modeBytes.getBytes();
    }

    public byte[] getBytes() {
        return modeBytes;
    }
}
