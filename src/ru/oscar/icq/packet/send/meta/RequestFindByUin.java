
package ru.oscar.icq.packet.send.meta;

public class RequestFindByUin extends RequestUserInfo {
    
    public RequestFindByUin(String uinSearch, String uinForRequestm, int request) {
            super(uinSearch, uinForRequestm, SEARCH_BY_UIN_PLAIN, request);
    }	    
    
}
