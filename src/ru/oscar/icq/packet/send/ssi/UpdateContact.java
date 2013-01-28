
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.DataWork;
import ru.oscar.Tlv;
import ru.oscar.icq.contacts.Contact;
import ru.oscar.util.StringUtil;

/**
 * Обновит контакт в контакт листе на сервере
 * @author Kornackiy Alexsandr
 */
public class UpdateContact extends SsiUpdate {
    
    public UpdateContact(Contact contact){
        super(contact.getSn());
        //Group ID#
        snac.addSnacData(DataWork.putWord(contact.getGroupID()));
        //Item ID#
        snac.addSnacData(DataWork.putWord(contact.getId()));
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(TYPE_CONTACT));
        
        byte[] nick = StringUtil.bytesOfStringUTF8(contact.getName());
        
        Tlv tlv0131 = new Tlv(0x0131);
        tlv0131.addTlvData(DataWork.putArray(nick));
        
        snac.addSnacData(DataWork.putWord(nick.length + 4));
        
        snac.addTlv(tlv0131);	        
        
        finalizePacket();
    }
    
}
