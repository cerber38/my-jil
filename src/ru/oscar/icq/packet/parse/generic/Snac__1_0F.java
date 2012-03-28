
package ru.oscar.icq.packet.parse.generic;

//import ru.oscar.icq.DataWork;
//import ru.oscar.icq.Flap;
//import ru.oscar.icq.Tlv;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;
//import ru.oscar.icq.util.StringUtil;

/**
 * Snac(1, 13)
 * Этот SNAC посылается после изменения статуса клиента.
 * @author Kornackiy Alexsandr
 */
public class Snac__1_0F extends DefaultCommand{
    
    public Snac__1_0F(){
        super();
    }
    
//    @Override
//    public void exec(Flap f) {
//        int index = 0;  // 6 = flags and request-id  
//        byte data[] = f.getDataArray();
//        
//        // correction is taken from caffeineim
//        if((f.getSnac().getFlags0() & 0x80) != 0) {
//            int unknownDataLen = DataWork.getWord(data, index);
//            index += unknownDataLen + 2;
//        }
//        
//        //uin string length
//        int uinLen = DataWork.getByte(data, index);
//        index++;
//        
//        //uin string
//        byte[] uinData = DataWork.getArray(data, index, uinLen);
//        index += uinLen;
//        
//        String uin = StringUtil.stringOfBytes(uinData);
//        
//        //warning level
//        int warnLevel = DataWork.getWord(data, index);
//        index += 2;
//        
//        int tlvNumber = DataWork.getWord(data, index);
//        index += 2;
//        
//        for(int a = 0; a < tlvNumber; a++){
//            Tlv tlv = new Tlv(data, index);     
//            //tlvType = 1
//            //tlvType = 5
//            //tlvType = 13
//            //tlvType = 15
//            //tlvType = 3
//            //tlvType = 21
//            //tlvType = 10
//            //tlvType = 34
//            //tlvType = 30
//            //tlvType = 40
//            //tlvType = 45
//            //tlvType = 44  
//            index += tlv.getTlvLength();
//        }
//
//    }

    @Override
    public void notify(Connect connect) {

    }
    

}