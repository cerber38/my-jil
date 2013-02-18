
package ru.oscar.icq.packet.parse.meta;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import ru.oscar.DataWork;
import ru.oscar.icq.events.MetaEmailUserInfoEvent;
import ru.oscar.icq.listener.MetaInfoListener;
import ru.oscar.util.StringUtil;

/**
 * @author Kornackiy Alexsandr
 */
public class EmailUserInfoParser extends BaseMetaInfoParser {

	private List emails = new ArrayList();

    protected EventObject getNewEvent() {
        return new MetaEmailUserInfoEvent(this);
    }


    protected void sendMessage(EventListener listener, EventObject e) {
        ((MetaInfoListener) listener).onEmailUserInfo((MetaEmailUserInfoEvent) e);
    }


    public void parse(byte[] data, int index, int request){
        // success byte
        index++;
        
        int len = DataWork.getByte(data, index);
        index++;

        for (int i = 0; i < len; i++) {
            // Skiping publish byte
            index++;

            // Email lenght
            int rStrLen = DataWork.getWordLE(data, index);
            index += 2;

            // Email
            String email = StringUtil.utf8ByteArrayToString(data, index, rStrLen - 1);
            index += rStrLen;
            emails.add(email);
        }
    }

    public List getEmails() {
        return emails;
    }
}
