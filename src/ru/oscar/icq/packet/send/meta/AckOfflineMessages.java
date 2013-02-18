
package ru.oscar.icq.packet.send.meta;


public class AckOfflineMessages extends BaseClientMeta {
	
    protected static final int REQUEST_LENGHT = 0x0008;

    public AckOfflineMessages(String uinForRequest) {
            super(REQUEST_LENGHT, uinForRequest, CLIENT_ACK_OFFLINE_MESSAGES);
            finalizePacket();
    }
}
