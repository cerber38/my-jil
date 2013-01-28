
package ru.oscar.icq.packet.parse.privacy;

import ru.oscar.core.Connect;
import ru.oscar.command.DefaultCommand;
import ru.oscar.icq.packet.send.ssi.RequestParametersSsi;

/**
 * Snac (9, 3)
 * Ответ на SNAC 9,2
 * @author Kornackiy Alexsandr
 */

public class ResponseParametersPrivacy extends DefaultCommand{   
    
    public ResponseParametersPrivacy(){
        super();
    }
    @Override
    public void notify(Connect connect) {
        connect.sendPacket(new RequestParametersSsi());
    }
}