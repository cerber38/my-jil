
package ru.oscar.icq.packet.parse.generic;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.constants.MotdConstants;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;

/**
 * Snac(1, 13)
 * Сообщения дня.
 * @author Kornackiy Alexsandr
 */
public class Snac__1_13 extends DefaultCommand{    
    
    private int type; 
    
    public Snac__1_13(){
        super();
    }
    
    @Override
    public void exec(Flap f) {
        int index = 0;   
        byte data[] = f.getDataArray();
        
        type = DataWork.getWord(data, index);
    }

    @Override
    public void notify(Connect connect) {
        System.out.println(new MotdConstants(type).toString());
    }
    

}
