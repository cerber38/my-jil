
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.ssi.AuthorizationReply;

/**
 * @author Kornackiy Alexsandr
 */

public class AuthReplyEvent extends EventObject {
    
    public AuthReplyEvent(AuthorizationReply source){
        super(source);
    }
    
    public String getMessage() {
        return ((AuthorizationReply) getSource()).getMessage();
    }

    public String getSn() {
        return ((AuthorizationReply) getSource()).getSn();
    }      
    
    public boolean isAuth() {
        return ((AuthorizationReply) getSource()).isAuth();
    }       
    
}
