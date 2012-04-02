
package ru.oscar.icq.packet.send.meta;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.constants.MetaSubTypeConstants;
import ru.oscar.icq.constants.MetaTypeConstants;

/**
 * Шапка запроса/изменения мета данных
 * snac (15, 2)
 * @author Kornackiy Alexsandr
 */

public abstract class ClientMeta extends Flap implements MetaTypeConstants, MetaSubTypeConstants {
    
    protected Snac snac;
    protected Tlv tlv;       
    
    public ClientMeta(int lenght, String sn, int type){
        super(CHANNEL2);
        
        snac = new Snac(0x15, 0x02, 0x00, 0x00);    
        
        //TLV.Type(1) - encapsulated META_DATA
        tlv = new Tlv(0x01);
        
        //TLV.Length
        tlv.addTlvData(DataWork.putWord(lenght));
        
        //request owner uin (LE)
        tlv.addTlvData(DataWork.putDWordLE(Integer.parseInt(sn)));
        
        //request type (LE)
        tlv.addTlvData(DataWork.putWordLE(type));
        
        //request sequence number 
        tlv.addTlvData(DataWork.putWordLE(0x0000));
    }
    
    public ClientMeta(int lenght, String sn, int type, int subType){
        this(lenght, sn, type);
        
        //request sybtype (LE)
        tlv.addTlvData(DataWork.putWordLE(subType));
    }
    
    public void finalizePacket(int request) {
        snac.addTlv(tlv);
        snac.finishHeader(request);
        addSnac(snac);
    }    
    
    public void finalizePacket() {
        snac.addTlv(tlv);
        addSnac(snac);
    }        
    
}