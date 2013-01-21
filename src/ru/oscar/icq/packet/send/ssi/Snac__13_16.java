
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.util.StringUtil;

/**
 * Удалить себя из кл подьзователя
 * Snac(13, 16)
 * @author Kornackiy Alexsandr
 */
public class Snac__13_16 extends Flap {
    
	public Snac__13_16(String sn){
            super(CHANNEL2);
            Snac snac = new Snac(0x13, 0x16, 0x00, 0x00, 0x00000000);	

            //length of the uin string
            snac.addSnacData(DataWork.putByte(sn.length()));
            //remote client uin string
            snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(sn)));

            addSnac(snac);
	}    
    
}
