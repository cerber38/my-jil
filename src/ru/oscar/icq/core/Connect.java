
package ru.oscar.icq.core;

import java.io.IOException;
import java.util.ArrayList;
import ru.oscar.icq.Cookies;
import ru.oscar.icq.Flap;
import ru.oscar.icq.constants.LoginErrorConstants;
import ru.oscar.icq.contacts.ContactList;
import ru.oscar.icq.core.Client;
import ru.oscar.icq.core.PacketRecognizer;
import ru.oscar.icq.core.api.listener.ListenerConnection;
import ru.oscar.icq.core.api.listener.ListenerContactList;
import ru.oscar.icq.core.api.listener.ListenerMessages;
import ru.oscar.icq.core.api.listener.ListenerMetaInfo;
import ru.oscar.icq.core.api.listener.ListenerXStatus;
import ru.oscar.icq.packet.send.Goodbye;
import ru.oscar.icq.packet.send.KeepAlive;
import ru.oscar.icq.packet.send.meta.RequestShortInfo;
import ru.oscar.icq.setting.OptionsConnecting;
import ru.oscar.icq.util.StringUtil;


/**
 * @author Kornackiy Alexsandr
 */

public class Connect {
    
    private Client client;
    private PacketRecognizer recognizer;
    
    private String sn;
    private String password;
    private String host;
  
    private int sequence;
    private int port;   
    
    private boolean connected = false;
    private boolean authorized = false;
    
    private Cookies cookies;
    
    private OptionsConnecting options;
    
    private ArrayList<ListenerConnection> listenersConnectArray = new ArrayList<ListenerConnection>(1);   
    private ArrayList<ListenerMessages> listenersMessagesArray = new ArrayList<ListenerMessages>(1);   
    private ArrayList<ListenerXStatus> listenersXStatusArray = new ArrayList<ListenerXStatus>(1); 
    private ArrayList<ListenerContactList> listenersContactList = new ArrayList<ListenerContactList>(1);
    private ArrayList<ListenerMetaInfo> listenersMetaInfo = new ArrayList<ListenerMetaInfo>(1); 
    
    private ContactList contactList;
    
    /**
     * Конструктор подключения.
     * @param sn
     * @param password
     * @param host
     * @param port
     * @param options 
     */
    
    public Connect(String sn, String password, String host, int port, OptionsConnecting options){ 
        recognizer = new PacketRecognizer(this);
        this.sn = sn;
        this.password = password;
        this.host = host;
        this.port = port;
        client = new Client(host, port, recognizer, this);
        cookies = new Cookies();
        this.options = options;
    }
    
    /**
     * Конструктор подключения.
     * @param sn
     * @param password
     * @param options 
     */
    
    public Connect(String sn, String password, OptionsConnecting options){  
        this(sn, password, "login.icq.com", 5190, options);
    }   
    
    /**
     * Отправить FLAP пакет.
     * @param f 
     */
    
    public synchronized void sendPacket(Flap f){
        sequence++;       
        f.setSeq(sequence);   
        try{
            client.sendPacket(f.getByteArray());
        }catch (IOException e) {
            if(connected){               
                try{                  
                    client.disconnect();
                    }catch (IOException ex) {
                        System.err.println("Send packet error: " + ex.getMessage());
                    }finally {
                    if(authorized){
                        breakConnection(e.getMessage());
                        close();
                    }               
                }
            }
        }
    }
    
    /**
     * Подключение.
     */
    
    public synchronized void connect() {

        System.out.println("Connect " + host + ":" + port);
    	try {
            client.connect();
            connected = true;
        }catch (IOException e) {            
            breakConnection(e.getMessage());
            }
    }
    
    /**
     * Уведомляет слушателей о неудачной авторизации.
     * @param message 
     */

    
    public void failureConnection(String message){
        for (int i = 0; i < getListenerConnection().size(); i++) {
            ListenerConnection l = getListenerConnection().get(i);
            l.failureConnection(message);
        }        
    }
    
    /**
     * Уведомляет слушателей о разрыве соединения.
     * @param message 
     */
    
    public void breakConnection(String message){
        for (int i = 0; i < getListenerConnection().size(); i++) {
            ListenerConnection l = getListenerConnection().get(i);
            l.breakConnection(message);
        }        
    } 
    
    /**
     * Уведомляет слушателей о успешном подключении.
     */
    
    public void successfulConnected(){
        for (int i = 0; i < getListenerConnection().size(); i++) {
            ListenerConnection l = getListenerConnection().get(i);
            l.successfulConnected();
        }        
    }
    
    /**
     * Уведомляет слушателей о успешном загрузке контакт листа.
     */
    
    public void isLoadedContactList(){
        for (int i = 0; i < getListenerContactList().size(); i++) {
            ListenerContactList l = getListenerContactList().get(i);
            l.isLoadedContactList();
        }        
    }       
    
    /**
     * Закрываем соединение.
     */
    
    public synchronized void close() {
            System.out.println("Close");     
    	try {            
            if (connected) { 
                client.disconnect();
                cookies.clear();
            }
            }catch (IOException e) {
    		//..
            }finally {
    		connected = false;
            }
    }
    
    /**
     * Мы авторизованы, подключаемся к BOSS серверу.
     * @param BosServerAddress
     * @param cookie 
     */
    
    public void weLogged(String BosServerAddress, byte[] cookie){
        System.out.println("We logged");
        try{
            this.authorized = true;
            // сохраним cookie 
            cookies.set(1, cookie);
            // прощаемся с login сервером
            sendPacket(new Goodbye());
            // отключаемся от него       
            client.disconnect();
            // подключаемся к boss серверу
            client = new Client(StringUtil.getAddress(BosServerAddress), StringUtil.getPort(BosServerAddress), recognizer, this);
            client.connect();
            System.out.println("Connect BOSS server: " + BosServerAddress);        
            
            if(options.isContactList()){
                contactList = new ContactList(this);
            }
            
            }catch (IOException e) {
                breakConnection(e.getMessage());
                System.err.println("weLogger error: " + e.getMessage());
                close();
            }        
    }
    
    /**
     * Мы не прошли авторизацию.
     * @param error - код ошибки
     */
    
    public void notAuthorized(int error){
        this.authorized = false;
        failureConnection(new LoginErrorConstants(error).toString());
        close();
    }
    
    public String getSN(){
        return sn;
    }
    
    public String getPassword(){
        return password;
    }
    
    public boolean getAuthorized(){
        return authorized;
    }
    
    public Cookies getCookies(){
        return cookies;
    }
    
    public OptionsConnecting getOptionsConnect(){
        return options;
    }
    
    public Client getClient(){
        return client;
    }
    
    public void sendKeepAlive(){
        sendPacket(new KeepAlive());
    }
    
    public int getSequence(){
        return sequence;
    }
    
    public void putListenerConnection(ListenerConnection listenersConnect){
        listenersConnectArray.add(listenersConnect);
    }
    
    public ArrayList<ListenerConnection> getListenerConnection(){
        return listenersConnectArray;
    }
    
    public void removeListenerConnection(ListenerConnection listenersConnect) {
        int i = listenersConnectArray.indexOf(listenersConnect);
        if (i>=0) listenersConnectArray.remove(i);
    }
    
    public void putListenerMessages(ListenerMessages listenerMessages){
        listenersMessagesArray.add(listenerMessages);
    }
    
    public ArrayList<ListenerMessages> getListenerMessages(){
        return listenersMessagesArray;
    }
    
    public void removeListenerMessages(ListenerMessages listenerMessages) {
        int i = listenersMessagesArray.indexOf(listenerMessages);
        if (i>=0) listenersMessagesArray.remove(i);
    }  
    
    public void putListenerXStatus(ListenerXStatus listenerXStatus){
        listenersXStatusArray.add(listenerXStatus);
    }
    
    public ArrayList<ListenerXStatus> getListenerXStatus(){
        return listenersXStatusArray;
    }
    
    public void removeListenerXStatus(ListenerXStatus listenerXStatus) {
        int i = listenersXStatusArray.indexOf(listenerXStatus);
        if (i>=0) listenersXStatusArray.remove(i);
    }
    
    public void putListenerContactList(ListenerContactList listenerContactList){
        listenersContactList.add(listenerContactList);
    }
    
    public ArrayList<ListenerContactList> getListenerContactList(){
        return listenersContactList;
    }
    
    public void removeListenerContactList(ListenerContactList listenerContactList) {
        int i = listenersContactList.indexOf(listenerContactList);
        if (i>=0) listenersContactList.remove(i);
    }    
    
    public void putListenerMetaInfo(ListenerMetaInfo listenerMetaInfo){
        listenersMetaInfo.add(listenerMetaInfo);
    }
    
    public ArrayList<ListenerMetaInfo> getListenerMetaInfo(){
        return listenersMetaInfo;
    }
    
    public void removeListenerMetaInfo(ListenerMetaInfo listenerMetaInfo) {
        int i = listenersMetaInfo.indexOf(listenerMetaInfo);
        if (i>=0) listenersMetaInfo.remove(i);
    }      

    /**
     * @return the contactList
     */
    public ContactList getContactList() {
        return contactList;
    }
        

}
