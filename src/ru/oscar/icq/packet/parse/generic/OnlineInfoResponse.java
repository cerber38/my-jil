
package ru.oscar.icq.packet.parse.generic;

import ru.oscar.command.DefaultCommand;

/**
 * Snac(1, 13)
 * Этот SNAC посылается после изменения статуса клиента.
 * @author Kornackiy Alexsandr
 */
public class OnlineInfoResponse extends DefaultCommand{
    
    public OnlineInfoResponse(){
        super();
    }
}