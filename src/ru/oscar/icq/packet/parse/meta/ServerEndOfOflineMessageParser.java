
package ru.oscar.icq.packet.parse.meta;

import ru.oscar.core.Connect;
import ru.oscar.icq.packet.send.meta.AckOfflineMessages;

/**
 * @author Kornackiy Alexsandr
 */

public class ServerEndOfOflineMessageParser implements IMetaInfoParser {

    public void notifyEvent(Connect connect) {
        connect.sendPacket(new AckOfflineMessages(connect.getSN()));
    }


    public void parse(byte[] data, int index, int request){
    }
}
