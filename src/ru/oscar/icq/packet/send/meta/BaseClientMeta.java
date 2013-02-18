
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

public abstract class BaseClientMeta extends Flap {
	
    protected Snac snac;
    protected Tlv tlv;
	
    public BaseClientMeta(int lenght, String snForRequest, int type, int request) {
        super(CHANNEL2);
               
        snac = new Snac(0x15, 0x02, 0x00, 0x00, request);	

        // Creating TLV 
        tlv = new Tlv(0x01);

        //data size
        tlv.addTlvData(DataWork.putWordLE(lenght));

        //request owner uin (LE)
        tlv.addTlvData(DataWork.putDWordLE(Integer.parseInt(snForRequest)));
        
        //request type (LE)
        tlv.addTlvData(DataWork.putWordLE(type));
        
        //request sequence number 
        tlv.addTlvData(DataWork.putWordLE(0x0002));		
    }
    
    public BaseClientMeta(int lenght, String snForRequest, int type) {
        this(lenght, snForRequest, type, 0x00);
    }
    
    public BaseClientMeta(int lenght, String snForRequest, int type, int subType, int request) {
        this(lenght, snForRequest, type, request);

        //request sybtype (LE)
        tlv.addTlvData(DataWork.putWordLE(subType));		
    }

    public void finalizePacket() {
        snac.addTlv(tlv);
        addSnac(snac);
    }
}