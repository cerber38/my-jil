
package ru.oscar.icq.packet.parse.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.constants.SsiModifyingAckConstants;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.api.events.SsiAckEvent;
import ru.oscar.icq.core.api.listener.ListenerContactList;
import ru.oscar.icq.core.cmd.DefaultCommand;
import ru.oscar.icq.util.Dumper;

/**
 * snac (13, 0e)
 * Ответ при изменении, добавление и т.п.
 * 
 * @author Kornackiy Alexsandr
 */

public class Snac__13_0E extends DefaultCommand{   
    
    private SsiModifyingAckConstants results;
    
    public Snac__13_0E(){
        super();       
    }
    
        @Override
    public void exec(Flap f) { 
        int index = 0;      
//        if(f.getSnac().getRequestID() != 0x00000009){
//            index = 8;
//        }        
        byte[] data = f.getDataArray();
        
        // result code
        results = new SsiModifyingAckConstants(DataWork.getWord(data, index));
    }  
        
    public void notify(Connect connect) { 
        SsiAckEvent e = new SsiAckEvent(this);
        for (int i = 0; i < connect.getListenerContactList().size(); i++) {
            ListenerContactList l = connect.getListenerContactList().get(i);
            l.onSsiAck(e);
        }          
    }
    
    public SsiModifyingAckConstants getResuls() {
            return results;
    }    
                        
}
