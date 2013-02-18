
package ru.oscar.icq.packet.parse.meta;

import java.util.EventListener;
import java.util.EventObject;
import ru.oscar.DataWork;
import ru.oscar.icq.events.MetaFindByUinUserInfoEvent;
import ru.oscar.icq.listener.MetaInfoListener;
import ru.oscar.util.StringUtil;

/**
 * @author Kornackiy Alexsandr
 */

public class FindByUinUserInfoParser extends BaseMetaInfoParser {
    
    private boolean isSearch;
    private boolean isContactCheck;
          
    private String foundUin;
    private String nickName; 
    private String firstName;
    private String lastName;
    private String email;
    private boolean authFlag;    
    
    private int onlineStatus;
    private int gender;
    private int age;    
    
    private int groupID;

    @Override
    protected EventObject getNewEvent() {
        return new MetaFindByUinUserInfoEvent(this);
    }

    @Override
    protected void sendMessage(EventListener listener, EventObject e) {
        ((MetaInfoListener) listener).onFindByUinUserInfo((MetaFindByUinUserInfoEvent) e);
    }

    public void parse(byte[] data, int index, int request){
        isContactCheck = (request != 0x00); 
    /**
     * TODO: Важно!
     * Параметр передается при добавлении контакта.
     * В качестве параметра группа контакта. (getGroupID())
     * Если он найден добавим его в эту группу.
     * Пустой параметр значит обычный пойск.
     */        
        if(isContactCheck){
            groupID = request;
        }

        // success byte
        isSearch = (DataWork.getByte(data, index) == 0x0A);  
        index++;
        
        if(!isSearch){
            return;
        }
        
        // following data size
        index += 2;
        
        // found user uin
        foundUin = Integer.toString(DataWork.getDWordLE(data, index));
        index += 4;    
        
        // Nickname lenght
        int nickNameLen = DataWork.getWordLE(data, index);
        index += 2;        

        // Nickname
        nickName = StringUtil.byteArrayWin1251ToString(data, index, nickNameLen - 1);
        index += nickNameLen;
        
        // First Name lenght
        int firstNameLen = DataWork.getWordLE(data, index);
        index += 2;

        // First Name
        firstName = StringUtil.byteArrayWin1251ToString(data, index, firstNameLen - 1);
        index += firstNameLen;  
        
        // Last Name lenght
        int lastNameLen = DataWork.getWordLE(data, index);
        index += 2;

        // Last Name
        lastName = StringUtil.byteArrayWin1251ToString(data, index, lastNameLen - 1);
        index += lastNameLen; 

        // Email lenght
        int emailNameLen = DataWork.getWordLE(data, index);
        index += 2;

        // Email
        email = emailNameLen > 1 ? StringUtil.stringOfBytes(data, index, lastNameLen - 1) : "";
        index += emailNameLen; 

        // Auth Flag
        authFlag = (DataWork.getByte(data, index) == 0);
        index++;
        
        // online status (0 - offline, 1 - online, 2 - non_webaware)
        onlineStatus = DataWork.getWordLE(data, index);
        index +=2;
        
        // gender
        gender = DataWork.getByte(data, index);
        index++;
        
        // age
        age = DataWork.getWordLE(data, index);    
                
    }
    
    /**
     * @return the foundUin
     */
    public String getFoundUin() {
        return foundUin;
    }
    
    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }    

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the authFlag
     */
    public boolean isAuthFlag() {
        return authFlag;
    }

    /**
     * @return the onlineStatus
     */
    public int getOnlineStatus() {
        return onlineStatus;
    }

    /**
     * @return the gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * @return the isSearch
     */
    public boolean isSearch() {
        return isSearch;
    }

    /**
     * @return the isContactCheck
     */
    public boolean isContactCheck() {
        return isContactCheck;
    }

}
