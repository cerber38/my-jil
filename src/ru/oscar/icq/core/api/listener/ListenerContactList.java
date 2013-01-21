
package ru.oscar.icq.core.api.listener;

import ru.oscar.icq.core.api.events.AuthReplyEvent;
import ru.oscar.icq.core.api.events.AuthRequestEvent;
import ru.oscar.icq.core.api.events.FutureAuthEvent;
import ru.oscar.icq.core.api.events.SsiAckEvent;

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
