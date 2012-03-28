
package ru.oscar.icq.packet.parse.ssi;

import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;
import ru.oscar.icq.packet.send.generic.Snac__1_1E;
import ru.oscar.icq.packet.send.generic.Snac__1_2;
import ru.oscar.icq.packet.send.location.Snac__2_4;
import ru.oscar.icq.packet.send.ssi.Snac__13_4;
import ru.oscar.icq.packet.send.ssi.Snac__13_7;

/**
 * Snac (13, 3)
 * Ответ на SNAC 13,2
 * @author Kornackiy Alexsandr
 */

public class Snac__13_3 extends DefaultCommand{   
    
    public Snac__13_3(){
        super();
    }
    @Override
    public void notify(Connect connect) {
        if(connect.getOptionsConnect().isContactList()){
            // активируем северный контакт лист
            connect.sendPacket(new Snac__13_7());   
            // запрос контакт листа
            connect.sendPacket(new Snac__13_4());
                System.out.println("Contakt list load");
        } else { // - работаем без кл
        // set status
        connect.sendPacket(new Snac__1_1E(connect.getOptionsConnect().getStatus(),
                connect.getOptionsConnect().getStatusFlag(), 
                connect.getOptionsConnect().getDirectConnect(),
                connect.getOptionsConnect().getProtocolVersion()));   
        // возможности и х-статус
        connect.sendPacket(new Snac__2_4(connect.getOptionsConnect().getCapabilities(),
                    connect.getOptionsConnect().getXstatus(), connect.getOptionsConnect().getStatus()));
        // Мы готовы выйти в интернет
        connect.sendPacket(new Snac__1_2());
        
        connect.successfulConnected();
        }
    }
}
