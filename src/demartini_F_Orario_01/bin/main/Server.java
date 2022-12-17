package demartini_F_Orario_01.bin.main;

import demartini_F_Orario_01.bin.MTPServer;

public class Server {
    public static void main(String[] args) {
        MTPServer mtpServer = new MTPServer(1234);
        mtpServer.startListening();
    }
}
