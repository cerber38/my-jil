
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;

/**
 * Snac (13, 7)
 * @author Kornackiy Alexsandr
 */

public class Snac__13_7 extends Flap {

    public Snac__13_7(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x07, 0x00, 0x00, 0x00);
        
        addSnac(snac);
    }
}
