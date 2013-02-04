
package ru.oscar.icq.packet.parse.meta;

import java.util.EventObject;
import ru.oscar.DataWork;
import ru.oscar.icq.events.MetaSetInfoAskEvents;
import ru.oscar.icq.listener.ListenerMetaInfo;

/**
 * @author Kornackiy Alexsandr
 */
public class MetaSetInfoAsk extends BaseMetaInfoParser {
    
    private boolean setInfo;

    @Override
    protected EventObject getNewEvent() {
        return new MetaSetInfoAskEvents(this);
    }

    @Override
    protected void sendMessage(ListenerMetaInfo listener, EventObject e) {
        listener.onSetInfoAsk((MetaSetInfoAskEvents) e);
    }

    public void parse(byte[] data, int index, int request) {
        setInfo = (DataWork.getByte(data, index) == 0x0A);  
    }

    /**
     * @return the setInfo
     */
    public boolean isSetInfo() {
        return setInfo;
    }
    
}
