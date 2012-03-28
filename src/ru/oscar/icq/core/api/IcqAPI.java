
package ru.oscar.icq.core.api;

import ru.oscar.icq.constants.MessageTypesConstants;
import ru.oscar.icq.constants.PrivacyStatusConstants;
import ru.oscar.icq.constants.StatusConstants;
import ru.oscar.icq.constants.StatusFlagConstants;
import ru.oscar.icq.constants.XStatusConstants;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.packet.send.generic.Snac__1_1E;
import ru.oscar.icq.packet.send.icbm.SendMessageChannel1;
import ru.oscar.icq.packet.send.icbm.SendMessageChannel2;
import ru.oscar.icq.packet.send.icbm.SendMessageChannel4;
import ru.oscar.icq.packet.send.icbm.XStatusRequest;
import ru.oscar.icq.packet.send.location.Snac__2_4;
import ru.oscar.icq.packet.send.ssi.SendPrivacyStatus;

/**
 * @author Kornackiy Alexsandr
 */

public class IcqAPI {
    
    /**
     * Отправить обычное сообщение
     * @param connect
     * @param sn
     * @param message 
     */
    public static void sendNormalMessage(Connect connect, String sn, String message){
        connect.sendPacket(new SendMessageChannel1(sn, message));
    }
    
    /**
     * Отправить расширенное сообщение
     * @param connect
     * @param sn
     * @param message 
     */    
    public static void sendExtendedMessage(Connect connect, String sn, String message){
        connect.sendPacket(new SendMessageChannel2(connect, sn, message));
    }
    
    /**
     * Старый тип сообщения или url
     * @param connect
     * @param sn
     * @param message
     * @param mtype 
     */
    
    public static void sendOldTypeMessage(Connect connect, String sn, String message, MessageTypesConstants mtype){
        connect.sendPacket(new SendMessageChannel4(sn, message, mtype));
    }
    
    /**
     * Запрос х-статуса у пользователя
     * @param connect
     * @param sn 
     */
    
    public static void requestXStatus(Connect connect, String sn){
        connect.sendPacket(new XStatusRequest(connect, sn, connect.getSN()));
    }
    
    /**
     * Изменить status
     * @param connect
     * @param status 
     */
    
    public static void changeStatus(Connect connect, StatusConstants status){
        connect.getOptionsConnect().setStatus(status);

        connect.sendPacket(new Snac__2_4(connect.getOptionsConnect().getCapabilities(),
                    connect.getOptionsConnect().getXstatus(), status));             

        connect.sendPacket(new Snac__1_1E(status,
                connect.getOptionsConnect().getStatusFlag(), 
                connect.getOptionsConnect().getDirectConnect(),
                connect.getOptionsConnect().getProtocolVersion()));  
        
    }
    
    /**
     * Изменим flag status
     * @param connect
     * @param statusFlag 
     */
    
    public static void changeStatusFlag(Connect connect, StatusFlagConstants statusFlag){
        connect.getOptionsConnect().setStatusFlag(statusFlag);
        connect.sendPacket(new Snac__1_1E(connect.getOptionsConnect().getStatus(),
                statusFlag, 
                connect.getOptionsConnect().getDirectConnect(),
                connect.getOptionsConnect().getProtocolVersion()));        
    }   
    
    /**
     * Изменить x-status
     * @param connect
     * @param xstatus 
     */
    
    public static void changeXStatus(Connect connect, XStatusConstants xstatus){
        connect.getOptionsConnect().setXstatus(xstatus);
        connect.sendPacket(new Snac__2_4(connect.getOptionsConnect().getCapabilities(),
                    xstatus, connect.getOptionsConnect().getStatus()));      
    }
    
    /**
     * Изменить приватный статус
     * @param connect
     * @param xstatus 
     */
    
    public static void changePrivacyStatus(Connect connect, PrivacyStatusConstants privacyStatus){
        connect.getOptionsConnect().setPrivacyStatus(privacyStatus);
        connect.sendPacket(new SendPrivacyStatus(privacyStatus,
                    connect.getOptionsConnect().getPrivacyStatusId()));    
    }    
    
}
