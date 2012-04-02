package ru.oscar.test;

import java.util.Iterator;
import ru.oscar.icq.constants.DirectConnectConstants;
import ru.oscar.icq.constants.MessageTypesConstants;
import ru.oscar.icq.constants.PrivacyStatusConstants;
import ru.oscar.icq.constants.ProtocolVersionConstants;
import ru.oscar.icq.constants.StatusConstants;
import ru.oscar.icq.constants.StatusFlagConstants;
import ru.oscar.icq.constants.XStatusConstants;
import ru.oscar.icq.contacts.Contact;
import ru.oscar.icq.contacts.ContactList;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.api.IcqAPI;
import ru.oscar.icq.core.api.events.MessageEvent;
import ru.oscar.icq.core.api.events.MetaShortInfoEvent;
import ru.oscar.icq.core.api.events.MetaSearchSn;
import ru.oscar.icq.core.api.events.SsiAckEvent;
import ru.oscar.icq.core.api.events.XStatusEvent;
import ru.oscar.icq.core.api.listener.ListenerConnection;
import ru.oscar.icq.core.api.listener.ListenerContactList;
import ru.oscar.icq.core.api.listener.ListenerMessages;
import ru.oscar.icq.core.api.listener.ListenerMetaInfo;
import ru.oscar.icq.core.api.listener.ListenerXStatus;
import ru.oscar.icq.setting.Capabilities;
import ru.oscar.icq.setting.OptionsConnecting;

public class testOscar implements ListenerConnection, ListenerMessages, ListenerXStatus, ListenerContactList,
        ListenerMetaInfo{
    
    private String sn = "363499099";
    private String password = "R8UYza2b";     
    private String title = "title";
    private String description = "description";
    
    private Connect c;

    public void init(){
        /*Установим параметры для подключения*/
        OptionsConnecting options = new OptionsConnecting();
        // TODO: приватный статус установится тока с загрузкой контакт листа!
        options.setPrivacyStatus(new PrivacyStatusConstants(PrivacyStatusConstants.VISIBLE_ALL));
        options.setStatus(new StatusConstants(StatusConstants.STATUS_ONLINE));
        options.setXstatus(new XStatusConstants(XStatusConstants.XSTATUS_DRINKING_BEER, title, description));
        options.setStatusFlag(new StatusFlagConstants(StatusFlagConstants.STATUS_FLAG_WEBAWARE));
        options.setDirectConnect(new DirectConnectConstants(DirectConnectConstants.DC_NORMAL));
        options.setProtocolVersion(new ProtocolVersionConstants(ProtocolVersionConstants.DCP_ICQLITE));
        options.setCapabilities(new Capabilities(Capabilities.JIL));
        options.setContactList(true);
        options.setTyping(false);
        c = new Connect(sn, password, options);       
        c.putListenerConnection(this);
        c.putListenerMessages(this);
        c.putListenerXStatus(this);
        c.putListenerContactList(this);
        c.putListenerMetaInfo(this);
        c.connect();        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testOscar testOscar = new testOscar();
        testOscar.init();
    }
    
    /**
     * Успешная авторизация
     */

    @Override
    public void successfulConnected() {
        System.out.println(sn + " - online");
    }
    
    /**
     * Авторизация не удалась
     * @param message 
     */

    @Override
    public void failureConnection(String message) {
        System.out.println(message);
        
    }
    
    /**
     * Разрыв соединения
     * @param message 
     */

    @Override
    public void breakConnection(String message) {
        System.out.println("Break connect " + message);
    }
    
    /**
     * Входящее сообщение
     * @param e 
     */
    public void onIncomingMessage(MessageEvent e) {
        System.out.println("Incoming message: " + e.getSenderID() + " >> " + e.getMessage());
        sendMessage(e.getSenderID(), e.getMessage(), 1);    
    }
    
    /**
     * Отправить сообщение
     * @param sn
     * @param messgae
     * @param chanel 
     */
    
    public void sendMessage(String sn, String messgae, int chanel){
        switch(chanel){
            case 1:
                IcqAPI.sendNormalMessage(c, sn, messgae);// обычное
                break;
            case 2:
                IcqAPI.sendExtendedMessage(c, sn, messgae);// расширенное
                break;
            case 4:
                IcqAPI.sendOldTypeMessage(c, sn, messgae, new MessageTypesConstants(MessageTypesConstants.MESSAGE_PLAIN));// старый тип или url
                break;                
        }
    }
    
    /**
     * Ответ на запрос х-statusa у пользователя
     * @param e 
     */

    public void onXStatusResponse(XStatusEvent e) {
        System.out.println(":XSTATUS INFO:\nsn: " + e.getSenderID() + ", XStatus: " + e.getXStatus() +
                ", Title: " + e.getTitle() + ", Description: " + e.getDescription());
        c.getContactList().getContact(e.getSenderID()).setXstatus(new XStatusConstants(e.getXStatus(), e.getTitle(), e.getDescription()));
    }
    
    /**
     * Запрос х-статуса
     * @param sn 
     */
    
    public void requestXStatus(String sn) {
        IcqAPI.requestXStatus(c, sn);
    }
    
    /**
     * Запрос короткой информации о пользователе
     * @param sn 
     */
    
    public void requestShortUserInfo(String sn) {
        IcqAPI.requestShortUserInfo(c, sn);
    }
    
    /**
     * Поиск пользователя по uin
     * @param sn 
     */
    
    public void requestSearchByUin(String sn) {
        IcqAPI.requestSearchByUin(c, sn);
    }    
    
    /**
     * Сменить статус
     * @param status 
     */
    
    public void changeStatus(int status){
        int i = StatusConstants.STATUS_ONLINE;
        switch (status)  {
            case 0 :
                i = StatusConstants.STATUS_ONLINE; 
                break; 
            case 1 :
                i = StatusConstants.STATUS_LUNCH;
                break; 
            case 2 :
                i = StatusConstants.STATUS_EVIL;
                break; 
            case 3 :
                i = StatusConstants.STATUS_DEPRESSION; 
                break; 
            case 4 :
                i = StatusConstants.STATUS_HOME; 
                break; 
            case 5 :
                i = StatusConstants.STATUS_WORK; 
                break; 
            case 6 :
                i = StatusConstants.STATUS_AWAY;
                break; 
            case 7 : 
                i = StatusConstants.STATUS_NA;
                break; 
            case 8 : 
                i = StatusConstants.STATUS_OCCUPIED; 
                break; 
            case 9 : 
                i = StatusConstants.STATUS_DND; 
                break; 
            case 10 :
                i = StatusConstants.STATUS_FREE4CHAT; 
                break; 
            case 11 :
                i = StatusConstants.STATUS_INVISIBLE; 
                break; 
            case 12 :
                i = StatusConstants.STATUS_INVISIBLE_ALL; 
                break; 
            case 13 :
                i = StatusConstants.STATUS_OFFLINE; 
                break;             
        }   
        IcqAPI.changeStatus(c, new StatusConstants(i));
    }
    
    /**
     * Сменить флаг статус
     * @param statusFlag 
     */
    
    public void changeStatusFlag(int statusFlag){
        int i = StatusFlagConstants.STATUS_FLAG_NONE;
        switch (statusFlag)  {
            case 0 :
                i = StatusFlagConstants.STATUS_FLAG_WEBAWARE;
                break;
            case 1 :
                i = StatusFlagConstants.STATUS_FLAG_SHOWIP;
                break;
            case 2 : 
                i = StatusFlagConstants.STATUS_FLAG_BIRTHDAY;
                break;
            case 3 :
                i = StatusFlagConstants.STATUS_FLAG_WEBFRONT;
                break;
            case 4 :
                i = StatusFlagConstants.STATUS_FLAG_DCDISABLED;
                break;
            case 5 :
                i = StatusFlagConstants.STATUS_FLAG_DCAUTH;
                break;
            case 6 :
                i = StatusFlagConstants.STATUS_FLAG_DCCONT;
                break;
            case 7 :
                i = StatusFlagConstants.STATUS_FLAG_DCALLOWED;
                break;
            case 8 :
                i = StatusFlagConstants.STATUS_FLAG_NONE; 
                break;
        }        
        IcqAPI.changeStatusFlag(c, new StatusFlagConstants(i));
    }
    
    /**
     * Сменить приватный статус
     * @param status 
     */
    
    public void changePrivateStatus(int status) {               
        int i = PrivacyStatusConstants.VISIBLE_ALL;
        switch (status)  {            
            case 1 : 
                i = PrivacyStatusConstants.VISIBLE_ALL;
                break;
            case 2 :
                i = PrivacyStatusConstants.INVISIBLE_ALL; 
                break;
            case 3 :
                i = PrivacyStatusConstants.VISIBLE_TO_VISIBLE_LIST;
                break;
            case 4 : 
                i = PrivacyStatusConstants.INVISIBLE_TO_INVISIBLE_LIST;
                break;
            case 5 : 
                i = PrivacyStatusConstants.VISIBLE_TO_CONTACT_LIST;
                break;            
                }        
        IcqAPI.changePrivacyStatus(c, new PrivacyStatusConstants(i));        
    }    
    
    /**
     * Изменить х-статус
     * @param xstatus
     * @param title
     * @param description 
     */
    
    public void changeXStatus(int xstatus, String title, String description){
        IcqAPI.changeXStatus(c, new XStatusConstants(xstatus, title, description));
    }
    
    /**
     * Контакт лист загружен
     */

    public void isLoadedContactList() {
        System.out.println("Contact list is loaded.");
        System.out.println(myContactList());
    }
    
    /**
     * Ответ на ssi запросы
     * @param e 
     */

    public void onSsiAck(SsiAckEvent e) {
        System.out.println("On ssi modifying ask:");
        System.out.println("(" + e.getResults().getCode() + ") " + e.getResults().toString());    
    }
    
    /**
     * Добавим контакт в группу
     * @param sn
     * @param group 
     */
    
    public void addContact(String sn, String group){
        c.getContactList().checkContact(sn, group);
    }
    
    /**
     * Удалим контакт
     * @param sn 
     */
    
    public void removeContact(String sn){
        c.getContactList().removeContact(sn);
    }
    
    /**
     * Представи текстовый контакт лист
     * @return 
     */    
    
    public String myContactList(){
        if(!c.getOptionsConnect().isContactList()){
            return "Contact list is not loaded!";
        }
        StringBuilder s = new StringBuilder();
        
        ContactList list = c.getContactList();
        
        Iterator iterator  = list.sorterGroup().iterator();
        
        Integer f = null;
        
        s.append("My contact list:");
        s.append("\n\n");
        
        while (iterator.hasNext()) {
            Contact c = (Contact) iterator.next();
            if(!((Integer) c.getGroupID()).equals(f)){
            s.append("Group: ").append(list.getGroup(c.getGroupID()).getName()); 
            s.append("\n\n");   
            }
            s.append("Sn: ").append(c.getSn()).
                    append(" Nick: ").append(c.getName()).
                    append(" ").
                    append("(authorization: ").
                    append(c.isAuth() == true ? "required)" : "no required)");
            s.append("\n");   
            s.append("\n");   
            f = c.getGroupID();
        }        
        
        return s.toString();        
    }
    
    /**
     * Ответ на запрос короткой информации о пользователе
     * @param e 
     */

    public void onShortUserInfo(MetaShortInfoEvent e) {
        System.out.println("Information:\n" +
                "\nNick = " + e.getNickName() +
                "\nFirst name = " + e.getFirstName() +
                "\nLast name = " + e.getLastName() +
                "\nLast email = " + e.getEmail() +
                "\nAuth = " + e.isAuth());           
    }
    
    /**
     * Результаты поиска
     * @param e 
     */

    public void onSearchSn(MetaSearchSn e) {
        if(e.isContactCheck()){
            if(e.isSearch()){
                c.getContactList().addContact(e.getFoundUin(), e.getNickName(), e.getGroupID(), e.isAuth());
            } else {
                System.out.println("Contact " + e.getFoundUin() + " does not exist.");
            }
        } else if(e.isSearch()){
            System.out.println("Search Results for:\n" +
                    "\nUIN/SN = " + e.getFoundUin() +
                    "\nNick = " + e.getNickName() +
                    "\nFirst name = " + e.getFirstName() +
                    "\nLast name = " + e.getLastName() +
                    "\nAge = " + e.getAge() +
                    "\nGender = " + e.getGender() +
                    "\nAuth = " + e.isAuth() +
                    // TODO: offline and online (web awera)
                    "\nOnlline status = " + e.getOnlineStatus());
        } else {
            System.out.println("Contact "+ e.getFoundUin () +" not found.");
        }
    }

}
