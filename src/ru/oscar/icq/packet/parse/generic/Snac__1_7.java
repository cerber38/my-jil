
package ru.oscar.icq.packet.parse.generic;

import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;
import ru.oscar.icq.packet.send.generic.Snac__1_8;
import ru.oscar.icq.packet.send.location.Snac__2_2;

/**
 * Snac (1, 7)
 * Первая часть SNAC содержит параметры лимитов обращений, а вторая часть содержит список SNAC в этих лимитах.
 * @author Kornackiy Alexsandr
 */

public class Snac__1_7 extends DefaultCommand{
    
    public Snac__1_7(){
        super();
    }

    @Override
    public void notify(Connect connect) {
        connect.sendPacket(new Snac__1_8());
        connect.sendPacket(new Snac__2_2());

    }

       
}
