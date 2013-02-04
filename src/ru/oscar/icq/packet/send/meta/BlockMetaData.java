
package ru.oscar.icq.packet.send.meta;

import java.util.ArrayList;
import ru.oscar.DataWork;
import ru.oscar.Tlv;
import ru.oscar.icq.constants.MetaAffilationConstants;
import ru.oscar.icq.constants.MetaCountryConstants;
import ru.oscar.icq.constants.MetaGenderConstants;
import ru.oscar.icq.constants.MetaInterestsConstants;
import ru.oscar.icq.constants.MetaLanguagesConstants;
import ru.oscar.icq.constants.MetaOccupationConstants;
import ru.oscar.icq.constants.MetaTimeZoneConstants;
import ru.oscar.icq.constants.MetaTlvConstants;
import ru.oscar.util.StringUtil;

/**
 * @author Kornackiy Alexsandr
 */

public class BlockMetaData implements MetaTlvConstants {
    
    private String firstName = null;	 
    private String lastName = null;  
    private String nickName = null; 
    private String userEmail = null;  
    private int[] ageRangeSearch = null;
    private int userAge = -1;
    private MetaGenderConstants userGender = null;
    private MetaLanguagesConstants[] userSpokenLanguage = null;
    private String userHomeCity = null; 
    private String userHomeState = null; 
    private MetaCountryConstants userHomeCountryCode = null;
    private String userWorkName = null; 
    private String userWorkDepartment = null;
    private String userWorkTitle = null;
    private MetaOccupationConstants[] userOcupationCode = null;
    private MetaAffilationConstants[] userAffilation = null;
    private MetaInterestsConstants[] userInterests = null;
    private String userHomepage = null;
    private int searchOnlyOnline = -1;
    private int[] userBirthday = null;
    private String userNotes = null;
    private String userHomeStrreetAddress = null;
    private String userHomeZipCode = null;
    private String userHomePhoneNumber = null;
    private String userHomeFaxNumbere = null;
    private String userHomeCellularPhoneNumber = null;
    private String userWorkStreerAddress = null;
    private String userWorkCityName = null;
    private String userWorkStateName = null;
    private MetaCountryConstants userWorkCountryCode = null;
    private String userWorkZipCode = null;
    private String userWorkPhoneNumber = null;
    private String userWorkFaxNumbere = null;
    private String userWorkWebPage = null;
    private int userWebStatus = -1;
    private int userAuthorization = -1;
    private MetaTimeZoneConstants userGMT = null;
    private String userOriginallyCity = null;
    private String userOriginallyState = null;
    private MetaCountryConstants userOriginallyCountryCode = null;
    
    private int dataLenght;
    
    public ArrayList<Tlv>  getData(){
        ArrayList<Tlv> metaData = new ArrayList<Tlv>();
        dataLenght = 0;
        if(nickName != null){
            metaData.add(sstring(META_TLV_NICK_NAME, nickName));
        }
        return metaData;
    }
    
    /**
     * uint16 value (length) and then asciiz string
     * @param tlvType
     * @param a
     * @return 
     */
    
    public Tlv sstring(int tlvType, String a){
        Tlv tlv = new Tlv(tlvType);
        
        byte[] data = StringUtil.bytesOfStringUTF8(a);
        
        //uint16 value (length)
        tlv.addTlvData(DataWork.putWordLE(data.length));
        //asciiz string     
        tlv.addTlvData(DataWork.putArray(data)); 
        
        //set len
        dataLenght += tlv.getTlvLength() + tlv.TLV_HEADER_SIZE;    
        return tlv;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @param ageRangeSearch the ageRangeSearch to set
     */
    public void setAgeRangeSearch(int[] ageRangeSearch) {
        this.ageRangeSearch = ageRangeSearch;
    }

    /**
     * @param userAge the userAge to set
     */
    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    /**
     * @param userGender the userGender to set
     */
    public void setUserGender(MetaGenderConstants userGender) {
        this.userGender = userGender;
    }

    /**
     * @param userSpokenLanguage the userSpokenLanguage to set
     */
    public void setUserSpokenLanguage(MetaLanguagesConstants[] userSpokenLanguage) {
        this.userSpokenLanguage = userSpokenLanguage;
    }

    /**
     * @param userHomeCity the userHomeCity to set
     */
    public void setUserHomeCity(String userHomeCity) {
        this.userHomeCity = userHomeCity;
    }

    /**
     * @param userHomeState the userHomeState to set
     */
    public void setUserHomeState(String userHomeState) {
        this.userHomeState = userHomeState;
    }

    /**
     * @param userHomeCountryCode the userHomeCountryCode to set
     */
    public void setUserHomeCountryCode(MetaCountryConstants userHomeCountryCode) {
        this.userHomeCountryCode = userHomeCountryCode;
    }

    /**
     * @param userWorkName the userWorkName to set
     */
    public void setUserWorkName(String userWorkName) {
        this.userWorkName = userWorkName;
    }

    /**
     * @param userWorkDepartment the userWorkDepartment to set
     */
    public void setUserWorkDepartment(String userWorkDepartment) {
        this.userWorkDepartment = userWorkDepartment;
    }

    /**
     * @param userWorkTitle the userWorkTitle to set
     */
    public void setUserWorkTitle(String userWorkTitle) {
        this.userWorkTitle = userWorkTitle;
    }

    /**
     * @param userOcupationCode the userOcupationCode to set
     */
    public void setUserOcupationCode(MetaOccupationConstants[] userOcupationCode) {
        this.userOcupationCode = userOcupationCode;
    }

    /**
     * @param userAffilation the userAffilation to set
     */
    public void setUserAffilation(MetaAffilationConstants[] userAffilation) {
        this.userAffilation = userAffilation;
    }

    /**
     * @param userInterests the userInterests to set
     */
    public void setUserInterests(MetaInterestsConstants[] userInterests) {
        this.userInterests = userInterests;
    }

    /**
     * @param userHomepage the userHomepage to set
     */
    public void setUserHomepage(String userHomepage) {
        this.userHomepage = userHomepage;
    }

    /**
     * @param searchOnlyOnline the searchOnlyOnline to set
     */
    public void setSearchOnlyOnline(int searchOnlyOnline) {
        this.searchOnlyOnline = searchOnlyOnline;
    }

    /**
     * @param userBirthday the userBirthday to set
     */
    public void setUserBirthday(int[] userBirthday) {
        this.userBirthday = userBirthday;
    }

    /**
     * @param userNotes the userNotes to set
     */
    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }

    /**
     * @param userHomeStrreetAddress the userHomeStrreetAddress to set
     */
    public void setUserHomeStrreetAddress(String userHomeStrreetAddress) {
        this.userHomeStrreetAddress = userHomeStrreetAddress;
    }

    /**
     * @param userHomeZipCode the userHomeZipCode to set
     */
    public void setUserHomeZipCode(String userHomeZipCode) {
        this.userHomeZipCode = userHomeZipCode;
    }

    /**
     * @param userHomePhoneNumber the userHomePhoneNumber to set
     */
    public void setUserHomePhoneNumber(String userHomePhoneNumber) {
        this.userHomePhoneNumber = userHomePhoneNumber;
    }

    /**
     * @param userHomeFaxNumbere the userHomeFaxNumbere to set
     */
    public void setUserHomeFaxNumbere(String userHomeFaxNumbere) {
        this.userHomeFaxNumbere = userHomeFaxNumbere;
    }

    /**
     * @param userHomeCellularPhoneNumber the userHomeCellularPhoneNumber to set
     */
    public void setUserHomeCellularPhoneNumber(String userHomeCellularPhoneNumber) {
        this.userHomeCellularPhoneNumber = userHomeCellularPhoneNumber;
    }

    /**
     * @param userWorkStreerAddress the userWorkStreerAddress to set
     */
    public void setUserWorkStreerAddress(String userWorkStreerAddress) {
        this.userWorkStreerAddress = userWorkStreerAddress;
    }

    /**
     * @param userWorkCityName the userWorkCityName to set
     */
    public void setUserWorkCityName(String userWorkCityName) {
        this.userWorkCityName = userWorkCityName;
    }

    /**
     * @param userWorkStateName the userWorkStateName to set
     */
    public void setUserWorkStateName(String userWorkStateName) {
        this.userWorkStateName = userWorkStateName;
    }

    /**
     * @param userWorkCountryCode the userWorkCountryCode to set
     */
    public void setUserWorkCountryCode(MetaCountryConstants userWorkCountryCode) {
        this.userWorkCountryCode = userWorkCountryCode;
    }

    /**
     * @param userWorkZipCode the userWorkZipCode to set
     */
    public void setUserWorkZipCode(String userWorkZipCode) {
        this.userWorkZipCode = userWorkZipCode;
    }

    /**
     * @param userWorkPhoneNumber the userWorkPhoneNumber to set
     */
    public void setUserWorkPhoneNumber(String userWorkPhoneNumber) {
        this.userWorkPhoneNumber = userWorkPhoneNumber;
    }

    /**
     * @param userWorkFaxNumbere the userWorkFaxNumbere to set
     */
    public void setUserWorkFaxNumbere(String userWorkFaxNumbere) {
        this.userWorkFaxNumbere = userWorkFaxNumbere;
    }

    /**
     * @param userWorkWebPage the userWorkWebPage to set
     */
    public void setUserWorkWebPage(String userWorkWebPage) {
        this.userWorkWebPage = userWorkWebPage;
    }

    /**
     * @param userWebStatus the userWebStatus to set
     */
    public void setUserWebStatus(boolean userWebStatus) {
        if(userWebStatus){
            this.userWebStatus = 0x01;
        } else {
            this.userWebStatus = 0x00;
        }
        
    }

    /**
     * @param userAuthorization the userAuthorization to set
     */
    public void setUserAuthorization(boolean userAuthorization) {
        if(userAuthorization){
            this.userAuthorization = 0x01;
        } else {
            this.userAuthorization = 0x00;
        }        
    }

    /**
     * @param userGMT the userGMT to set
     */
    public void setUserGMT(MetaTimeZoneConstants userGMT) {
        this.userGMT = userGMT;
    }

    /**
     * @param userOriginallyCity the userOriginallyCity to set
     */
    public void setUserOriginallyCity(String userOriginallyCity) {
        this.userOriginallyCity = userOriginallyCity;
    }

    /**
     * @param userOriginallyState the userOriginallyState to set
     */
    public void setUserOriginallyState(String userOriginallyState) {
        this.userOriginallyState = userOriginallyState;
    }

    /**
     * @param userOriginallyCountryCode the userOriginallyCountryCode to set
     */
    public void setUserOriginallyCountryCode(MetaCountryConstants userOriginallyCountryCode) {
        this.userOriginallyCountryCode = userOriginallyCountryCode;
    }


    /**
     * @return the dataLenght
     */
    public int getDataLenght() {
        return dataLenght;
    }
  
}
