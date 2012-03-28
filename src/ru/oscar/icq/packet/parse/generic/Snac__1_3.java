
package ru.oscar.icq.packet.parse.generic;

import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;
import ru.oscar.icq.packet.send.generic.Snac__1_17;

/**
 * Snac (1, 3)
 * Это — самый первый SNAC, который посылает сервер клиенту.
 * SNAC содержит массив из групп, поддерживаемых сервером. 
 * @author Kornackiy Alexsandr
 */

public class Snac__1_3 extends DefaultCommand{
    
    public Snac__1_3(){
        super();
    }

    @Override
    public void notify(Connect connect) {
        connect.sendPacket(new Snac__1_17());
    }

       
}
