
package ru.oscar.icq.packet.parse.meta;

import java.util.Date;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import ru.oscar.DataWork;
import ru.oscar.core.Connect;
import ru.oscar.icq.constants.MessageFlagsConstants;
import ru.oscar.icq.constants.MessageTypesConstants;
import ru.oscar.icq.events.OfflineMessageEvent;
import ru.oscar.icq.listener.MessagesListener;
import ru.oscar.util.DateTools;
import ru.oscar.util.StringUtil;

/**
 * @author Kornackiy Alexsandr
 */

public class OfflineMessageParser extends BaseMetaInfoParser {

    private String sn;
    private Date sendDate;
    private String message;
    private int type;
    private int flag;

    protected EventObject getNewEvent() {
        return new OfflineMessageEvent(this);
    }

    protected void sendMessage(EventListener listener, EventObject e) {
        ((MessagesListener) listener).onOfflineMessage((OfflineMessageEvent) e);
    }

    public void parse(byte[] data, int index, int request){
        //message sender uin
        sn = Integer.toString(DataWork.getDWordLE(data, index));
        index += 4;       
        //year when message was sent
        int year = DataWork.getWordLE(data, index);
        index += 2;
        //month when message was sent
        int month = DataWork.getByte(data, index);
        index++;
        //day when message was sent
        int day = DataWork.getByte(data, index);
        index++;   
        //hour (GMT) when message was sent
        int hour = DataWork.getByte(data, index);
        index++;  
        //minute when message was sent
        int minute = DataWork.getByte(data, index);
        index++;    
        
        sendDate = DateTools.makeDate(year, month, day, hour, minute);
        
        //message type
        type = DataWork.getByte(data, index);
        index++;         
        //message flags
        flag = DataWork.getByte(data, index);
        index++; 
        //message string length
        int messageLen = DataWork.getByte(data, index);
        index++; 
        //message
        message = StringUtil.byteArrayWin1251ToString(data, index, messageLen - 1);
    }

    public String getSN() {
        return sn;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public String getMessage() {
        return message;
    }

    public MessageTypesConstants getMessageType() {
          return new MessageTypesConstants(type);
      }

    public MessageFlagsConstants getMessageFlag() {
        return new MessageFlagsConstants(flag);
    }
}
