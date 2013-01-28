
package ru.oscar.icq.listener;

import ru.oscar.icq.events.MetaShortInfoEvent;
import ru.oscar.icq.events.MetaSearchSn;

/**
 * @author Kornackiy Alexsandr
 */
public interface ListenerMetaInfo {
    
    public void onShortUserInfo(MetaShortInfoEvent e);
    
    public void onSearchSn(MetaSearchSn e);
    
}
