
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.meta.ShortInfoParser;

/**
 * @author Kornackiy Alexsandr
 */

public class MetaShortInfoEvent extends EventObject {
    
    public MetaShortInfoEvent(ShortInfoParser source) {
            super(source);
    }	

    public String getNickName() {
            return ((ShortInfoParser) getSource()).getNickName();
    }

    public String getFirstName() {
            return ((ShortInfoParser) getSource()).getFirstName();
    }

    public String getLastName() {
            return ((ShortInfoParser) getSource()).getLastName();
    }

    public String getEmail() {
            return ((ShortInfoParser) getSource()).getEmail();
    }

    public boolean isAuth() {
            return ((ShortInfoParser) getSource()).isAuthFlag();
    }    
    
}
