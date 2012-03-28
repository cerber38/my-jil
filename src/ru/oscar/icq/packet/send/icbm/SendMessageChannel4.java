
package ru.oscar.icq.packet.send.icbm;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.constants.MessageFlagsConstants;
import ru.oscar.icq.constants.MessageTypesConstants;
import ru.oscar.icq.util.StringUtil;

/**
 * snac (4,6)
 * Сообщение по четвертому каналу, старый тип сообщения или url.
 * @author Kornackiy Alexsandr
 */
public class SendMessageChannel4 extends Message {
    
    public SendMessageChannel4(String sn, String message, MessageTypesConstants mtype){
        super(sn, MESSAGE_CHANNEL4);
        
        Tlv tlv5 = new Tlv(0x05);
        
        // Sender uin
        tlv5.addTlvData(DataWork.putDWordLE(Integer.parseInt(sn)));
        // message type
        tlv5.addTlvData(DataWork.putByte(mtype.getCode()));
        // message flags
        tlv5.addTlvData(DataWork.putByte(MessageFlagsConstants.MESSAGE_NORMAL));       
        // Message string length
        tlv5.addTlvData(DataWork.putWordLE(message.length()));
        // Message
        tlv5.addTlvData(DataWork.putArray(StringUtil.bytesOfString(message)));
        
        snac.addTlv(tlv5);
        
        // TLV.Type(0x03) - request an ack from server
        snac.addSnacData(DataWork.putWord(0x03));
        snac.addSnacData(DataWork.putWord(0x00));
        
        // TLV.Type(0x06) - store message if recipient offline
        snac.addSnacData(DataWork.putWord(0x06));
        snac.addSnacData(DataWork.putWord(0x00));
        
        addSnac(snac);        
    }
   
}
