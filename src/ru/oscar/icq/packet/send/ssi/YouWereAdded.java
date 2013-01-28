
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.util.StringUtil;

/**
 * This is the "you-were-added" message meaning that somebody 
 * (snac contain his/her screenname) added you to his/her roster.
 * 
 * @author Kornackiy Alexsandr
 */
public class YouWereAdded extends Flap {
    
    public YouWereAdded(String sn){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x1C, 0x00, 0x00, 0x00000000);

        //Length of the item name
        snac.addSnacData(DataWork.putByte(sn.length()));
        //Item name string
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(sn)));   
        
        addSnac(snac);
    }
}
