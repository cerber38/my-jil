
package ru.oscar.icq.packet.parse.generic;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.constants.ErrorConstants;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;

/**
 * Snca (4, 1)
 * Ошибка в группе generic.
 * @author Kornackiy Alexsandr
 */

public class Snac__1_1 extends DefaultCommand{
    
    private int code;
    
    public Snac__1_1(){
        super();
    }

    @Override
    public void exec(Flap f) {
        // 00 00
        
        // error code 
        code = DataWork.getWord(f.getDataArray(), 2);
    }

    @Override
    public void notify(Connect connect) {
        System.out.println(new ErrorConstants(code).toString());
    }
}