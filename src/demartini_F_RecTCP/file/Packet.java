package demartini_F_RecTCP.file;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lorenzo
 */
public class Packet {

    public void dumpPack(byte req[]) {
        System.out.print(req[0]);
        System.out.print(" ");
        int l = Byte.toUnsignedInt(req[1])*256+Byte.toUnsignedInt(req[2]);
        
        System.out.print(l);
        System.out.print(" ");
        for (int k = 0; k < l; k++) {
            System.out.printf("%2x ", req[k + 3]);
        }
        System.out.println();
    }
        public void dumpPack(byte req[],int l) {
        for (int k = 0; k < l; k++) {
            System.out.printf("%2x ", req[k ]);
        }
        System.out.println();
    }

    public int getPack(DataInputStream in, byte b[]) {
        int ncl; // numero byte letti
        ncl = 0;
        byte ch;
        try {
            b[ncl++] = in.readByte();
            b[ncl++] = in.readByte();
            b[ncl++] = in.readByte();
            int l = Byte.toUnsignedInt(b[1])*256+Byte.toUnsignedInt(b[2]);
            System.out.println("letta intestazione" + b[0] + " " + l);
            for (int k = 0; k < l; k++) {
                b[ncl++] = in.readByte();
            }
            System.out.println("letto pacchetto" + l);
        } catch (IOException ex) {
            Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return -1;
        }
        //System.out.print("<<< ");
        //dumpPack(b);
        return ncl;
    }

    private boolean send(DataOutputStream out, byte b[]) {
        try {
            //System.out.print(">>> ");
            //dumpPack(b);
            out.write(b);
            out.flush();
            System.out.println("spediti " + b.length);
        } catch (IOException ex) {
            Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean sendPack(DataOutputStream out, String mex, int cod) {
        int l = mex.getBytes().length;
        byte b[] = new byte[l + 3];
        System.arraycopy(mex.getBytes(), 0, b, 3, l);
        b[0] = (byte) cod;
        b[1] = (byte)(l /256);
        b[2] = (byte)(l%5256);
        return send(out, b);
    }

    public boolean sendPack(DataOutputStream out, int dato, int cod) {
        byte b[] = new byte[5];
        b[0] = (byte) cod;
        b[3] = (byte) (dato / 256);
        b[4] = (byte) (dato % 256);
        b[1] = 0;
        b[2] = 2;
        return send(out, b);
    }

    public boolean sendPack(DataOutputStream out, int cod) {
        byte b[] = new byte[3];
        b[0] = (byte) cod;
        b[1] = 0;
        b[2] = 0;
        return send(out, b);
    }

}
