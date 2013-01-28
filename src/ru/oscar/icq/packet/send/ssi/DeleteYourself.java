
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.util.StringUtil;

/**
 * Удалить себя из кл подьзователя
 * Snac(13, 16)
 * @author Kornackiy Alexsandr
 */
public class DeleteYourself extends Flap {
    
	public DeleteYourself(String sn){
            super(CHANNEL2);
            Snac snac = new Snac(0x13, 0x16, 0x00, 0x00, 0x00000000);	

            //length of the uin string
            snac.addSnacData(DataWork.putByte(sn.length()));
            //remote client uin string
            snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(sn)));

            addSnac(snac);
	}    
    
}
