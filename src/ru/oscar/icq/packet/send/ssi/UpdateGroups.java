
package ru.oscar.icq.packet.send.ssi;

import java.util.ArrayList;
import ru.oscar.DataWork;
import ru.oscar.Tlv;

/**
 * Обновит список групп в контакт листе на сервере
 * @author Kornackiy Alexsandr
 */
public class UpdateGroups extends SsiUpdate {
    
    public UpdateGroups(ArrayList groupsID){
        super("");
        
        //Group ID
        snac.addSnacData(DataWork.putWord(0x0000));  
        //Item ID
        snac.addSnacData(DataWork.putWord(0x00)); 
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(TYPE_GROUP));         
            
        snac.addSnacData(DataWork.putWord(4 + groupsID.size() * 2)); 

        Tlv tlv00c8 = new Tlv(0x00C8);
        for(Object id : groupsID){
            tlv00c8.addTlvData(DataWork.putWord((Integer)id));
        }

        snac.addTlv(tlv00c8);
            
        finalizePacket();
    }

}
