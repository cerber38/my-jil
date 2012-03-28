
package ru.oscar.icq.packet.parse;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.constants.TlvConstants;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.packet.Packet;
import ru.oscar.icq.util.StringUtil;

/**
 * Ответ на запрос авторизации.
 * @author Kornackiy Alexsandr
 */

public class AuthorizationReply extends Packet{
    
    private String bosServerAddress;
    private byte[] cookie;
    private int error;
    private boolean authorized = false;     
    
    public AuthorizationReply(byte array[], Connect conect){   
        super(array, false);
        int index = 0;
        byte[] data = getDataArray();
        
        while(index < data.length){
            Tlv tlv = new Tlv(data, index);
            switch(tlv.getTlvType()){               
                case TlvConstants.TLV_TYPE_RECONNECT_HERE:
                    bosServerAddress = StringUtil.stringOfBytes(tlv.getDataArray());                   
                    break;
                case TlvConstants.TLV_TYPE_AUTHORIZATION_COOKIE:
                    // если есть куки, то мы авторизованны
                    authorized = true;
                    cookie = tlv.getDataArray();
                    break; 
                case TlvConstants.TLV_TYPE_ERROR_SUBCODE:
                    error = DataWork.getWord(tlv.getDataArray(), 0); 
                    break;                    
            }
            index += tlv.getTlvLength() + 4;
        }
        
    }

    public void notify(Connect connect) {
        if(authorized){
            connect.weLogged(bosServerAddress, cookie);
        }else{
            connect.notAuthorized(error);
        }
    }
}
