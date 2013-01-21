
package ru.oscar.icq.packet.parse.privacy;

import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;
//import ru.oscar.icq.packet.send.generic.Snac__1_1E;
//import ru.oscar.icq.packet.send.generic.Snac__1_2;
import ru.oscar.icq.packet.send.ssi.Snac__13_2;

/**
 * Snac (9, 3)
 * Ответ на SNAC 9,2
 * @author Kornackiy Alexsandr
 */

public class Snac__9_3 extends DefaultCommand{   
    
    public Snac__9_3(){
        super();
    }
    @Override
    public void notify(Connect connect) {
        connect.sendPacket(new Snac__13_2());
    }
}