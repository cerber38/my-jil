
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.util.StringUtil;

/**
 * Ответ на запрос авторизации пользователя
 * Snac(13, 1A)
 * @author Kornackiy Alexsandr
 */
public class SendAuthorizationReply extends Flap {
    
	public SendAuthorizationReply(String sn, String message, boolean auth){
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

