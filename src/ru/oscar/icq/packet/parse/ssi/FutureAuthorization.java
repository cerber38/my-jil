
package ru.oscar.icq.packet.parse.ssi;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.core.Connect;
import ru.oscar.icq.events.FutureAuthEvent;
import ru.oscar.icq.listener.ContactListListener;
import ru.oscar.command.DefaultCommand;
import ru.oscar.util.StringUtil;

/**
 * Пользователь разрешает добавить его в кл
 * Snac(13, 15) 
 * @author Kornackiy Alexsandr
 */
public class FutureAuthorization extends DefaultCommand{  
    
    private String sn;
    private String message;
    
    public FutureAuthorization(){
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
             ContactListListener l = (ContactListListener) connect.getListenerContactList().get(i);
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
