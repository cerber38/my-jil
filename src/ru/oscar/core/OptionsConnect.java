
package ru.oscar.core;

import ru.oscar.icq.constants.BotInfoConstants;
import ru.oscar.icq.constants.DirectConnectConstants;
import ru.oscar.icq.constants.PrivacyStatusConstants;
import ru.oscar.icq.constants.ProtocolVersionConstants;
import ru.oscar.icq.constants.StatusConstants;
import ru.oscar.icq.constants.StatusFlagConstants;
import ru.oscar.icq.constants.UserClassConstants;
import ru.oscar.icq.constants.XStatusConstants;
import ru.oscar.icq.setting.Capabilities;

/**
 * Класс хранит необходимые опции при работе с протоколом.
 * @author Kornackiy Alexsandr
 */

public class OptionsConnect {

    private StatusFlagConstants statusFlag;
    private StatusConstants status;
    private DirectConnectConstants directConnect;
    private ProtocolVersionConstants protocolVersion;
    private boolean typing;
    private boolean contactList;
    private Capabilities capabilities;
    private XStatusConstants xstatus;
    private int privacyStatusId;
    private PrivacyStatusConstants privacyStatus;
    private boolean debug;
    private boolean MD5uthorization;
    private UserClassConstants userClass;
    private BotInfoConstants botInfo;

    /**
     * @return the statusFlag
     */
    public StatusFlagConstants getStatusFlag() {
        if(statusFlag == null){
            return new StatusFlagConstants(StatusFlagConstants.STATUS_FLAG_NONE);
        }
        return statusFlag;
    }

    /**
     * @param statusFlag the statusFlag to set
     */
    public void setStatusFlag(StatusFlagConstants statusFlag) {
        this.statusFlag = statusFlag;
    }

    /**
     * @return the status
     */
    public StatusConstants getStatus() {
        if(status == null){
            return new StatusConstants(StatusConstants.STATUS_ONLINE);
        }
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusConstants status) {
        this.status = status;
    }

    /**
     * @return the directConnect
     */
    public DirectConnectConstants getDirectConnect() {
        if(directConnect == null){
            return new DirectConnectConstants(DirectConnectConstants.DC_NORMAL);
        }
        return directConnect;
    }

    /**
     * @param directConnect the directConnect to set
     */
    public void setDirectConnect(DirectConnectConstants directConnect) {
        this.directConnect = directConnect;
    }

    /**
     * @return the protocolVersion
     */
    public ProtocolVersionConstants getProtocolVersion() {
        if(protocolVersion == null){
            return new ProtocolVersionConstants(ProtocolVersionConstants.DCP_ICQLITE);
        }
        return protocolVersion;
    }

    /**
     * @param protocolVersion the protocolVersion to set
     */
    public void setProtocolVersion(ProtocolVersionConstants protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /**
     * @return the typing
     */
    public boolean isTyping() {
        return typing;
    }

    /**
     * @param typing the typing to set
     */
    public void setTyping(boolean typing) {
        this.typing = typing;
    }

    /**
     * @return the contactList
     */
    public boolean isContactList() {
        return contactList;
    }

    /**
     * @param contactList the contactList to set
     */
    public void setContactList(boolean contactList) {
        this.contactList = contactList;
    }

    /**
     * @return the client
     */
    public Capabilities getCapabilities() {
        if(capabilities == null){
            return new Capabilities(Capabilities.JIL);
        }
        return capabilities;
    }

    /**
     * @param client the client to set
     */
    public void setCapabilities(Capabilities client) {
        this.capabilities = client;
    }

    /**
     * @return the xstatus
     */
    public XStatusConstants getXstatus() {
        if(xstatus == null){
            return new XStatusConstants(XStatusConstants.NONE);
        }
        return xstatus;
    }

    /**
     * @param xstatus the xstatus to set
     */
    public void setXstatus(XStatusConstants xstatus) {
        this.xstatus = xstatus;
    }

    /**
     * @return the privacyStatusId
     */
    public int getPrivacyStatusId() {
        return privacyStatusId;
    }

    /**
     * @param privateStatusId the privacyStatusId to set
     */
    public void setPrivacyStatusId(int privacyStatusId) {
        this.privacyStatusId = privacyStatusId;
    }

    /**
     * @return the privacyStatus
     */
    public PrivacyStatusConstants getPrivacyStatus() {
        if(privacyStatus == null){
            return new PrivacyStatusConstants(PrivacyStatusConstants.VISIBLE_ALL);
        }
        return privacyStatus;
    }

    /**
     * @param privateStatus the privacyStatus to set
     */
    public void setPrivacyStatus(PrivacyStatusConstants privacyStatus) {
        this.privacyStatus = privacyStatus;
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * @return the MD5uthorization
     */
    public boolean isMD5uthorization() {
        return MD5uthorization;
    }

    /**
     * @param MD5uthorization the MD5uthorization to set
     */
    public void setMD5uthorization(boolean MD5uthorization) {
        this.MD5uthorization = MD5uthorization;
    }

    /**
     * @return the userClass
     */
    public UserClassConstants getUserClass() {
        if(userClass == null){
            userClass = new UserClassConstants(UserClassConstants.OFFICIAL);
        }
        return userClass;
    }

    /**
     * @param userClass the userClass to set
     */
    public void setUserClass(UserClassConstants userClass) {
        this.userClass = userClass;
    }

    /**
     * @return the botInfo
     */
    public BotInfoConstants getBotInfo() {
        if(botInfo == null){
            botInfo = new BotInfoConstants(BotInfoConstants.NORMAL_BOT);
        }
        return botInfo;
    }

    /**
     * @param botInfo the botInfo to set
     */
    public void setBotInfo(BotInfoConstants botInfo) {
        this.botInfo = botInfo;
    }
}
