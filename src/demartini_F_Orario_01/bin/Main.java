package demartini_F_Orario_01.bin;

import demartini_F_Orario_01.bin.packages.registration.MTSRegistrationError;
import demartini_F_Orario_01.bin.packages.registration.MTSRegistrationRequest;

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

        MTS MTS = new MTS(ipTarget, 7);

        MTS.sendPacket(new MTSRegistrationRequest("deMartiniFrancesco"));
        System.out.println(MTS.receivePacket());

        System.out.println("End");

    }
}
