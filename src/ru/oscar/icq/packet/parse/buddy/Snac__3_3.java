
package ru.oscar.icq.packet.parse.buddy;

import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;
import ru.oscar.icq.packet.send.icbm.Snac__4_4;

/**
 * Snac (3, 3)
 * Ответ на Snac (3, 2).
 * @author Kornackiy Alexsandr
 */

public class Snac__3_3 extends DefaultCommand{
    
    public Snac__3_3(){
        super();
    }

    @Override
    public void notify(Connect connect) {
        connect.sendPacket(new Snac__4_4());
    }
       
}