package demartini_F_RecTCP.bin;

import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class RecTCP {
    public static void main(String[] args) throws UnknownHostException {

        Client client = new Client(InetAddress.getByName("172.16.13.228"), 3333);
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

