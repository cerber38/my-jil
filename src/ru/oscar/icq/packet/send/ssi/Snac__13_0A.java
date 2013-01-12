
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.contacts.Contact;
import ru.oscar.icq.contacts.Group;
import ru.oscar.icq.util.StringUtil;

/**
 * Удалить данные
 * @author Kornackiy Alexsandr
 */

public class Snac__13_0A extends Flap {

    public Snac__13_0A(Contact c){
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
        
        snac.addSnacData(DataWork.putWord(TYPE_CONTACT)); 
        snac.addSnacData(DataWork.putWord(0x0000)); 
        
        addSnac(snac);
    }
    
    public Snac__13_0A(Group g){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x0A, 0x00, 0x00, 0x00);
        
        //Length of the item name
        snac.addSnacData(DataWork.putWord(g.getName().length()));
        //Item name string
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(g.getName())));  
        //Group ID#
        snac.addSnacData(DataWork.putWord(g.getIdGroup()));
        //Item ID#
        snac.addSnacData(DataWork.putWord(g.getItemID()));    
        
        snac.addSnacData(DataWork.putWord(TYPE_GROUP)); 
        snac.addSnacData(DataWork.putWord(0x0000)); 
        
        addSnac(snac);        
        
    }
    
}
