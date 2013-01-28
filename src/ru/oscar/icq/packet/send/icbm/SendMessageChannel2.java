
package ru.oscar.icq.packet.send.icbm;

import ru.oscar.DataWork;
import ru.oscar.Tlv;
import ru.oscar.icq.constants.MessageFlagsConstants;
import ru.oscar.icq.constants.MessageTypesConstants;
import ru.oscar.core.Connect;
import ru.oscar.icq.setting.Capabilities;
import ru.oscar.util.StringUtil;

/**
 * snac (4,6)
 * Сообщение по второму каналу, расширенное сообщение.
 * @author Kornackiy Alexsandr
 */
public class SendMessageChannel2 extends Message {
    
    public SendMessageChannel2(Connect connect, String sn, String message){
        super(sn, MESSAGE_CHANNEL2);
        
        Tlv tlv5 = new Tlv(0x05);
        // message type (0 - request, 1 - cancel, 2 - accept)
        tlv5.addTlvData(DataWork.putWord(0x0000));
        // msg-id cookie (same as above)
        tlv5.addTlvData(DataWork.putDWord(0x00000000));
        tlv5.addTlvData(DataWork.putDWord(0x00000000));
        // capability
        tlv5.addTlvData(DataWork.putArray(Capabilities.AIM_SERVER_RELAY_CAPS));
        // TLV.Type(0x0A) - ask type
        Tlv tlv0A = new Tlv(0x0A, 0x0001);
        tlv5.addTlvtoTlv(tlv0A);
        // TLV.Type(0x0F) - unknown
        Tlv tlv0F = new Tlv(0x0F);
        tlv0F.addTlvData(null);
        tlv5.addTlvtoTlv(tlv0F);
        // TLV.Type(0x2711) - extention data
        Tlv tlv2711 = new Tlv(0x2711);
        //length of following data
        tlv2711.addTlvData(DataWork.putWordLE(0x001B));
        // protocol version
        tlv2711.addTlvData(DataWork.putWordLE(connect.getOptionsConnect().getProtocolVersion().getCode()));
        // plugin or (16) zero bytes TODO: LE но нули
        tlv2711.addTlvData(DataWork.putArray(new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));
        // unknown
        tlv2711.addTlvData(DataWork.putWord(0x0000));
        tlv2711.addTlvData(DataWork.putByte(0x03));
        // client capabilities flags 0x00000000 (normal)
        tlv2711.addTlvData(DataWork.putDWord(0x00000000));
        // unknown
        //tlv2711.addTlvData(DataWork.putByte(0x00));
        // seq
        int seq = connect.getSequence();
        tlv2711.addTlvData(DataWork.putWordLE(seq));
        // lenght
        tlv2711.addTlvData(DataWork.putWordLE(0x000E));
        // seq cope
        tlv2711.addTlvData(DataWork.putWordLE(seq));    
        // unknown, usually zeros
        tlv2711.addTlvData(DataWork.putArray(new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));
        // message type
        tlv2711.addTlvData(DataWork.putByte(MessageTypesConstants.MESSAGE_PLAIN));
        // message flags
        //tlv2711.addTlvData(DataWork.putByte(MessageFlagsConstants.MESSAGE_NORMAL));
        tlv2711.addTlvData(DataWork.putByte(0x00));
        // status code
        tlv2711.addTlvData(DataWork.putWordLE(connect.getOptionsConnect().getStatus().getCode()));
        // priority code
        tlv2711.addTlvData(DataWork.putWordLE(0x0021));
        // message 
        // TODO: реализовать отправку в разных кодировках
        byte[] messageAraay = StringUtil.bytesOfStringUTF8(message);
        tlv2711.addTlvData(DataWork.putWordLE(messageAraay.length + 1));
        tlv2711.addTlvData(DataWork.putArray(messageAraay));
        tlv2711.addTlvData(DataWork.putByte(0x00));        
        
        // text color (rgbn, optional)
        tlv2711.addTlvData(DataWork.putDWord(0x00000000));
        // background color (rgbn, optional)
        tlv2711.addTlvData(DataWork.putDWord(0x00FFFFFF));
        
        //guid string length (optional)
        tlv2711.addTlvData(DataWork.putDWordLE(Capabilities.UTF8_GUID.length));
        //guid string (optional)
        tlv2711.addTlvData(DataWork.putArray(Capabilities.UTF8_GUID));
        
        tlv5.addTlvtoTlv(tlv2711);
        
        snac.addTlv(tlv5);
        
        // TLV.Type(0x03) - request an ack from server
        snac.addSnacData(DataWork.putWord(0x03));
        snac.addSnacData(DataWork.putWord(0x00));
        addSnac(snac);
    }
    
}
