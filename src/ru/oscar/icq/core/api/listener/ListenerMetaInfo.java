
package ru.oscar.icq.core.api.listener;

import ru.oscar.icq.core.api.events.MetaShortInfoEvent;
import ru.oscar.icq.core.api.events.MetaSearchSn;

/**
 * @author Kornackiy Alexsandr
 */
public interface ListenerMetaInfo {
    
    public void onShortUserInfo(MetaShortInfoEvent e);
    
    public void onSearchSn(MetaSearchSn e);
    
}
