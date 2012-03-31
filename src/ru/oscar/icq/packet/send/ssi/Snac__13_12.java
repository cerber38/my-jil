
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;

/**
 * Snac (13, 12)
 * (SSI) modification
 * @author Kornackiy Alexsandr
 */

public class Snac__13_12 extends Flap {

    public Snac__13_12(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x12, 0x00, 0x00, 0x00000012);
        
        addSnac(snac);
    }
}
