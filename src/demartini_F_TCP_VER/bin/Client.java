package demartini_F_TCP_VER.bin;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;

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

class Main {
    public static void main(String[] args) throws UnknownHostException {

        Client client = new Client(InetAddress.getByName("172.16.13.228"), 3333);
        Scanner scanner = new Scanner(System.in);
        String request;
        do {
            System.out.println("A)Richiesta Benvenuto");
            System.out.println("B)Richiesta di nota a caso");
            System.out.println("C)Richiesta di numero note presenti");
            System.out.println("D)Richiesta di tutte le note");
            System.out.println("E)Richiesta di una determinata nota");
            System.out.println("F)Richiesta di inserimento nuova nota");
            System.out.println("Q)Uscita");
            System.out.println("Richiesta :");
            request = scanner.nextLine().toUpperCase();
            switch (request) {
                case "A" -> {
                    client.sendPacket(20, null);
                    client.recevePacket();
                }
                case "B" -> {
                    client.sendPacket(21, null);
                    client.recevePacket();
                }
                case "C" -> {
                    client.sendPacket(22, null);
                    client.recevePacket();
                }
                case "D" -> {
                    client.sendPacket(23, null);
                    boolean other = client.recevePacket();
                    while (other) {
                        other = client.recevePacket();
                    }
                }
                case "E" -> {
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    System.out.println("Inserire l'indice della nota");
                    arrayOutputStream.writeBytes(ByteBuffer.allocate(2).putShort((short) Integer.parseInt(scanner.nextLine().trim())).array());
                    client.sendPacket(24, arrayOutputStream.toByteArray());
                }
                case "F" -> {
                    System.out.println("Inserire nuova nota da inserire");
                    String nota = scanner.nextLine().trim();
                    client.sendPacket(25, nota.getBytes());
                }
            }
            scanner.nextLine();

        } while (!request.equals("Q"));

        System.out.println("Fine Programma");
    }
}