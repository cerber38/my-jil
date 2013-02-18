
package ru.oscar.icq.listener;

import ru.oscar.icq.events.MessageEvent;
import ru.oscar.icq.events.OfflineMessageEvent;

/**
 * @author Kornackiy Alexsandr
 */

public interface MessagesListener {
    
    public void onIncomingMessage(MessageEvent e);
    
    public void onOfflineMessage(OfflineMessageEvent e);
    
}
