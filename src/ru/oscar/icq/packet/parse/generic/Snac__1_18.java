
package ru.oscar.icq.packet.parse.generic;

import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;
import ru.oscar.icq.packet.send.generic.Snac__1_6;

/**
 * Snac(1, 18)
 * Содержит информацию о том, какие группы протокола поддерживает сервер.
 * @author Kornackiy Alexsandr
 */
public class Snac__1_18 extends DefaultCommand{
    
    public Snac__1_18(){
        super();
    }
    
    @Override
    public void notify(Connect connect) {
        connect.sendPacket(new Snac__1_6());
    }    
}
