
package ru.oscar.icq.contacts;

import ru.oscar.icq.events.FutureAuthEvent;

/**
 * Работа с контакт листом
 * @author Kornackiy Alexsandr
 */

public interface IContactList {
    
    /**
     * Добавить контакт в группу
     * @param sn - контакт
     * @param group - группа
     */
    
    public void addContact(String sn, String group);
    
    
    /**
     * Удалить контакт
     * @param sn - контакт
     */
    public void removeContact(String sn);
    
    /**
     * Переминовать контакт
     * @param sn - контакт
     * @param newNick - новый ник
     */
    public void renameContact(String sn, String newNick);
    
    /**
     * Переместить контакт в другую группу
     * @param sn - контакт
     * @param newGroup - новая группа
     */
    public void changeContactGroup(String sn, String newGroup);
    
    /**
     * Создать группу
     * @param name - имя группы
     */
    public void addGroup(String name);
       
    /**
     * Удалить группу
     * @param name - имя группы
     */
    public void removeGroup(String name);
    
    /**
     * Удалит группу
     * TODO: Перед удалением перенесет контакты в группу name1
     * @param name - имя группы
     * @param name1 - группу куда переносим контакты
     */
    public void removeGroup(String name, String name1);
    
    /**
     * Переминовать группу
     * @param group - имя группы
     * @param newName  - новое имя группы
     */
    public void renameGroup(String group, String newName);
    
    /**
     * Добавить контакт в приватный список
     * @param sn - контакт
     * @param list  - список
     */
    public void addSsiList(String sn, int list);
    
    
    /**
     * Удалить контакт из списка
     * @param sn - контакт
     */
    public void removeSsiList(String sn);
    
    /**
     * Отправить вас добавили
     * @param sn - контакт 
     */   
    public void sendYouWereAdded(String sn);
    
    /**
     * Запрос авторизации
     * @param sn - контакт у которого запрашиваем
     * @param message - сообщение
     */
    public void sendAuthRequest(String sn, String message);
    
    /**
     * Ответ на запрос авторизации
     * @param sn - контакт который просит авторизацию
     * @param message - сообщение
     * @param auth - приянть/отклонить
     */   
    public void sendAuthReply(String sn, String message, boolean auth);
    
    /**
     * Разрешить контакту добавить нас
     * @param sn - контакт
     * @param message  - сообщение
     */
    public void sendFutureAuth(String sn, String message);
    
    /**
     * Удалить себя из кл контакта
     * @param sn - контакт
     */
    public void sendDeleteYourself(String sn);
    
}
