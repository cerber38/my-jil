
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.contacts.Contact;

/**
 * Добавление контакт в "список"
 * @author Kornackiy Alexsandr
 */
public class AddsList extends Snac__13_8 {
    
    public AddsList(String sn, int itemID, int list){
        super(sn);
        //Group ID#
        snac.addSnacData(DataWork.putWord(0x0000));
        //Item ID#
        snac.addSnacData(DataWork.putWord(itemID));
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(list)); 
        //len
        snac.addSnacData(DataWork.putWord(0x00));   
        
        finalizePacket();
   }
    
}
