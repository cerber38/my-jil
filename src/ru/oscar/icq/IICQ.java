
package ru.oscar.icq;

import ru.oscar.icq.constants.MessageTypesConstants;
import ru.oscar.icq.constants.PrivacyStatusConstants;
import ru.oscar.icq.constants.StatusConstants;
import ru.oscar.icq.constants.StatusFlagConstants;
import ru.oscar.icq.constants.XStatusConstants;
import ru.oscar.core.Connect;
import ru.oscar.icq.packet.send.generic.SetStatus;
import ru.oscar.icq.packet.send.icbm.SendMessageChannel1;
import ru.oscar.icq.packet.send.icbm.SendMessageChannel2;
import ru.oscar.icq.packet.send.icbm.SendMessageChannel4;
import ru.oscar.icq.packet.send.icbm.XStatusRequest;
import ru.oscar.icq.packet.send.location.SetUserInformation;
import ru.oscar.icq.packet.send.meta.BlockMetaData;
import ru.oscar.icq.packet.send.meta.RequestShortInfo;
import ru.oscar.icq.packet.send.meta.SearchByUin;
import ru.oscar.icq.packet.send.meta.SetFullInfo;
import ru.oscar.icq.packet.send.ssi.PrivacyStatus;

/**
 * @author Kornackiy Alexsandr
 */

public class IICQ {
    
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

        connect.sendPacket(new SetUserInformation(connect.getOptionsConnect().getCapabilities(),
                    connect.getOptionsConnect().getXstatus(), status));             

        connect.sendPacket(new SetStatus(status,
                connect.getOptionsConnect().getStatusFlag(), 
                connect.getOptionsConnect().getDirectConnect(),
                connect.getOptionsConnect().getProtocolVersion(),
                connect.getOptionsConnect().isDebug()));  
        
    }
    
    /**
     * Изменим flag status
     * @param connect
     * @param statusFlag 
     */
    
    public static void changeStatusFlag(Connect connect, StatusFlagConstants statusFlag){
        connect.getOptionsConnect().setStatusFlag(statusFlag);
        connect.sendPacket(new SetStatus(connect.getOptionsConnect().getStatus(),
                statusFlag, 
                connect.getOptionsConnect().getDirectConnect(),
                connect.getOptionsConnect().getProtocolVersion(),
                connect.getOptionsConnect().isDebug()));        
    }   
    
    /**
     * Изменить x-status
     * @param connect
     * @param xstatus 
     */
    
    public static void changeXStatus(Connect connect, XStatusConstants xstatus){
        connect.getOptionsConnect().setXstatus(xstatus);
        connect.sendPacket(new SetUserInformation(connect.getOptionsConnect().getCapabilities(),
                    xstatus, connect.getOptionsConnect().getStatus()));      
    }
    
    /**
     * Изменить приватный статус
     * @param connect
     * @param privacyStatus 
     */
    
    public static void changePrivacyStatus(Connect connect, PrivacyStatusConstants privacyStatus){
        connect.getOptionsConnect().setPrivacyStatus(privacyStatus);
        connect.sendPacket(new PrivacyStatus(privacyStatus,
                    connect.getOptionsConnect().getPrivacyStatusId(),
                    connect.getOptionsConnect().isDebug()));    
    }   
    
    /**
     * Запрос кароткой информации
     * @param connect
     * @param userId 
     */
    
    public static void requestShortUserInfo(Connect connect, String userId) {
        connect.sendPacket(new RequestShortInfo(userId, connect.getSN()));
    }    
    
    public static void requestSearchByUin(Connect connect, String userId) {
        connect.sendPacket(new SearchByUin(userId, connect.getSN(), -1));
    }   
    
    /**
     * Меняет информацию
     * @param connect
     * @param metaData 
     */
    
    public static void setFullInfo(Connect connect, BlockMetaData metaData) {
        connect.sendPacket(new SetFullInfo(connect.getSN(), metaData));
    }
    
}
