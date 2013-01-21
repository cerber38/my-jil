
package ru.oscar.icq.core.api.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.ssi.Snac__13_15;

/**
 * @author Kornackiy Alexsandr
 */
public class FutureAuthEvent extends EventObject {
    
    public FutureAuthEvent(Snac__13_15 source){
        super(source);
    }
    
    public String getMessage() {
        return ((Snac__13_15) getSource()).getMessage();
    }

    public String getSn() {
        return ((Snac__13_15) getSource()).getSn();
    }      
    
}
