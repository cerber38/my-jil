
package ru.oscar.icq.packet.send.icbm;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Snac;
import ru.oscar.icq.constants.XStatusConstants;
import ru.oscar.util.StringUtil;

/**
 * Отправим xstatus
 * @author Kornackiy Alexsandr
 */

public class SendXStatus extends Flap {
    
    public SendXStatus(byte[] time, byte[] cookies, String snUser, String snMy,
            int protocolVersion, int msgType, int msgFlag, XStatusConstants xstatus){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x04, 0x0B, 0x00, 0x00, 0x00);
        // msg-id cookie
        snac.addSnacData(DataWork.putArray(time));
        snac.addSnacData(DataWork.putArray(cookies)); 
        // message channel
        snac.addSnacData(DataWork.putWord(CHANNEL2));  
        // screenname string length
        snac.addSnacData(DataWork.putByte(snUser.length()));
        // screenname string
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString(snUser)));  
        // reason code
        snac.addSnacData(DataWork.putWord(0x0003));   
        //length of following data
        snac.addSnacData(DataWork.putWordLE(0x001B));     
        //protocol version
        snac.addSnacData(DataWork.putWordLE(protocolVersion));  
        //
        snac.addSnacData(DataWork.putWord(0x00));
        //plugin or zero bytes
        snac.addSnacData(DataWork.putArray(new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));   
        //unknown
        snac.addSnacData(DataWork.putByte(0x01));
        //snac.addSnacData(DataWork.putWord(0x0000));       
        //client capabilities flags
        snac.addSnacData(DataWork.putDWordLE(0x00000000));   
        
        //unknown
        snac.addSnacData(DataWork.putByte(0x72));
        //seems to be a downcounter
        snac.addSnacData(DataWork.putWordLE(0x0E19));
        //length of Following data
        snac.addSnacData(DataWork.putWord(0x0072));
        //
        snac.addSnacData(DataWork.putWordLE(0x0019));
        snac.addSnacData(DataWork.putDWordLE(0x00000000));  
        snac.addSnacData(DataWork.putDWordLE(0x00000000));  
        snac.addSnacData(DataWork.putWordLE(0x0000));  
        snac.addSnacData(DataWork.putByte(0x00));  
        
        snac.addSnacData(DataWork.putArray(new byte[]{
					0x1A, 0x00, 0x00, 0x00, 0x01, 0x00, 0x01, 
					0x00, 0x00, 0x4F, 0x00, 0x3B, 0x60, (byte)0xB3, 
					(byte)0xEF, (byte)0xD8}));        
        snac.addSnacData(DataWork.putArray(new byte[]{
					0x2A, 0x6C, 0x45, (byte)0xA4, (byte)0xE0, (byte)0x9C,
					0x5A, 0x5E, 0x67, (byte)0xE8, 0x65, 0x08,
					0x00, 0x2A, 0x00, 0x00}));
        
        snac.addSnacData(DataWork.putByte(0x00)); 
        
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString("Script Plug-in: Remote Notification Arrive")));
        snac.addSnacData(DataWork.putByte(0x00)); 
        // message type
        snac.addSnacData(DataWork.putByte(msgType));
        // message flags
        snac.addSnacData(DataWork.putByte(msgFlag)); 
        
        snac.addSnacData(DataWork.putArray(new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));
        
        snac.addSnacData(DataWork.putDWordLE(0x0000F501)); 
        snac.addSnacData(DataWork.putDWordLE(0x0000F101)); 
        
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString("<NR><RES>&lt;ret event='OnRemoteNotification'&gt;&lt;srv&gt;&lt;id&gt;cAwaySrv&lt;/id&gt;&lt;val srv_id='cAwaySrv'&gt;&lt;Root&gt;&lt;CASXtraSetAwayMessage&gt;&lt;/CASXtraSetAwayMessage&gt;&lt;uin&gt;" + snMy + "&lt;/uin&gt;&lt;index&gt;" + xstatus.getCode() + "&lt;/index&gt;&lt;title&gt;")));
    
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfStringUTF8(xstatus.getTitle())));
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString("&lt;/title&gt;&lt;desc&gt;")));
        
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfStringUTF8(xstatus.getDescription())));
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString("&lt;/desc&gt;&lt;/Root&gt;\r\n")));
        snac.addSnacData(DataWork.putArray(StringUtil.bytesOfString("&lt;/val&gt;&lt;/srv&gt;&lt;srv&gt;&lt;id&gt;cRandomizerSrv&lt;/id&gt;&lt;val srv_id='cRandomizerSrv'&gt;undefined&lt;/val&gt;&lt;/srv&gt;&lt;/ret&gt;</RES></NR>\r\n")));
        
        addSnac(snac);
    }
     
}
