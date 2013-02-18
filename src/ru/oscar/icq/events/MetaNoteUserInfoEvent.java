
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.meta.NotesUserInfoParser;

public class MetaNoteUserInfoEvent extends EventObject {

    public MetaNoteUserInfoEvent(NotesUserInfoParser source) {
            super(source);
    }
	
    public String getNote() {
            return ((NotesUserInfoParser) getSource()).getNote();
    }
}
