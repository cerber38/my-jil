
package ru.oscar.icq.packet.send.icbm;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.core.Connect;

/**
 * Snca (4, 2)
 * Установим параметры ICBM
 * @author Kornackiy Alexsandr
 */

public class SetIcbmParameters extends Flap {

    public SetIcbmParameters(Connect connect){
        super(CHANNEL2);       
        
        int flags = 0x03;
        
        if(connect.getOptionsConnect().isTyping()){            
            flags |= 0x0B;
        }
        Snac snac = new Snac(0x04, 0x02, 0x00, 0x00, 0x00);
        snac.addSnacData(DataWork.putWord(0x00));
        snac.addSnacData(DataWork.putDWord(flags));
        snac.addSnacData(DataWork.putWord(8000)); // Maximum message size
        snac.addSnacData(DataWork.putWord(0x03E7));
        snac.addSnacData(DataWork.putWord(0x03E7));
        snac.addSnacData(DataWork.putWord(0x0000)); // Minimum message interval
        snac.addSnacData(DataWork.putWord(0x0000));        
        addSnac(snac);
    }
}