
package ru.oscar.icq.packet.parse.generic;

import ru.oscar.core.Connect;
import ru.oscar.command.DefaultCommand;
import ru.oscar.icq.packet.send.generic.RequestFamilies;

/**
 * Snac (1, 3)
 * Это — самый первый SNAC, который посылает сервер клиенту.
 * SNAC содержит массив из групп, поддерживаемых сервером. 
 * @author Kornackiy Alexsandr
 */

public class SnacFamiliesList extends DefaultCommand{
    
    public SnacFamiliesList(){
        super();
    }

    @Override
    public void notify(Connect connect) {
        //Client ask for services version numbers
        connect.sendPacket(new RequestFamilies());
    }

       
}
