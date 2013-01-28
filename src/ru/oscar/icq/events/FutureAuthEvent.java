
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.ssi.FutureAuthorization;

/**
 * @author Kornackiy Alexsandr
 */
public class FutureAuthEvent extends EventObject {
    
    public FutureAuthEvent(FutureAuthorization source){
        super(source);
    }
    
    public String getMessage() {
        return ((FutureAuthorization) getSource()).getMessage();
    }

    public String getSn() {
        return ((FutureAuthorization) getSource()).getSn();
    }      
    
}
