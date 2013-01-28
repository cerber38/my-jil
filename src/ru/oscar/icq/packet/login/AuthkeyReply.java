
package ru.oscar.icq.packet.login;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.core.Connect;

/**
 * Получим ключ
 * Snac(17, 7)
 * @author Kornackiy Alexsandr
 */
public class AuthkeyReply extends Flap{
    
    private byte[] authkey;
    
    public AuthkeyReply(byte array[]){
        super(array, false);

        int index = 8;
        byte[] data = getDataArray();
        //Auth key length
        int len = DataWork.getDWord(data, index);
        index += 4;
        //Auth key
        authkey = DataWork.getArray(data, index, len);

    }
    
    public void notify(Connect connect) {
        //Client sends authorization request
        connect.sendPacket(new AuthorizationRequest(connect.getSN(), connect.getPassword(), authkey));
    }

}
