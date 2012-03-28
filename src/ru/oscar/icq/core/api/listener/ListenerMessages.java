
package ru.oscar.icq.core.api.listener;

import ru.oscar.icq.core.api.events.MessageEvent;

/**
 * @author Kornackiy Alexsandr
 */

public interface ListenerMessages {
    
    public void onIncomingMessage(MessageEvent e);
    
}
