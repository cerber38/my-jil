
package ru.oscar.icq.packet;

import java.util.HashMap;
import java.util.Map;
import ru.oscar.icq.core.cmd.Command;
import ru.oscar.icq.packet.parse.buddy.Snac__3_3;
import ru.oscar.icq.packet.parse.generic.Snac__1_0F;
import ru.oscar.icq.packet.parse.generic.Snac__1_13;
import ru.oscar.icq.packet.parse.generic.Snac__1_15;
import ru.oscar.icq.packet.parse.generic.Snac__1_18;
import ru.oscar.icq.packet.parse.generic.Snac__1_3;
import ru.oscar.icq.packet.parse.generic.Snac__1_7;
import ru.oscar.icq.packet.parse.icbm.Snac__4_11;
import ru.oscar.icq.packet.parse.icbm.Snac__4_5;
import ru.oscar.icq.packet.parse.icbm.Snac__4_7;
import ru.oscar.icq.packet.parse.location.Snac__2_3;
import ru.oscar.icq.packet.parse.meta.Snac__15_3;
import ru.oscar.icq.packet.parse.privacy.Snac__9_3;
import ru.oscar.icq.packet.parse.ssi.Snac__13_0E;
import ru.oscar.icq.packet.parse.ssi.Snac__13_15;
import ru.oscar.icq.packet.parse.ssi.Snac__13_19;
import ru.oscar.icq.packet.parse.ssi.Snac__13_1B;
import ru.oscar.icq.packet.parse.ssi.Snac__13_3;
import ru.oscar.icq.packet.parse.ssi.Snac__13_6;

/**
 * У каждого принятого SNAC'а свой класс-парсер.
 * Запоминаем их, и вызываем по типу и подтипу.
 * @author Kornackiy Alexsandr
 */

public class SnacBuilder extends ClassLoader{
    
    private Map<String, String> cmd;  
        
    public SnacBuilder(){
        builder();
    }
    
    private void builder(){
        cmd = new HashMap<String, String>();      
        cmd.put("1_3", new Snac__1_3().getClass().getName());
        cmd.put("1_7", new Snac__1_7().getClass().getName());
        cmd.put("1_15", new Snac__1_0F().getClass().getName());
        cmd.put("1_19", new Snac__1_13().getClass().getName());
        cmd.put("1_21", new Snac__1_15().getClass().getName());
        cmd.put("1_24", new Snac__1_18().getClass().getName());
        cmd.put("2_3", new Snac__2_3().getClass().getName());
        cmd.put("3_3", new Snac__3_3().getClass().getName());
        cmd.put("4_5", new Snac__4_5().getClass().getName());
        cmd.put("4_7", new Snac__4_7().getClass().getName()); 
        cmd.put("4_11", new Snac__4_11().getClass().getName()); 
        cmd.put("9_3", new Snac__9_3().getClass().getName());
        cmd.put("19_3", new Snac__13_3().getClass().getName());   
        cmd.put("19_6", new Snac__13_6().getClass().getName()); 
        cmd.put("19_14", new Snac__13_0E().getClass().getName());
        cmd.put("19_21", new Snac__13_15().getClass().getName());
        cmd.put("19_27", new Snac__13_1B().getClass().getName());
        cmd.put("19_25", new Snac__13_19().getClass().getName());
        cmd.put("21_3", new Snac__15_3().getClass().getName());         
        // 1 10 rate limit
        // 4 12 ask
    }    
    
    public Command getCommand(String command) { 
        Command c = null;
        if(!cmd.containsKey(command)){
            return c;
        } 
        try {
            c = (Command) Class.forName(cmd.get(command)).getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Error load class: " + e.getMessage());
        }
        return c;
    }
 
}
