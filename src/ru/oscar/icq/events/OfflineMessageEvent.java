
package ru.oscar.icq.events;

import java.util.Date;
import java.util.EventObject;
import ru.oscar.icq.packet.parse.icbm.ICBMMessage;
import ru.oscar.icq.packet.parse.meta.OfflineMessageParser;

public class OfflineMessageEvent extends EventObject {
    
    public OfflineMessageEvent(OfflineMessageParser parser) {
        super(parser);
    }
    
    public OfflineMessageEvent(ICBMMessage parser) {
        super(parser);
    }    

    public String getSN() {
        if (getSource() instanceof OfflineMessageParser) {
            return ((OfflineMessageParser) getSource()).getSN();
        } else {
            return ((ICBMMessage) getSource()).getSenderID();
        }
    }

    public Date getSendDate() {
        if (getSource() instanceof OfflineMessageParser) {
            return ((OfflineMessageParser) getSource()).getSendDate();
        } else {
            return ((ICBMMessage) getSource()).getMessageDate();
        }        
    }

    public String getMessage() {
        if (getSource() instanceof OfflineMessageParser) {
            return ((OfflineMessageParser) getSource()).getMessage();
        } else {
            return ((ICBMMessage) getSource()).getMessage();
        }          
    }
}