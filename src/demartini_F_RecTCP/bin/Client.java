package demartini_F_RecTCP.bin;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

class Client {
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;


    public Client(InetAddress targetAddress, int targetPort) {
        try {
            Socket socket = new Socket(targetAddress, targetPort);

            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void sendPacket(int code, byte[] payload) {
        try {
            System.out.println("Sending...");

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            byteArrayOutputStream.write((byte) code);
            if (payload != null) {
                byteArrayOutputStream.writeBytes(intTo2Byte(payload.length));
                byteArrayOutputStream.write(payload);
            }
            else {
                byteArrayOutputStream.writeBytes(intTo2Byte(0));
            }
            System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));
            outputStream.write(byteArrayOutputStream.toByteArray());


            outputStream.flush();
            System.out.println("Send");
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    public boolean recevePacket() {
        System.out.println("Reciving...");
        try {
            switch (inputStream.readByte()) {

                case 50, 51 -> {
                    short length = inputStream.readShort();
                    System.out.println(new String(inputStream.readNBytes(length)));
                }
                case 52 -> {
                    return false;
                }
                case 53 -> {
                    inputStream.readShort();
                    System.out.println(inputStream.readInt());
                }
                case 54 -> System.out.println("Risposta positiva");
                case 55 -> {
                    short length = inputStream.readShort();
                    System.out.println("Errore: " + new String(inputStream.readNBytes(length)));
                }


            }
        } catch (IOException ignored) {
        }
        return true;
    }

    private byte[] intTo2Byte(int num) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.writeBytes(
                ByteBuffer.allocate(2).putShort((short) num).array()
        );
        return outputStream.toByteArray();
    }


}