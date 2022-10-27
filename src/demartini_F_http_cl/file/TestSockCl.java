
import java.io.*;
import java.net.*;
import java.util.*;

public class TestSockCl {

    public static void main(String[] args) {

        try {
            String hostName = "172.16.1.99";
            int portNumber = 7;

            Socket cs = new Socket(hostName, portNumber);        // 1st statement
            System.out.println(cs.getLocalSocketAddress() + "/" + cs.getRemoteSocketAddress());
            PrintWriter out
                    = // 2nd statement
                    new PrintWriter(cs.getOutputStream(),true);
            BufferedReader in
                    = // 3rd statement
                    new BufferedReader(
                            new InputStreamReader(cs.getInputStream()));

            Scanner stdIn = new Scanner((System.in));

            String l = stdIn.nextLine();
            while (l.length() > 0) {
                out.println(l);// scrivi su socket
                System.out.println("-->" + in.readLine());// scrivi quello che leggi dal socket
                l = stdIn.nextLine();
            }
            cs.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}
