
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.constants.MetaCountryConstants;
import ru.oscar.icq.constants.MetaOccupationConstants;
import ru.oscar.icq.packet.parse.meta.WorkUserInfoParser;


public class MetaWorkUserInfoEvent extends EventObject {

    public MetaWorkUserInfoEvent(WorkUserInfoParser source) {
        super(source);
    }

    public String getWorkCity() {
        return ((WorkUserInfoParser) getSource()).getWorkCity();
    }

    public String getWorkState() {
        return ((WorkUserInfoParser) getSource()).getWorkState();
    }

    public String getWorkPhone() {
        return ((WorkUserInfoParser) getSource()).getWorkPhone();
    }

    public String getWorkFax() {
        return ((WorkUserInfoParser) getSource()).getWorkFax();
    }

    public String getWorkAddress() {
        return ((WorkUserInfoParser) getSource()).getWorkAddress();
    }

    public String getWorkZip() {
        return ((WorkUserInfoParser) getSource()).getWorkZip();
    }

    public String getWorkCompany() {
        return ((WorkUserInfoParser) getSource()).getWorkCompany();
    }

    public String getWorkDepartment() {
        return ((WorkUserInfoParser) getSource()).getWorkDepartment();
    }

    public String getWorkPosition() {
        return ((WorkUserInfoParser) getSource()).getWorkPosition();
    }

    public String getWorkWebPage() {
        return ((WorkUserInfoParser) getSource()).getWorkWebPage();
    }

    public MetaOccupationConstants getWorkOccupation() {
        return ((WorkUserInfoParser) getSource()).getWorkOccupation();
    }

    public MetaCountryConstants getWorkCountry() {
        return ((WorkUserInfoParser) getSource()).getWorkCountry();
    }
}