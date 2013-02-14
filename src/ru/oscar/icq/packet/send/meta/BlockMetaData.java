
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
    private MetaOccupationConstants userWorkOcupationCode = null;
    private MetaAffilationConstants[] userAffilation = null;
    private MetaInterestsConstants[] userInterests = null;
    private String userHomepage = null;
    private int searchOnlyOnline = -1;
    private int[] userBirthday = null;
    private String userNotes = null;
    private String userHomeStrreetAddress = null;
    private int userHomeZipCode = -1;
    private String userHomePhoneNumber = null;
    private String userHomeFaxNumber = null;
    private String userHomeCellularPhoneNumber = null;
    private String userWorkStreetAddress = null;
    private String userWorkCityName = null;
    private String userWorkStateName = null;
    private MetaCountryConstants userWorkCountryCode = null;
    private int userWorkZipCode = -1;
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
    private int emailPermission  = 0x00;
    
    public ArrayList<Tlv>  getData(){
        ArrayList<Tlv> metaData = new ArrayList<Tlv>();
        dataLenght = 0;
        
        Tlv nickNameTlv = new Tlv(META_TLV_NICK_NAME);
        sstring(nickNameTlv, nickName);
        dataLenght += nickNameTlv.getTlvLength() + nickNameTlv.TLV_HEADER_SIZE;
        metaData.add(nickNameTlv);
        
        Tlv firstNameTlv = new Tlv(META_TLV_FIRST_NAME);
        sstring(firstNameTlv, firstName);
        dataLenght += firstNameTlv.getTlvLength() + firstNameTlv.TLV_HEADER_SIZE;
        metaData.add(firstNameTlv);        
        
        Tlv lastNameTlv = new Tlv(META_TLV_LAST_NAME);
        sstring(lastNameTlv, lastName);
        dataLenght += lastNameTlv.getTlvLength() + lastNameTlv.TLV_HEADER_SIZE;
        metaData.add(lastNameTlv);

        Tlv userEmailTlv = new Tlv(META_TLV_USER_EMAIL);
        ecombo(userEmailTlv, userEmail, emailPermission);
        dataLenght += userEmailTlv.getTlvLength() + userEmailTlv.TLV_HEADER_SIZE;
        metaData.add(userEmailTlv);        
  
        Tlv ageRangeSearchTlv = new Tlv(META_TLV_AGE_RANGE_TO_SEARCH);
        acombo(ageRangeSearchTlv, ageRangeSearch);
        dataLenght += ageRangeSearchTlv.getTlvLength() + ageRangeSearchTlv.TLV_HEADER_SIZE;
        metaData.add(ageRangeSearchTlv); 
        
        Tlv tlvAge = new Tlv(META_TLV_USER_AGE);
        uint16(tlvAge, userAge == -1 ? 0x00 : userAge);
        dataLenght += tlvAge.getTlvLength() + tlvAge.TLV_HEADER_SIZE;   
        metaData.add(tlvAge);        

        Tlv tlvGender = new Tlv(META_TLV_USER_GENDER);
        uint8(tlvGender, userGender == null ? 0x00 : userGender.getGender());
        dataLenght += tlvGender.getTlvLength() + tlvGender.TLV_HEADER_SIZE;   
        metaData.add(tlvGender);    
        
        if(userSpokenLanguage != null){
            for(MetaLanguagesConstants ml : userSpokenLanguage){
                Tlv tlvSpokenLanguage = new Tlv(META_TLV_SPOKEN_LANGUAGE);            
                uint16(tlvSpokenLanguage, ml.getLanguage());           
                dataLenght += tlvSpokenLanguage.getTlvLength() + tlvSpokenLanguage.TLV_HEADER_SIZE;   
                metaData.add(tlvSpokenLanguage);            
            }
        } else {
                Tlv tlvSpokenLanguage = new Tlv(META_TLV_SPOKEN_LANGUAGE);            
                uint16(tlvSpokenLanguage, 0x00);
                dataLenght += tlvSpokenLanguage.getTlvLength() + tlvSpokenLanguage.TLV_HEADER_SIZE;   
                metaData.add(tlvSpokenLanguage);              
        }
        
        Tlv userHomeCityTlv = new Tlv(META_TLV_USER_HOME_CITY);
        sstring(userHomeCityTlv, userHomeCity);
        dataLenght += userHomeCityTlv.getTlvLength() + userHomeCityTlv.TLV_HEADER_SIZE;
        metaData.add(userHomeCityTlv);  
        
        Tlv userHomeStateTlv = new Tlv(META_TLV_USER_HOME_STATE);
        sstring(userHomeStateTlv, userHomeState);
        dataLenght += userHomeStateTlv.getTlvLength() + userHomeStateTlv.TLV_HEADER_SIZE;
        metaData.add(userHomeStateTlv);        

        Tlv tlvHomeCountryCode = new Tlv(META_TLV_USER_HOME_COUNTRY_CODE);
        uint16(tlvHomeCountryCode, userHomeCountryCode == null ? 0x00 : userHomeCountryCode.getCountry());
        dataLenght += tlvHomeCountryCode.getTlvLength() + tlvHomeCountryCode.TLV_HEADER_SIZE;   
        metaData.add(tlvHomeCountryCode); 

        Tlv userWorkNameTlv = new Tlv(META_TLV_USER_WORK_NAME);
        sstring(userWorkNameTlv, userWorkName);
        dataLenght += userWorkNameTlv.getTlvLength() + userWorkNameTlv.TLV_HEADER_SIZE;
        metaData.add(userWorkNameTlv);     
        
        Tlv userWorkDepartmentTlv = new Tlv(META_TLV_USER_WORK_DEPARTMENT);
        sstring(userWorkDepartmentTlv, userWorkDepartment);
        dataLenght += userWorkDepartmentTlv.getTlvLength() + userWorkDepartmentTlv.TLV_HEADER_SIZE;
        metaData.add(userWorkDepartmentTlv);          

        Tlv userWorkTitleTlv = new Tlv(META_TLV_USER_WORK_POSITION);
        sstring(userWorkTitleTlv, userWorkTitle);
        dataLenght += userWorkTitleTlv.getTlvLength() + userWorkTitleTlv.TLV_HEADER_SIZE;
        metaData.add(userWorkTitleTlv);     
  
        Tlv tlvWorkOcupationCode = new Tlv(META_TLV_USER_OCUPATION_CODE);
        uint16(tlvWorkOcupationCode, userWorkOcupationCode == null ? 0x00 : userWorkOcupationCode.getOccupation());        
        dataLenght += tlvWorkOcupationCode.getTlvLength() + tlvWorkOcupationCode.TLV_HEADER_SIZE;   
        metaData.add(tlvWorkOcupationCode);             

        if(userAffilation != null){
            for(MetaAffilationConstants ma : userAffilation){  
                Tlv userAffilationTlv = new Tlv(META_TLV_USER_AFFILATIONS_NODE); 
                icombo(userAffilationTlv, ma.getAffiliation(), ma.getDescription());
                dataLenght += userAffilationTlv.getTlvLength() + userAffilationTlv.TLV_HEADER_SIZE; 
                metaData.add(userAffilationTlv);            
            }
        } else {
                Tlv userAffilationTlv = new Tlv(META_TLV_USER_AFFILATIONS_NODE);       
                icombo(userAffilationTlv, 0x00, null);
                dataLenght += userAffilationTlv.getTlvLength() + userAffilationTlv.TLV_HEADER_SIZE; 
                metaData.add(userAffilationTlv);              
        }      
        if(userInterests != null){
            for(MetaInterestsConstants ia : userInterests){  
                Tlv userInterestsTlv = new Tlv(META_TLV_USER_INTERESTS_NODE); 
                icombo(userInterestsTlv, ia.getInterest(), ia.getDescription()); 
                dataLenght += userInterestsTlv.getTlvLength() + userInterestsTlv.TLV_HEADER_SIZE; 
                metaData.add(userInterestsTlv);            
            }
        } else {
                Tlv userInterestsTlv = new Tlv(META_TLV_USER_INTERESTS_NODE);   
                icombo(userInterestsTlv, 0x00, null);
                dataLenght += userInterestsTlv.getTlvLength() + userInterestsTlv.TLV_HEADER_SIZE; 
                metaData.add(userInterestsTlv);              
        }  
        
        Tlv userHomepageTlv = new Tlv(META_TLV_USER_HOMEPAGE_URL);
        sstring(userHomepageTlv, userHomepage);
        dataLenght += userHomepageTlv.getTlvLength() + userHomepageTlv.TLV_HEADER_SIZE;
        metaData.add(userHomepageTlv);          

        
        Tlv tlvSearchOnlyOnline = new Tlv(META_TLV_SEARCH_ONLY_ONLINE);
        uint8(tlvSearchOnlyOnline, searchOnlyOnline == -1 ? 0x00 : searchOnlyOnline);
        dataLenght += tlvSearchOnlyOnline.getTlvLength() + tlvSearchOnlyOnline.TLV_HEADER_SIZE;   
        metaData.add(tlvSearchOnlyOnline);             

        Tlv userBirthdayTlv = new Tlv(META_TLV_USER_BIRTHDAY);
        bcombo(userBirthdayTlv, userBirthday);
        dataLenght += userBirthdayTlv.getTlvLength() + userBirthdayTlv.TLV_HEADER_SIZE;   
        metaData.add(userBirthdayTlv);         

        Tlv userNotesTlv = new Tlv(META_TLV_USER_NOTES);
        sstring(userNotesTlv, userNotes);
        dataLenght += userNotesTlv.getTlvLength() + userNotesTlv.TLV_HEADER_SIZE;
        metaData.add(userNotesTlv);        

        Tlv userHomeStrreetAddressTlv = new Tlv(META_TLV_USER_HOME_STREET_ADDRESS);
        sstring(userHomeStrreetAddressTlv, userHomeStrreetAddress);
        dataLenght += userHomeStrreetAddressTlv.getTlvLength() + userHomeStrreetAddressTlv.TLV_HEADER_SIZE;
        metaData.add(userHomeStrreetAddressTlv);        
 
        // TODO: как очистить параметр на сервере?
        Tlv tlvHomeZipCode = new Tlv(META_TLV_USER_HOME_ZIP_CODE); 
        uint32(tlvHomeZipCode, userHomeZipCode == -1 ? 0x00 : userHomeZipCode);
        dataLenght += tlvHomeZipCode.getTlvLength() + tlvHomeZipCode.TLV_HEADER_SIZE;   
        metaData.add(tlvHomeZipCode); 
        
        Tlv userHomePhoneNumberTlv = new Tlv(META_TLV_USER_HOME_PHONE);
        sstring(userHomePhoneNumberTlv, userHomePhoneNumber);
        dataLenght += userHomePhoneNumberTlv.getTlvLength() + userHomePhoneNumberTlv.TLV_HEADER_SIZE;
        metaData.add(userHomePhoneNumberTlv);        
        
        Tlv userHomeFaxNumberTlv = new Tlv(META_TLV_USER_HOME_FAX);
        sstring(userHomeFaxNumberTlv, userHomeFaxNumber);
        dataLenght += userHomeFaxNumberTlv.getTlvLength() + userHomeFaxNumberTlv.TLV_HEADER_SIZE;
        metaData.add(userHomeFaxNumberTlv); 
        
        Tlv userHomeCellularPhoneNumberTlv = new Tlv(META_TLV_USER_HOME_CELLULAR_PHONE);
        sstring(userHomeCellularPhoneNumberTlv, userHomeCellularPhoneNumber);
        dataLenght += userHomeCellularPhoneNumberTlv.getTlvLength() + userHomeCellularPhoneNumberTlv.TLV_HEADER_SIZE;
        metaData.add(userHomeCellularPhoneNumberTlv);

        Tlv userWorkStreetAddressTlv = new Tlv(META_TLV_USER_WORK_STREET_ADDRESS);
        sstring(userWorkStreetAddressTlv, userWorkStreetAddress);
        dataLenght += userWorkStreetAddressTlv.getTlvLength() + userWorkStreetAddressTlv.TLV_HEADER_SIZE;
        metaData.add(userWorkStreetAddressTlv);        

        Tlv userWorkCityNameTlv = new Tlv(META_TLV_USER_WORK_CITY_NAME);
        sstring(userWorkCityNameTlv, userWorkCityName);
        dataLenght += userWorkCityNameTlv.getTlvLength() + userWorkCityNameTlv.TLV_HEADER_SIZE;
        metaData.add(userWorkCityNameTlv);         

        Tlv userWorkStateNameTlv = new Tlv(META_TLV_USER_WORK_STATE_NAME);
        sstring(userWorkStateNameTlv, userWorkStateName);
        dataLenght += userWorkStateNameTlv.getTlvLength() + userWorkStateNameTlv.TLV_HEADER_SIZE;
        metaData.add(userWorkStateNameTlv);        

        Tlv tlvWorkCountryCode = new Tlv(META_TLV_USER_WORK_COUNTRY_CODE);
        uint16(tlvWorkCountryCode, userWorkCountryCode == null ? 0 : userWorkCountryCode.getCountry());
        dataLenght += tlvWorkCountryCode.getTlvLength() + tlvWorkCountryCode.TLV_HEADER_SIZE;   
        metaData.add(tlvWorkCountryCode);              

        Tlv tlvWorkZipCode = new Tlv(META_TLV_USER_WORK_ZIP_CODE); 
        uint32(tlvWorkZipCode, userWorkZipCode == -1 ? 0x00 : userWorkZipCode);
        dataLenght += tlvWorkZipCode.getTlvLength() + tlvWorkZipCode.TLV_HEADER_SIZE;   
        metaData.add(tlvWorkZipCode);         

        Tlv userWorkPhoneNumberTlv = new Tlv(META_TLV_USER_WORK_PHONER);
        sstring(userWorkPhoneNumberTlv, userWorkPhoneNumber);
        dataLenght += userWorkPhoneNumberTlv.getTlvLength() + userWorkPhoneNumberTlv.TLV_HEADER_SIZE;
        metaData.add(userWorkPhoneNumberTlv);         

        Tlv userWorkFaxNumbereTlv = new Tlv(META_TLV_USER_WORK_FAX);
        sstring(userWorkFaxNumbereTlv, userWorkFaxNumbere);
        dataLenght += userWorkFaxNumbereTlv.getTlvLength() + userWorkFaxNumbereTlv.TLV_HEADER_SIZE;
        metaData.add(userWorkFaxNumbereTlv);         
   
        Tlv userWorkWebPageTlv = new Tlv(META_TLV_USER_WORK_WEBPAGE);
        sstring(userWorkWebPageTlv, userWorkWebPage);
        dataLenght += userWorkWebPageTlv.getTlvLength() + userWorkWebPageTlv.TLV_HEADER_SIZE;
        metaData.add(userWorkWebPageTlv); 
       
        Tlv userWebStatusTlv = new Tlv(META_TLV_USER_WEB_STATUS);
        uint8(userWebStatusTlv, userWebStatus == -1 ? 0x00 : userWebStatus);
        dataLenght += userWebStatusTlv.getTlvLength() + userWebStatusTlv.TLV_HEADER_SIZE;   
        metaData.add(userWebStatusTlv); 
        
        Tlv userAuthorizationTlv = new Tlv(META_TLV_USER_AUTHORIZATION);
        uint8(userAuthorizationTlv, userAuthorization == -1 ? 0x00 : userAuthorization);
        dataLenght += userAuthorizationTlv.getTlvLength() + userAuthorizationTlv.TLV_HEADER_SIZE;   
        metaData.add(userAuthorizationTlv);         
        
        Tlv userGMTTlv = new Tlv(META_TLV_USER_GMT_OFFSET);
        uint8(userGMTTlv, userGMT == null ? 0x00 : userGMT.getTimeZone());
        dataLenght += userGMTTlv.getTlvLength() + userGMTTlv.TLV_HEADER_SIZE;   
        metaData.add(userGMTTlv); 
        
        Tlv userOriginallyCityTlv = new Tlv(META_TLV_USER_ORIGINALLY_CITY);
        sstring(userOriginallyCityTlv, userOriginallyCity);
        dataLenght += userOriginallyCityTlv.getTlvLength() + userOriginallyCityTlv.TLV_HEADER_SIZE;
        metaData.add(userOriginallyCityTlv);         
        
        Tlv userOriginallyStateTlv = new Tlv(META_TLV_USER_ORIGINALLY_STATE);
        sstring(userOriginallyStateTlv, userOriginallyState);
        dataLenght += userOriginallyStateTlv.getTlvLength() + userOriginallyStateTlv.TLV_HEADER_SIZE;
        metaData.add(userOriginallyStateTlv);         
        
        Tlv userOriginallyCountryCodeTlv = new Tlv(META_TLV_USER_ORIGINALLY_COUNTRY_CODE);
        uint16(userOriginallyCountryCodeTlv, userOriginallyCountryCode == null ? 0x00 : userOriginallyCountryCode.getCountry());        
        dataLenght += userOriginallyCountryCodeTlv.getTlvLength() + userOriginallyCountryCodeTlv.TLV_HEADER_SIZE;   
        metaData.add(userOriginallyCountryCodeTlv);     
        
        return metaData;
    }
    
    private void uint32(Tlv tlv, int a){
        tlv.addTlvData(DataWork.putDWordLE(a));
    }
    
   /**
     * uint16
     * @param tlv
     * @param a 
     */
    
   private void uint16(Tlv tlv, int a){
        tlv.addTlvData(DataWork.putWordLE(a));
   }  
   
   /**
    * uint8
    * @param tlv
    * @param a 
    */
   
   private void uint8(Tlv tlv, int a){
       tlv.addTlvData(DataWork.putByte(a)); 
   }      
    
    /**
     * uint16 value (year), uint16 - month, uint16 - day
     * @param tlvType
     * @param a
     * @param b
     * @param c
     */
    
    private void bcombo(Tlv tlv, int[] a){  
        //uint16 value (year)
        uint16(tlv, a == null ? 0x00 : a[0]);
        //uint16 - month
        uint16(tlv, a == null ? 0x00 : a[1]);
        //uint16 - day
        uint16(tlv, a == null ? 0x00 : a[2]);
    }
    
    /**
     * icombo - uint16 value (category) and then sstring (keywords)
     * @param tlvType
     * @param a
     * @param b
     */
    private void icombo(Tlv tlv, int b, String a){ 
        //uint16 value
        uint16(tlv, b);
        //then sstring (keywords)
        sstring(tlv, a);               
    }
    
    /**
     * uint16 value (length) and then asciiz string
     * @param tlvType
     * @param a
     * @param lengthOf 
     */
    
    private void sstring(Tlv tlv, String a){
        if(a != null){
            byte[] data = StringUtil.bytesOfStringCP_1251(a);       
            //uint16 value (length)
            uint16(tlv, data.length + 1);
            //asciiz string     
            tlv.addTlvData(DataWork.putArray(data));        
            tlv.addTlvData(DataWork.putByte(0x00));   
        }else{
            tlv.addTlvData(DataWork.putWordLE(0x00));
            tlv.addTlvData(DataWork.putWordLE(0x00));
        }    
    }
    
    /**
     * two uint16 values
     * @param tlvType
     * @param a
     * @param b
     */
    
    private void acombo (Tlv tlv, int[] a){ 
            uint16(tlv, a == null ? 0x00 : a[0]);   
            uint16(tlv, a == null ? 0x00 : a[1]);  
    }
    
    /**
     * ecombo - sstring and uint8 value (email permission)
     * @param tlvType
     * @param a
     * @param b
     */
    private void ecombo (Tlv tlv, String a, int b){       
        //sstring
        sstring(tlv, a);
        //uint8 value       
        uint8(tlv, b);       
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
     * @param emailPermission  
     */
    public void setUserEmail(String userEmail, boolean emailPermission) {
        this.userEmail = userEmail;
        if(emailPermission){
            this.emailPermission = 0x01;
        }
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
     * @param userWorkOcupationCode the userOcupationCode to set
     */
    public void setUserWorkOcupationCode(MetaOccupationConstants userWorkOcupationCode) {
        this.userWorkOcupationCode = userWorkOcupationCode;
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
    public void setSearchOnlyOnline(boolean searchOnlyOnline) {
        if(searchOnlyOnline){
            this.searchOnlyOnline = 0x01;
        } else {
            this.searchOnlyOnline = 0x00;
    }
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
    public void setUserHomeZipCode(int userHomeZipCode) {
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
    public void setUserHomeFaxNumber(String userHomeFaxNumber) {
        this.userHomeFaxNumber = userHomeFaxNumber;
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
    public void setUserWorkStreetAddress(String userWorkStreetAddress) {
        this.userWorkStreetAddress = userWorkStreetAddress;
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
    public void setUserWorkZipCode(int userWorkZipCode) {
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
