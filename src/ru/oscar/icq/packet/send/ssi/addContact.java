
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.contacts.Contact;
import ru.oscar.icq.util.StringUtil;

/**
 * Добавить контакт
 * @author Kornackiy Alexsandr
 */

public class addContact extends Flap {

    public addContact(Contact c){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x08, 0x00, 0x00, 0x00);
        
        //Length of the item name
        snac.addSnacData(DataWork.putWord(c.getSn().length()));
        //Item name string
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(c.getSn())));
        //Group ID#
        snac.addSnacData(DataWork.putWord(c.getGroupID()));
        //Item ID#
        snac.addSnacData(DataWork.putWord(c.getId()));
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(0x0000));
        
        byte[] nick = StringUtil.bytesOfStringUTF8(c.getName());
        
        snac.addSnacData(DataWork.putWord(8 + nick.length));
        
        Tlv tlv = new Tlv(0x0131);
        tlv.addTlvData(DataWork.putArray(nick));
        snac.addTlv(tlv);	

        // unknown		
        snac.addTlv(new Tlv(0x0066));        
             
        addSnac(snac);
    }
}
