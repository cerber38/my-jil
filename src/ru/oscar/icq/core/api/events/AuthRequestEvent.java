
package ru.oscar.icq.core.api.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.ssi.Snac__13_19;

/**
 * @author Kornackiy Alexsandr
 */

public class AuthRequestEvent extends EventObject {
    
    public AuthRequestEvent(Snac__13_19 source){
        super(source);
    }
    
    public String getMessage() {
        return ((Snac__13_19) getSource()).getMessage();
    }

    public String getSn() {
        return ((Snac__13_19) getSource()).getSn();
    }      
    
}
