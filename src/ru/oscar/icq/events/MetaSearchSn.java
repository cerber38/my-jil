
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.meta.SearchSnParse;

/**
 * @author Kornackiy Alexsandr
 */
public class MetaSearchSn extends EventObject {
    
    public MetaSearchSn(SearchSnParse source) {
            super(source);
    }	
    
    public boolean isSearch() {
        return ((SearchSnParse) getSource()).isSearch();
    }   
    
    public boolean isContactCheck() {
        return ((SearchSnParse) getSource()).isContactCheck();
    }    

    public String getFoundUin() {
        return ((SearchSnParse) getSource()).getFoundUin();
    }    
    
    public String getNickName() {
            return ((SearchSnParse) getSource()).getNickName();
    }

    public String getFirstName() {
            return ((SearchSnParse) getSource()).getFirstName();
    }

    public String getLastName() {
            return ((SearchSnParse) getSource()).getLastName();
    }

    public String getEmail() {
            return ((SearchSnParse) getSource()).getEmail();
    }

    public boolean isAuth() {
            return ((SearchSnParse) getSource()).isAuthFlag();
    }   
    
    public int getOnlineStatus() {
        return ((SearchSnParse) getSource()).getOnlineStatus();
    }

    public int getGender() {
        return ((SearchSnParse) getSource()).getGender();
    }

    public int getAge() {
        return ((SearchSnParse) getSource()).getAge();
    }    
    
    public int getGroupID() {
        return ((SearchSnParse) getSource()).getGroupID();
    }      
    
    
}
