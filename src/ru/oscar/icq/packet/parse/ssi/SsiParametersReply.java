
package ru.oscar.icq.packet.parse.ssi;

import ru.oscar.core.Connect;
import ru.oscar.command.DefaultCommand;
import ru.oscar.icq.packet.send.generic.SetStatus;
import ru.oscar.icq.packet.send.generic.OnlineReady;
import ru.oscar.icq.packet.send.location.SetUserInformation;
import ru.oscar.icq.packet.send.ssi.RequestContactList;
import ru.oscar.icq.packet.send.ssi.LoadServerContact;

/**
 * Snac (13, 3)
 * Ответ на SNAC 13,2
 * @author Kornackiy Alexsandr
 */

public class SsiParametersReply extends DefaultCommand{   
    
    public SsiParametersReply(){
        super();
    }
    @Override
    public void notify(Connect connect) {
        // активируем северный контакт лист
        connect.sendPacket(new LoadServerContact()); 
        // set status
        connect.sendPacket(new SetStatus(connect.getOptionsConnect().getStatus(),
                connect.getOptionsConnect().getStatusFlag(), 
                connect.getOptionsConnect().getDirectConnect(),
                connect.getOptionsConnect().getProtocolVersion(),
                connect.getOptionsConnect().isDebug()));   
        // возможности и х-статус
        connect.sendPacket(new SetUserInformation(connect.getOptionsConnect().getCapabilities(),
                    connect.getOptionsConnect().getXstatus(), connect.getOptionsConnect().getStatus()));
        // Мы готовы выйти в интернет
        connect.sendPacket(new OnlineReady(connect.getOptionsConnect().isDebug()));

        connect.successfulConnected();
        
        if(connect.getOptionsConnect().isContactList()){
  
            // запрос контакт листа
            connect.sendPacket(new RequestContactList());
            
            if(connect.getOptionsConnect().isDebug()){
                System.out.println("Contakt list load");                           
            }
             
         }         
        }
    
}
