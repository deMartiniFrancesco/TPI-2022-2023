package demartini_F_TCP_VER.bin.packets;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Utility {

public static byte[] intTo2Byte(int num) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    outputStream.writeBytes(
            ByteBuffer.allocate(2).putShort((short) num).array()
    );
    return outputStream.toByteArray();
}


    public static byte[] trim(byte[] bytes) {
        int i = bytes.length - 1;
        while (i >= 0 && bytes[i] == 0) --i;
        return Arrays.copyOf(bytes, i + 1);
    }
}
