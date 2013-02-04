
package ru.oscar.icq.packet.send.meta;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.Tlv;

/**
 * Шапка запроса
 * snac (15, 2)
 * @author Kornackiy Alexsandr
 */

public abstract class ClientMeta extends Flap{
    
    protected Snac snac;
    protected Tlv tlv;     
    
    public ClientMeta(String sn, int type, int lenght){
        super(CHANNEL2);
        
        snac = new Snac(0x15, 0x02, 0x00, 0x00);    
        
        //TLV.Type(1) - encapsulated META_DATA
        tlv = new Tlv(0x01);
        
        //TLV.Length
        tlv.addTlvData(DataWork.putWordLE(lenght));
        
        //request owner uin (LE)
        tlv.addTlvData(DataWork.putDWordLE(Integer.parseInt(sn)));
        
        //request type (LE)
        tlv.addTlvData(DataWork.putWordLE(type));
        
        //request sequence number 
        tlv.addTlvData(DataWork.putWordLE(0x0000));
    }
    
    public ClientMeta(String sn, int type, int subType, int lenght){
        this(sn, type, lenght);
        
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
