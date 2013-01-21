
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.util.StringUtil;

/**
 * Snac (13,18)
 * Запрос авторизации у пользователя
 * @author Kornackiy Alexsandr
 */
public class Snac__13_18 extends Flap {
    
	public Snac__13_18(String sn, String message){
            super(CHANNEL2);
            Snac snac = new Snac(0x13, 0x18, 0x00, 0x00, 0x00000000);	

            //Length of the item name
            snac.addSnacData(DataWork.putByte(sn.length()));
            //Item name string
            snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(sn)));
            
            byte[] msg = StringUtil.bytesOfStringUTF8(message);   
            //Reason message length 
            snac.addSnacData(DataWork.putWord(msg.length));
            //Reason message
            snac.addSnacData(DataWork.putArray(msg));
            //unknown
            snac.addSnacData(DataWork.putWord(0x00));

            addSnac(snac);
	}    
    
}
