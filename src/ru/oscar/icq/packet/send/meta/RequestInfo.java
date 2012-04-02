
package ru.oscar.icq.packet.send.meta;

import ru.oscar.icq.DataWork;

/**
 * Шапка запроса информации
 * @author Kornackiy Alexsandr
 */
public abstract class RequestInfo extends ClientMeta {
    
    protected static final int REQUEST_LENGHT = 0x0E00;
    
    public RequestInfo(String snSearch, String snForRequest, int subType) {
        super(REQUEST_LENGHT, snForRequest, CLIENT_ADVANCED_META, subType);
        
        //uin to search
        tlv.addTlvData(DataWork.putDWordLE(Integer.parseInt(snSearch)));
        
        finalizePacket();
    }
    
    public RequestInfo(String snSearch, String snForRequest, int subType, int requestID) {
        super(REQUEST_LENGHT, snForRequest, CLIENT_ADVANCED_META, subType);
        
        //uin to search       
        tlv.addTlvData(DataWork.putDWordLE(Integer.parseInt(snSearch)));
        
        finalizePacket(requestID);
    }    
    
}