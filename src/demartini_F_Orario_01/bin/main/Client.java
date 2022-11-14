package demartini_F_Orario_01.bin.main;

import demartini_F_Orario_01.bin.MTPClient;
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

        MTPClient mtpClient = new MTPClient(12345);

        mtpClient.connect(ipTarget, 1234);
        mtpClient.sendPacket(new MTPRegistrationRequest("deMartini"));

        System.out.println("End");
    }
}
