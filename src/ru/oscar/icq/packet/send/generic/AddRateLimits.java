/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oscar.icq.packet.send.generic;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;

/**
 * Snac(1, 8)
 * Подписка на любые изменения значений лимитов обращений.
 * @author Kornackiy Alexsandr
 */

public class AddRateLimits extends Flap {
    
    public AddRateLimits(){
        super(CHANNEL2);
    
        Snac snac = new Snac(0x01, 0x08, 0x0, 0x0, 0x00);
        snac.addSnacData(DataWork.putWord(0x0001));
        snac.addSnacData(DataWork.putWord(0x0002));
        snac.addSnacData(DataWork.putWord(0x0003));
        snac.addSnacData(DataWork.putWord(0x0004));
        snac.addSnacData(DataWork.putWord(0x0005));
              
        addSnac(snac);
        
    }
    
}
