
package ru.oscar.icq.packet.parse.generic;

import ru.oscar.core.Connect;
import ru.oscar.command.DefaultCommand;
import ru.oscar.icq.packet.send.generic.RequesRateLimits;

/**
 * Snac(1, 18)
 * Содержит информацию о том, какие группы протокола поддерживает сервер.
 * @author Kornackiy Alexsandr
 */
public class FamiliesVersions extends DefaultCommand{
    
    public FamiliesVersions(){
        super();
    }
    
    @Override
    public void notify(Connect connect) {
        //Server sends its services version numbers
        connect.sendPacket(new RequesRateLimits());
    }    
}
