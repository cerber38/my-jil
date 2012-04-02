
package ru.oscar.icq.contacts;

/**
 * @author Kornackiy Alexsandr
 */

public class Group {
    
    private int id;
    private String name;
    
    public Group(int id, String name){
        this.id = id;
        this.name = id == 0 ? "Master Group" : name;
    }
    
    public Group(int id){
        this.id = id;       
    }    
    
    public boolean isMaster() {
        return id == 0;
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
    
}
