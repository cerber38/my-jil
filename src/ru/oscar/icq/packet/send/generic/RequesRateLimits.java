
package ru.oscar.icq.packet.send.generic;

import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * Snac (1, 6)
 * Этот SNAC посылает клиент, чтобы запросить текущие параметры лимитов.
 * @author Kornackiy Alexsandr
 */

public class RequesRateLimits extends Flap {
    
    public RequesRateLimits(){
        super(CHANNEL2);
    
        Snac snac = new Snac(0x01, 0x06, 0x0, 0x0, 0x00);
              
        addSnac(snac);
        
    }
        
}
