
package ru.oscar.icq.contacts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ru.oscar.icq.core.Connect;

/**
 * @author Kornackiy Alexsandr
 */

public class ContactList {
    
    private Connect connect;
    
    private Map<Integer, Group> group; 
    private Map<String, Contact> contacts;
    
    public ContactList(Connect connect){
        this.connect = connect;
        group = new HashMap<Integer, Group>(20);
        contacts = new HashMap<String, Contact>(20);
    }
           
    public void putContacts(Map<String, Contact> contacts,
            Map<Integer, Group> group){
        getGroup().putAll(group);
        getContacts().putAll(contacts);  
        
        // Распределим контакты по группам
        Iterator iterator  = contacts.keySet().iterator();
        while (iterator.hasNext()) {
            String sn = (String)iterator.next();
            Contact c = getContacts().get(sn);
            Group g = getGroup().get(c.getGroupID());
            g.putContact(c);
        }
        
    }    

    /**
     * @return the group
     */
    public Map<Integer, Group> getGroup() {
        return group;
    }

    /**
     * @return the contacts
     */
    public Map<String, Contact> getContacts() {
        return contacts;
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("My contact list:");
        s.append("\n");
    
        Iterator iterator  = getGroup().keySet().iterator();
        while (iterator.hasNext()) {
            int key = (Integer)iterator.next();
            Group g = getGroup().get(key);
            s.append("Group: ");  
            s.append(g.getName()); 
            s.append("\n");
            s.append("Group id: "); 
            s.append(g.getId());             
            s.append("\n");  
            s.append("Contacts:"); 
            s.append("\n");  
            for(Contact c : g.getContacts().values()){
                s.append(c.getSn()); 
                s.append(" ");
                s.append(c.getName()); 
                s.append(" ");
                s.append(c.isAuth()); 
                s.append("\n");  
            } 
            s.append("\n");  
        }  
        return s.toString();
    }    
    
}
