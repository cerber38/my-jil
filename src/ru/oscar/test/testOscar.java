package ru.oscar.test;

import ru.oscar.icq.constants.DirectConnectConstants;
import ru.oscar.icq.constants.PrivacyStatusConstants;
import ru.oscar.icq.constants.ProtocolVersionConstants;
import ru.oscar.icq.constants.StatusConstants;
import ru.oscar.icq.constants.StatusFlagConstants;
import ru.oscar.icq.constants.XStatusConstants;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.api.IcqAPI;
import ru.oscar.icq.core.api.events.MessageEvent;
import ru.oscar.icq.core.api.events.SsiAckEvent;
import ru.oscar.icq.core.api.events.XStatusEvent;
import ru.oscar.icq.core.api.listener.ListenerConnection;
import ru.oscar.icq.core.api.listener.ListenerContactList;
import ru.oscar.icq.core.api.listener.ListenerMessages;
import ru.oscar.icq.core.api.listener.ListenerXStatus;
import ru.oscar.icq.setting.Capabilities;
import ru.oscar.icq.setting.OptionsConnecting;

public class testOscar implements ListenerConnection, ListenerMessages, ListenerXStatus, ListenerContactList{
    
    private String sn = "363499099";
    private String password = "R8UYza2b";  
    private Connect c;
    private String title = "title";
    private String description = "description";

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
        c.connect();        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testOscar testOscar = new testOscar();
        testOscar.init();
    }

    @Override
    public void successfulConnected() {
        System.out.println(sn + " online");
    }

    @Override
    public void failureConnection(String message) {
        System.out.println(message);
        
    }

    @Override
    public void breakConnection(String message) {
        System.out.println("break connect " + message);
    }
    
    public void onIncomingMessage(MessageEvent e) {
        System.out.println("sn: " + e.getSenderID() + ", message: " + e.getMessage());
        IcqAPI.sendNormalMessage(c, e.getSenderID(), e.getMessage());
        requestXStatus(e.getSenderID());
    }

    public void onXStatusResponse(XStatusEvent e) {
        System.out.println(":XSTATUS INFO:\nsn: " + e.getSenderID() + ", XStatus: " + e.getXStatus() +
                ", Title: " + e.getTitle() + ", Description: " + e.getDescription());
    }
    
    /**
     * Запрос х-статуса
     * @param sn 
     */
    
    public void requestXStatus(String sn) {
        IcqAPI.requestXStatus(c, sn);
    }
    
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
    
    public void changeXStatus(int xstatus, String title, String description){
        IcqAPI.changeXStatus(c, new XStatusConstants(xstatus, title, description));
    }

    public void isLoadedContactList() {
        System.out.println("Contact list is loaded.");
        System.out.println(c.getContactList().toString());
    }

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
        c.getContactList().addContact(sn, group);
    }
    
    /**
     * Удалим контакт
     * @param sn 
     */
    
    public void removeContact(String sn){
        c.getContactList().removeContact(sn);
    }

}
