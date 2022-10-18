package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.packages.registration.MTPRegistrationRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {

        System.out.println("Start");

        InetAddress ipTarget = null;
        try {
            ipTarget = InetAddress.getByName("172.16.1.99");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        MTP MTP = new MTP(ipTarget, 7);

        MTP.sendPacket(new MTPRegistrationRequest("deMartiniFrancesco"));
        System.out.println(MTP.receivePacket());

        System.out.println("End");

    }
}
