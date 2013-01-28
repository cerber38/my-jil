
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * Snac (13, 12)
 * (SSI) modification
 * @author Kornackiy Alexsandr
 */

public class FinishTransaction extends Flap {

    public FinishTransaction(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x13, 0x12, 0x00, 0x00, 0x00000012);
        
        addSnac(snac);
    }
}
