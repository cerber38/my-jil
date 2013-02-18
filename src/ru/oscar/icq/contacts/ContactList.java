
package ru.oscar.icq.contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ru.oscar.icq.constants.SsiConstants;
import ru.oscar.core.Connect;
import ru.oscar.icq.packet.send.meta.RequestFindByUin;
import ru.oscar.icq.packet.send.ssi.AddsContact;
import ru.oscar.icq.packet.send.ssi.AddsGroup;
import ru.oscar.icq.packet.send.ssi.AddsList;
import ru.oscar.icq.packet.send.ssi.RemoveContact;
import ru.oscar.icq.packet.send.ssi.RemoveGroup;
import ru.oscar.icq.packet.send.ssi.RemoveList;
import ru.oscar.icq.packet.send.ssi.BeginTransaction;
import ru.oscar.icq.packet.send.ssi.FinishTransaction;
import ru.oscar.icq.packet.send.ssi.SendFutureAuthorization;
import ru.oscar.icq.packet.send.ssi.DeleteYourself;
import ru.oscar.icq.packet.send.ssi.SendAuthorizationRequest;
import ru.oscar.icq.packet.send.ssi.SendAuthorizationReply;
import ru.oscar.icq.packet.send.ssi.YouWereAdded;
import ru.oscar.icq.packet.send.ssi.UpdateContact;
import ru.oscar.icq.packet.send.ssi.UpdateGroup;
import ru.oscar.icq.packet.send.ssi.UpdateGroups;
import ru.oscar.util.StringUtil;
import ru.oscar.util.Util;

/**
 * Класс работает с контакт листом
 * @author Kornackiy Alexsandr
 */

public class ContactList {
    
    private Connect connect;
    
    private Map<Integer, Group> groups; 
    
    private ArrayList<Privacy> privacyList;  
    
    public ContactList(Connect connect){
        this.connect = connect;
        groups = new HashMap<Integer, Group>(20);
        privacyList = new ArrayList<Privacy>(10);           
    }
    
    /**
     * Получем набор групп с контактами
     * @param group
     * @param visibleList_ 
     * @param invisibleList_
     * @param ignoreList_  
     */
           
    public void putGroups(Map<Integer, Group> group, ArrayList<Privacy> privacyList_){      
        groups.putAll(group);  
        privacyList.addAll(privacyList_);
    }   
    
    /**
     * Добавить контакт
     * @param sn
     * @param name 
     * @param groupID
     * @param auth  
     */
    
    public void addContact(String sn, String name, int groupID, boolean auth){ 
        Contact contact = new Contact(createRandomId(), sn, name, groupID, auth);
   
        Group g = groups.get(groupID);
        g.putContact(contact);
        groups.put(groupID, g);
               
        addContact(contact);
        
        if(!auth){
            // authorization request
            sendAuthRequest(sn, "Authorizes me please.");
        } else {
            // send you-were-added
            //sendYouWereAdded(sn);
        }       
    }

    private void addContact(Contact contact){      
        //begin transaction
        connect.sendPacket(new BeginTransaction());
        //Add contacts to the server's contact list
        connect.sendPacket(new AddsContact(contact));
        // Update group
        connect.sendPacket(new UpdateGroup(groups.get(contact.getGroupID())));
        //finish transaction
        connect.sendPacket(new FinishTransaction());             
    }   
    
    /**
     * Добавить группу
     * @param name 
     */

    public void addGroup(String name){
        //begin transaction
        connect.sendPacket(new BeginTransaction());
        //Add group to the server's contact list
        int id = createRandomId();
        connect.sendPacket(new AddsGroup(new Group(id, name)));
        //update groups
        if(groups.size() > 0){
            ArrayList groupsID = new ArrayList(groups.size()); 
            for(Group group : groups.values()){
                groupsID.add(group.getIdGroup());
            }       
            connect.sendPacket(new UpdateGroups(groupsID));   
        }
        //finish transaction
        connect.sendPacket(new FinishTransaction()); 
        groups.put(id, new Group(id, name));
    }
    
    /**
     * Проверим контакт перед добавлением
     * @param sn
     * @param groupID  
     */
    
    public void checkContact(String sn, int groupID){         
        connect.sendPacket(new RequestFindByUin(sn, connect.getSN(), groupID));
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
        Contact c = getContact(sn); 
        //begin transaction
        connect.sendPacket(new BeginTransaction());
        //Remove contacts to the server's contact list
        connect.sendPacket(new RemoveContact(c));
        // Update group
        connect.sendPacket(new UpdateGroup(groups.get(c.getGroupID())));        
        //finish transaction
        connect.sendPacket(new FinishTransaction()); 
        groups.get(c.getGroupID()).removeContact(sn);
    }
    
    /**
     * Удалить группу
     * @param g 
     */
    
    public void removeGroup(Group g){     
        //begin transaction
        connect.sendPacket(new BeginTransaction());
        //Remove group to the server's contact list
        connect.sendPacket(new RemoveGroup(g)); 
        //update groups
        ArrayList groupsID = new ArrayList(groups.size()); 
        for(Group group : groups.values()){
            groupsID.add(group.getIdGroup());
        }
        connect.sendPacket(new UpdateGroups(groupsID));     
        //finish transaction
        connect.sendPacket(new FinishTransaction()); 
        groups.remove(g.getIdGroup());
    }
    
    /**
     * Удалить группу
     * Перед удалением перенесет контакты в другую группу
     * @param g
     * @param g1 - группа в которую переносим контакты
     */
    
    public void removeGroup(Group g, Group g1){   
        if(g1 != null){
            for(Contact contact : g.getContacts().values()){
                changeContactGroup(contact.getSn(), g1);
            }
        }
        //begin transaction
        connect.sendPacket(new BeginTransaction());
        //Remove group to the server's contact list
        connect.sendPacket(new RemoveGroup(g)); 
        //update groups
        ArrayList groupsID = new ArrayList(groups.size()); 
        for(Group group : groups.values()){
            groupsID.add(group.getIdGroup());
        }
        connect.sendPacket(new UpdateGroups(groupsID));     
        //finish transaction
        connect.sendPacket(new FinishTransaction()); 
        groups.remove(g.getIdGroup());        
    }
    
    /**
     * Переименовать контакт
     * @param sn
     * @param newName 
     */
    
    public void renameContact(String sn, String newName){      
        if(StringUtil.isEmpty(newName)){
            System.err.println("Incorrect contact nick.");
            return;            
        }
       Contact c = getContact(sn); 
       c.setName(newName);
       groups.get(c.getGroupID()).putContact(c);
       connect.sendPacket(new UpdateContact(c));
    }
    
    /**
     * Переминовать группу
     */
    
    public void renameGroup(Group g, String newName){      
        if(StringUtil.isEmpty(newName)){
            System.err.println("Incorrect contact nick.");
            return;            
        }
       g.setName(newName);
       groups.put(g.getIdGroup(), g);
       connect.sendPacket(new UpdateGroup(g));
    }    
    
    /**
     * Добавить в "список"
     * @param sn
     */
    
    public void addList(String sn, int list){
        int id = createRandomId(); 
        switch(list){
            case SsiConstants.TYPE_VISIBLE:                 
                privacyList.add(new Privacy(sn, id, SsiConstants.TYPE_VISIBLE));
                connect.sendPacket(new AddsList(sn, id, SsiConstants.TYPE_VISIBLE));
            break;
            case SsiConstants.TYPE_INVISIBLE:              
                privacyList.add(new Privacy(sn, id, SsiConstants.TYPE_INVISIBLE));
                connect.sendPacket(new AddsList(sn, id, SsiConstants.TYPE_INVISIBLE));
            break;
            case SsiConstants.TYPE_IGNORE_LIST:
                privacyList.add(new Privacy(sn, id, SsiConstants.TYPE_IGNORE_LIST));
                connect.sendPacket(new AddsList(sn, id, SsiConstants.TYPE_IGNORE_LIST));
            break;                
        }       
    }
    
    /**
     * Удалить из "списка"
     * @param sn
     */
    
    public void removeList(String sn){
        Privacy p = getPrivacyList(sn);
        if(p == null){
            return;// TODO: этого быть не должно
        }
        switch(p.getType()){
            case SsiConstants.TYPE_VISIBLE:  
                connect.sendPacket(new RemoveList(sn, p.getId(), SsiConstants.TYPE_VISIBLE));
            break;
            case SsiConstants.TYPE_INVISIBLE:
                connect.sendPacket(new RemoveList(sn, p.getId(), SsiConstants.TYPE_INVISIBLE));
            break;
            case SsiConstants.TYPE_IGNORE_LIST:
                connect.sendPacket(new RemoveList(sn, p.getId(), SsiConstants.TYPE_IGNORE_LIST));
            break;                
        }
        removePrivacyList(p);
    }
    
    /**
     * Найдет контакт в приватных списках
     * @param sn
     * @return 
     */
    
    public Privacy getPrivacyList(String sn) {
        for (Privacy p : privacyList) {
            if (p.getSn().equals(sn)) {
                return p;
            }
        }
        return null;
    }    
    
    /**
     * Перенесем контакт в другую группу
     * @param sn
     * @param groupNew - куда переносим
     */
    
    public void changeContactGroup(String sn, Group groupNew){
       Contact c = getContact(sn); 
       //begin transaction
       connect.sendPacket(new BeginTransaction());  
       //Remove contacts to the server's contact list
       connect.sendPacket(new RemoveContact(c));       
       c.setGroupID(groupNew.getIdGroup());
       groups.get(c.getGroupID()).putContact(c);
       //Add contacts to the server's contact list
       connect.sendPacket(new AddsContact(c));       
       // Update group
       connect.sendPacket(new UpdateGroup(groups.get(c.getGroupID())));        
       //finish transaction
       connect.sendPacket(new FinishTransaction());       
    }
   
    /**
     * Отправить вас добавили
     * @param sn 
     */
    
    public void sendYouWereAdded(String sn){
        connect.sendPacket(new YouWereAdded(sn));
    }
    
    /**
     * Запрос авторизации
     * @param sn
     * @param message 
     */
    
    public void sendAuthRequest(String sn, String message){
        connect.sendPacket(new SendAuthorizationRequest(sn, message));
    } 
    
    /**
     * Ответ на запрос авторизации
     * @param sn
     * @param message
     * @param auth 
     */
    
    public void sendAuthReply(String sn, String message, boolean auth){
        connect.sendPacket(new SendAuthorizationReply(sn, message, auth));
    }     
    
    /**
     * Позволить добавить нас
     * @param sn
     * @param message 
     */
    
    public void sendFutureAuth(String sn, String message){
        connect.sendPacket(new SendFutureAuthorization(sn, message));
    }  
    
    /**
     * Удалить себя из кл
     * @param sn 
     */
    
    public void sendDeleteYourself(String sn){
        connect.sendPacket(new DeleteYourself(sn));
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
    
    /**
     * 
     * @param sn
     * @return
     */
    public Contact getContact(String sn){
        Iterator iterator  = getGroups().keySet().iterator();
        while (iterator.hasNext()) {
            int id = (Integer)iterator.next();
            Group g = getGroups().get(id);
            if(g.isContact(sn)){
                return g.getContact(sn);
            }
        }
        return null;
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
     * Сортируем контакты по статусам
         * @param g 
         * @return 
     */
    
    public List<Contact> sorterStatus(Group g){

        List<Contact> list = new ArrayList<Contact>(g.getContacts().values());
        
        Collections.sort(list, new Comparator<Contact> () {
        
            @Override
            public int compare(Contact c1, Contact c2) {
                int k = c1.getStatus().getCode() - c2.getStatus().getCode();
                return k == 0 ? c1.getId() - c2.getId() : k;
            }
        });         
        
        return list;
    }
    
    // From miranda source
    
    public int createRandomId() {
        int id;
        do {
            // Max value is probably 0x7FFF, lowest value is unknown.
            // We use range 0x1000-0x7FFF.          
            id = Util.nextRandInt() % 0x6FFF + 0x1000;
        } while (isExistId(id));
        return id;
    }
    
    private boolean isExistId(final int id) {
        if (id == connect.getOptionsConnect().getPrivacyStatusId()) {
            return true;
        }       
        Iterator iteratorG  = getGroups().keySet().iterator();
        
        while (iteratorG.hasNext()) {
            int idGroup = (Integer)iteratorG.next();
            if(idGroup == id){
                return true;
            }    
            if(groups.get(idGroup).isExistId(id)){
                return true;
            }
        }
        
        return false;
    }

    /**
     * @return the privacyList
     */
    public ArrayList getPrivacyList() {
        return privacyList;
    }
    
    private void removePrivacyList(Privacy p){
        if(privacyList.contains(p)){
            privacyList.remove(p);
        }
    }  
    
    }
