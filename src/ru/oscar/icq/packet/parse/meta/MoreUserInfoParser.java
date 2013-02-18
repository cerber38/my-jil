
package ru.oscar.icq.packet.parse.meta;

import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import ru.oscar.DataWork;
import ru.oscar.icq.constants.MetaCountryConstants;
import ru.oscar.icq.constants.MetaGenderConstants;
import ru.oscar.icq.constants.MetaLanguagesConstants;
import ru.oscar.icq.constants.MetaMaritalStatusConstants;
import ru.oscar.icq.constants.MetaTimeZoneConstants;
import ru.oscar.icq.events.MetaMoreUserInfoEvent;
import ru.oscar.icq.listener.MetaInfoListener;
import ru.oscar.util.DateTools;
import ru.oscar.util.StringUtil;

/**
 * @author Kornackiy Alexsandr
 */
public class MoreUserInfoParser extends BaseMetaInfoParser {
    
    private int age;
    private MetaGenderConstants gender;
    private String homePage;
    private Date birth;
    private List<MetaLanguagesConstants> languages;
    private String originalCity;
    private String originalState;
    private MetaCountryConstants originalCountry;
    private MetaTimeZoneConstants userTimeZone;
    private MetaMaritalStatusConstants maritalStatus;

    protected EventObject getNewEvent() {
        return new MetaMoreUserInfoEvent(this);
    }

    protected void sendMessage(EventListener listener, EventObject e) {
        ((MetaInfoListener) listener).onMoreUserInfo((MetaMoreUserInfoEvent) e);
    }

    public void parse(byte[] data, int index, int request){
        // success byte
        index++;

        // Age
        age = DataWork.getWordLE(data, index);
        index += 2;

        // Gender
        gender = new MetaGenderConstants(DataWork.getByte(data, index));
        index++;

        // Home Page Lenght
        int rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Home page
        homePage = StringUtil.utf8ByteArrayToString(data, index, rStrLen - 1);
        index += rStrLen;

        // Birth year
        int year = DataWork.getWordLE(data, index);;
        index += 2;

        // Birth month
        int month = DataWork.getWordLE(data, index);
        index += 2;

        // Birth day
        int day = DataWork.getWordLE(data, index);
        index += 2;

        birth = DateTools.makeDate(year, month, day, 0, 0);

        // Languages
        languages = new ArrayList<MetaLanguagesConstants>();
        int lang = DataWork.getByte(data, index);
        index++;
        languages.add(new MetaLanguagesConstants(lang));

        lang = DataWork.getByte(data, index);
        index++;
        languages.add(new MetaLanguagesConstants(lang));

        lang = DataWork.getByte(data, index);
        index++;
        languages.add(new MetaLanguagesConstants(lang));

        // UNKNOWN
        index++;

        // Original From City lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Original From City
        originalCity = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;

        // Original From State lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Original From State
        originalState = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;

        // Original From country
        int originalCountryRD = DataWork.getWordLE(data, index);
        originalCountry = new MetaCountryConstants(originalCountryRD);
        index += 2;

        // Marital Status
        int maritalStatusRD = DataWork.getWordLE(data, index);
        maritalStatus = new MetaMaritalStatusConstants(maritalStatusRD);
        index += 2;

        // User time zone
        userTimeZone = new MetaTimeZoneConstants(DataWork.getByte(data, index));
    }

    public int getAge() {
        return age;
    }

    public MetaGenderConstants getGender() {
        return gender;
    }

    public String getHomePage() {
        return homePage;
    }

    public Date getBirth() {
        return birth;
    }

    public List getLanguages() {
        return languages;
    }

    public String getOriginalCity() {
        return originalCity;
    }

    public String getOriginalState() {
        return originalState;
    }

    public MetaCountryConstants getOriginalCountry() {
        return originalCountry;
    }

    public MetaTimeZoneConstants getUserTimeZone() {
        return userTimeZone;
    }

    public MetaMaritalStatusConstants getMaritalStatus() {
        return maritalStatus;
    }
}
