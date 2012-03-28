
package ru.oscar.icq;

/**
 * @author Kornackiy Alexsandr
 */

public class Data {
    
    private byte[] data;
    
    public Data(byte[] data){
        this.data = data;
    }
    
    public int getDataLength(){
        return data.length;
    }
    
    public byte[] getData(){
        return data;
    }
    
}
