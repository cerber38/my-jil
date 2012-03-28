
package ru.oscar.icq.packet.send.icbm;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.util.StringUtil;

/**
 * snac (4, 0B)
 * Ответ на входящее сообщение.
 * @author Kornackiy Alexsandr
 */
public class SendMessageAsk extends Flap {
    
    public SendMessageAsk(byte[] time, byte[] cookies, String snUser, 
            int protocolVersion, int msgSeqNum, int msgType, int msgFlag){
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
        //plugin or zero bytes
        snac.addSnacData(DataWork.putArray(new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));   
        //unknown
        snac.addSnacData(DataWork.putWord(0x0000));       
        //client capabilities flags
        snac.addSnacData(DataWork.putDWordLE(0x00000003));       
        //unknown
        snac.addSnacData(DataWork.putWord(0x00));     
        // seq
        snac.addSnacData(DataWork.putWordLE(msgSeqNum));
        // lenght
        snac.addSnacData(DataWork.putWordLE(0x000E));
        // seq cope
        snac.addSnacData(DataWork.putWordLE(msgSeqNum));  
        // unknown, usually zeros
        snac.addSnacData(DataWork.putArray(new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));
        // message type
        snac.addSnacData(DataWork.putByte(msgType));
        // message flags
        snac.addSnacData(DataWork.putByte(msgFlag));  
        //status code
        snac.addSnacData(DataWork.putWordLE(0x0000));
        //priority code
        snac.addSnacData(DataWork.putWordLE(0x0000));
        //message string length
        snac.addSnacData(DataWork.putWordLE(0x0000));
        
        addSnac(snac);
    }
}
