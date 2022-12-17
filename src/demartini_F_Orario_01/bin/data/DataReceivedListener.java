package demartini_F_Orario_01.bin.data;


import java.util.EventListener;

public interface DataReceivedListener extends EventListener {

    void onDataReceived(DataReceivedEvent event);


}
