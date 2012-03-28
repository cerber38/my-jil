
package ru.oscar.icq.packet.send.generic;

import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;

/**
 * Snac (1, 6)
 * Этот SNAC посылает клиент, чтобы запросить текущие параметры лимитов.
 * @author Kornackiy Alexsandr
 */

public class Snac__1_6 extends Flap {
    
    public Snac__1_6(){
        super(CHANNEL2);
    
        Snac snac = new Snac(0x01, 0x06, 0x0, 0x0, 0x00);
              
        addSnac(snac);
        
    }
        
}
