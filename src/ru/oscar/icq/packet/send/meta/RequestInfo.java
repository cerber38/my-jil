
package ru.oscar.icq.packet.send.meta;

import ru.oscar.DataWork;

/**
 * @author Kornackiy Alexsandr
 */
public abstract class RequestInfo extends ClientMeta {
    
    protected static final int REQUEST_LENGHT = 0x000E;

    
    public RequestInfo(String snSearch, String snForRequest, int subType) {
        super(snForRequest, CLIENT_ADVANCED_META, subType, REQUEST_LENGHT);
        
        //uin to search
        tlv.addTlvData(DataWork.putDWordLE(Integer.parseInt(snSearch)));
        
        finalizePacket();
    }
    
    public RequestInfo(String snSearch, String snForRequest, int subType, int requestID) {
        super(snForRequest, CLIENT_ADVANCED_META, subType, REQUEST_LENGHT);
        
        //uin to search       
        tlv.addTlvData(DataWork.putDWordLE(Integer.parseInt(snSearch)));
        
        finalizePacket(requestID);
    }         
    
}
