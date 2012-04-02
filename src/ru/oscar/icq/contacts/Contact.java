
package ru.oscar.icq.contacts;

import ru.oscar.icq.constants.StatusConstants;
import ru.oscar.icq.constants.XStatusConstants;

/**
 * @author Kornackiy Alexsandr
 */

public class Contact {
    
    private int id;
    private String name;
    private int groupID;
    private boolean auth;
    private StatusConstants status;
    private XStatusConstants xstatus;
    
    private String sn;
    
    public Contact(int id, String sn, String name, int groupID, boolean auth){
        this.id = id;
        this.sn = sn;
        this.name = name;
        this.groupID = groupID;
        this.auth = auth;  
        status = new StatusConstants(StatusConstants.STATUS_OFFLINE);
        xstatus = new XStatusConstants(XStatusConstants.NONE);
    }    

    public Contact(int id, String sn, String name, int groupID){
        this.id = id;
        this.sn = sn;
        this.name = name;
        this.groupID = groupID;       
    }    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    /**
     * @return the auth
     */
    public boolean isAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    /**
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @return the status
     */
    public StatusConstants getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusConstants status) {
        this.status = status;
    }

    /**
     * @return the xstatus
     */
    public XStatusConstants getXstatus() {
        return xstatus;
    }

    /**
     * @param xstatus the xstatus to set
     */
    public void setXstatus(XStatusConstants xstatus) {
        this.xstatus = xstatus;
    }
    
}
