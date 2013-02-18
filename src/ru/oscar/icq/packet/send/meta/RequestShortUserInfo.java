
package ru.oscar.icq.packet.send.meta;

public class RequestShortUserInfo extends RequestUserInfo {		

    public RequestShortUserInfo(String uinSearch, String uinForRequest) {
            super(uinSearch, uinForRequest, REQUEST_SHORT_USER_INFO, 0x00);
    }	
}
