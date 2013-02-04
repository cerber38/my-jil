
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.meta.MetaSetInfoAsk;

/**
 * @author Kornackiy Alexsandr
 */

public class MetaSetInfoAskEvents extends EventObject {
    
    public MetaSetInfoAskEvents(MetaSetInfoAsk source) {
            super(source);
    }	
    
    public boolean isSetInfo() {
        return ((MetaSetInfoAsk) getSource()).isSetInfo();
    }   
    
}
