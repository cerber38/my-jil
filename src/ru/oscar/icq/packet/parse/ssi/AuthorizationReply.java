
package ru.oscar.icq.packet.parse.ssi;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.core.Connect;
import ru.oscar.icq.events.AuthReplyEvent;
import ru.oscar.icq.listener.ListenerContactList;
import ru.oscar.command.DefaultCommand;
import ru.oscar.util.StringUtil;

/**
 * Ответ на наш запрос авторизации
 * Snac(13,1B)
 * @author Kornackiy Alexsandr
 */
public class AuthorizationReply  extends DefaultCommand{
    
    private String sn;
    private String message;
    
    private boolean auth;
    
    public AuthorizationReply(){
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
        
        auth = DataWork.getByte(data, index) == 1;
        index++;
        
        int messageLen = DataWork.getWord(data, index);
        index += 2; 
        
        message = StringUtil.utf8ByteArrayToString(data, index, messageLen);        
    }
    
    public void notify(Connect connect) { 
         AuthReplyEvent e = new AuthReplyEvent(this);
         for (int i = 0; i < connect.getListenerMessages().size(); i++) {
             ListenerContactList l = (ListenerContactList) connect.getListenerContactList().get(i);
             l.onAuthReply(e);
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

    /**
     * @return the auth
     */
    public boolean isAuth() {
        return auth;
    }
}
