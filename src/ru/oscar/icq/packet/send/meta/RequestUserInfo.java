
package ru.oscar.icq.packet.send.meta;

import ru.oscar.DataWork;

public abstract class RequestUserInfo extends BaseClientMeta {
	
    protected static final int REQUEST_LENGHT = 0x000E;

    public RequestUserInfo(String snSearch, String snForRequest, int subType, int request) {
        super(REQUEST_LENGHT, snForRequest, CLIENT_ADVANCED_META, subType, request);

        //uin to search       
        tlv.addTlvData(DataWork.putDWordLE(Integer.parseInt(snSearch)));
        
        finalizePacket();
    }
    
}