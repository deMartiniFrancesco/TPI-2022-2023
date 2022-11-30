package demartini_F_Orario_01.bin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * The type Utility.
 */
public class Utility {

    /**
     * Concat byte arrays byte [ ].
     *
     * @param a the a
     * @param b the b
     * @return the byte [ ]
     * @throws IOException the io exception
     */
    public static byte[] concatByteArrays(byte[] a, byte[] b) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(a);
        bos.write(b);
        return bos.toByteArray();
    }

    /**
     * To integer length byte [ ].
     *
     * @param bytes the bytes
     * @return the byte [ ]
     */
    public static byte[] toIntegerLength(byte[] bytes) {
        int lenBytes = bytes.length;
        if (lenBytes < Integer.BYTES) {
            try {
                return concatByteArrays(new byte[Integer.BYTES - lenBytes], bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Arrays.copyOfRange(bytes, lenBytes - Integer.BYTES, lenBytes);
    }

    /**
     * Trim byte [ ].
     *
     * @param bytes the bytes
     * @return the byte [ ]
     */
    public static byte[] trim(byte[] bytes) {
        int i = bytes.length - 1;
        while (i >= 0 && bytes[i] == 0) --i;
        return Arrays.copyOf(bytes, i + 1);
    }

    /**
     * Next non negative int.
     *
     * @return the int
     */
    public static int nextNonNegative() {
        return Math.abs(new Random().nextInt());
    }
}
