
package ru.oscar.icq.packet.parse.ssi;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.core.Connect;
import ru.oscar.icq.events.AuthRequestEvent;
import ru.oscar.icq.listener.ListenerContactList;
import ru.oscar.command.DefaultCommand;
import ru.oscar.util.Dumper;
import ru.oscar.util.StringUtil;

/**
 * Контакт просит у нас авторизацию
 * Snac(13, 19)
 * @author Kornackiy Alexsandr
 */
public class AuthorizationRequest extends DefaultCommand{
    
    private String sn;
    private String message;
   
    public AuthorizationRequest(){
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
         AuthRequestEvent e = new AuthRequestEvent(this);
         for (int i = 0; i < connect.getListenerMessages().size(); i++) {
             ListenerContactList l = (ListenerContactList) connect.getListenerContactList().get(i);
             l.onAuthRequest(e);
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
