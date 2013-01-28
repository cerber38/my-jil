
package ru.oscar.icq.packet.send;

import ru.oscar.Flap;
import ru.oscar.icq.constants.ChannelsConstants;

/**
 * Поддержка соединения
 * @author Kornackiy Alexsandr
 */

public class KeepAlive extends Flap {
    
    public KeepAlive(){
        super(ChannelsConstants.CHANNEL5);
    }
}

