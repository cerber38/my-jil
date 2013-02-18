
package ru.oscar.icq.listener;

import ru.oscar.icq.events.XStatusEvent;

/**
 * @author Kornackiy Alexsandr
 */

public interface XStatusListener {
    
    public void onXStatusResponse(XStatusEvent e);
}
