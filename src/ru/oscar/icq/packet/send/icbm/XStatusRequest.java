
package ru.oscar.icq.packet.send.icbm;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.setting.Capabilities;
import ru.oscar.icq.util.StringUtil;

/**
 * snac (4,6)
 * Запрос х-статуса.
 * @author Kornackiy Alexsandr
 */
public class XStatusRequest extends Message {
    
    public XStatusRequest(Connect connect, String sn, String mySn){
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
        
        tlv5.addTlvData(DataWork.putWord(0x000F));
        tlv5.addTlvData(DataWork.putWord(0x0000)); 
        
        Tlv tlv2711 = new Tlv(0x2711);
        //length of following data
        tlv2711.addTlvData(DataWork.putWordLE(0x001B));
        // protocol version
        tlv2711.addTlvData(DataWork.putWordLE(connect.getOptionsConnect().getProtocolVersion().getCode()));
        // plugin or (16) zero bytes TODO: LE но нули
        tlv2711.addTlvData(DataWork.putArray(new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));        
        tlv2711.addTlvData(DataWork.putWord(0x0000));
        // client capabilities flags 0x00000000 (normal)
        tlv2711.addTlvData(DataWork.putDWord(0x00000000));
        // unknown
        tlv2711.addTlvData(DataWork.putByte(0x00));
        
        tlv2711.addTlvData(DataWork.putWordLE(0xD295));
        tlv2711.addTlvData(DataWork.putWordLE(0x000E));
        tlv2711.addTlvData(DataWork.putWordLE(0xD295));  
        
        // unknown, usually zeros
        tlv2711.addTlvData(DataWork.putArray(new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));   
        
        tlv2711.addTlvData(DataWork.putArray(new byte[] {
					0x1A, 0x00, 0x00, 0x00, 0x01, 0x00, 0x01, 
					0x00, 0x00, 0x4F, 0x00, 0x3B, 0x60, (byte)0xB3, 
					(byte)0xEF, (byte)0xD8})); 
        tlv2711.addTlvData(DataWork.putArray(new byte[] {
					0x2A, 0x6C, 0x45, (byte)0xA4, (byte)0xE0, (byte)0x9C,
					0x5A, 0x5E, 0x67, (byte)0xE8, 0x65, 0x08,
					0x00, 0x2A, 0x00, 0x00})); 
        
        tlv2711.addTlvData(DataWork.putByte(0x00));
        
        tlv2711.addTlvData(DataWork.putArray(StringUtil.bytesOfString("Script Plug-in: Remote Notification Arrive")));
        tlv2711.addTlvData(DataWork.putByte(0x00));
        
        tlv2711.addTlvData(DataWork.putWord(0x0001));
        
        tlv2711.addTlvData(DataWork.putArray(new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));  
        
        tlv2711.addTlvData(DataWork.putDWord(0x13010000));
        
        tlv2711.addTlvData(DataWork.putDWord(0xF1010000));     
        
        int trans = (int)Math.random()*18;
        tlv2711.addTlvData(DataWork.putArray(StringUtil.bytesOfString("<N><QUERY>&lt;Q&gt;&lt;PluginID&gt;srvMng&lt;/PluginID&gt;&lt;/Q&gt;</QUERY><NOTIFY>&lt;srv&gt;&lt;id&gt;cAwaySrv&lt;/id&gt;&lt;req&gt;&lt;id&gt;AwayStat&lt;/id&gt;&lt;trans&gt;" + trans + "&lt;/trans&gt;&lt;senderId&gt;" + mySn + "&lt;/senderId&gt;&lt;/req&gt;&lt;/srv&gt;</NOTIFY></N>")));
   
        tlv5.addTlvtoTlv(tlv2711);
        
        snac.addTlv(tlv5);
        
        // TLV.Type(0x03) - request an ack from server
        snac.addSnacData(DataWork.putWord(0x03));
        snac.addSnacData(DataWork.putWord(0x00));
        addSnac(snac); 
    }
    
}
