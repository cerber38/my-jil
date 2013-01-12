
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.contacts.Contact;
import ru.oscar.icq.contacts.Group;
import ru.oscar.icq.util.StringUtil;

/**
 * Изменить данные
 * @author Kornackiy Alexsandr
 */
public class Snac__13_9 extends Flap {

    public Snac__13_9(Contact c){
        super(CHANNEL2);
               
        Snac snac = new Snac(0x13, 0x09, 0x00, 0x00, 0x00);
        
        //Length of the item name
        snac.addSnacData(DataWork.putWord(c.getSn().length()));
        //Item name string
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(c.getSn())));
        //Group ID#
        snac.addSnacData(DataWork.putWord(c.getGroupID()));
        //Item ID#
        snac.addSnacData(DataWork.putWord(c.getId()));
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(TYPE_CONTACT));
        
        byte[] nick = StringUtil.bytesOfStringUTF8(c.getName());
        
        snac.addSnacData(DataWork.putWord(8 + nick.length));
        
        Tlv tlv = new Tlv(0x0131);
        tlv.addTlvData(DataWork.putArray(nick));
        snac.addTlv(tlv);	

        if(c.isAuth()){	
            snac.addTlv(new Tlv(0x0066));     
        }
             
        addSnac(snac);
        
    }
    
    public Snac__13_9(Group g){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x09, 0x00, 0x00, 0x00);
        
        //Length of the item name
        snac.addSnacData(DataWork.putWord(g.getName().length()));
        //Item name string
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(g.getName())));      
        //Group ID#
        snac.addSnacData(DataWork.putWord(g.getIdGroup()));
        //Item ID#
        snac.addSnacData(DataWork.putWord(g.getItemID()));  
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(TYPE_GROUP));
             
        snac.addSnacData(DataWork.putWord(0x0000)); 
        
        addSnac(snac);
        
    }
}
