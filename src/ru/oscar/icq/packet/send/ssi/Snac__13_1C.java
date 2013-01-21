
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.util.StringUtil;

/**
 * This is the "you-were-added" message meaning that somebody 
 * (snac contain his/her screenname) added you to his/her roster.
 * 
 * @author Kornackiy Alexsandr
 */
public class Snac__13_1C extends Flap {
    
    public Snac__13_1C(String sn){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x1C, 0x00, 0x00, 0x00000000);

        //Length of the item name
        snac.addSnacData(DataWork.putByte(sn.length()));
        //Item name string
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(sn)));   
        
        addSnac(snac);
    }
}
