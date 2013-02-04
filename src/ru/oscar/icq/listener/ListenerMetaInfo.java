
package ru.oscar.icq.listener;

import ru.oscar.icq.events.MetaShortInfoEvent;
import ru.oscar.icq.events.MetaSearchSn;
import ru.oscar.icq.events.MetaSetInfoAskEvents;

/**
 * @author Kornackiy Alexsandr
 */
public interface ListenerMetaInfo {
    
    public void onShortUserInfo(MetaShortInfoEvent e);
    
    public void onSearchSn(MetaSearchSn e);
    
    public void onSetInfoAsk(MetaSetInfoAskEvents e);
}
