
package ru.oscar.icq.packet.send;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.constants.ChannelsConstants;

/**
 * При подключении к BOSS серверу, посылаем ему cookies
 * @author Kornackiy Alexsandr
 */

public class SendsCookie extends Flap {
    
    public SendsCookie(byte[] cookies){
        super(ChannelsConstants.CHANNEL1);
        
        // protocol version number
        addFlapData(DataWork.putDWord(0x01));
        
        // TLV.Type(0x06) - authorization cookie
        addTlv(new Tlv(TLV_TYPE_AUTHORIZATION_COOKIE,  DataWork.putArray(cookies)));
    }
}
