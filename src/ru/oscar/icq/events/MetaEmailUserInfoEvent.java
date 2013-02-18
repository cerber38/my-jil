
package ru.oscar.icq.events;

import java.util.EventObject;
import java.util.List;
import ru.oscar.icq.packet.parse.meta.EmailUserInfoParser;

public class MetaEmailUserInfoEvent extends EventObject {

    public MetaEmailUserInfoEvent(EmailUserInfoParser source) {
        super(source);
    }

    public List getEmails() {
        return ((EmailUserInfoParser) getSource()).getEmails();
    }
}
