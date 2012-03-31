
package ru.oscar.icq.contacts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.packet.send.ssi.Snac__13_11;
import ru.oscar.icq.packet.send.ssi.Snac__13_12;
import ru.oscar.icq.packet.send.ssi.addContact;
import ru.oscar.icq.packet.send.ssi.removeContact;

/**
 * Класс работает с контакт листом
 * @author Kornackiy Alexsandr
 */

public class ContactList {
    
    private Connect connect;
    
    private Map<Integer, Group> groups; 
    private Map<String, Contact> contacts;
    
    private int maxContactID;
    
    public ContactList(Connect connect){
        this.connect = connect;
        groups = new HashMap<Integer, Group>(20);
        contacts = new HashMap<String, Contact>(20);
    }
    
    /**
     * Получем набор груп и контактов, сохраняем их
     * @param contacts
     * @param group 
     */
           
    public void putContacts(Map<String, Contact> contacts,
            Map<Integer, Group> group){
        getGroups().putAll(group);
        getContacts().putAll(contacts);  
        
        // Распределим контакты по группам
        Iterator iterator  = contacts.keySet().iterator();
        while (iterator.hasNext()) {
            String sn = (String)iterator.next();
            Contact c = getContacts().get(sn);
            Group g = getGroups().get(c.getGroupID());
            g.putContact(c);
            if(c.getId() > maxContactID){
                maxContactID = c.getId();
            }
        }
        
    }   
    
    /**
     * Добавить контакт
     * @param sn
     * @param groupName 
     */
    
    public void addContact(String sn, String groupName){
        if(isContact(sn)){
            return;
        }
        if(!isGroup(groupName)){
            return;
        }       
        Group g = getGroup(groupName);
        maxContactID++;
        Contact c = new Contact(maxContactID, sn, sn, g.getId());
        connect.sendPacket(new Snac__13_11());
        connect.sendPacket(new addContact(c));
        connect.sendPacket(new Snac__13_12());
        g.putContact(c);
        getContacts().put(sn, c);
    }
    
    /**
     * Удалить контакт
     * @param sn 
     */
    
    public void removeContact(String sn){
        if(!isContact(sn)){
            return;
        }  
        Contact c = getContacts().get(sn);
        connect.sendPacket(new Snac__13_11());
        connect.sendPacket(new removeContact(c));
        connect.sendPacket(new Snac__13_12());        
        getContacts().remove(sn);
        // Удалим из группы
        Group g = getGroup(c.getGroupID());
        g.removeContact(sn);
    }

    
    /**
     * Получим группу по id
     * @param id
     * @return 
     */
    
    public Group getGroup(int id){
        return groups.get(id);
    }
    
    /**
     * Получим группу по названию
     * @param groupName
     * @return 
     */
    
    public Group getGroup(String groupName){
        Iterator iterator  = getGroups().keySet().iterator();
        while (iterator.hasNext()) {
            int id = (Integer)iterator.next();
            Group g = getGroups().get(id);
            if(g.getName().equals(groupName)){
                return g;
            }
        }
        return null;
    }
    
    public boolean isContact(String sn){
        return contacts.containsKey(sn);
    }    
    
    /**
     * Есть такая группа?
     * @param groupName
     * @return 
     */
    
    public boolean isGroup(String groupName){
        return getGroup(groupName) != null;
    }

    /**
     * Набор групп
     * @return the group
     */
    public Map<Integer, Group> getGroups() {
        return groups;
    }

    /**
     * Набор контактов
     * @return the contacts
     */
    public Map<String, Contact> getContacts() {
        return contacts;
    }
    
    /**
     * Представи текстовый контакт лист
     * @return 
     */
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("My contact list:");
        s.append("\n");
        s.append("Number of contacts: ");
        s.append(getContacts().size());
        s.append("\n");
        
        Iterator iterGroup  = getGroups().keySet().iterator();
        while (iterGroup.hasNext()) {
            int id = (Integer)iterGroup.next();
            Group g = getGroups().get(id);
            s.append("Group: ");  
            s.append(g.getName()); 
            s.append("\n");
            s.append("Group id: "); 
            s.append(g.getId());             
            s.append("\n");  
            s.append("Contacts:"); 
            s.append("\n");  
            Iterator iterContact  = getGroups().get(id).getContacts().keySet().iterator();
            while (iterContact.hasNext()) {
                String sn = (String)iterContact.next();
                Contact c = getContacts().get(sn);
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
