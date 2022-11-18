package demartini_F_UDP_4.bin;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

class UDP_4 {
    public static void main(String[] args) throws IOException {


        System.out.println("Start");


        if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        int startPort = Integer.parseInt(args[0]);
        int endPort = Integer.parseInt(args[1]);



        PrintWriter out;
        BufferedReader in;

        ArrayList<Integer> availablePorts = new ArrayList();

        for (int port = startPort; port < endPort; port++) {
            Socket s = null;
            System.out.println("port = " + port);
            try {
                s = new Socket(InetAddress.getLocalHost(), port);
            } catch (ConnectException ignored) {
            }

            if (s != null) {
                out = new PrintWriter(s.getOutputStream(), true);
                out.println("Test echo message");
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                if (in.readLine() != null) {
                    availablePorts.add(port);
                }
            }
        }
        System.out.println("availablePorts = " + availablePorts);

        System.out.println("End");

    }
}