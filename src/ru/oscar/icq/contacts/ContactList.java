
package ru.oscar.icq.contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ru.oscar.icq.constants.SsiConstants;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.packet.send.meta.SearchByUin;
import ru.oscar.icq.packet.send.ssi.SsiService;
import ru.oscar.icq.util.StringUtil;

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
     * @param maxContactID  
     */
           
    public void putContacts(Map<String, Contact> contacts,
            Map<Integer, Group> group, int maxContactID){
        
        getGroups().putAll(group);
        getContacts().putAll(contacts);  
        
        this.maxContactID = maxContactID;
        
    }   
    
    /**
     * Добавить контакт
     * @param sn
     * @param name 
     * @param groupID
     * @param auth  
     */
    
    public void addContact(String sn, String name, int groupID, boolean auth){     
        maxContactID++;
        Contact c = new Contact(maxContactID, sn, name, groupID, auth);
        SsiService ssi = new SsiService(connect, c, SsiConstants.SSI_ADD);
        ssi.init();        
        getContacts().put(sn, c);
        if(auth){
            // authorization request
        }
    }

    private void addContact(String sn, int groupID){   
        if(isContact(sn)){
            System.err.println("Contact "+ sn +" has been added to your contact list.");
            return;
        }      
        maxContactID++;
        Contact c = new Contact(maxContactID, sn, sn, groupID);
        SsiService ssi = new SsiService(connect, c, SsiConstants.SSI_ADD);
        ssi.init();
        getContacts().put(sn, c);        
    }   
    
    public void addContact(String sn, String groupName){          
        if(getGroup(groupName) == null){
            addContact(sn, 0);
        } else {
            addContact(sn, getGroup(groupName).getIdGroup());
        }       
    }

    public void addContact(String sn){   
        if(isContact(sn)){
            System.err.println("Contact "+ sn +" has been added to your contact list.");
            return;
        }  
        maxContactID++;
        Contact c = new Contact(maxContactID, sn, sn, 0);
        SsiService ssi = new SsiService(connect, c, SsiConstants.SSI_ADD);
        ssi.init();
        getContacts().put(sn, c);          
    }    
    
    /**
     * Проверим контакт перед добавлением
     * @param sn
     * @param groupID  
     */
    
    public void checkContact(String sn, int groupID){      
        if(isContact(sn)){
            System.err.println("Contact "+ sn +" has been added to your contact list.");
            return;
        }      
        connect.sendPacket(new SearchByUin(sn, connect.getSN(), groupID));
    }
    
    public void checkContact(String sn, String groupName){ 
        if(getGroup(groupName) == null){
            checkContact(sn, 0);
        } else {
            checkContact(sn, getGroup(groupName).getIdGroup());
        }
    }    
    
    /**
     * Удалить контакт
     * @param sn 
     */
    
    public void removeContact(String sn){
        if(!isContact(sn)){
            System.err.println("Contact "+ sn +" is not in contact list.");
            return;
        }  
        Contact c = getContact(sn);      
        SsiService ssi = new SsiService(connect, c, SsiConstants.SSI_REMOVE);
        ssi.init();
        getContacts().remove(sn);
    }
    
    /**
     * Переименовать контакт
     * @param sn
     * @param newName 
     */
    
    public void renameContact(String sn, String newName){
        Contact c = getContact(sn);        
//        connect.sendPacket(new SetNameContact(c));
        if(StringUtil.isEmpty(newName)){
            System.err.println("Incorrect contact nick.");
            return;            
        }
        c.setName(newName);
        SsiService ssi = new SsiService(connect, c, SsiConstants.SSI_UPDATE);
        ssi.init();
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
     * Получить контакт 
     * @param sn
     * @return 
     */
    
    public Contact getContact(String sn){
        return contacts.get(sn);
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
    
    public boolean isGroup(int groupID){
        return getGroup(groupID) != null;
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
     * Распределяем контакты по групам сортируя их по статусам
     * @return 
     */
    
    public List<Contact> sorterGroup(){
        
        List<Contact> list = new ArrayList<Contact>(contacts.values());
        
        Collections.sort(list, new Comparator<Contact> () {
        
            @Override
            public int compare(Contact c1, Contact c2) {
                int k = c1.getGroupID() - c2.getGroupID();
                return k == 0 ? c1.getStatus().getCode() - c2.getStatus().getCode() : k;
            }
        });         
        
        return list;
    }
    
    /**
     * Сортируем контакты по статусам
     * @return 
     */
    
    public List<Contact> sorterStatus(){
        
        List<Contact> list = new ArrayList<Contact>(contacts.values());
        
        Collections.sort(list, new Comparator<Contact> () {
        
            @Override
            public int compare(Contact c1, Contact c2) {
                int k = c1.getStatus().getCode() - c2.getStatus().getCode();
                return k == 0 ? c1.getId() - c2.getId() : k;
            }
        });         
        
        return list;
    }
    
    }
