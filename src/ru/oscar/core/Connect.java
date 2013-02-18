
package ru.oscar.core;

import ru.oscar.icq.packet.ParserPackage;
import java.io.IOException;
import java.util.ArrayList;
import ru.oscar.Cookies;
import ru.oscar.Flap;
import ru.oscar.icq.constants.LoginErrorConstants;
import ru.oscar.icq.contacts.ContactList;
import ru.oscar.icq.listener.ConnectionListener;
import ru.oscar.icq.listener.ContactListListener;
import ru.oscar.icq.listener.MessagesListener;
import ru.oscar.icq.listener.MetaAckListener;
import ru.oscar.icq.listener.MetaInfoListener;
import ru.oscar.icq.listener.XStatusListener;
import ru.oscar.icq.packet.send.Goodbye;
import ru.oscar.icq.packet.send.KeepAlive;
import ru.oscar.icq.packet.send.ssi.RequestContactList;
import ru.oscar.util.Dumper;
import ru.oscar.util.StringUtil;


/**
 * @author Kornackiy Alexsandr
 */

public class Connect {
    
    private Client client;
    private ParserPackage parser;
    
    private String sn;
    private String password;
    private String host;
  
    private int sequence;
    private int port;   
    
    private boolean connected = false;
    private boolean authorized = false;   
    
    private Cookies cookies;
    
    private OptionsConnect options;
    
    private ArrayList<ConnectionListener> listenersConnectArray = new ArrayList<ConnectionListener>(1);   
    private ArrayList<MessagesListener> listenersMessagesArray = new ArrayList<MessagesListener>(1);   
    private ArrayList<XStatusListener> listenersXStatusArray = new ArrayList<XStatusListener>(1); 
    private ArrayList<ContactListListener> listenersContactList = new ArrayList<ContactListListener>(1);
    private ArrayList<MetaInfoListener> listenersMetaInfo = new ArrayList<MetaInfoListener>(1); 
    private ArrayList<MetaAckListener> listenersMetaAck = new ArrayList<MetaAckListener>(1);
    
    private ContactList contactList = null;
    
    /**
     * Конструктор подключения.
     * @param sn
     * @param password
     * @param host
     * @param port
     * @param options 
     */
    
    public Connect(String sn, String password, String host, int port, OptionsConnect options){ 
        parser = new ParserPackage(this);
        this.sn = sn;
        this.password = password;
        this.host = host;
        this.port = port;
        client = new Client(host, port, parser, this);
        cookies = new Cookies();
        this.options = options;
    }
    
    /**
     * Конструктор подключения.
     * @param sn
     * @param password
     * @param options 
     */
    
    public Connect(String sn, String password, OptionsConnect options){  
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
            if(options.isDebug()){
                if(f.getSnac() != null ){
                    System.out.println("Send packet: (" + f.getSnac().getFamilyID() + "," + f.getSnac().getSubTypeID() + ")\n"
                            + "Dump:\n" + Dumper.dump(f.getByteArray(), true));
                } else {
                    System.out.println("Send packet: \n"
                            + "Dump:\n" + Dumper.dump(f.getByteArray(), true));
                }
            }            
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
          if(options.isDebug()){
              System.out.println("Connect " + host + ":" + port);
          }
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
            ConnectionListener l = getListenerConnection().get(i);
            l.failureConnection(message);
        }        
    }
    
    /**
     * Уведомляет слушателей о разрыве соединения.
     * @param message 
     */
    
    public void breakConnection(String message){
        for (int i = 0; i < getListenerConnection().size(); i++) {
            ConnectionListener l = getListenerConnection().get(i);
            l.breakConnection(message);
        }        
    } 
    
    /**
     * Уведомляет слушателей о успешном подключении.
     */
    
    public void successfulConnected(){
        for (int i = 0; i < getListenerConnection().size(); i++) {
            ConnectionListener l = getListenerConnection().get(i);
            l.successfulConnected();
        }        
    }
    
    /**
     * Уведомляет слушателей о успешном загрузке контакт листа.
     */
    
    public void isLoadedContactList(){
        for (int i = 0; i < getListenerContactList().size(); i++) {
            ContactListListener l = getListenerContactList().get(i);
            l.isLoadedContactList();
        }        
    }   
    
    /**
     * Ошибка в группе Snac
     * @param family 
     * @param subTupe 
     * @param errorCode 
     */
    
    public void errorNotification(int family, int subTupe, int errorCode){
        for (int i = 0; i < getListenerConnection().size(); i++) {
            ConnectionListener l = getListenerConnection().get(i);
            l.errorNotification(family, subTupe, errorCode);
        }          
    }
    
    /**
     * Закрываем соединение.
     */
    
    public synchronized void close() {
        if(options.isDebug()){
            System.out.println("Close"); 
        }
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
        if(options.isDebug()){
            System.out.println("We logged");
        }
        try{
            this.authorized = true;
            // сохраним cookie 
            cookies.set(1, cookie);
            // прощаемся с login сервером
            sendPacket(new Goodbye());
            // отключаемся от него       
            client.disconnect();
            // подключаемся к boss серверу
            client = new Client(StringUtil.getAddress(BosServerAddress), StringUtil.getPort(BosServerAddress), parser, this);
            client.connect();
            if(options.isDebug()){
                System.out.println("Connect BOSS server: " + BosServerAddress);       
            }
            
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
    
    public OptionsConnect getOptionsConnect(){
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
    
    public void putListenerConnection(ConnectionListener listenersConnect){
        listenersConnectArray.add(listenersConnect);
    }
    
    public ArrayList<ConnectionListener> getListenerConnection(){
        return listenersConnectArray;
    }
    
    public void removeListenerConnection(ConnectionListener listenersConnect) {
        int i = listenersConnectArray.indexOf(listenersConnect);
        if (i>=0) listenersConnectArray.remove(i);
    }
    
    public void putListenerMessages(MessagesListener listenerMessages){
        listenersMessagesArray.add(listenerMessages);
    }
    
    public ArrayList<MessagesListener> getListenerMessages(){
        return listenersMessagesArray;
    }
    
    public void removeListenerMessages(MessagesListener listenerMessages) {
        int i = listenersMessagesArray.indexOf(listenerMessages);
        if (i>=0) listenersMessagesArray.remove(i);
    }  
    
    public void putListenerXStatus(XStatusListener listenerXStatus){
        listenersXStatusArray.add(listenerXStatus);
    }
    
    public ArrayList<XStatusListener> getListenerXStatus(){
        return listenersXStatusArray;
    }
    
    public void removeListenerXStatus(XStatusListener listenerXStatus) {
        int i = listenersXStatusArray.indexOf(listenerXStatus);
        if (i>=0) listenersXStatusArray.remove(i);
    }
    
    public void putListenerContactList(ContactListListener listenerContactList){
        listenersContactList.add(listenerContactList);
    }
    
    public ArrayList<ContactListListener> getListenerContactList(){
        return listenersContactList;
    }
    
    public void removeListenerContactList(ContactListListener listenerContactList) {
        int i = listenersContactList.indexOf(listenerContactList);
        if (i>=0) listenersContactList.remove(i);
    }    
    
    public void putListenerMetaInfo(MetaInfoListener listenerMetaInfo){
        listenersMetaInfo.add(listenerMetaInfo);
    }
    
    public ArrayList<MetaInfoListener> getListenerMetaInfo(){
        return listenersMetaInfo;
    }
    
    public void removeListenerMetaInfo(MetaAckListener listenerAckInfo) {
        int i = listenersMetaAck.indexOf(listenerAckInfo);
        if (i>=0) listenersMetaAck.remove(i);
    }    
    
    public void putListenerMetaAck(MetaAckListener listenerAckInfo){
        listenersMetaAck.add(listenerAckInfo);
    }
    
    public ArrayList<MetaAckListener> getListenerMetaAck(){
        return listenersMetaAck;
    }
    
    public void removeListenerMetaAck(MetaAckListener listenerAckInfo) {
        int i = listenersMetaAck.indexOf(listenerAckInfo);
        if (i>=0) listenersMetaAck.remove(i);
    }       

    /**
     * @return the contactList
     */
    public ContactList getContactList() {
        if(contactList == null){
            contactList = new ContactList(this);
            sendPacket(new RequestContactList());
        }
        return contactList;
    }
        

}
