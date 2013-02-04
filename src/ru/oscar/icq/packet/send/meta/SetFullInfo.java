
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
    
//2A 02 00 18  00 29 00 29  *....).)
//00 15 00 02  00 00 00 00  ........
//00 00 00 01  00 1B 19 00  ........
//ED 18 09 00  D0 07 00 00  �...�...
//3A 0C 
//54 01
//0B 00 07 00  :.T.....
//00 00 4A 49  4C 32 30 31  ..JIL201
//33                        3
    
// 00 00 02 00   i.+..eP. B..9....
//1b 00 28 00 
//15 00 02 00  00 00 00 00 01 00 01 00   ..(..... ........
//1a 18 00 ed 18 09 00 d0  07 02 00 3a 0c 
//54 01 0a   ........ ...:.T..
// 00 08 00 4a 49 4c 32 30  31 33 00                  ...JIL20 13.         
 
}
