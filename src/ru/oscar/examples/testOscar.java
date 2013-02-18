package ru.oscar.examples;

import java.util.Iterator;
import java.util.Map.Entry;
import ru.oscar.icq.constants.DirectConnectConstants;
import ru.oscar.icq.constants.ErrorConstants;
import ru.oscar.icq.constants.MessageTypesConstants;
import ru.oscar.icq.constants.PrivacyStatusConstants;
import ru.oscar.icq.constants.ProtocolVersionConstants;
import ru.oscar.icq.constants.SsiConstants;
import ru.oscar.icq.constants.StatusConstants;
import ru.oscar.icq.constants.StatusFlagConstants;
import ru.oscar.icq.constants.XStatusConstants;
import ru.oscar.icq.contacts.Contact;
import ru.oscar.icq.contacts.ContactList;
import ru.oscar.icq.contacts.Group;
import ru.oscar.icq.contacts.IContactList;
import ru.oscar.core.Connect;
import ru.oscar.icq.IICQ;
import ru.oscar.icq.events.AuthReplyEvent;
import ru.oscar.icq.events.AuthRequestEvent;
import ru.oscar.icq.events.FutureAuthEvent;
import ru.oscar.icq.events.MessageEvent;
import ru.oscar.icq.events.MetaAckEvent;
import ru.oscar.icq.events.MetaAffilationsUserInfoEvent;
import ru.oscar.icq.events.MetaBasicUserInfoEvent;
import ru.oscar.icq.events.MetaEmailUserInfoEvent;
import ru.oscar.icq.events.MetaFindByUinUserInfoEvent;
import ru.oscar.icq.events.MetaInterestsUserInfoEvent;
import ru.oscar.icq.events.MetaMoreUserInfoEvent;
import ru.oscar.icq.events.MetaNoteUserInfoEvent;
import ru.oscar.icq.events.MetaShortUserInfoEvent;
import ru.oscar.icq.events.MetaWorkUserInfoEvent;
import ru.oscar.icq.events.OfflineMessageEvent;
import ru.oscar.icq.events.SsiAckEvent;
import ru.oscar.icq.events.XStatusEvent;
import ru.oscar.icq.listener.ConnectionListener;
import ru.oscar.icq.listener.ContactListListener;
import ru.oscar.icq.listener.MessagesListener;
import ru.oscar.icq.listener.XStatusListener;
import ru.oscar.icq.setting.Capabilities;
import ru.oscar.core.OptionsConnect;
import ru.oscar.icq.constants.MetaAffilationConstants;
import ru.oscar.icq.constants.MetaCountryConstants;
import ru.oscar.icq.constants.MetaGenderConstants;
import ru.oscar.icq.constants.MetaInterestsConstants;
import ru.oscar.icq.constants.MetaLanguagesConstants;
import ru.oscar.icq.constants.MetaOccupationConstants;
import ru.oscar.icq.constants.MetaPostBackgroundConstants;
import ru.oscar.icq.constants.MetaTimeZoneConstants;
import ru.oscar.icq.listener.MetaAckListener;
import ru.oscar.icq.listener.MetaInfoListener;
import ru.oscar.icq.packet.send.meta.BlockMetaData;

public class testOscar implements ConnectionListener, MessagesListener, XStatusListener, ContactListListener,
        MetaInfoListener, MetaAckListener, IContactList{
    
    private static final int MAX_CONTACTS_IN_GROUP = 200;// TODO: не факт что 200
    
    private String sn = "596205";
    private String password = "";         
    private String title = "title";
    private String description = "description";   
    
    private Connect c;

    public void init(){
        /*Установим параметры для подключения*/
        OptionsConnect options = new OptionsConnect();
        // TODO: приватный статус установится тока с загрузкой контакт листа!
        options.setPrivacyStatus(new PrivacyStatusConstants(PrivacyStatusConstants.VISIBLE_ALL));
        options.setStatus(new StatusConstants(StatusConstants.STATUS_ONLINE));
        options.setXstatus(new XStatusConstants(XStatusConstants.XSTATUS_DRINKING_BEER, title, description));
        options.setStatusFlag(new StatusFlagConstants(StatusFlagConstants.STATUS_FLAG_WEBAWARE));
        options.setDirectConnect(new DirectConnectConstants(DirectConnectConstants.DC_NORMAL));
        options.setProtocolVersion(new ProtocolVersionConstants(ProtocolVersionConstants.DCP_ICQLITE));
        options.setCapabilities(new Capabilities(Capabilities.JIL));
        options.setContactList(true);
        options.setDebug(false);
        options.setTyping(false);
        options.setMD5uthorization(true);
        c = new Connect(sn, password, options);       
        c.putListenerConnection(this);
        c.putListenerMessages(this);
        c.putListenerXStatus(this);
        c.putListenerContactList(this);
        c.putListenerMetaInfo(this);
        c.putListenerMetaAck(this);
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
        System.out.println("Authorization failed!\nMessage: " + message);
        
    }
    
    /**
     * Разрыв соединения
     * @param message 
     */

    @Override
    public void breakConnection(String message) {
        System.out.println("Break connect!\nMessage: " + message);
    }
    
    /**
     * Входящее сообщение
     * @param e 
     */    
    
    public void onIncomingMessage(MessageEvent e) {
        if(cmd(e.getMessage())){
            return;
        }
        System.out.println("Incoming message: " + e.getSenderID() + " >> " + e.getMessage());
    }
    
    public boolean cmd(String message){
        message = message.trim();
        
        if(message.equalsIgnoreCase("mycl")){
            System.out.println(myContactList());
            return true;
        } else if(message.equalsIgnoreCase("setinfo")){ 
            setMetaInfo();
            return true;
        }else if(message.equalsIgnoreCase("offmessage")){ 
            IICQ.requestOfflineMessages(c);
            return true;
        }else if(message.split(" ")[0].equalsIgnoreCase("search")){ 
            requestFindByUinUserInfo(message.split(" ")[1]);
             return true;
        }else if(message.split(" ")[0].equalsIgnoreCase("shortinfo")){
            requestShortUserInfo(message.split(" ")[1]);
            return true;
        }else if(message.split(" ")[0].equalsIgnoreCase("fullinfo")){
            requestFullUserInfo(message.split(" ")[1]);
        }else if(message.split(" ")[0].equalsIgnoreCase("addc")){
            addContact(message.split(" ")[1], message.split(" ")[2]);
            return true;
        }else if(message.split(" ")[0].equalsIgnoreCase("delc")){
            removeContact(message.split(" ")[1]);
            return true;            
        }else if(message.split(" ")[0].equalsIgnoreCase("message")){      
            sendMessage(message.split(" ")[1], message.split(" ")[2], 1);
            return true;
        }
        return false;
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
                IICQ.sendNormalMessage(c, sn, messgae);// обычное
                break;
            case 2:
                IICQ.sendExtendedMessage(c, sn, messgae);// расширенное
                break;
            case 4:
                IICQ.sendOldTypeMessage(c, sn, messgae, new MessageTypesConstants(MessageTypesConstants.MESSAGE_PLAIN));// старый тип или url
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
     * Ошибка в snac группе (?,?)
     * @param family
     * @param subTupe
     * @param errorCode 
     */

    public void errorNotification(int family, int subTupe, int errorCode) {
        System.err.println("This is an error notification snac (" + family + "," + subTupe  + 
                ").\nMessage: " + new ErrorConstants(errorCode).toString());
    }     
    
    /**
     * Запрос х-статуса
     * @param sn 
     */
    
    public void requestXStatus(String sn) {
        IICQ.requestXStatus(c, sn);
    }
    
    /**
     * Запрос короткой информации о пользователе
     * @param sn 
     */
    
    public void requestShortUserInfo(String sn) {
        IICQ.requestShortUserInfo(c, sn);
    }
    
    /**
     * Запрос полной о пользователе
     * @param sn 
     */
    
    public void requestFullUserInfo(String sn) {
        IICQ.requestFullUserInfo(c, sn);
    }    
    
    /**
     * Поиск пользователя по uin 
     * @param sn 
     */
    
    public void requestFindByUinUserInfo(String sn) {
        IICQ.requestFindByUinUserInfo(c, sn);
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
        IICQ.changeStatus(c, new StatusConstants(i));
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
        IICQ.changeStatusFlag(c, new StatusFlagConstants(i));
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
        IICQ.changePrivacyStatus(c, new PrivacyStatusConstants(i));        
    }    
    
    /**
     * Изменить х-статус
     * @param xstatus
     * @param title
     * @param description 
     */
    
    public void changeXStatus(int xstatus, String title, String description){
        IICQ.changeXStatus(c, new XStatusConstants(xstatus, title, description));
    }
    
    /**
     * Контакт лист загружен
     */

    public void isLoadedContactList() {
        System.out.println("Contact list is loaded.");
        //System.out.println(myContactList());
    }
    
    /**
     * Ответ на ssi запросы
     * @param e 
     */

    public void onSsiAck(SsiAckEvent e) {
        System.out.println("On ssi modifying ask: (" + e.getResults().getCode() + ") " + e.getResults().toString());    
    }
    
    /**
     * Добавим контакт в группу
     * @param sn
     * @param group 
     */
    
    public void addContact(String sn, String group){
        Group g = c.getContactList().getGroup(group);
        if(g == null){
            return;// нет такой группы
        }
        if(g.contactsCount() == MAX_CONTACTS_IN_GROUP){
            return;// группа заполнена
        }
        // check contact
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
     * Переминовать контакт
     * @param sn
     * @param newNick 
     */
    
    public void renameContact(String sn, String newNick){
        c.getContactList().renameContact(sn, newNick);
    }
    
    /**
     * Переместить контакт в группу
     * @param sn
     * @param newGroup - новая группа для контакта
     */
    
    public void changeContactGroup(String sn, String newGroup){
        Group g = c.getContactList().getGroup(newGroup);
        if(g == null){
            return;// нет такой группы
        }     
        if(g.contactsCount() == MAX_CONTACTS_IN_GROUP){
            return;// группа заполнена
        }        
        c.getContactList().changeContactGroup(sn, g);
    }
    
    /**
     * Создать группу
     * @param name 
     */
    
    public void addGroup(String name){
        Group g = c.getContactList().getGroup(name);
        if(g != null){
            return;// уже создали
        }    
        c.getContactList().addGroup(name);
    }
    
    /**
     * Удалить группу С КОНТАКТАМИ
     * @param name 
     */
    
    public void removeGroup(String name){
        Group g = c.getContactList().getGroup(name);
        if(g == null){
            return;// нет такой группы
        }     
        c.getContactList().removeGroup(g);
    }
    
    
    /**
     * Удалить группу
     * Перед удалением перенесет контакты в группу name1
     * @param name
     * @param name1 
     */
    
    public void removeGroup(String name, String name1){
        Group g = c.getContactList().getGroup(name);
        Group g1 = c.getContactList().getGroup(name1);
        if(g == null || g1 == null){
            return;// нет такой группы
        }   
        c.getContactList().removeGroup(g, g1);
    }
    
    /**
     * Переминовать группу
     * @param group
     * @param newName 
     */
    
    public void renameGroup(String group, String newName){
        Group g = c.getContactList().getGroup(group);
        if(g == null){
            return;// нет такой группы
        }             
        c.getContactList().renameGroup(g, newName);
    }
    
    /**
     * Полностью очистит контакт лист, удалит все группы с контактами!!!
     */
    
    public void clearContactList(){
        for(Group group : c.getContactList().getGroups().values()){
            removeGroup(group.getName());
        }
    }    
    
    /**
     * Добавим контакт в "список"
     * @param sn
     * @param list 
     */
    
    public void addSsiList(String sn, int list){
        switch(list){
            case SsiConstants.TYPE_VISIBLE:
                 c.getContactList().addList(sn, SsiConstants.TYPE_VISIBLE);
            break;
            case SsiConstants.TYPE_INVISIBLE:               
                 c.getContactList().addList(sn, SsiConstants.TYPE_INVISIBLE);
            break;
            case SsiConstants.TYPE_IGNORE_LIST:
                 c.getContactList().addList(sn, SsiConstants.TYPE_IGNORE_LIST);
            break;                
        }
    }
    
    /**
     * Удалим контакт из "списока"
     * @param sn
     * @param list 
     */
    
    public void removeSsiList(String sn){
        c.getContactList().removeList(sn);
    } 
    
    /**
     * Пользователь разрешил добавить его в кл
     * @param e 
     */

    public void onFutureAuth(FutureAuthEvent e) {
        System.out.println(e.getSn() + " allowed to add it to the contact list.\nMessage: " + e.getMessage());
    }
    
    /**
     * Ответ на наш запрос авторизации
     * @param e 
     */

    public void onAuthReply(AuthReplyEvent e) {
        if(e.isAuth()){
            System.out.println(e.getSn() + " has authorized us.\nMessage: " + e.getMessage());
        }else{
            System.out.println(e.getSn() + " refused to authorize.\nMessage: " + e.getMessage());
        }
    }
    
    /**
     * Контакт попросил у нас авторизацию
     * @param e 
     */

    public void onAuthRequest(AuthRequestEvent e) {
        System.out.println(e.getSn() + " request have authorization.\nMessage: " + e.getMessage());
        c.getContactList().sendAuthReply(e.getSn(), "You are authorized!", true);
    }
        
    /**
     * Отправить вас добавили
     * @param sn 
     */

    public void sendYouWereAdded(String sn) {
        c.getContactList().sendYouWereAdded(sn);
    }
    
    /**
     * Запрос авторизации
     * @param sn
     * @param message 
     */

    public void sendAuthRequest(String sn, String message) {
        c.getContactList().sendAuthRequest(sn, message);
    }
    
    /**
     * Ответ на запрос авторизации
     * @param sn
     * @param message
     * @param auth 
     */

    public void sendAuthReply(String sn, String message, boolean auth) {
        c.getContactList().sendAuthReply(sn, message, auth);
    }
    
    /**
     * Разрешить контакту добавить нас
     * @param sn
     * @param message 
     */

    public void sendFutureAuth(String sn, String message) {
        c.getContactList().sendFutureAuth(sn, message);
    }
    
    /**
     * Удалить себя из кл
     * @param sn 
     */

    public void sendDeleteYourself(String sn) {
        c.getContactList().sendDeleteYourself(sn);
    }
    
    
    /**
     * Текстовый набор групп
     * @return 
     */
    
    public String myGroups(){
        String s = "My groups:\n";
        for(Group group : c.getContactList().getGroups().values()){
            s += group.getName() + " (ID= " + group.getIdGroup() + ")";
            s += "\n";
        }
        return s;
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
        
        s.append("My contact list:");
        s.append("\n\n");  
        for(Group g : list.getGroups().values()){
            s.append("Group: ").append(g.getName()).
                    append("(").
                    append(g.getIdGroup()).
                    append(")"); 
            s.append("\n\n");  
            
            if(g.isContacts()){
            Iterator iterator  = list.sorterStatus(g).iterator();
 
            while (iterator.hasNext()) {
                Contact c = (Contact) iterator.next();            
                s.append("Sn: ").append(c.getSn()).
                        append(" Nick: ").append(c.getName()).
                        append(" ").
                        append("(authorization: ").
                        append(c.isAuth() == true ? "required)" : "no required) ").
                        append(list.getPrivacyList(c.getSn()) == null ? "" : list.getPrivacyList(c.getSn()).toString());
                s.append("\n");   
                s.append("\n");  
            }
            } else {
                s.append("No contacts in the group");
                s.append("\n");   
                s.append("\n");                  
            }
          
        }
        return s.toString();        
    }
    
    /**
     * Ответ на запрос короткой информации о пользователе
     * @param e 
     */
    
    /**
     * Результаты поиска
     * @param e 
     */

    public void onFindByUinUserInfo(MetaFindByUinUserInfoEvent e) {
        // Искали контакт чтобы добавить его!
        // Группу передали в запросе на поиск
        // Следовательно добавим в эту группу
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
    
    
    /**
     * Сменит данные в пользовательской анкете
     */
    
    public void setMetaInfo(){
            BlockMetaData metaData = new BlockMetaData();
            /*Ник*/
            metaData.setNickName("JIL2013");
            /*Имя*/
            metaData.setFirstName("");
            /*Фамилия*/
            metaData.setLastName("");
            /*Мыло, показывать/не показывать*/
            metaData.setUserEmail("", true);  
            /*Ранг поиска*/
            metaData.setAgeRangeSearch(new int[]{18, 30});
            /*Возраст*/
            metaData.setUserAge(22);
            /*Пол*/
            metaData.setUserGender(new MetaGenderConstants(MetaGenderConstants.MALE));
            /*Владение языками*/
            MetaLanguagesConstants[]  l = new MetaLanguagesConstants[]{
                new MetaLanguagesConstants(MetaLanguagesConstants.RUSSIAN),
                new MetaLanguagesConstants(MetaLanguagesConstants.TATAR),
                new MetaLanguagesConstants(MetaLanguagesConstants.AZERBAIJANI)
            };
            metaData.setUserSpokenLanguage(l);
            /*Город*/
            metaData.setUserHomeCity("");
            /*Район/Область*/
            metaData.setUserHomeState("");
            /*Страна*/
            metaData.setUserHomeCountryCode(new MetaCountryConstants(MetaCountryConstants.UNSPECIFIED));
            /*Адресс*/
            metaData.setUserHomeStrreetAddress("");
            /*Почтовый индекс*/
            metaData.setUserHomeZipCode(0);    
            /*Телефон*/
            metaData.setUserHomePhoneNumber("");
            /*Факс*/
            metaData.setUserHomeFaxNumber("");
            /*Мобильный телефон*/
            metaData.setUserHomeCellularPhoneNumber("");
            /*Работа*/
            metaData.setUserWorkName("");
            /*Отдел*/
            metaData.setUserWorkDepartment("");
            /*Должность*/
            metaData.setUserWorkTitle("");
            /*Профессия*/
            metaData.setUserWorkOcupationCode(new MetaOccupationConstants(MetaOccupationConstants.UNSPECIFIED));
            /*Рабочий адресс*/
            metaData.setUserWorkStreetAddress("");
            /*Город*/
            metaData.setUserWorkCityName("");
            /*Район/Область*/
            metaData.setUserWorkStateName("");
            /*Страна*/
            metaData.setUserWorkCountryCode(new MetaCountryConstants(MetaCountryConstants.UNSPECIFIED));
            /*Почтовый индекс*/
            metaData.setUserWorkZipCode(0);
            /*Телефон*/
            metaData.setUserWorkPhoneNumber("");
            /*Факс*/
            metaData.setUserWorkFaxNumbere("");
            /*Рабочая веб страница*/
            metaData.setUserWorkWebPage("");           
            MetaAffilationConstants[] a = new MetaAffilationConstants[]{
                new MetaAffilationConstants(MetaAffilationConstants.FAN_CLUBS, "Fan Club"),
                new MetaAffilationConstants(MetaAffilationConstants.SPORTS, "Sport")
            };
            metaData.setUserAffilation(a);
            /*Интересы*/
            MetaInterestsConstants[] i = new MetaInterestsConstants[]{
                new MetaInterestsConstants(MetaInterestsConstants.CARS, "Авто"),
                new MetaInterestsConstants(MetaInterestsConstants.GAMES, "Игры"),
                new MetaInterestsConstants(MetaInterestsConstants.WOMEN, "Девушки"),
                new MetaInterestsConstants(MetaInterestsConstants.MUSIC, "Музыка"),
            };
            metaData.setUserInterests(i);  
            /*Веб страничка*/
            metaData.setUserHomepage("");
            /*В поиске онлайн?*/
            metaData.setSearchOnlyOnline(true);
            /*Дата дня рождения*/
            metaData.setUserBirthday(new int[]{1990, 10, 9});
            /*О себе*/
            metaData.setUserNotes("Im java icq library");
            /*Веб статус*/
            metaData.setUserWebStatus(false);
            /*Авторизация*/
            metaData.setUserAuthorization(false);
            /*Тайм зона*/
            metaData.setUserGMT(new MetaTimeZoneConstants(-10));
            /*Город где родился*/
            metaData.setUserOriginallyCity("");
            /*Район/Область*/
            metaData.setUserOriginallyState("");
            /*Страна*/
            metaData.setUserOriginallyCountryCode(new MetaCountryConstants(MetaCountryConstants.UNSPECIFIED));
            IICQ.requestSetFullInfo(c, metaData);          
    }
    
    /**
     * Ответ на запрос оффлайн сообщений
     * @param e 
     */

    public void onOfflineMessage(OfflineMessageEvent e) {
        System.out.println("Offline message:\n" + e.getSendDate().toString() + "\n" + e.getSN() + " ~ " + e.getMessage() + "\n");
    }

    public void onShortUserInfo(MetaShortUserInfoEvent e) {
        System.out.println("Short User Info: ");
        System.out.println("  Nick Name = "  + e.getNickName());
        System.out.println("  First Name = " + e.getFirstName());
        System.out.println("  Last Name = "  + e.getLastName());
        System.out.println("  Email = "      + e.getEmail());
        System.out.println("  Auth = "       + e.isAuth());
    }

    public void onBasicUserInfo(MetaBasicUserInfoEvent e) {
        System.out.println("Basic User Info: ");
        System.out.println("  Nick Name = "  + e.getNickName());
        System.out.println("  First Name = " + e.getFirstName());
        System.out.println("  Last Name = "  + e.getLastName());
        System.out.println("  Email = "      + e.getEmail());
        System.out.println("  Home City = "  + e.getHomeCity());
        System.out.println("  Home State = " + e.getHomeState());
        System.out.println("  Home Phone = " + e.getHomePhone());
        System.out.println("  Home Fax = "   + e.getHomeFax());
        System.out.println("  Home Address = "  + e.getHomeAddress());
        System.out.println("  Cell Phone = "  + e.getCellPhone());
        System.out.println("  Zip = "         + e.getZipCode());
        System.out.println("  Home Country = "  + e.getHomeCountry());
        System.out.println("  GMT offset = "  + e.getTimeZone());
        System.out.println("  Auth = "       + e.isAuth());
        System.out.println("  WebAware = "       + e.isWebaware());
        System.out.println("  DirectConnection = " + e.isDirectConnection());
        System.out.println("  PublishPrimaryEmail = " + e.isPublishPrimaryEmail());
    }

    public void onEmailUserInfo(MetaEmailUserInfoEvent e) {
        System.out.println("Email User Info: ");
        for (int i = 0; i < e.getEmails().size(); i++)
            System.out.println("Email: " + e.getEmails().get(i));
    }

    public void onWorkUserInfo(MetaWorkUserInfoEvent e) {
        System.out.println("Work User Info: ");
        System.out.println("  Work City = "  + e.getWorkCity());
        System.out.println("  Work State = "  + e.getWorkState());
        System.out.println("  Work Phone = "  + e.getWorkPhone());
        System.out.println("  Work Fax = "  + e.getWorkFax());
        System.out.println("  Work Address = "  + e.getWorkAddress());
        System.out.println("  Work Zip = "  + e.getWorkZip());
        System.out.println("  Work Country = "  + e.getWorkCountry());
        System.out.println("  Work Company = "  + e.getWorkCompany());
        System.out.println("  Work Department = "  + e.getWorkDepartment());
        System.out.println("  Work Position = "  + e.getWorkPosition());
        System.out.println("  Work WebPage = "  + e.getWorkWebPage());
        System.out.println("  Work Occupation = "  + e.getWorkOccupation());
    }

    public void onInterestsUserInfo(MetaInterestsUserInfoEvent e) {
        System.out.println("Interests User Info: ");
        for (Iterator iter = e.getInterests().entrySet().iterator(); iter.hasNext();) {
            Entry entry = (Entry) iter.next();
            MetaInterestsConstants interests = (MetaInterestsConstants) entry.getKey();
            System.out.println("Category: " + interests.toString() + " interest: " + entry.getValue());
        }
    }

    public void onMoreUserInfo(MetaMoreUserInfoEvent e) {
        System.out.println("More User Info");
        System.out.println(" age = " + e.getAge());
        System.out.println(" gender = " + e.getGender());
        System.out.println(" homePage = " + e.getHomePage());
        System.out.println(" birth = " + e.getBirth());
        System.out.println(" languages:");
        for (int i = 0; i < e.getLanguages().size(); i++) {
            System.out.println(e.getLanguages().get(i));
        }
        System.out.println(" original City = " + e.getOriginalCity());
        System.out.println(" original State = " + e.getOriginalState());
        System.out.println(" original Country = " + e.getOriginalCountry());
        System.out.println(" marital Status = " + e.getMaritalStatus());
    }

    public void onNotesUserInfo(MetaNoteUserInfoEvent e) {
        System.out.println(" About info = " + e.getNote());
    }

    public void onAffilationsUserInfo(MetaAffilationsUserInfoEvent e) {
        System.out.println("PostBackgrounds User Info: ");
        for (Iterator iter = e.getPostBackgrounds().entrySet().iterator(); iter.hasNext();) {
            Entry entry = (Entry) iter.next();
            MetaPostBackgroundConstants postbackgrounds = (MetaPostBackgroundConstants) entry.getKey();
            System.out.println("Category: " + postbackgrounds.toString() + " postbackground: " + entry.getValue());
        }

        System.out.println("Affilations User Info: ");
        for (Iterator iter = e.getAffilations().entrySet().iterator(); iter.hasNext();) {
            Entry entry = (Entry) iter.next();
            MetaAffilationConstants affilations = (MetaAffilationConstants) entry.getKey();
            System.out.println("Category: " + affilations.toString() + " affilations: " + entry.getValue());
        }
    }

    public void onMetaAck(MetaAckEvent e) {
        System.out.println("Meta ack = " + e.isOk());
    }
    
}
