
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.meta.ShortUserInfoParser;


public class MetaShortUserInfoEvent extends EventObject {
		
    public MetaShortUserInfoEvent(ShortUserInfoParser source) {
        super(source);
    }	

    public String getNickName() {
        return ((ShortUserInfoParser) getSource()).getNickName();
    }

    public String getFirstName() {
        return ((ShortUserInfoParser) getSource()).getFirstName();
    }

    public String getLastName() {
        return ((ShortUserInfoParser) getSource()).getLastName();
    }

    public String getEmail() {
        return ((ShortUserInfoParser) getSource()).getEmail();
    }

    public boolean isAuth() {
        return ((ShortUserInfoParser) getSource()).isAuthFlag();
    }
}
