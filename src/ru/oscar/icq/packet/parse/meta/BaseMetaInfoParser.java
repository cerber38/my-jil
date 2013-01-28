
package ru.oscar.icq.packet.parse.meta;

import java.util.EventObject;
import ru.oscar.core.Connect;
import ru.oscar.icq.listener.ListenerMetaInfo;

/**
 * @author Kornackiy Alexsandr
 */

public abstract class BaseMetaInfoParser implements IMetaInfoParser {
 
    public void notify(Connect connect) {
        EventObject e = getNewEvent();

        for (int i = 0; i < connect.getListenerMetaInfo().size(); i++) {
            ListenerMetaInfo l = connect.getListenerMetaInfo().get(i);
            sendMessage(l, e);
        }
    }

    protected abstract EventObject getNewEvent();

    protected abstract void sendMessage(ListenerMetaInfo listener, EventObject e);

    
}
