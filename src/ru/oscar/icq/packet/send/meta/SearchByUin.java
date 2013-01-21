
package ru.oscar.icq.packet.send.meta;

/**
 * Поиск по sn
 * @author Kornackiy Alexsandr
 */

public class SearchByUin extends RequestInfo {

	public SearchByUin(String snSearch, String snForRequest, int request) {
		super(snSearch, snForRequest, SEARCH_BY_UIN_PLAIN, request);
	}
}

