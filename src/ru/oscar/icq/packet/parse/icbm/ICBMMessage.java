
package ru.oscar.icq.packet.parse.icbm;

import java.util.Date;
import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Tlv;
import ru.oscar.icq.constants.MessageTypesConstants;
import ru.oscar.core.Connect;
import ru.oscar.icq.events.MessageEvent;
import ru.oscar.icq.listener.MessagesListener;
import ru.oscar.command.DefaultCommand;
import ru.oscar.icq.events.OfflineMessageEvent;
import ru.oscar.icq.packet.send.icbm.SendMessageAsk;
import ru.oscar.icq.packet.send.icbm.SendXStatus;
import ru.oscar.icq.setting.Capabilities;
import ru.oscar.util.ByteUtil;
import ru.oscar.util.StringUtil;

/**
 * SNAC (4,7)
 * Входящее сообщение
 * @author Kornackiy Alexsandr
 */

public class ICBMMessage extends DefaultCommand{

    private byte[] time;
    private byte[] cookies;  
    
    private int messageChannel;
    private int msgType;
    private int protocolVersion;
    private int msgSeqNum;
    private int msgFlag;   
    private int fileSize;   
    private int port = -1; 
    
    private String senderID;
    private String message; 
    private String externalIP;
    private String internalIP; 
    private String fileName;  
    
    private boolean isPlainMessage = false;    
    private boolean isFileAck = false; 
    private boolean isStopping = false; 
    private boolean isUTF8 = false;    
    private boolean isRequestXStatus = false; 
    private boolean isRequestAwayMessage = false;
    private boolean isOfflineMessage = false;
            
    private Date messageDate;        
    
    public ICBMMessage(){
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
        
        //sender warning level
        index += 2;
        
        //number of TLVs in fixed part
        int numberTlv = DataWork.getWord(data, index);
	index += 2;    
        
        for (int i = 0; i < numberTlv; i++) {  
            Tlv tlv = new Tlv(data, index);
            /*TLV.Type(0x01) - user class (nick flags)*/
            /*TLV.Type(0x06) - user status [ICQ only]*/
            /*TLV.Type(0x0F) - online time*/
            /*TLV.Type(0x03) - signon time*/
            /*TLV.Type(0x02) - create time*/ 
            index += tlv.getTlvLength() + tlv.TLV_HEADER_SIZE;
        }
        if(messageChannel == 1){
            parseChannel_1(index, data);
        } else if(messageChannel == 2){
            parseChannel_2(index, data);
        } else if(messageChannel == 4){
            parseChannel_4(index, data);
        }          
    }

    @Override
    public void notify(Connect connect) {
        if (messageChannel == 2 && !isRequestXStatus && !isStopping) {
            //ask
            connect.sendPacket( new SendMessageAsk(time, cookies,
                    senderID, protocolVersion, msgSeqNum, msgType, msgFlag));
        }   
        
        if (isPlainMessage){
             MessageEvent e = new MessageEvent(this);
             for (int i = 0; i < connect.getListenerMessages().size(); i++) {
                 MessagesListener l = (MessagesListener) connect.getListenerMessages().get(i);
                 l.onIncomingMessage(e);
             }
         } else if (isOfflineMessage){
             OfflineMessageEvent e = new OfflineMessageEvent(this);
             for (int i = 0; i < connect.getListenerMessages().size(); i++) {
                 MessagesListener l = (MessagesListener) connect.getListenerMessages().get(i);
                 l.onOfflineMessage(e);   
             }
        } else if (isRequestXStatus && !isStopping && connect.getOptionsConnect().getPrivacyStatus().getCode() != 2){
            connect.sendPacket(new SendXStatus(time, cookies, senderID, connect.getSN(),
                    protocolVersion, msgType, msgFlag, connect.getOptionsConnect().getXstatus()));       
        }
    } 
    
    private void parseChannel_1(int position, byte[] data){
        //TLV.Type(0x02) - message data
        position += 4; 
        
        msgType = 0x01;
        isPlainMessage = true; 
        
        /*array of required capabilities*/
        Tlv capabilitiesData = new Tlv(data, position);
        position += capabilitiesData.getTlvLength() + capabilitiesData.TLV_HEADER_SIZE;
        
        /*message*/
        Tlv MessageData = new Tlv(data, position);
        int msgLen = MessageData.getTlvLength();
        position += 4;   
            
        /*Message charset number*/
        int charsetNumber = DataWork.getWord(data, position);
        position += 2;
            
        /*Message charset subset*/
        position += 2;
            
        if(0x0002 == charsetNumber){
            message = StringUtil.ucs2beByteArrayToString(data, position, msgLen - 4);
        }else{
            message = StringUtil.byteArrayWin1251ToString(data, position, msgLen - 4);
       } 
        position += msgLen;
        position += 36;
        while(position < data.length){
            Tlv tlv = new Tlv(data, position);
            switch(tlv.getTlvType()){ 
                case 0x16:
                   byte[] array = tlv.getDataArray(); 
                   long messageTime = ByteUtil.toLong(array);
                   messageDate = new Date(messageTime*1000);  
                break;
                case 0x06: 
                    isOfflineMessage = true;
                    isPlainMessage = false;
                break;    
            }
            position += tlv.getTlvLength() + tlv.TLV_HEADER_SIZE;
        }
    }    
    
    private void parseChannel_2(int position, byte[] data){
        //TLV.Type(0x05) - rendezvous message data    
        position += 4;

        // TLV(A): Acktype 0x0000 - normal message
        //                 0x0001 - file request / abort request
        //                 0x0002 - file ack         
        int ackType = DataWork.getWord(data, position);
        position += 2;   
        
        if (0x0000 != ackType){
            isFileAck = true;
        }

        //msg-id cookie (same as above)
        position += 8;   

        //capability 
        position += 16;           

        byte[] hereMessage = null;
//        int fileNameLen = 0;

//        boolean dataFile = false;
        
       while(position < data.length) {
            Tlv exDataTlv = new Tlv(data, position);                
            switch (exDataTlv.getTlvType()) { 
                //internalIP
                case 0x02 :    
//                    internalIP = getIP(exDataTlv.getByteArray());
                    break;                   
                //TLV.Type(0x04) - external ip   
                case 0x04:
//                    externalIP = getIP(exDataTlv.getByteArray());
                    break;                        
                //TLV.Type(0x05) - listening port   
                case 0x05:
//                    port = DataWork.getWord(exDataTlv.getDataArray(), 0);
                        break;                     
                // TLV.Type(0x0A) - unknown 
                case 0x000a:

                        break;
                //TLV.Type(0x0B) - unknown 
                case 0x0B:
//                            log.debug("Tlv ( " + exDataTlv.getType() + " ) Length = " + exDataTlv.getLength());
//                            log.debug("Tlv ( " + exDataTlv.getType() + " ) Value = " + exDataTlv.getValue());
                        break;                         
                // TLV.Type(0x0F) - unknown 
                case 0x000f:
//                            log.debug("Tlv ( " + exDataTlv.getType() + " ) Length = " + exDataTlv.getLength());
//                            log.debug("Tlv ( " + exDataTlv.getType() + " ) Value = " + exDataTlv.getValue());
                        break; 
                // TLV.Type(0x2711) - extention data   
                case 0x2711:
//                            log.debug("Tlv ( " + exDataTlv.getType() + " ) Length = " + exDataTlv.getLength());
//                            log.debug("Tlv ( " + exDataTlv.getType() + " ) Value = " + exDataTlv.getValue());
                        hereMessage = exDataTlv.getDataArray();
//                        fileNameLen = exDataTlv.getTlvLength();
//                            System.err.println("\n" + Dumper.dump(hereMessage, true));
                        break;                             
            }            
        position += exDataTlv.getTlvLength() + exDataTlv.TLV_HEADER_SIZE;    
        }   
        
        if (hereMessage == null/* || dataFile*/) { 
            isStopping = true;
            return;
        } 

        int herePosition = 0;

//        if(isFile()){              
//
//            herePosition += 8;
//
//            fileName = StringUtil.stringOfBytes(DataWork.getArray(hereMessage, herePosition, (fileNameLen - herePosition) - 1));
//
//            isStopping = true;
//            return;                
//        }  
                                          
        //length of following data
        herePosition += 2;

        //protocol version
        protocolVersion = DataWork.getWord(hereMessage, herePosition);
        herePosition += 2;  

        //plugin or zero bytes
        herePosition += 16;

        //unknown
        herePosition += 2;

        //client capabilities flags
        herePosition += 4;

        //unknown
        herePosition++;

        //seems to be a downcounter
        msgSeqNum = DataWork.getWord(hereMessage, herePosition);
        herePosition += 2;    

        //length of following data
        herePosition += 2;

        //seems to be a downcounter as in first chunk above
        herePosition += 2;

        //unknown, usually zeros
        herePosition += 12;

        //message type 
        msgType = DataWork.getByte(hereMessage, herePosition);
        herePosition++;

        //message flags
        msgFlag = DataWork.getByte(hereMessage, herePosition);
        herePosition++;        
        //Plain message
        if(msgType == MessageTypesConstants.MESSAGE_PLAIN){
            
            isPlainMessage = true;

            //status code
            herePosition += 2;

            //priority code
            herePosition += 2;

            //message string length
            int msgLen = DataWork.getWordLE(hereMessage, herePosition);
            herePosition += 2; 

            if(msgLen == 0){
                isStopping = true;
                return;
            }

            //message 
            byte[] msg = DataWork.getArray(hereMessage, herePosition, msgLen - 1);
            herePosition += msgLen;                

            //text color 
            herePosition += 4;

            //background 
            herePosition += 4;

            if(hereMessage.length >= herePosition + 4){

            //guid string length
            int guidLen = DataWork.getDWordLE(hereMessage, herePosition);
            herePosition += 4;                 

            if(guidLen >= 16){
                byte[] guid = DataWork.getArray(hereMessage, herePosition, guidLen);
                if(ByteUtil.eguals(guid, Capabilities.UTF8_SUPPORT_CAPS) || ByteUtil.eguals(guid,Capabilities.UTF8_GUID)){
                    isUTF8 = true;
                }               
            }                
            }

            if(isUTF8){
                message = StringUtil.utf8ByteArrayToString(msg, 0, msgLen - 1);
            }else{
                message = StringUtil.byteArrayWin1251ToString(msg, 0, msgLen - 1);
            }                  
        } else if(msgType == MessageTypesConstants.MESSAGE_PLUGIN){
                // Запрос X-status
                byte[] xstatus_guid = DataWork.getArray(hereMessage, herePosition + 9, 16);    
                if(ByteUtil.eguals(xstatus_guid, Capabilities.XTRAZ_GUID)){
                    isRequestXStatus = true;
                }
        } else if(msgType == MessageTypesConstants.MESSAGE_AUTOAWAY){
                // away
                isRequestAwayMessage = true;
        } else {
            System.err.println("Unknown message type: " + msgType);
        }               
    }   
    
    private void parseChannel_4(int position, byte[] data){  
           //TLV.Type(0x05) - rendezvous message data    
        position += 4;

        //Sender uin
//        int senderUin = DataWork.getDWordLE(data, position);
        position += 4;

        //message type 
        msgType = 0x01;
        isPlainMessage = true;
        position++;

        //message flags
        msgFlag = DataWork.getByte(data, position);
        position++;

        //Message string length
        int msgLen = DataWork.getWordLE(data, position);
        position += 2;  

        //Message string
        message = StringUtil.stringOfBytes(data, position, msgLen);     
    }     
    
    private String getIP(byte[] data){
        int index = 0;
        String ip = "";
        ip += DataWork.getByte(data, index) + ".";
        index++;
        ip += DataWork.getByte(data, index) + ".";
        index++;
        ip += DataWork.getByte(data, index) + ".";
        index++;
        ip += DataWork.getByte(data, index);   
        return ip;
    }
    
    private boolean isFile(){
        return externalIP != null && internalIP != null && port != -1;
    }

    /**
     * @return the senderID
     */
    public String getSenderID() {
        return senderID;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }  

    /**
     * @return the messagedate
     */
    public Date getMessageDate() {
        // TODO: пока так
        if(!isOfflineMessage) {
            return new Date(System.currentTimeMillis());
        }
        return messageDate;
    }

    /**
     * @return the isOfflineMessage
     */
    public boolean isOfflineMessage() {
        return isOfflineMessage;
    }

}
