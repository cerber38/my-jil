
package ru.oscar.icq.packet.send.meta;

import java.util.ArrayList;
import ru.oscar.DataWork;
import ru.oscar.Tlv;

/**
 * Snac(15, 2)
 * Цепь tlv с параметрами для полной смены инфы
 * @author Kornackiy Alexsandr
 */

public class RequestSetFullUserInfo extends BaseClientMeta {
    
    public RequestSetFullUserInfo(String snForRequest, int lenght, ArrayList<Tlv> metaData) {     
        super(lenght + 10, snForRequest, CLIENT_ADVANCED_META, SAVE_INFO_TLV_BASED, 0x00);
        //tlv-based
        for(Tlv t : metaData){
            //tlv.addTlvtoTlv(t); 
            // TODO: type and tlv lenght LE
            // create tlv LE
            tlv.addTlvData(DataWork.putWordLE(t.getTlvType()));
            tlv.addTlvData(DataWork.putWordLE(t.getTlvLength()));
            tlv.addTlvData(DataWork.putArray(t.getDataArray()));
        }
        
        finalizePacket();        
    }    
            
}