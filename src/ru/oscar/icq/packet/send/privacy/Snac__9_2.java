
package ru.oscar.icq.packet.send.privacy;

import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;

/**
 * Snac (9, 2)
 * Используется клиентом для запроса параметров группы privacy.
 * @author Kornackiy Alexsandr
 */

public class Snac__9_2 extends Flap {

    public Snac__9_2(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x09, 0x02, 0x00, 0x00, 0x00);
        
        addSnac(snac);
    }
}

