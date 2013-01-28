
package ru.oscar.icq.listener;

import ru.oscar.icq.events.MessageEvent;

/**
 * @author Kornackiy Alexsandr
 */

public interface ListenerMessages {
    
    public void onIncomingMessage(MessageEvent e);
    
}
