
package ru.oscar.icq.packet.parse.meta;

import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import ru.oscar.DataWork;
import ru.oscar.icq.constants.MetaAffilationConstants;
import ru.oscar.icq.constants.MetaPostBackgroundConstants;
import ru.oscar.icq.events.MetaAffilationsUserInfoEvent;
import ru.oscar.icq.listener.MetaInfoListener;
import ru.oscar.util.StringUtil;

/**
 * @author Kornackiy Alexsandr
 */

public class AffilationsUserInfoParser extends BaseMetaInfoParser {

    private HashMap<MetaPostBackgroundConstants, String> postbackgrounds;
    private HashMap<MetaAffilationConstants, String> affilations;

    protected EventObject getNewEvent() {
        return new MetaAffilationsUserInfoEvent(this);
    }

    protected void sendMessage(EventListener listener, EventObject e) {
        ((MetaInfoListener) listener).onAffilationsUserInfo((MetaAffilationsUserInfoEvent) e);
    }


    public void parse(byte[] data, int index, int request){
        postbackgrounds = new HashMap<MetaPostBackgroundConstants, String>();
        affilations = new HashMap<MetaAffilationConstants, String>();
        
        //success byte
        index++;

        //number of past cat (allways 3)
        int len = DataWork.getByte(data, index);
        index++;

        for (int i = 0; i < len; i++){
            // Category code
            int postBackgroundCode = DataWork.getWordLE(data, index);
            index += 2;

            // PostBackground length
            int postBackgroundLen = DataWork.getWordLE(data, index);
            index += 2;

            // PostBackground
            String postBackground = StringUtil.byteArrayWin1251ToString(data, index, postBackgroundLen - 1);
            index += postBackgroundLen;

            postbackgrounds.put(new MetaPostBackgroundConstants(postBackgroundCode), postBackground);
        }

        len = DataWork.getByte(data, index);
        index++;

        for (int i = 0; i < len; i++){
            // Category code
            int affilationCode = DataWork.getWordLE(data, index);
            index += 2;            

            // Affilation length
            int affilationLen = DataWork.getWordLE(data, index);
            index += 2;            

            // Affilation
            String affilation = StringUtil.byteArrayWin1251ToString(data, index, affilationLen - 1);
            index += affilationLen;

            affilations.put(new MetaAffilationConstants(affilationCode), affilation);
        }
    }

    public HashMap<MetaPostBackgroundConstants, String> getPostBackgrounds() {
        return postbackgrounds;
    }

    public HashMap<MetaAffilationConstants, String> getAffilations() {
        return affilations;
    }

}
