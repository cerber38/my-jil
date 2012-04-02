
package ru.oscar.icq.packet.parse.meta;

import ru.oscar.icq.core.Connect;

/**
 * @author Kornackiy Alexsandr
 */

public interface IMetaInfoParser {
    
    /**
     * Парсер мета данных
     * @param data
     * @param index
     * @param request 
     */
    public void parse(byte[] data, int index, int request);
    
    public void notify(Connect connection);
    
}
