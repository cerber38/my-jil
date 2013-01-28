
package ru.oscar.icq.packet.send.buddy;

import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * Snac (3, 2)
 * Этот SNAC посылают, чтобы запросить права клиента и пользователя.
 * @author Kornackiy Alexsandr
 */

public class RequestLimitationsBuddy extends Flap {

    public RequestLimitationsBuddy(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x03, 0x02, 0x00, 0x00, 0x00);
                
        addSnac(snac);
    }
}
