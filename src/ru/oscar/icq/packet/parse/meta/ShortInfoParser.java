
package ru.oscar.icq.packet.parse.meta;

import java.util.EventObject;
import ru.oscar.icq.DataWork;
import ru.oscar.icq.core.api.events.MetaShortInfoEvent;
import ru.oscar.icq.core.api.listener.ListenerMetaInfo;
import ru.oscar.icq.util.StringUtil;


/**
 * @author Kornackiy Alexsandr
 */

public class ShortInfoParser extends BaseMetaInfoParser {
    
    private String nickName;
    private String firstName;
    private String lastName;
    private String email;
    private boolean authFlag;    

    @Override
    protected EventObject getNewEvent() {
        return new MetaShortInfoEvent(this);
    }

    @Override
    protected void sendMessage(ListenerMetaInfo listener, EventObject e) {
        listener.onShortUserInfo((MetaShortInfoEvent) e);
    }
    
    public void parse(byte[] data, int index, int request) {
        // success byte
        index++;
        
        // Nickname lenght
        int nickNameLen = DataWork.getWordLE(data, index);
        index += 2;
        
        // Nickname
        nickName = StringUtil.utf8ByteArrayToString(data, index, nickNameLen - 1);
        index += nickNameLen;
        
        // First Name lenght
        int firstNameLen = DataWork.getWordLE(data, index);
        index += 2;

        // First Name
        firstName = StringUtil.utf8ByteArrayToString(data, index, firstNameLen - 1);
        index += firstNameLen;  
        
        // Last Name lenght
        int lastNameLen = DataWork.getWordLE(data, index);
        index += 2;

        // Last Name
        lastName = StringUtil.utf8ByteArrayToString(data, index, lastNameLen - 1);
        index += lastNameLen; 

        // Email lenght
        int emailNameLen = DataWork.getWordLE(data, index);
        index += 2;

        // Email
        email =  StringUtil.stringOfBytes(data, index, lastNameLen - 1);
        index += emailNameLen; 

        // Auth Flag
        authFlag = (DataWork.getByte(data, index) == 0);        
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
    
}
