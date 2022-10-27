package demartini_F_http_cl.file;

import java.io.*;
import java.net.*;
import java.util.*;

public class TestSockC2Byte {

    public static void main(String[] args) {

        try {
            String hostName = "172.16.1.99";
            int portNumber = 7;

            Socket cs = new Socket(hostName, portNumber);        // 1st statement
            System.out.println(cs.getLocalSocketAddress() + "<-->" + cs.getRemoteSocketAddress());

            DataOutputStream dOut = new DataOutputStream(cs.getOutputStream());
            DataInputStream dIn = new DataInputStream(cs.getInputStream());


            Scanner stdIn = new Scanner((System.in));

            String l = stdIn.nextLine();
            while (l.length() > 0) {
                // scrivi lunghezza stringa e byte stringa
                int len = l.length();
                dOut.writeInt(len);
                System.out.println(dOut.size());
                dOut.write(l.getBytes());
                System.out.println(dOut.size());
                //dOut.flush();

                len = dIn.readInt();
                byte[] b = new byte[200];
                dIn.read(b, 0, len);
                System.out.println("-->" + len + " " + new String(b));// scrivi quello che leggi dal socket

                l = stdIn.nextLine();
            }
            cs.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}
