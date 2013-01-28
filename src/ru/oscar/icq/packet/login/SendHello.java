
package ru.oscar.icq.packet.login;

import ru.oscar.DataWork;
import ru.oscar.Flap;

/**
 * Подтвердим что мы готовы к авторизации
 * @author Kornackiy Alexsandr
 */
public class SendHello extends Flap {
    
    public SendHello(){
        super(CHANNEL1);
        //Флаг установки соединения с сервером
        addFlapData(DataWork.putDWord(0x01));
    }
}
