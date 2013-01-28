
package ru.oscar.icq.packet.send;

import ru.oscar.Flap;

/**
 * Поддержка соединения
 * @author Kornackiy Alexsandr
 */

public class KeepAlive extends Flap {
    
    public KeepAlive(){
        super(CHANNEL5);
    }
}

