
package ru.oscar.icq.contacts;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kornackiy Alexsandr
 */

public class Group {
    
    private int id;
    private String name;
    private Map<String, Contact> contacts;
    
    public Group(int id, String name){
        contacts = new HashMap<String, Contact>(20);
        this.id = id;
        this.name = id == 0 ? "Master Group" : name;
    }
    
    public Group(int id){
        contacts = new HashMap<String, Contact>(20);
        this.id = id;       
    }
    
    public void putContact(Contact contact){
        contacts.put(contact.getSn(), contact);
    }
    
    public void removeContact(String sn){
        contacts.remove(sn);
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

    /**
     * @return the contacts
     */
    public Map<String, Contact> getContacts() {
        return contacts;
    }
    
}
