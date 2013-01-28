
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.util.StringUtil;

/**
 * Добавить данные на сервер
 * snac (13, 8)
 * @author Kornackiy Alexsandr
 */

public abstract class SsiAddItem extends Flap {
    
    protected Snac snac;

    public SsiAddItem(String sn){
        super(CHANNEL2);
               
        snac = new Snac(0x13, 0x08, 0x00, 0x00, 0x00);
        
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
