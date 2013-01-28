
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.icbm.ICBMMessage;

/**
 * @author Kornackiy Alexsandr
 */
public class MessageEvent extends EventObject {
    
    public MessageEvent(ICBMMessage source){
        super(source);
    }
    
    public String getSenderID() {
        return ((ICBMMessage) getSource()).getSenderID();
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return ((ICBMMessage) getSource()).getMessage();
    }    
    
}
