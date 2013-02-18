
package ru.oscar.icq.packet.send.meta;

public class RequestOfflineMessages extends BaseClientMeta {

    protected static final int REQUEST_LENGHT = 0x0008;

    public RequestOfflineMessages(String uinForRequest) {
        super(REQUEST_LENGHT, uinForRequest, CLIENT_REQUEST_OFFLINE_MESSAGES);
        finalizePacket();
    }	
}
