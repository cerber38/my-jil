
package ru.oscar.icq.contacts;

/**
 * @author Kornackiy Alexsandr
 */

public class Group {
    
    private int idGroup;
    private int itemID;
    private String name;
    
    public Group(int idGroup, int itemID, String name){
        this.idGroup = idGroup;
        this.itemID = itemID;
        this.name = name;
    }
    
    public Group(int idGroup){
        this.idGroup = idGroup;       
    }    
    
    public boolean isMaster() {
        return idGroup == 0;
    }    

    /**
     * @return the id
     */
    public int getIdGroup() {
        return idGroup;
    }

    /**
     * @return the name
     */
    public String getName() {
        if(idGroup == 0){
            return "Master Group";
        }
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the itemID
     */
    public int getItemID() {
        return itemID;
    }
    
}
