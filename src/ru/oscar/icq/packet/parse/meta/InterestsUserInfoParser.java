
package ru.oscar.icq.packet.parse.meta;

import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import ru.oscar.DataWork;
import ru.oscar.icq.constants.MetaInterestsConstants;
import ru.oscar.icq.events.MetaInterestsUserInfoEvent;
import ru.oscar.icq.listener.MetaInfoListener;
import ru.oscar.util.StringUtil;

/**
 * @author Kornackiy Alexsandr
 */

public class InterestsUserInfoParser extends BaseMetaInfoParser {

    private HashMap<MetaInterestsConstants, String> interests;

    protected EventObject getNewEvent() {
        return new MetaInterestsUserInfoEvent(this);
    }

    protected void sendMessage(EventListener listener, EventObject e) {
        ((MetaInfoListener) listener).onInterestsUserInfo((MetaInterestsUserInfoEvent) e);
    }

    public void parse(byte[] data, int index, int request){
        interests = new HashMap<MetaInterestsConstants, String>();
        //success byte
        index++;

        //number of past cat (allways 3)
        int len = DataWork.getByte(data, index);
        index++;

        for (int i = 0; i < len; i++){
            // interests code
            int interestCode = DataWork.getWordLE(data, index);
            index += 2;

            // interests length
            int interestLen = DataWork.getWordLE(data, index);
            index += 2;

            // interests
            String interest = StringUtil.byteArrayWin1251ToString(data, index, interestLen - 1);
            index += interestLen;

            interests.put(new MetaInterestsConstants(interestCode), interest);
        }
    }

    public HashMap<MetaInterestsConstants, String> getInterests() {
        return interests;
    }
}
