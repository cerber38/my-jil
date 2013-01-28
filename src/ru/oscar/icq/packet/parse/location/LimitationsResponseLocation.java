
package ru.oscar.icq.packet.parse.location;

import ru.oscar.core.Connect;
import ru.oscar.command.DefaultCommand;
import ru.oscar.icq.packet.send.buddy.RequestLimitationsBuddy;


/**
 * Snac (2, 3)
 * Овет на 2,2
 * @author Kornackiy Alexsandr
 */

public class LimitationsResponseLocation extends DefaultCommand{
    
    public LimitationsResponseLocation(){
        super();
    }

    @Override
    public void notify(Connect connect) {
        //Client ask server BLM service limitations
        connect.sendPacket(new RequestLimitationsBuddy());
    }

       
}
