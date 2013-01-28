
package ru.oscar.icq.listener;

import ru.oscar.icq.events.AuthReplyEvent;
import ru.oscar.icq.events.AuthRequestEvent;
import ru.oscar.icq.events.FutureAuthEvent;
import ru.oscar.icq.events.SsiAckEvent;

/**
 * @author Kornackiy Alexsandr
 */

public interface ListenerContactList {
    
    public void isLoadedContactList(); 
    
    public void onSsiAck(SsiAckEvent e);
    
    public void onFutureAuth(FutureAuthEvent e);
    
    public void onAuthReply(AuthReplyEvent e);
    
    public void onAuthRequest(AuthRequestEvent e);
}
