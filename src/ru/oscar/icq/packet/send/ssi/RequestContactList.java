
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * Snac (13, 2)
 * Запрос параметров SSI
 * @author Kornackiy Alexsandr
 */

public class RequestContactList extends Flap {

    public RequestContactList(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x04, 0x00, 0x00, 0x00);
        
        addSnac(snac);
    }
}
