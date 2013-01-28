
package ru.oscar.icq.packet.send.generic;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * SNAC (1, 17)
 * Доступ к каким группам снаков мы хотим получить
 * @author Kornackiy Alexsandr
 */

public class RequestFamilies extends Flap {
    
    private final byte[] FAMILIES_LIST = { 0x0022, 0x0001, 0x0001, 0x0004, 0x0013, 0x0004, 0x0002, 0x0001,
        0x0025, 0x0001, 0x0003, 0x0001, 0x0015, 0x0001, 0x0004, 0x0001, 0x0006, 0x0001, 0x0009, 0x0001, 0x000a,
        0x0001, 0x000b, 0x0001};   
    
    
    public RequestFamilies(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x01, 0x17, 0x0, 0x0, 0x00);
        
        for (int i = 0; i < FAMILIES_LIST.length; ++i) {
        snac.addSnacData(DataWork.putWord(FAMILIES_LIST[i]));
        }
        
        addSnac(snac);
    }
}
