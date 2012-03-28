
package ru.oscar.icq.setting;

import ru.oscar.icq.constants.DirectConnectConstants;
import ru.oscar.icq.constants.PrivacyStatusConstants;
import ru.oscar.icq.constants.ProtocolVersionConstants;
import ru.oscar.icq.constants.StatusConstants;
import ru.oscar.icq.constants.StatusFlagConstants;
import ru.oscar.icq.constants.XStatusConstants;

/**
 * Класс хранит необходимые опции при работе с протоколом.
 * @author Kornackiy Alexsandr
 */

public class OptionsConnecting {
    
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
       
}
