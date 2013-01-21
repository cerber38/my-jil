
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.contacts.Contact;

/**
 * Добавить контакт в контакт лист на сервере
 * @author Kornackiy Alexsandr
 */
public class AddsContact extends Snac__13_8 {
    
    public AddsContact(Contact contact){
        super(contact.getSn());
        //Group ID#
        snac.addSnacData(DataWork.putWord(contact.getGroupID()));
        //Item ID#
        snac.addSnacData(DataWork.putWord(contact.getId()));
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(TYPE_CONTACT)); 
        
        if(contact.isAuth()){
            snac.addSnacData(DataWork.putWord(0x04)); 
            snac.addTlv(new Tlv(0x0066));
        }else{
            snac.addSnacData(DataWork.putWord(0x00));
        }
                
        finalizePacket();
    }
    
}
