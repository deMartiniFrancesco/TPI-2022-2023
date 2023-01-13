package demartini_F_RecTCP.file;

import java.io.*;
import java.net.*;
import java.util.*;


public class TcpClient {

    
    public static void main(String args[]) throws Exception {

        InetAddress IPAddress = InetAddress.getByName("172.16.13.228");
        Socket s = new Socket(IPAddress, 2222);
        DataInputStream in = new DataInputStream(s.getInputStream());
        
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        
        Scanner input = new Scanner(System.in);
        String richiesta;
        Packet p=new Packet();
        byte buff[]=new byte[2000];
        int bl; // bletti
        do {
            System.out.println("A)Richiesta Benvenuto");
            System.out.println("B)Richiesta di frase a caso");
            System.out.println("C)Richiesta di numero frasi presenti");
            System.out.println("D)Richiesta di tutte le frasi");
            System.out.println("E)Richiesta di una determinata frase");
            System.out.println("F)Richiesta di inserimento nuova frase");
            System.out.println("Q)Uscita");
            System.out.println("Richiesta :");
            richiesta = input.nextLine().toUpperCase();
            if (richiesta.equals("A")) {}
            if (richiesta.equals("B")) {}


        } while (!richiesta.equals("Q"));
        System.out.println("Fine Programma");

        System.out.println("Terminato!!!");
        s.close();
    }
}
