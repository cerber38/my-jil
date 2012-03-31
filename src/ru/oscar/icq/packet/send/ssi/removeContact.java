
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.contacts.Contact;
import ru.oscar.icq.util.StringUtil;

/**
 * Удалить контакт
 * @author Kornackiy Alexsandr
 */

public class removeContact extends Flap {

    public removeContact(Contact c){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x0A, 0x00, 0x00, 0x00);
        
        //Length of the item name
        snac.addSnacData(DataWork.putWord(c.getSn().length()));
        //Item name string
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(c.getSn())));  
        //Group ID#
        snac.addSnacData(DataWork.putWord(c.getGroupID()));
        //Item ID#
        snac.addSnacData(DataWork.putWord(c.getId()));    
        
        snac.addSnacData(DataWork.putWord(0x0000)); 
        snac.addSnacData(DataWork.putWord(0x0000)); 
        
        addSnac(snac);
    }
    
}
