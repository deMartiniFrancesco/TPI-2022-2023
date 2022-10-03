package demartini_F_TFTP.bin;

import demartini_F_TFTP.bin.packages.TftpFileRequestPackage;

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

        TFTP tftp = new TFTP(ipTarget, 69);

        tftp.sendPacket(new TftpFileRequestPackage(
                PacketMode.OCTET,
                "filecorto"));
        tftp.receivePacket();

        System.out.println("End");

    }
}
