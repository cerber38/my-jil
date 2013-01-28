
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * Snac (13, 7)
 * @author Kornackiy Alexsandr
 */

public class LoadServerContact extends Flap {

    public LoadServerContact(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x07, 0x00, 0x00, 0x00);
        
        addSnac(snac);
    }
}
