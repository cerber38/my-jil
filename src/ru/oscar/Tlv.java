
package ru.oscar;

import ru.oscar.util.StringUtil;

/**
 * TLV (Type-Length-Value) 
 * @author Kornackiy Alexsandr
 */

public class Tlv extends BasicData { 
    
    public final int TLV_HEADER_SIZE = 4;
    
    private int tlvType;
    private int tlvLength;
    
    public Tlv(int type) {
        putHeader(DataWork.putWord(type));
        tlvType = type;
        modifyHeader = true;
    }
    
    public Tlv(int tlvType, Data data) {
        this(tlvType);  
        tlvLength += data.getDataLength();
        putData(data);
    }
    
    public Tlv(int tlvType, String data) {
        this(tlvType, new Data(StringUtil.bytesOfString(data)));
    }
    
    public Tlv(int tlvType, int value) {
        this(tlvType, DataWork.putWord(value));            
    }      
    
    public Tlv(byte[] array, int index){
        tlvType = DataWork.getWord(array, index); 
        index += 2;
        tlvLength = DataWork.getWord(array, index);  
        index += 2;
        byte[] data = new byte[tlvLength];
        System.arraycopy(array, index , data, 0, tlvLength);
        putData(new Data(data));
    }        
    
    public int getTlvType(){
        return tlvType;
    }
    
    public int getTlvLength(){
        return tlvLength;
    }    
    
    public void addTlvData(Data data){
        tlvLength += data.getDataLength();
        putData(data);
    }    
    
    public void addTlvtoTlv(Tlv tlv){
        tlvLength += tlv.getTlvLength() + TLV_HEADER_SIZE;
        putData(new Data(tlv.getByteArray()));
    }
    
}
