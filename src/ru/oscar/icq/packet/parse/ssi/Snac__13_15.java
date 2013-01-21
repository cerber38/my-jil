
package ru.oscar.icq.packet.parse.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.api.events.FutureAuthEvent;
import ru.oscar.icq.core.api.listener.ListenerContactList;
import ru.oscar.icq.core.cmd.DefaultCommand;
import ru.oscar.icq.util.StringUtil;

/**
 * Пользователь разрешает добавить его в кл
 * Snac(13, 15) 
 * @author Kornackiy Alexsandr
 */
public class Snac__13_15 extends DefaultCommand{  
    
    private String sn;
    private String message;
    
    public Snac__13_15(){
        super();       
    }    
    
    public void exec(Flap f) { 
        int index = 8;   
        byte[] data = f.getDataArray(); 
        
        int uinLen = DataWork.getByte(data, index);
        index++;
        
        byte[] uinData = DataWork.getArray(data, index, uinLen);
        index += uinLen;
        
        sn = StringUtil.stringOfBytes(uinData);
        
        int messageLen = DataWork.getWord(data, index);
        index += 2; 
        
        message = StringUtil.utf8ByteArrayToString(data, index, messageLen);
    }
    
    public void notify(Connect connect) { 
         FutureAuthEvent e = new FutureAuthEvent(this);
         for (int i = 0; i < connect.getListenerMessages().size(); i++) {
             ListenerContactList l = (ListenerContactList) connect.getListenerContactList().get(i);
             l.onFutureAuth(e);
         }        
    }

    /**
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
}
