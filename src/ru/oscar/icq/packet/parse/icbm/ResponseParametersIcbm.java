
package ru.oscar.icq.packet.parse.icbm;

import ru.oscar.core.Connect;
import ru.oscar.command.DefaultCommand;
import ru.oscar.icq.packet.send.icbm.SetIcbmParameters;
import ru.oscar.icq.packet.send.privacy.RequestParametersPrivacy;

/**
 * Snac (4, 5)
 * Ответ на SNAC 4,4
 * @author Kornackiy Alexsandr
 */

public class ResponseParametersIcbm extends DefaultCommand{
    
    public ResponseParametersIcbm(){
        super();
    }

    @Override
    public void notify(Connect connect) {
        connect.sendPacket(new SetIcbmParameters(connect));
        connect.sendPacket(new RequestParametersPrivacy());
        
    }
}
