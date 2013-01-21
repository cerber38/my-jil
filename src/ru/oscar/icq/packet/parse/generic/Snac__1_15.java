
package ru.oscar.icq.packet.parse.generic;

import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;

/**
 * Не понятный набор ссылок
 * Snac (1, 15)
 * @author Kornackiy Alexsandr
 */
public class Snac__1_15 extends DefaultCommand{
    
    public Snac__1_15(){
        super();
    }
    
    @Override
    public void notify(Connect connect) {
    }    
}