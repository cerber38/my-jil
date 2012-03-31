
package ru.oscar.icq.packet.send.ssi;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.constants.PrivacyStatusConstants;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.util.Util;

/**
 * snac (13, 9)
 * Установим приватный статус
 * @author Kornackiy Alexsandr
 */
public class SendPrivacyStatus extends Flap {

    public SendPrivacyStatus(PrivacyStatusConstants privateStatus, int itemID){
        super(CHANNEL2);
        
        System.out.println("Send privacy setting: status:" + privateStatus.toString() + "\nItemID: " + itemID);
        
        Snac snac = new Snac(0x13, 0x09, 0x00, 0x00, 0x09);
        //Length of the item name
        snac.addSnacData(DataWork.putWord(0x0000));
        //Item name string
        //...
        //Group ID#        
        snac.addSnacData(DataWork.putWord(0x0000));
        //Item ID#
        snac.addSnacData(DataWork.putWord(itemID));
        //Type of item flag (see list bellow)
        snac.addSnacData(DataWork.putWord(0x0004));
        //0x0005 data len
        snac.addSnacData(DataWork.putWord(0x0005));
        //Privacy Settings (0x00ca)
        Tlv tlv = new Tlv(0x00ca);
        tlv.addTlvData(DataWork.putByte(privateStatus.getCode()));       
        snac.addTlv(tlv);
        
        addSnac(snac);
    }
          
}
