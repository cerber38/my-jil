
package ru.oscar.icq.packet;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;

/**
 * Предворительно обработаем пакет
 * @author Kornackiy Alexsandr
 */

public class Packet extends Flap {
    
    public Packet(byte[] array, boolean hasSnac){
        super();
        
        setChanel(DataWork.getByte(array, 1));
        setSeq(DataWork.getWord(array, 2));
                     
	if (hasSnac){
            addSnac(new Snac(array, 6));
        }else{
            addFlapData(array, 6, array.length - 6);
	}        
    }
    

}
