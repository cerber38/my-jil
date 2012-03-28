
package ru.oscar.icq.packet.parse.location;

import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;
import ru.oscar.icq.packet.send.buddy.Snac__3_2;
//import ru.oscar.icq.packet.send.location.Snac__2_4;


/**
 * Snac (2, 3)
 * Овет на 2,2
 * @author Kornackiy Alexsandr
 */

public class Snac__2_3 extends DefaultCommand{
    
    public Snac__2_3(){
        super();
    }

    @Override
    public void notify(Connect connect) {
//        connect.sendPacket(new Snac__2_4(connect));
        connect.sendPacket(new Snac__3_2());
    }

       
}
