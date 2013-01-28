
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.DataWork;
import ru.oscar.Tlv;
import ru.oscar.icq.constants.PrivacyStatusConstants;

/**
 * snac (13, 9)
 * Установим приватный статус
 * Установится тока с загрузкой кл
 * @author Kornackiy Alexsandr
 */
public class PrivacyStatus extends SsiUpdate {

    public PrivacyStatus(PrivacyStatusConstants privateStatus, int itemID, boolean debug){
        super(itemID);
        
        if(debug){
            System.out.println("Send private status: code= " + privateStatus.getCode() +
                    "\nitemID= " + itemID);
        }
        
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(TYPE_VISIBILITY));
        //0x0005 data len
        snac.addSnacData(DataWork.putWord(0x0005));
        //Privacy Settings (0x00ca)
        Tlv tlv = new Tlv(0x00ca);
        tlv.addTlvData(DataWork.putByte(privateStatus.getCode()));       
        snac.addTlv(tlv);
        
        finalizePacket();
    }
          
}
