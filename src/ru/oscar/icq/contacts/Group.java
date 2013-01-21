
package ru.oscar.icq.contacts;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kornackiy Alexsandr
 */

public class Group {
    
    private int idGroup;
    private String name;
    
    private Map<String, Contact> contacts = null;
    
    public Group(int idGroup, String name){
        this.idGroup = idGroup;
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
        if(isMaster()){
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
     * 
     * @param c 
     */
    
    public void putContact(Contact c){
        if(!isContacts()){
            contacts = new HashMap<String, Contact>(20);
        }else{
            contacts.put(c.getSn(), c);
        }
    }
    
    /**
     * 
     * @param sn
     * @return 
     */
    
    public Contact getContact(String sn){
        if(isContact(sn)){
            return contacts.get(sn);
        } else {
            return null;
        }
    }
    
    /**
     * 
     * @param sn 
     */
    
    public void removeContact(String sn){
        if(isContact(sn)){
            contacts.remove(sn);
        }
    }
    
    /**
     * 
     * @param sn
     * @return 
     */
    
    public boolean isContact(String sn){
        if(!isContacts()){
            return false;
        }
        return contacts.containsKey(sn);
    }  
    
    /**
     * 
     * @return the contacts
     */
    public Map<String, Contact> getContacts() {
        return contacts;
    }   
    
    /**
     * 
     * @return 
     */
    
    public boolean isContacts() {
        return contacts != null;
    } 
    
    /**
     * 
     * @param id
     * @return 
     */
    
    public boolean isExistId(int id){
        if(!isContacts()){
            return false;
        }
        for(Contact c : getContacts().values()){
            if(c.getId() == id){
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @return 
     */
    
    public int contactsCount(){
        if(contacts == null){
            return 0;
        }
        return contacts.size();
    }

}
