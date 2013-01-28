
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.util.StringUtil;

/**
 * Изменить данные
 * Snac(13,9)
 * @author Kornackiy Alexsandr
 */
public abstract class SsiUpdate extends Flap {
    
    protected Snac snac;

    public SsiUpdate(String data){
        super(CHANNEL2);
               
        snac = new Snac(0x13, 0x09, 0x00, 0x00, 0x00);
        
        byte[] itemName = StringUtil.bytesOfStringUTF8(data);
        
        //Length of the item name
        snac.addSnacData(DataWork.putWord(itemName.length));
        //Item name string
        snac.addSnacData(DataWork.putArray(itemName));      
    }
    
    public SsiUpdate(int id){
        super(CHANNEL2);
               
        snac = new Snac(0x13, 0x09, 0x00, 0x00, 0x00);
        
        //Length of the item name
        snac.addSnacData(DataWork.putWord(0x0000));
        //..
        //Group ID#        
        snac.addSnacData(DataWork.putWord(0x0000));
        //Item ID#
        snac.addSnacData(DataWork.putWord(id));        
    }    
    
    
    public void finalizePacket() {
        addSnac(snac);
    } 
    
}
