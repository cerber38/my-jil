
package ru.oscar.icq.packet.send.icbm;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.constants.MessageChannelConstants;
import ru.oscar.icq.util.StringUtil;

/**
 * Основная часть исходящего пакета
 * @author Kornackiy Alexsandr
 */
public abstract class Message extends Flap implements MessageChannelConstants  {
    
    protected Snac snac;

    public Message(String sn, int messageChannel){
        super(CHANNEL2);   
        
        snac = new Snac(0x04, 0x06, 0x00, 0x00, 0x00);
        
        // msg-id cookie
        snac.addSnacData(DataWork.putDWord(0x00));
        snac.addSnacData(DataWork.putDWord(0x00));
        
        // message channel (see table below)
        snac.addSnacData(DataWork.putWord(messageChannel));
        
        // screenname string length
        snac.addSnacData(DataWork.putByte(sn.length()));
        // screenname string
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(sn)));
    }
    
}
