
package ru.oscar.icq.packet.parse.buddy;

import ru.oscar.core.Connect;
import ru.oscar.command.DefaultCommand;
import ru.oscar.icq.packet.send.icbm.RequestParametersIcbm;

/**
 * Snac (3, 3) >> server 
 * Ответ на Snac (3, 2).
 * @author Kornackiy Alexsandr
 */

public class LimitationsResponseBuddy extends DefaultCommand{
    
    public LimitationsResponseBuddy(){
        super();
    }

    @Override
    public void notify(Connect connect) {
        connect.sendPacket(new RequestParametersIcbm());
    }
       
}