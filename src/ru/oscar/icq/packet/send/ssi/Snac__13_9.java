
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.util.StringUtil;

/**
 * Изменить данные
 * Snac(13,9)
 * @author Kornackiy Alexsandr
 */
public abstract class Snac__13_9 extends Flap {
    
    protected Snac snac;

    public Snac__13_9(String data){
        super(CHANNEL2);
               
        snac = new Snac(0x13, 0x09, 0x00, 0x00, 0x00);
        
        byte[] itemName = StringUtil.bytesOfStringUTF8(data);
        
        //Length of the item name
        snac.addSnacData(DataWork.putWord(itemName.length));
        //Item name string
        snac.addSnacData(DataWork.putArray(itemName));      
    }
    
    public Snac__13_9(int id){
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
