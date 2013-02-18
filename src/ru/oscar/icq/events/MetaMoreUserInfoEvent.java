
package ru.oscar.icq.events;

import java.util.Date;
import java.util.EventObject;
import java.util.List;
import ru.oscar.icq.constants.MetaCountryConstants;
import ru.oscar.icq.constants.MetaGenderConstants;
import ru.oscar.icq.constants.MetaMaritalStatusConstants;
import ru.oscar.icq.packet.parse.meta.MoreUserInfoParser;


public class MetaMoreUserInfoEvent extends EventObject {

    public MetaMoreUserInfoEvent(MoreUserInfoParser source) {
        super(source);
    }

    public int getAge() {
        return ((MoreUserInfoParser) getSource()).getAge();
    }

    public MetaGenderConstants getGender() {
        return ((MoreUserInfoParser) getSource()).getGender();
    }

    public String getHomePage() {
        return ((MoreUserInfoParser) getSource()).getHomePage();
    }

    public Date getBirth() {
        return ((MoreUserInfoParser) getSource()).getBirth();
    }

    public List getLanguages() {
        return ((MoreUserInfoParser) getSource()).getLanguages();
    }

    public String getOriginalCity() {
        return ((MoreUserInfoParser) getSource()).getOriginalCity();
    }

    public String getOriginalState() {
        return ((MoreUserInfoParser) getSource()).getOriginalState();
    }

    public MetaCountryConstants getOriginalCountry() {
        return ((MoreUserInfoParser) getSource()).getOriginalCountry();
    }

    public MetaMaritalStatusConstants getMaritalStatus() {
        return ((MoreUserInfoParser) getSource()).getMaritalStatus();
    }
}
