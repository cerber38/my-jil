
package ru.oscar.icq.packet.login;

import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.Tlv;

/**
 * Запрашиваем ключ с сервера.
 * Snac(17, 6)
 * @author Kornackiy Alexsandr
 */
public class AuthkeyRequest extends Flap {
    
    public AuthkeyRequest(String sn){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x17, 0x06, 0x00, 0x00, 0x00);
        
        //TLV.Type(0x01) - screen name
        snac.addTlv(new Tlv(TLV_TYPE_SCREEN_NAME, sn));
        //TLV.Type(0x4B) - unknown (empty) 
        snac.addTlv(new Tlv(TLV_TYPE_UNKNOWN0_MD5_AUTH));
        //TLV.Type(0x5A) - unknown (empty)
        snac.addTlv(new Tlv(TLV_TYPE_UNKNOWN1_MD5_AUTH));

        addSnac(snac);        
    }
}
