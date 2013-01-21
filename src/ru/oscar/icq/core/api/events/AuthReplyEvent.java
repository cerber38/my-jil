
package ru.oscar.icq.core.api.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.ssi.Snac__13_1B;

/**
 * @author Kornackiy Alexsandr
 */

public class AuthReplyEvent extends EventObject {
    
    public AuthReplyEvent(Snac__13_1B source){
        super(source);
    }
    
    public String getMessage() {
        return ((Snac__13_1B) getSource()).getMessage();
    }

    public String getSn() {
        return ((Snac__13_1B) getSource()).getSn();
    }      
    
    public boolean isAuth() {
        return ((Snac__13_1B) getSource()).isAuth();
    }       
    
}
