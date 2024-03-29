
package ru.oscar.icq.packet.send.ssi;

import java.util.Iterator;
import ru.oscar.DataWork;
import ru.oscar.Tlv;
import ru.oscar.icq.contacts.Contact;
import ru.oscar.icq.contacts.Group;
import ru.oscar.util.StringUtil;

/**
 * Удалить группу с контактами
 * @author Kornackiy Alexsandr
 */
public class RemoveGroup  extends SsiRemoveItem {
    
    public RemoveGroup(Group group){
        super(group.getName());
        //Group ID
        snac.addSnacData(DataWork.putWord(group.getIdGroup()));  
        //Item ID
        snac.addSnacData(DataWork.putWord(0x00)); 
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(TYPE_GROUP)); 
        
        if(group.getContacts() != null && group.getContacts().size() > 0){
            
            //len tlv data
            snac.addSnacData(DataWork.putWord(4 + group.getContacts().size() * 2)); 
            
            Tlv tlv00c8 = new Tlv(0x00C8);
            Iterator contacts  = group.getContacts().keySet().iterator();
            while (contacts.hasNext()) {                
                Contact c = group.getContact((String) contacts.next());
                tlv00c8.addTlvData(DataWork.putWord(c.getId()));
                }
            
            snac.addTlv(tlv00c8);
            
        } else {
            // len tlv data
            snac.addSnacData(DataWork.putWord(0x0000)); 
        }  
        
        finalizePacket();        
    }
    
}
