
package ru.oscar.icq.packet.parse.meta;

import ru.oscar.core.Connect;

/**
 * @author Kornackiy Alexsandr
 */

public interface IMetaInfoParser {
	
    /**
     * Осуществляет разбор сообщения и заполнение полей метаинформации
     * 
     * @param data
     * @param index
     * @param request - дополнительный параметр при запросе 
     */
        public void parse(byte[] data, int index, int request);


    /**
     * Создаем событие и уведомляем о нем слушателей
     * 
     * @param connection
     */
        public void notifyEvent(Connect connection);

}
