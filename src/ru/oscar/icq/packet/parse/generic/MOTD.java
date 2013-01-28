
package ru.oscar.icq.packet.parse.generic;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.icq.constants.MotdConstants;
import ru.oscar.core.Connect;
import ru.oscar.command.DefaultCommand;

/**
 * Snac(1, 13)
 * Сообщения дня.
 * @author Kornackiy Alexsandr
 */
public class MOTD extends DefaultCommand{    
    
    private int type; 
    
    public MOTD(){
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
        if(connect.getOptionsConnect().isDebug()){
            System.out.println(new MotdConstants(type).toString());
        }
    }
    

}
