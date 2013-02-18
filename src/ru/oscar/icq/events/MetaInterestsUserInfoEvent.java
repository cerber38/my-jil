
package ru.oscar.icq.events;

import java.util.EventObject;
import java.util.HashMap;
import ru.oscar.icq.constants.MetaInterestsConstants;
import ru.oscar.icq.packet.parse.meta.InterestsUserInfoParser;

public class MetaInterestsUserInfoEvent extends EventObject {

    private static final long serialVersionUID = -5349542463432171361L;

    public MetaInterestsUserInfoEvent(InterestsUserInfoParser source) {
        super(source);
    }

    public HashMap<MetaInterestsConstants, String> getInterests() {
        return ((InterestsUserInfoParser) getSource()).getInterests();
    }
}
