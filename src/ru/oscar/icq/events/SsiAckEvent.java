
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.constants.SsiAckConstants;
import ru.oscar.icq.packet.parse.ssi.SsiServerAck;

/**
 * @author Kornackiy Alexsandr
 */

public class SsiAckEvent extends EventObject {
    
    public SsiAckEvent(SsiServerAck source) {
            super(source);		
    }

    public SsiAckConstants getResults() {
            return ((SsiServerAck)getSource()).getResuls();
    }  
        
}
