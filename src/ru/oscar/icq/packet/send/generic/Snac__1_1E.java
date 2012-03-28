
package ru.oscar.icq.packet.send.generic;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.constants.DirectConnectConstants;
import ru.oscar.icq.constants.ProtocolVersionConstants;
import ru.oscar.icq.constants.StatusConstants;
import ru.oscar.icq.constants.StatusFlagConstants;
import ru.oscar.icq.util.Dumper;

/**
 * SNAC (1, 1E)
 * Отправлемя статус и информацию о подключении.
 * @author Kornackiy Alexsandr
 */

public class Snac__1_1E extends Flap {   
    
    public Snac__1_1E(StatusConstants status, StatusFlagConstants statusFlag, DirectConnectConstants directConnect
            ,ProtocolVersionConstants protocolVersion){
        super(CHANNEL2);
        
        System.out.println("Status flag: " + statusFlag.toString() +
                "\nStatus: " + status.toString() + 
                "\nConnect type: " + directConnect.toString() +
                "\nProtocol Version: " + protocolVersion.toString());     
        Snac snac = new Snac(0x01, 0x1E, 0x0, 0x0, 0x00);       
        
        // TLV.Type(0x06) - user status / status flags
        Tlv statusTlv = new Tlv(0x06);         
        statusTlv.addTlvData(DataWork.putWord(statusFlag.getCode()));
        // TODO
        if(status.getCode() <= 0x0100){
            statusTlv.addTlvData(DataWork.putWord(status.getCode()));
        } else {
            statusTlv.addTlvData(DataWork.putWord(0x0000));
        }
        snac.addTlv(statusTlv);
       
        // TLV.Type(0x0C) - dc info
        Tlv dcTlv = new Tlv(0x0C);
        // DC Internal ip address
        dcTlv.addTlvData(DataWork.putDWord(0xC0A80001));
        // DC tcp port
        dcTlv.addTlvData(DataWork.putDWord(0x0000ABCD));
        // DC type
        dcTlv.addTlvData(DataWork.putByte(directConnect.getCode()));
        // DC protocol version
        dcTlv.addTlvData(DataWork.putWord(protocolVersion.getCode()));
        // DC auth cookie
        dcTlv.addTlvData(DataWork.putDWord(0x00));
        // Web front port
        dcTlv.addTlvData(DataWork.putDWord(0x00));
        // Client futures
        dcTlv.addTlvData(DataWork.putDWord(0x00));
        // last info update time
        dcTlv.addTlvData(DataWork.putDWord(0x00));
        // last ext info update time
        dcTlv.addTlvData(DataWork.putDWord(0x00));
        // last ext status update time
        dcTlv.addTlvData(DataWork.putDWord(0x00));
        // unknown
        dcTlv.addTlvData(DataWork.putWord(0x00));   
              
        snac.addTlv(dcTlv);
        
        // TODO добавить класс пользователя
                      
        addSnac(snac);
    }

}
