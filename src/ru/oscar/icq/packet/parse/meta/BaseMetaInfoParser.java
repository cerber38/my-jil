
package ru.oscar.icq.packet.parse.meta;

import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import ru.oscar.core.Connect;

/**
 * @author Kornackiy Alexsandr
 */

public abstract class BaseMetaInfoParser implements IMetaInfoParser {

    public void notifyEvent(Connect connection) {
        EventObject e = getNewEvent();
        for (int i = 0; i < getListenersList(connection).size(); i++) {
            EventListener l = (EventListener) getListenersList(connection).get(i);
            sendMessage(l, e);
        }
    }
    
    /**
     * Создаем новый объект-событие заданного типа
     * @return
     */
    protected abstract EventObject getNewEvent();
    
    /**
     * Уведомляем слушателя о сообщении, вызывая нужный метод
     *
     * @param listener
     * @param e
     */
    protected abstract void sendMessage(EventListener listener, EventObject e);

    protected List getListenersList(Connect connection) {
        return connection.getListenerMetaInfo();
    }
}
