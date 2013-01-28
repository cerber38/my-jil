
package ru.oscar.icq.packet.parse.generic;

import ru.oscar.core.Connect;
import ru.oscar.command.DefaultCommand;
import ru.oscar.icq.packet.send.generic.AddRateLimits;
import ru.oscar.icq.packet.send.location.RequestLimitations;

/**
 * Snac (1, 7)
 * Первая часть SNAC содержит параметры лимитов обращений, а вторая часть содержит список SNAC в этих лимитах.
 * @author Kornackiy Alexsandr
 */

public class RateLimitsInfo extends DefaultCommand{
    
    public RateLimitsInfo(){
        super();
    }

    @Override
    public void notify(Connect connect) {
        //Client ask server for rate limits info
        connect.sendPacket(new AddRateLimits());
        connect.sendPacket(new RequestLimitations());

    }

       
}
