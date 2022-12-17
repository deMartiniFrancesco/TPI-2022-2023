package demartini_F_Orario_01.bin.main;

import demartini_F_Orario_01.bin.MTPClient;
import demartini_F_Orario_01.bin.PacketOperationCode;
import demartini_F_Orario_01.bin.Utility;
import demartini_F_Orario_01.bin.packages.MTPDataRequest;
import demartini_F_Orario_01.bin.packages.MTPRegistrationRequest;
import demartini_F_Orario_01.bin.packages.MTPRegistrationSuccess;

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
        mtpClient.sendPacket(new MTPRegistrationSuccess(Utility.nextNonNegative()));
        mtpClient.sendPacket(new MTPDataRequest(PacketOperationCode.PROFESSOR_REQUEST, "de Carli"));

        System.out.println("End");
    }
}
