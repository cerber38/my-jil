
package ru.oscar.icq.packet.parse.meta;

import java.util.EventListener;
import java.util.EventObject;
import ru.oscar.DataWork;
import ru.oscar.icq.constants.MetaCountryConstants;
import ru.oscar.icq.constants.MetaTimeZoneConstants;
import ru.oscar.icq.events.MetaBasicUserInfoEvent;
import ru.oscar.icq.listener.MetaInfoListener;
import ru.oscar.util.StringUtil;

/**
 * @author Kornackiy Alexsandr
 */

public class BasicUserInfoParser extends BaseMetaInfoParser {

    private String nickName;
    private String firstName;
    private String lastName;
    private String email;
    private String homeCity;
    private String homeState;
    private String homePhone;
    private String homeFax;
    private String homeAddress;
    private String cellPhone;
    private String zipCode;
    private int    homeCountry;
    private byte   timezone;
    private boolean authFlag;
    private boolean webawareFlag;
    private boolean directConnection;
    private boolean publishPrimaryEmail;


    protected EventObject getNewEvent() {
        return new MetaBasicUserInfoEvent(this);
    }


    protected void sendMessage(EventListener listener, EventObject e) {
        ((MetaInfoListener) listener).onBasicUserInfo((MetaBasicUserInfoEvent) e);
    }


    public void parse(byte[] data, int index, int request){
        index++; 

        // Nickname lenght
        int rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Nickname
        nickName = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;

        // First Name lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // First Name
        firstName = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;     

        // Last Name lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Last Name
        lastName = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;        

        // Email lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Email
        email = StringUtil.utf8ByteArrayToString(data, index, rStrLen - 1);
        index += rStrLen;        

        // Home city lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Home city
        homeCity = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;       

        // Home state lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Home state
        homeState = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;
        

        // Home phone lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Home phone
        homePhone = StringUtil.utf8ByteArrayToString(data, index, rStrLen - 1);
        index += rStrLen;

        // Home fax lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Home fax
        homeFax = StringUtil.utf8ByteArrayToString(data, index, rStrLen - 1);
        index += rStrLen;

        // Home address lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Home address
        homeAddress = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;

        // Cell phone lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Cell phone
        cellPhone = StringUtil.utf8ByteArrayToString(data, index, rStrLen - 1);
        index += rStrLen;

        // Home zip lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Home zip
        zipCode = StringUtil.utf8ByteArrayToString(data, index, rStrLen - 1);
        index += rStrLen;

        // Country code
        homeCountry = DataWork.getWordLE(data, index);
        index += 2;

        // GMT offset
        timezone = (byte) DataWork.getByte(data, index);
        index++;

        // Auth Flag
        int authdata = DataWork.getByte(data, index);
        authFlag = (authdata == 0);
        index++;

        // WebAware Flag
        int webawaredata = DataWork.getByte(data, index);
        webawareFlag = (webawaredata == 0);
        index++;

        // Direct connection permissions
        int directdata = DataWork.getByte(data, index);
        directConnection = (directdata == 0);
        index++;

        // Publish primary email flag
        int publishemaildata = DataWork.getByte(data, index);
        publishPrimaryEmail = (publishemaildata == 0);
        index++;

        // New Zip (russian zips is 6 simbols)
        if (index + 6 < data.length) {
            index++;
            zipCode = StringUtil.utf8ByteArrayToString(data, index, 6);
        }
    }

    public String getNickName() {
        return nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public String getHomeState() {
        return homeState;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getHomeFax() {
        return homeFax;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public MetaCountryConstants getHomeCountry() {
        return new MetaCountryConstants(homeCountry);
    }

    public MetaTimeZoneConstants getTimeZone() {
        return new MetaTimeZoneConstants(timezone);
    }

    public boolean isAuthFlag() {
        return authFlag;
    }

    public boolean isWebawareFlag() {
        return webawareFlag;
    }

    public boolean isDirectConnection() {
        return directConnection;
    }

    public boolean isPublishPrimaryEmail() {
        return publishPrimaryEmail;
    }
}
