
package ru.oscar.icq.packet.send.meta;

/**
 * Запрос кароткой информации
 * @author Kornackiy Alexsandr
 */

public class RequestShortInfo extends RequestInfo {

    public RequestShortInfo(String snSearch, String snForRequest) {
        super(snSearch, snForRequest, REQUEST_SHORT_USER_INFO);
    }    
    
}
