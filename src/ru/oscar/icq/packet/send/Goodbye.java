
package ru.oscar.icq.packet.send;

import ru.oscar.Flap;
import ru.oscar.icq.constants.ChannelsConstants;

/**
 * Прощаемся с сервером
 * @author Kornackiy Alexsandr
 */

public class Goodbye extends Flap {
    
    public Goodbye(){
        super(ChannelsConstants.CHANNEL4);
    }
}
