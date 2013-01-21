
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.contacts.Contact;

/**
 * Удалить контакт
 * @author Kornackiy Alexsandr
 */
public class RemoveContact extends Snac__13_0A {
    
    public RemoveContact(Contact contact){
        super(contact.getSn());
        //Group ID#
        snac.addSnacData(DataWork.putWord(contact.getGroupID()));
        //Item ID#
        snac.addSnacData(DataWork.putWord(contact.getId()));
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(TYPE_CONTACT));  
        // len data
        snac.addSnacData(DataWork.putWord(0x00));
               
        finalizePacket();
    }
}
