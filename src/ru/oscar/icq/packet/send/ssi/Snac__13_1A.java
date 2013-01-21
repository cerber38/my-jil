
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.util.StringUtil;

/**
 * Ответ на запрос авторизации пользователя
 * Snac(13, 1A)
 * @author Kornackiy Alexsandr
 */
public class Snac__13_1A extends Flap {
    
	public Snac__13_1A(String sn, String message, boolean auth){
            super(CHANNEL2);
            Snac snac = new Snac(0x13, 0x1A, 0x00, 0x00, 0x00000000);	

            //length of the uin string
            snac.addSnacData(DataWork.putByte(sn.length()));
            //uin string 
            snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(sn)));
            //flag: 1-auth accepted, 0-auth declined
            snac.addSnacData(DataWork.putByte(auth ? 0x01 : 0x00));
            
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

