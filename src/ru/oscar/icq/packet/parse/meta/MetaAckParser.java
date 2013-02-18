
package ru.oscar.icq.packet.parse.meta;

import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import ru.oscar.DataWork;
import ru.oscar.core.Connect;
import ru.oscar.icq.events.MetaAckEvent;
import ru.oscar.icq.listener.MetaAckListener;

/**
 * @author Kornackiy Alexsandr
 */
public class MetaAckParser extends BaseMetaInfoParser {

    private boolean isOk;

    protected EventObject getNewEvent() {
        return new MetaAckEvent(this);
    }

    protected void sendMessage(EventListener listener, EventObject e) {
        ((MetaAckListener) listener).onMetaAck((MetaAckEvent) e);
    }

    public void parse(byte[] data, int index, int request){
        // code ack
        int code = DataWork.getByte(data, index);
        isOk = (code == 0x0A);
    }

    protected List getListenersList(Connect connection) {
        return connection.getListenerMetaAck();
    }

    public boolean isOk() {
        return isOk;
    }
}
