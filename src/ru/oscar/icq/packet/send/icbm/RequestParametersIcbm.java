
package ru.oscar.icq.packet.send.icbm;

import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * Snca (4, 4)
 * Запрашивает параметры каналов ICBM
 * @author Kornackiy Alexsandr
 */

public class RequestParametersIcbm extends Flap {

    public RequestParametersIcbm(){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x04, 0x04, 0x00, 0x00, 0x00);
        
        addSnac(snac);
    }
}