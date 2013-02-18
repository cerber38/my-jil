
package ru.oscar.icq.packet.parse.meta;

import java.util.EventListener;
import java.util.EventObject;
import ru.oscar.DataWork;
import ru.oscar.icq.events.MetaShortUserInfoEvent;
import ru.oscar.icq.listener.MetaInfoListener;
import ru.oscar.util.StringUtil;

/**
 * @author Kornackiy Alexsandr
 */
public class ShortUserInfoParser extends BaseMetaInfoParser {

    private String nickName;
    private String firstName;
    private String lastName;
    private String email;
    private boolean authFlag;

    protected EventObject getNewEvent() {
        return new MetaShortUserInfoEvent(this);
    }

    protected void sendMessage(EventListener listener, EventObject e) {
        ((MetaInfoListener) listener).onShortUserInfo((MetaShortUserInfoEvent) e);
    }

    public void parse(byte[] data, int index, int request){
        // success byte
        index++;
        
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
        email =  StringUtil.stringOfBytes(data, index, emailNameLen - 1);
        index += emailNameLen; 

        // Auth Flag
        authFlag = (DataWork.getByte(data, index) == 0); 
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

    public boolean isAuthFlag() {
        return authFlag;
    }
}
