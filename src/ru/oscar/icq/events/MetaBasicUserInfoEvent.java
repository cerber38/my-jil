
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.constants.MetaCountryConstants;
import ru.oscar.icq.constants.MetaTimeZoneConstants;
import ru.oscar.icq.packet.parse.meta.BasicUserInfoParser;


public class MetaBasicUserInfoEvent extends EventObject {
    
    public MetaBasicUserInfoEvent(BasicUserInfoParser source) {
        super(source);
    }	

    public String getNickName() {
        return ((BasicUserInfoParser) getSource()).getNickName();
    }

    public String getFirstName() {
        return ((BasicUserInfoParser) getSource()).getFirstName();
    }

    public String getLastName() {
        return ((BasicUserInfoParser) getSource()).getLastName();
    }

    public String getEmail() {
        return ((BasicUserInfoParser) getSource()).getEmail();
    }

    public String getHomeCity() {
        return ((BasicUserInfoParser) getSource()).getHomeCity();
    }

    public String getHomeState() {
        return ((BasicUserInfoParser) getSource()).getHomeState();
    }

    public String getHomePhone() {
        return ((BasicUserInfoParser) getSource()).getHomePhone();
    }

    public String getHomeFax() {
        return ((BasicUserInfoParser) getSource()).getHomeFax();
    }

    public String getHomeAddress() {
        return ((BasicUserInfoParser) getSource()).getHomeAddress();
    }

    public String getCellPhone() {
        return ((BasicUserInfoParser) getSource()).getCellPhone();
    }

    public String getZipCode() {
        return ((BasicUserInfoParser) getSource()).getZipCode();
    }

    public MetaCountryConstants getHomeCountry() {
        return ((BasicUserInfoParser) getSource()).getHomeCountry();
    }

    public MetaTimeZoneConstants getTimeZone() {
        return ((BasicUserInfoParser) getSource()).getTimeZone();
    }

    public boolean isAuth() {
        return ((BasicUserInfoParser) getSource()).isAuthFlag();
    }

    public boolean isWebaware() {
        return ((BasicUserInfoParser) getSource()).isWebawareFlag();
    }

    public boolean isDirectConnection() {
        return ((BasicUserInfoParser) getSource()).isDirectConnection();
    }

    public boolean isPublishPrimaryEmail() {
        return ((BasicUserInfoParser) getSource()).isPublishPrimaryEmail();
    }
}
