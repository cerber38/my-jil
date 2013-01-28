
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.ssi.AuthorizationRequest;

/**
 * @author Kornackiy Alexsandr
 */

public class AuthRequestEvent extends EventObject {
    
    public AuthRequestEvent(AuthorizationRequest source){
        super(source);
    }
    
    public String getMessage() {
        return ((AuthorizationRequest) getSource()).getMessage();
    }

    public String getSn() {
        return ((AuthorizationRequest) getSource()).getSn();
    }      
    
}
