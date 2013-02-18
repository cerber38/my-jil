
package ru.oscar.icq.listener;

import java.util.EventListener;
import ru.oscar.icq.events.MetaAffilationsUserInfoEvent;
import ru.oscar.icq.events.MetaBasicUserInfoEvent;
import ru.oscar.icq.events.MetaEmailUserInfoEvent;
import ru.oscar.icq.events.MetaFindByUinUserInfoEvent;
import ru.oscar.icq.events.MetaInterestsUserInfoEvent;
import ru.oscar.icq.events.MetaMoreUserInfoEvent;
import ru.oscar.icq.events.MetaNoteUserInfoEvent;
import ru.oscar.icq.events.MetaShortUserInfoEvent;
import ru.oscar.icq.events.MetaWorkUserInfoEvent;


public interface MetaInfoListener extends EventListener {	
	
    public void onShortUserInfo(MetaShortUserInfoEvent e);

    public void onBasicUserInfo(MetaBasicUserInfoEvent e);

    public void onEmailUserInfo(MetaEmailUserInfoEvent e);

    public void onWorkUserInfo(MetaWorkUserInfoEvent e);

    public void onInterestsUserInfo(MetaInterestsUserInfoEvent e);

    public void onMoreUserInfo(MetaMoreUserInfoEvent e);

    public void onNotesUserInfo(MetaNoteUserInfoEvent e);

    public void onAffilationsUserInfo(MetaAffilationsUserInfoEvent e);	
    
    public void onFindByUinUserInfo(MetaFindByUinUserInfoEvent e);	
    
}