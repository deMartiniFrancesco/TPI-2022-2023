package demartini_F_TCP_NOV01.bin;

import demartini_F_TCP_NOV01.bin.packets.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Scanner;

class TCP_NOV01 {
    private Socket socket;
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;


    public TCP_NOV01(InetAddress targetAddress, int targetPort) {
        try {
            socket = new Socket(targetAddress, targetPort);

            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void sendPacket(Packet packet) {
        try {
            System.out.println("Sending...");
            outputStream.write(packet.getDataByte());
            outputStream.flush();
            System.out.println("Send");
        } catch (IOException e) {
            e.printStackTrace();
        }
        recevePacket();

    }

    public void recevePacket() {
        System.out.println("Reciving...");
        byte[] recBuf = new byte[200];
        try {
            inputStream.read(recBuf, 0, recBuf.length);
        } catch (IOException ignored) {
            return;
        }

        System.out.println("Recive: " + Arrays.toString(Utility.trim(recBuf)));
    }

    private int[] bytesToInts(byte[] bytes) {
        int[] ints = new int[bytes.length / 2];
        ByteBuffer.wrap(bytes).asIntBuffer().get(ints);
        return ints;
    }
}

class TCP_NOV01Test {
    public static void main(String[] args) throws UnknownHostException {

        System.out.println("Start");

        TCP_NOV01 tcp = new TCP_NOV01(InetAddress.getByName("172.16.13.228"), 2222);
        Scanner scanner = new Scanner(System.in);
        String request;
        do {
            System.out.println("A)Richiesta Benvenuto");
            System.out.println("B)Richiesta di frase a caso");
            System.out.println("C)Richiesta di numero frasi presenti");
            System.out.println("D)Richiesta di tutte le frasi");
            System.out.println("E)Richiesta di una determinata frase");
            System.out.println("F)Richiesta di inserimento nuova frase");
            System.out.println("Q)Uscita");
            System.out.println("Richiesta :");
            request = scanner.nextLine().toUpperCase();
            switch (request) {
                case "A" -> tcp.sendPacket(new BenvenutoPacket());
                case "B" -> tcp.sendPacket(new FraseCasoPacket());
                case "C" -> tcp.sendPacket(new NumFrasiPacket());
                case "D" -> tcp.sendPacket(new TutteFrasiPacket());
                case "E" -> {
                    System.out.println("Inserire l'indice della frase");
                    tcp.sendPacket(new FraseIndexPacket(Integer.parseInt(scanner.nextLine().trim())));
                }
                case "F" -> {
                    System.out.println("Inserire nuova frase da inserire");
                    tcp.sendPacket(new NewFrasePacket(scanner.nextLine().trim()));
                }
            }
            scanner.nextLine();

        } while (!request.equals("Q"));

        System.out.println("Fine Programma");

        System.out.println("End");

    }
}