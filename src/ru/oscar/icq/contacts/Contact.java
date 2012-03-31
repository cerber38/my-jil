
package ru.oscar.icq.contacts;

/**
 * @author Kornackiy Alexsandr
 */

public class Contact {
    
    private int id;
    private String name;
    private int groupID;
    private boolean auth;
    
    private String sn;
    
    public Contact(int id, String sn, String name, int groupID, boolean auth){
        this.id = id;
        this.sn = sn;
        this.name = name;
        this.groupID = groupID;
        this.auth = auth;       
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
    
}
