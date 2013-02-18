
package ru.oscar.icq.packet.parse.meta;

import java.util.EventListener;
import java.util.EventObject;
import ru.oscar.DataWork;
import ru.oscar.icq.events.MetaNoteUserInfoEvent;
import ru.oscar.icq.listener.MetaInfoListener;
import ru.oscar.util.StringUtil;

/**
 * @author Kornackiy Alexsandr
 */

public class NotesUserInfoParser extends BaseMetaInfoParser {

    private String note;

    protected EventObject getNewEvent() {
        return new MetaNoteUserInfoEvent(this);
    }

    protected void sendMessage(EventListener listener, EventObject e) {
    	((MetaInfoListener) listener).onNotesUserInfo((MetaNoteUserInfoEvent) e);
    }

    public void parse(byte[] data, int index, int request){
        // success byte
        index++;
        // Note string lenght
        int rStrLen = DataWork.getWordLE(data, index);
        index += 2;

        // Note String
        note = StringUtil.byteArrayWin1251ToString(data, index, rStrLen - 1);
    }

    public String getNote() {
        return note;
    }
}
