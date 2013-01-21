
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.util.StringUtil;

/**
 * Удалить данные
 * @author Kornackiy Alexsandr
 */

public abstract class Snac__13_0A extends Flap {
    
    protected Snac snac;

    public Snac__13_0A(String sn){
        super(CHANNEL2);
        
        snac = new Snac(0x13, 0x0A, 0x00, 0x00, 0x00);
        
        byte[] itemName = StringUtil.bytesOfStringUTF8(sn);
        
        //Length of the item name
        snac.addSnacData(DataWork.putWord(itemName.length));
        //Item name string
        snac.addSnacData(DataWork.putArray(itemName));
    }
    
    public void finalizePacket() {
        addSnac(snac);
    } 
    
}
   
