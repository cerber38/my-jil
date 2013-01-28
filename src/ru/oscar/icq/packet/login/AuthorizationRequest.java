
package ru.oscar.icq.packet.login;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.Tlv;
import ru.oscar.util.Crypto;
import ru.oscar.util.StringUtil;

/**
 * Запрашиваем аторизацию.
 * @author Kornackiy Alexsandr
 */

public class AuthorizationRequest extends Flap {
    
    private final String CLIENT_ID_STRING = "JAVA ICQ LIBRARY";
    
    private final int CLIENT_ID = 0x5577;
    private final int MAJOR_VERSION = 1;
    private final int MINOR_VERSION = 0;
    private final int LESSER_VERSION = 0;
    private final int BUILD_NUMBER = 0;
    
    private static final byte[] AIM_MD5_STRING = new byte[] {'A','O','L',' ','I','n','s','t','a','n','t',' ',
        'M','e','s','s','e','n','g','e','r',' ','(','S','M',')'};
    
    private final byte[] DISTRIBUTION_NUMBER = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};

    public AuthorizationRequest(String sn, String password){
        super(CHANNEL1);
        // protocol version number
        addFlapData(DataWork.putDWord(0x01));
        // TLV.Type(0x01) - screen name
        addTlv(new Tlv(TLV_TYPE_SCREEN_NAME, sn));
        // TLV.Type(0x02) - roasted password
        addTlv(new Tlv(TLV_TYPE_ROASTED_PASSWORD, DataWork.putArray(StringUtil.encryptPassword1(password))));
        // TLV.Type(0x03) - client id string
        addTlv(new Tlv(TLV_TYPE_CLIENT_ID_STRING, CLIENT_ID_STRING));
        // TLV.Type(0x16) - client id
        addTlv(new Tlv(TLV_TYPE_CLIENT_ID,  DataWork.putWord(CLIENT_ID)));
        // TLV.Type(0x17) 
        addTlv(new Tlv(TLV_TYPE_CLIENT_MAJOR_VERSION,  DataWork.putWord(MAJOR_VERSION)));
        // TLV.Type(0x18) 
        addTlv(new Tlv(TLV_TYPE_CLIENT_MINOR_VERSION,  DataWork.putWord(MINOR_VERSION)));
        // TLV.Type(0x19) 
        addTlv(new Tlv(TLV_TYPE_CLIENT_LESSER_VERSION,  DataWork.putWord(LESSER_VERSION)));
        // TLV.Type(0x1A) 
        addTlv(new Tlv(TLV_TYPE_CLIENT_BUILD_NUMBER,  DataWork.putWord(BUILD_NUMBER)));
        // TLV.Type(0x14) - distribution number
        addTlv(new Tlv(TLV_TYPE_CLIENT_DISTRIBUTION_NUMBER,  DataWork.putArray(DISTRIBUTION_NUMBER)));
        // TLV.Type(0x0F) - client language (2 symbols)
        addTlv(new Tlv(TLV_TYPE_CLIENT_LANGUAGE, "ru"));
        // TLV.Type(0x0E) - client country (2 symbols)
        addTlv(new Tlv(TLV_TYPE_CLIENT_ID_COUNTRY, "ru"));
    }
    
    public AuthorizationRequest(String sn, String password, byte[] authkey){
        super(CHANNEL2);

        Snac snac = new Snac(0x17, 0x02, 0x00, 0x00, 0x00);  
        
        // TLV.Type(0x01) - screen name
        snac.addTlv(new Tlv(TLV_TYPE_SCREEN_NAME, sn));
        // TLV.Type(0x03) - client id string
        snac.addTlv(new Tlv(TLV_TYPE_CLIENT_ID_STRING, CLIENT_ID_STRING));
        
        byte[] pass = StringUtil.bytesOfString(password);          
        byte[] md5 = new byte[authkey.length + pass.length + AIM_MD5_STRING.length];
        int md5len = 0;
        System.arraycopy(authkey, 0, md5, md5len, authkey.length);
        md5len += authkey.length;
        System.arraycopy(pass, 0, md5, md5len, pass.length);
        md5len += pass.length;
        System.arraycopy(AIM_MD5_STRING, 0, md5, md5len, AIM_MD5_STRING.length);   
        //TLV.Type(0x25) - password md5 hash
        snac.addTlv(new Tlv(TLV_TYPE_PASSWORD_MD5_HASH, DataWork.putArray(Crypto.digest(md5, "md5"))));  
        
        snac.addTlv(new Tlv(TLV_TYPE_CLIENT_ID,  DataWork.putWord(CLIENT_ID)));
        // TLV.Type(0x17) 
        snac.addTlv(new Tlv(TLV_TYPE_CLIENT_MAJOR_VERSION,  DataWork.putWord(MAJOR_VERSION)));
        // TLV.Type(0x18) 
        snac.addTlv(new Tlv(TLV_TYPE_CLIENT_MINOR_VERSION,  DataWork.putWord(MINOR_VERSION)));
        // TLV.Type(0x19) 
        snac.addTlv(new Tlv(TLV_TYPE_CLIENT_LESSER_VERSION,  DataWork.putWord(LESSER_VERSION)));
        // TLV.Type(0x1A) 
        snac.addTlv(new Tlv(TLV_TYPE_CLIENT_BUILD_NUMBER,  DataWork.putWord(BUILD_NUMBER)));
        // TLV.Type(0x14) - distribution number
        snac.addTlv(new Tlv(TLV_TYPE_CLIENT_DISTRIBUTION_NUMBER,  DataWork.putArray(DISTRIBUTION_NUMBER)));
        // TLV.Type(0x0F) - client language (2 symbols)
        snac.addTlv(new Tlv(TLV_TYPE_CLIENT_LANGUAGE, "ru"));
        // TLV.Type(0x0E) - client country (2 symbols)
        snac.addTlv(new Tlv(TLV_TYPE_CLIENT_ID_COUNTRY, "ru"));
        
        addSnac(snac); 
    }
}
