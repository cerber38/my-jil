
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * Snac (13, 11)
 * (SSI) modification
 * @author Kornackiy Alexsandr
 */

public class BeginTransaction extends Flap {

    public BeginTransaction(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x11, 0x00, 0x00, 0x00000011);
        
        addSnac(snac);
    }
}