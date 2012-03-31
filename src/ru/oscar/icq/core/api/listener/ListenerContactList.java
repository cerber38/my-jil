
package ru.oscar.icq.core.api.listener;

import ru.oscar.icq.core.api.events.SsiAckEvent;

/**
 * @author Kornackiy Alexsandr
 */

public interface ListenerContactList {
    
    public void isLoadedContactList(); 
    
    public void onSsiAck(SsiAckEvent e);
}
