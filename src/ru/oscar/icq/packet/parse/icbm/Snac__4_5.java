
package ru.oscar.icq.packet.parse.icbm;

import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;
import ru.oscar.icq.packet.send.icbm.Snac__4_2;
import ru.oscar.icq.packet.send.privacy.Snac__9_2;

/**
 * Snca (4, 5)
 * Ответ на SNAC 4,4
 * @author Kornackiy Alexsandr
 */

public class Snac__4_5 extends DefaultCommand{
    
    public Snac__4_5(){
        super();
    }

    @Override
    public void notify(Connect connect) {
        connect.sendPacket(new Snac__4_2(connect));
        connect.sendPacket(new Snac__9_2());
        
    }
}
