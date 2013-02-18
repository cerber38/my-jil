
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.meta.FindByUinUserInfoParser;

/**
 * @author Kornackiy Alexsandr
 */

public class MetaFindByUinUserInfoEvent extends EventObject {
    
    public MetaFindByUinUserInfoEvent(FindByUinUserInfoParser source) {
            super(source);
    }	
    
    public boolean isSearch() {
        return ((FindByUinUserInfoParser) getSource()).isSearch();
    }   
    
    public boolean isContactCheck() {
        return ((FindByUinUserInfoParser) getSource()).isContactCheck();
    }    

    public String getFoundUin() {
        return ((FindByUinUserInfoParser) getSource()).getFoundUin();
    }    
    
    public String getNickName() {
            return ((FindByUinUserInfoParser) getSource()).getNickName();
    }

    public String getFirstName() {
            return ((FindByUinUserInfoParser) getSource()).getFirstName();
    }

    public String getLastName() {
            return ((FindByUinUserInfoParser) getSource()).getLastName();
    }

    public String getEmail() {
            return ((FindByUinUserInfoParser) getSource()).getEmail();
    }

    public boolean isAuth() {
            return ((FindByUinUserInfoParser) getSource()).isAuthFlag();
    }   
    
    public int getOnlineStatus() {
        return ((FindByUinUserInfoParser) getSource()).getOnlineStatus();
    }

    public int getGender() {
        return ((FindByUinUserInfoParser) getSource()).getGender();
    }

    public int getAge() {
        return ((FindByUinUserInfoParser) getSource()).getAge();
    }    
    
    public int getGroupID() {
        return ((FindByUinUserInfoParser) getSource()).getGroupID();
    }      
    
    
}
