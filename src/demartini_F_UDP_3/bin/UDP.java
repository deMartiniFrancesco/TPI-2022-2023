package demartini_F_UDP_3.bin;

import java.io.File;


import java.io.*;
import java.net.*;


public class UDP {
    public static void main(String[] args) throws IOException {

        System.out.println("Start");


        ServerSocket serverSocket = new ServerSocket(2000);
        System.out.println("Server started");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        while ((inputLine = in.readLine()) != null) {
            out.println(inputLine);
        }

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();


        System.out.println("End");

    }
}