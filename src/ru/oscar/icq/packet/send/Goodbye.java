
package ru.oscar.icq.packet.send;

import ru.oscar.Flap;

/**
 * Прощаемся с сервером
 * @author Kornackiy Alexsandr
 */

public class Goodbye extends Flap {
    
    public Goodbye(){
        super(CHANNEL4);
    }
}
