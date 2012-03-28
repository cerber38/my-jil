
package ru.oscar.icq.packet.send.icbm;

import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;

/**
 * Snca (4, 4)
 * Запрашивает параметры каналов ICBM
 * @author Kornackiy Alexsandr
 */

public class Snac__4_4 extends Flap {

    public Snac__4_4(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x04, 0x04, 0x00, 0x00, 0x00);
        
        addSnac(snac);
    }
}