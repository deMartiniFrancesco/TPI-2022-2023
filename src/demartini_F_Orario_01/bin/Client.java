package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {

        System.out.println("Start");

        InetAddress ipTarget = null;
        try {
            ipTarget = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        MTP MTP = new MTP(ipTarget, 1234);

        MTP.sendPacket(new MTPRegistrationRequest("Nico"));
        System.out.println(MTP.receivePacket());

        System.out.println("End");

    }
}
