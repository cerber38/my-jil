
package ru.oscar.icq.packet.send.location;

import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * Snac (2, 2)
 * Этот запрос посылает клиент для получения параметров группы LOCATE.
 * @author Kornackiy Alexsandr
 */

public class RequestLimitations extends Flap {

    public RequestLimitations(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x02, 0x02, 0x00, 0x00, 0x00);
        
        addSnac(snac);
    }
}
