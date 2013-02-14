
package ru.oscar.icq.packet.send.meta;

import java.util.ArrayList;
import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.Tlv;

/**
 * Snac(15, 2)
 * Цепь tlv с параметрами для полной смены инфы
 * @author Kornackiy Alexsandr
 */

public class SetFullInfo extends Flap {
    
    public SetFullInfo(String sn, BlockMetaData metaData) {
        super(CHANNEL2);
        
        ArrayList<Tlv> md =  metaData.getData();
        
        Snac snac = new Snac(0x15, 0x02, 0x00, 0x00, 0x00);         
        
        //TLV.Type(1) - encapsulated META_DATA
        Tlv tlv = new Tlv(0x01);           
        
        //data chunk size (TLV.Length-2)
        tlv.addTlvData(DataWork.putWordLE(metaData.getDataLenght() + 10));
        
        //request owner uin (LE)
        tlv.addTlvData(DataWork.putDWordLE(Integer.parseInt(sn)));
        
        //request type (LE)
        tlv.addTlvData(DataWork.putWordLE(CLIENT_ADVANCED_META));
        
        //request sequence number 
        tlv.addTlvData(DataWork.putWordLE(0x0000));    
        
        //request sybtype (LE)
        tlv.addTlvData(DataWork.putWordLE(SAVE_INFO_TLV_BASED));        
        
        //tlv-based
        for(Tlv t : md){
            //tlv.addTlvtoTlv(t); 
            // TODO: type and tlv lenght LE
            // create tlv LE
            tlv.addTlvData(DataWork.putWordLE(t.getTlvType()));
            tlv.addTlvData(DataWork.putWordLE(t.getTlvLength()));
            tlv.addTlvData(DataWork.putArray(t.getDataArray()));
        }
        
        snac.addTlv(tlv);
        addSnac(snac);        
    }    
            
}
