
package ru.oscar.icq.core.api.listener;

import ru.oscar.icq.core.api.events.XStatusEvent;

/**
 * @author Kornackiy Alexsandr
 */

public interface ListenerXStatus {
    
    public void onXStatusResponse(XStatusEvent e);
}
