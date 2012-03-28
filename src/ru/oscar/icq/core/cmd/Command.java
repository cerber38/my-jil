
package ru.oscar.icq.core.cmd;

import ru.oscar.icq.Flap;
import ru.oscar.icq.core.Connect;

/**
 * Интерфейс команды
 */

public interface Command {

    /**
     * Инициализация. 
     */
    public void init();


    /**
     * Выполнение команды
     * @param p 
     */
    public void exec(Flap f);
   
    /**
     * Уведомит слушателей или отправит в ответ пакет серверу
     * @param conect 
     */
    
    public void notify(Connect conect);
    
}