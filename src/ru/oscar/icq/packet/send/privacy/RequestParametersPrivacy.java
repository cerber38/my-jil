
package ru.oscar.icq.packet.send.privacy;

import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * Snac (9, 2)
 * Используется клиентом для запроса параметров группы privacy.
 * @author Kornackiy Alexsandr
 */

public class RequestParametersPrivacy extends Flap {

    public RequestParametersPrivacy(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x09, 0x02, 0x00, 0x00, 0x00);
        
        addSnac(snac);
    }
}

