
package ru.oscar.icq.packet.send.location;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Snac;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.constants.StatusConstants;
import ru.oscar.icq.constants.XStatusConstants;
import ru.oscar.icq.setting.Capabilities;

/**
 * Snca (2, 4)
 * Отправим наши возможности.
 * @author Kornackiy Alexsandr
 */

public class Snac__2_4 extends Flap {

    public Snac__2_4(Capabilities capabilities, XStatusConstants xstatus, StatusConstants status){
        super(CHANNEL2);
        
        Snac snac = new Snac(0x02, 0x04, 0x00, 0x00, 0x00);

        Tlv capaTlv = new Tlv(5); 
        capaTlv.addTlvData(DataWork.putArray(capabilities.getClient()));
        
        if(xstatus.getCode() != 0){           
            capaTlv.addTlvData(DataWork.putArray(xstatus.getXStatusGUID()));
        }        
        
        if(status.getCode() >= 0x2001){
            capaTlv.addTlvData(DataWork.putArray(status.getHEXStatus(status.getCode())));
        }
        
        if(status.getCode() == 0x0100) {
            
        } else if(status.getCode() == 0x200) {
        // TODO: при STATUS_INVISIBLE_ALL qip шлет STATUS_INVISIBLE и
        // snac (13, 9) falgs 0x0001, len 0x0002, value 0x00004, data 0x0000    
            }        
        
        snac.addTlv(capaTlv);
              
        addSnac(snac);
    }
}
