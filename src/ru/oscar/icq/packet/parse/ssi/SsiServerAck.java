
package ru.oscar.icq.packet.parse.ssi;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.icq.constants.SsiAckConstants;
import ru.oscar.core.Connect;
import ru.oscar.icq.events.SsiAckEvent;
import ru.oscar.icq.listener.ContactListListener;
import ru.oscar.command.DefaultCommand;

/**
 * snac (13, 0e)
 * Ответ при изменении, добавление и т.п.
 * 
 * @author Kornackiy Alexsandr
 */

public class SsiServerAck extends DefaultCommand{   
    
    private SsiAckConstants results;
    
    public SsiServerAck(){
        super();       
    }
    
        @Override
    public void exec(Flap f) {            
        // result code
        results = new SsiAckConstants(DataWork.getWord(f.getDataArray(), f.getDataArray().length - 2));
    }  
        
    public void notify(Connect connect) { 
        SsiAckEvent e = new SsiAckEvent(this);
        for (int i = 0; i < connect.getListenerContactList().size(); i++) {
            ContactListListener l = connect.getListenerContactList().get(i);
            l.onSsiAck(e);
        }          
    }
    
    public SsiAckConstants getResuls() {
            return results;
    }    
                        
}
