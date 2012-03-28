
package ru.oscar.icq.packet.send.icbm;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.util.StringUtil;

/**
 * snac (4,6)
 * Сообщение по первому каналу, простое сообщение.
 * @author Kornackiy Alexsandr
 */
public class SendMessageChannel1 extends Message {
    
    public SendMessageChannel1(String sn, String message){
        super(sn, MESSAGE_CHANNEL1);
        
        // TLV.Type(0x02) - message data
        Tlv tlv2 = new Tlv(0x02);

        Tlv tlv0501 = new Tlv(0x0501, 2, 0x0106);
        
        Tlv tlv0101 = new Tlv(0x0101);
        tlv0101.addTlvData(DataWork.putWord(0x0002));
        tlv0101.addTlvData(DataWork.putWord(0x0000));
        tlv0101.addTlvData(DataWork.putArray(StringUtil.stringToUcs2beByteArray(message)));
        
        tlv2.addTlvtoTlv(tlv0501);
        tlv2.addTlvtoTlv(tlv0101);

        snac.addTlv(tlv2);
        
        // TLV.Type(0x03) - request an ack from server
        snac.addSnacData(DataWork.putWord(0x03));
        snac.addSnacData(DataWork.putWord(0x00));
        
        // TLV.Type(0x06) - store message if recipient offline
        snac.addSnacData(DataWork.putWord(0x06));
        snac.addSnacData(DataWork.putWord(0x00));
        
        addSnac(snac);
    }
      
}
