
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.util.StringUtil;

/**
 * Позволить контакту добавить нас.
 * @author Kornackiy Alexsandr
 */
public class SendFutureAuthorization extends Flap {
    
	public SendFutureAuthorization(String uin, String message){
            super(CHANNEL2);
            Snac snac = new Snac(0x13, 0x14, 0x00, 0x00, 0x00000000);	

            //Length of the item name
            snac.addSnacData(DataWork.putByte(uin.length()));
            //Item name string
            snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(uin)));
            
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
