
package ru.oscar.icq.core.api.events;

import java.util.EventObject;
import ru.oscar.icq.constants.SsiModifyingAckConstants;
import ru.oscar.icq.packet.parse.ssi.Snac__13_0E;

/**
 * @author Kornackiy Alexsandr
 */

public class SsiAckEvent extends EventObject {
    
    public SsiAckEvent(Snac__13_0E source) {
            super(source);		
    }

    public SsiModifyingAckConstants getResults() {
            return ((Snac__13_0E)getSource()).getResuls();
    }  
        
}
