
package ru.oscar.icq.packet.parse.meta;

import java.util.EventListener;
import java.util.EventObject;
import ru.oscar.DataWork;
import ru.oscar.icq.constants.MetaCountryConstants;
import ru.oscar.icq.constants.MetaOccupationConstants;
import ru.oscar.icq.events.MetaWorkUserInfoEvent;
import ru.oscar.icq.listener.MetaInfoListener;
import ru.oscar.util.StringUtil;

/**
 * @author Kornackiy Alexsandr
 */
public class WorkUserInfoParser extends BaseMetaInfoParser {

    private String workCity;
    private String workState;
    private String workPhone;
    private String workFax;
    private String workAddress;
    private String workZip;
    private int    workCountry;
    private String workCompany;
    private String workDepartment;
    private String workPosition;
    private String workWebPage;
    private int workOccupationCode;


    protected EventObject getNewEvent() {
        return new MetaWorkUserInfoEvent(this);
    }

    protected void sendMessage(EventListener listener, EventObject e) {
        ((MetaInfoListener) listener).onWorkUserInfo((MetaWorkUserInfoEvent) e);
    }

    public void parse(byte[] data, int index, int request){
        // success byte
        index++;

        // lenght
        int rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Work City
        workCity = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;

        // lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Work State
        workState = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;

        // lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Work Phone
        workPhone = StringUtil.utf8ByteArrayToString(data, index, rStrLen - 1);
        index += rStrLen;

        // lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Work Fax
        workFax = StringUtil.utf8ByteArrayToString(data, index, rStrLen - 1);
        index += rStrLen;

        // lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Work Address
        workAddress = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;

        // lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Work Zip code
        workZip = StringUtil.utf8ByteArrayToString(data, index, rStrLen - 1);
        index += rStrLen;

        // Work Country
        workCountry = DataWork.getWordLE(data, index);
        index += 2;

        // lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Work Company
        workCompany = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;

        // lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Work Department
        workDepartment = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;

        // lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;
        
        // Work Position
        workPosition = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
        index += rStrLen;

        // Work Occupation Code
        workOccupationCode = DataWork.getWordLE(data, index);
        index += 2;

        // lenght
        rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Work WebPage
        workWebPage = StringUtil.utf8ByteArrayToString(data, index, rStrLen - 1);
        index += rStrLen;

        // Unknown
        if (index + 1 <= data.length) {
                index++;
        }

        // New Zip (russian zips is 6 simbols)
        if (index + 6 < data.length) {
                index++;
                workZip = StringUtil.utf8ByteArrayToString(data, index, 6);
        }
    }

    public String getWorkCity() {
        return workCity;
    }

    public String getWorkState() {
        return workState;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getWorkFax() {
        return workFax;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public String getWorkZip() {
        return workZip;
    }

    public String getWorkCompany() {
        return workCompany;
    }

    public String getWorkDepartment() {
        return workDepartment;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public String getWorkWebPage() {
        return workWebPage;
    }

    public MetaOccupationConstants getWorkOccupation() {
        return new MetaOccupationConstants(workOccupationCode);
    }

    public MetaCountryConstants getWorkCountry() {
        return new MetaCountryConstants(workCountry);
    }
}
