
package ru.oscar.icq.packet.send.location;

import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;

/**
 * Snac (2, 2)
 * Этот запрос посылает клиент для получения параметров группы LOCATE.
 * @author Kornackiy Alexsandr
 */

public class Snac__2_2 extends Flap {

    public Snac__2_2(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x02, 0x02, 0x00, 0x00, 0x00);
        
        addSnac(snac);
    }
}
