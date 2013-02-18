
package ru.oscar.icq.events;

import java.util.EventObject;
import java.util.HashMap;
import ru.oscar.icq.constants.MetaAffilationConstants;
import ru.oscar.icq.constants.MetaPostBackgroundConstants;
import ru.oscar.icq.packet.parse.meta.AffilationsUserInfoParser;

public class MetaAffilationsUserInfoEvent extends EventObject {

    public MetaAffilationsUserInfoEvent(AffilationsUserInfoParser source) {
        super(source);
    }

    public HashMap<MetaPostBackgroundConstants, String> getPostBackgrounds() {
        return ((AffilationsUserInfoParser) getSource()).getPostBackgrounds();
    }

    public HashMap<MetaAffilationConstants, String> getAffilations() {
        return ((AffilationsUserInfoParser) getSource()).getAffilations();
    }
}
