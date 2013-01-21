
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.contacts.Group;


/**
 * Добавить группу в контакт лист на сервере
 * @author Kornackiy Alexsandr
 */
public class AddsGroup extends Snac__13_8 {
    
    public AddsGroup(Group g){
        super(g.getName());
        
        //Group ID#
        snac.addSnacData(DataWork.putWord(g.getIdGroup()));
        //Item ID#
        snac.addSnacData(DataWork.putWord(0x00));
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(TYPE_GROUP)); 
        //len data
        snac.addSnacData(DataWork.putWord(0x00)); 
        
        finalizePacket();
    }
    
}
