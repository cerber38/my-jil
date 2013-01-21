
package ru.oscar.icq.packet.send;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.util.StringUtil;

/**
 * Запрашиваем аторизацию.
 * @author Kornackiy Alexsandr
 */

public class AuthorizationRequest extends Flap {

    public AuthorizationRequest(){
        super(CHANNEL1);
    }
    
    public AuthorizationRequest(String sn, String password){
        this();
        // protocol version number
        addFlapData(DataWork.putDWord(0x01));
        // TLV.Type(0x01) - screen name
        addTlv(new Tlv(TLV_TYPE_SCREEN_NAME, sn));
        // TLV.Type(0x02) - roasted password
        addTlv(new Tlv(TLV_TYPE_ROASTED_PASSWORD, DataWork.putArray(StringUtil.encryptPassword1(password))));
        // TLV.Type(0x03) - client id string
        addTlv(new Tlv(TLV_TYPE_CLIENT_ID_STRING, "JAVA ICQ LIBRARY"));
        // TLV.Type(0x16) - client id
        addTlv(new Tlv(TLV_TYPE_CLIENT_ID,  DataWork.putWord(0x08)));
        // TLV.Type(0x17) 
        addTlv(new Tlv(TLV_TYPE_CLIENT_MAJOR_VERSION,  DataWork.putWord(0x05)));
        // TLV.Type(0x18) 
        addTlv(new Tlv(TLV_TYPE_CLIENT_MINOR_VERSION,  DataWork.putWord(0x25)));
        // TLV.Type(0x19) 
        addTlv(new Tlv(TLV_TYPE_CLIENT_LESSER_VERSION,  DataWork.putWord(0x00)));
        // TLV.Type(0x1A) 
        addTlv(new Tlv(TLV_TYPE_CLIENT_BUILD_NUMBER,  DataWork.putWord(0x00)));
        // TLV.Type(0x14) - distribution number
        addTlv(new Tlv(TLV_TYPE_CLIENT_DISTRIBUTION_NUMBER,  DataWork.putArray(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x04, (byte) 0x3D})));
        // TLV.Type(0x0F) - client language (2 symbols)
        addTlv(new Tlv(TLV_TYPE_CLIENT_LANGUAGE, "ru"));
        // TLV.Type(0x0E) - client country (2 symbols)
        addTlv(new Tlv(TLV_TYPE_CLIENT_ID_COUNTRY, "ru"));
    }
}
