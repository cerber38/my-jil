
package ru.oscar.icq.packet.send.meta;

public class RequestFullUserInfo extends RequestUserInfo {
	
    public RequestFullUserInfo(String uinSearch, String uinForRequest) {
            super(uinSearch, uinForRequest, REQUEST_FULL_USER_INFO, 0x00);
    }
}
