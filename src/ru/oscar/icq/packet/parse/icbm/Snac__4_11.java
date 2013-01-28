
package ru.oscar.icq.packet.parse.icbm;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.icq.constants.MessageTypesConstants;
import ru.oscar.core.Connect;
import ru.oscar.icq.events.XStatusEvent;
import ru.oscar.icq.listener.ListenerXStatus;
import ru.oscar.command.DefaultCommand;
import ru.oscar.icq.setting.Capabilities;
import ru.oscar.util.ByteUtil;
import ru.oscar.util.StringUtil;

/**
 * SNAC (4,11)
 * @author Kornackiy Alexsandr
 */
public class Snac__4_11 extends DefaultCommand{
    
    public static final short UNSUPPORTED_CHANNEL = 1;
    public static final short BUSTED_PAYLOAD      = 2;
    public static final short CHANNEL_SPECIFIC    = 3;

    private byte[] time;
    private byte[] cookies; 
    private int messageChannel;

    private String message;		
    private String senderID;

    private int messageType;

    private boolean isResponseXStatus = false;
        
    public Snac__4_11(){
        super();    
    }     
    
    @Override
    public void exec(Flap f) {
        int index = 0;   
        byte[] data = f.getDataArray();   
        
        // msg-id cookie
        time = DataWork.getArray(data, index, 4);
        index += 4;
        cookies = DataWork.getArray(data, index, 4);
        index += 4;
        
        // message channel (see table below)
        messageChannel = DataWork.getWord(data, index);
        index += 2;
        
        int len = DataWork.getByte(data, index);
        index++;
        
        //uin string
        byte[] uinData = DataWork.getArray(data, index, len);
        index += len;
        
        senderID = StringUtil.stringOfBytes(uinData);   
        
        //reason code
        index += 2;      
        
        if(messageChannel == 1 || messageChannel == 256){
            getChannel_1(index, data);
        }else if(messageChannel == 2){
            getChannel_2(index, data);
        }        
    }   
    
    private void getChannel_1(int index, byte[] data){  
        if(data.length - index <= 4){
            return; 
        }
        //fragment identifier (array of required capabilities)
        index++;

        //fragment version
        index++; 
        
        //Length of rest data
        int capabilitiesgLen = DataWork.getWord(data, index);
        index += 2;

        //capabilities 
        index += capabilitiesgLen;

        //fragment identifier (text message)
        index++;

        //fragment version
        index++;

        //Length of rest data
        int msgLen = DataWork.getWord(data, index);
        index += 2;  

        //Message charset number
        int charsetNumber = DataWork.getWord(data, index);
        index += 2;

        //Message language number
        index += 2;

        if(data.length - index > 4){
            message = StringUtil.utf8ByteArrayToString(data, index, msgLen - 4);
        }        
    }  
    
    
    private void getChannel_2(int index, byte[] data){  
        // Todo: что это за пакет?
//            2A 02 5E 67  00 22 00 04  *.^g."..
//            00 0B 00 00  80 86 F6 48  ....���
//            00 00 00 00  00 00 00 00  ........
//            00 02 07 31  37 36 35 35  ...17655
//            39 34 00 03  00 02 00 01  94......
        // пока так
        if(data.length - index <= 8){
            return;
        }      
        
        
        
        //length of following data
        index += 2;

        //protocol version
        index += 2;

        //plugin or zero bytes
        index += 16;   

        //unknown
        index += 2;

        //client capabilities flags
        index += 4;

        //unknown
        index++;   

        //seems to be a downcounter
        index += 2;

        //length of following data
        index += 2;

        //seems to be a downcounter as in first chunk above
        index += 2;

        //unknown, usually zeros
        index += 12; 

        //message type 
        messageType = DataWork.getByte(data, index);
        index++;

        //message flags
        index++;           

        //status code
        index += 2;

        //priority code
        index += 2;

        //message string length
        int msgLen = DataWork.getWordLE(data, index);
        index += 2;            

        if (messageType == MessageTypesConstants.MESSAGE_PLAIN) {

        } else if (messageType == MessageTypesConstants.MESSAGE_PLUGIN) {                     

           //Читаем x-status юзера
           byte[] xstatus_guid = DataWork.getArray(data, index + 3, 16);
           if(ByteUtil.eguals(xstatus_guid, Capabilities.XTRAZ_GUID)){
               message = StringUtil.utf8ByteArrayToString(data, index + 25, data.length - (index + 25));
               isResponseXStatus = true;                    

           }       
    }         
    }
    
    public void notify(Connect connection) {
        if (isResponseXStatus) {
            XStatusEvent e = new XStatusEvent(this);
            for (int i = 0; i < connection.getListenerXStatus().size(); i++) {
                    ListenerXStatus l = (ListenerXStatus) connection.getListenerXStatus().get(i);
                    l.onXStatusResponse(e);
            }            
        }
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the senderID
     */
    public String getSenderID() {
        return senderID;
    }
}
