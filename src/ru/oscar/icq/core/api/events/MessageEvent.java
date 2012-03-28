
package ru.oscar.icq.core.api.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.icbm.Snac__4_7;

/**
 * @author Kornackiy Alexsandr
 */
public class MessageEvent extends EventObject {
    
    public MessageEvent(Snac__4_7 source){
        super(source);
    }
    
    public String getSenderID() {
        return ((Snac__4_7) getSource()).getSenderID();
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return ((Snac__4_7) getSource()).getMessage();
    }    
    
}
