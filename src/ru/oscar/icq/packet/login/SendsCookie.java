
package ru.oscar.icq.packet.login;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Tlv;
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
