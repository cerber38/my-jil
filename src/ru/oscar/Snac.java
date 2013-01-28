
package ru.oscar;

/**
 * (SNAC) Simple Network Atomic Communication
 * @author Kornackiy Alexsandr
 */

public class Snac extends BasicData {

    private int familyID;
    private int subTypeID;
    private int flags0;
    private int flags1;
    private int requestID;
    
    public Snac(int familyID, int subTypeID, int flags0, int flags1, int requestID){
        this.familyID = familyID;
        this.subTypeID = subTypeID;
        this.flags0 = flags0;
        this.flags1 = flags1;
        this.requestID = requestID;       
        putHeader(DataWork.putWord(familyID));
        putHeader(DataWork.putWord(subTypeID));
        putHeader(DataWork.putByte(flags0));
        putHeader(DataWork.putByte(flags1));
        putHeader(DataWork.putDWord(requestID));
        modifyHeader = false;
    }
    
    public Snac(int familyID, int subTypeID, int flags0, int flags1){
        this.familyID = familyID;
        this.subTypeID = subTypeID;
        this.flags0 = flags0;
        this.flags1 = flags1;       
        putHeader(DataWork.putWord(familyID));
        putHeader(DataWork.putWord(subTypeID));
        putHeader(DataWork.putByte(flags0));
        putHeader(DataWork.putByte(flags1));
        modifyHeader = false;
    }    
    
    public Snac(int familyID){
        this.familyID = familyID;
        putHeader(DataWork.putWord(familyID));
        modifyHeader = false;
    }    
    
    public Snac(byte array[], int index){
        familyID = DataWork.getWord(array, index);
        index += 2;
        subTypeID = DataWork.getWord(array, index); 
        index += 2;
        flags0 = DataWork.getByte(array, index); 
        index++;
        flags1 = DataWork.getByte(array, index);
        index++;
        requestID = DataWork.getDWord(array, index);   
        index +=4;
        byte[] data = new byte[array.length - index];
        System.arraycopy(array, index, data, 0, array.length - index);
        addSnacData(new Data(data));
    }
    
    public void addSnacData(Data data){
        putData(data);
    }   
    
    public void addTlv(Tlv tlv) {
        addSnacData(new Data(tlv.getByteArray()));
    }
    
    public void finishHeader(int subTypeID, int flags0, int flags1, int requestID){
        putHeader(DataWork.putWord(subTypeID));
        putHeader(DataWork.putByte(flags0));
        putHeader(DataWork.putByte(flags1));
        putHeader(DataWork.putDWord(requestID));       
    }
    
    public void finishHeader(int requestID){
        putHeader(DataWork.putDWord(requestID));       
    }    
    
    public int getFamilyID(){
        return familyID;
    }
    
    public int getSubTypeID(){
        return subTypeID;
    }    
    
    public int getFlags0(){
        return flags0;
    }    
    
    public int getFlags1(){
        return flags1;
    } 
    
    public int getRequestID(){
        return requestID;
    }
    
    public int getError(){
        return DataWork.getWord(getDataArray(), 2);
    }
        
}
