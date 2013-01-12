
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.Flap;
import ru.oscar.icq.constants.SsiConstants;
import ru.oscar.icq.contacts.Contact;
import ru.oscar.icq.contacts.Group;
import ru.oscar.icq.core.Connect;

/**
 * Отправка ssi данных на сервер
 * Snac (13, 8)
 * Snac (13, 9)
 * Snac (13, A)
 * @author Kornackiy Alexsandr
 */
public class SsiService implements SsiConstants {
    
    private Connect con;
    private Contact c;
    private Group g;
    
    private int type;
    
    public SsiService(Connect con, Contact c, int type){
        this.con = con;
        this.c = c;
        this.g = null;
        
        this.type = type;
    }
    
    public SsiService(Connect con, Group g, int type){
        this.con = con;
        this.c = null;
        this.g = g;
        this.type = type;
    }    
    
    public void init(){
        Flap f;
        
        if(type != SSI_UPDATE){
            con.sendPacket(new Snac__13_11());
        }
        
        switch(type){
            case SSI_ADD:
                f = (c != null) ? new Snac__13_8(c) : new Snac__13_8(g);
                con.sendPacket(f);
                break;   
             case SSI_REMOVE:
                f = (c != null) ? new Snac__13_0A(c) : new Snac__13_0A(g);
                con.sendPacket(f);                 
                break;
             case SSI_UPDATE: 
                f = (c != null) ? new Snac__13_9(c) : new Snac__13_9(g);
                con.sendPacket(f);   
                break;
             case SSI_MOVE: 
                 
                 break;                 
        }
        
        if(type != SSI_UPDATE){
            con.sendPacket(new Snac__13_12());
        }        
    }
    
}
