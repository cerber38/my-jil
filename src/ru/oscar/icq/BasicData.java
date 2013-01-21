
package ru.oscar.icq;

import java.util.ArrayList;

/**
 * @author Kornackiy Alexsandr
 */

public abstract class BasicData {
    
    private ArrayList<Data> headerData;
    private int headerLength;
    
    private ArrayList<Data> dataPacket;
    private int dataLength;   
    
    private byte[] packet;
    
    protected boolean modifyHeader;
    
    public BasicData(){
        headerData = new ArrayList<Data>(20);
        dataPacket = new ArrayList<Data>(20);
    }
    
    public void putHeader(Data data){
        headerData.add(data);        
        headerLength += data.getDataLength();
    }
    
    public void putData(Data data){
        int len = data == null ? 0 : data.getDataLength();    
        dataLength += len;
        if(len > 0)
            dataPacket.add(data); 
    }
    
    public byte[] getByteArray(){
        // set lenght data
        if(modifyHeader){
            packet = new byte[(headerLength + 2) + dataLength];            
            putHeader(DataWork.putWord(dataLength));        
        } else{
            packet = new byte[headerLength + dataLength];    
        }      
        
        int index = 0;
        
        for(Data header : headerData){
            System.arraycopy(header.getData(), 0, packet, index,
                       header.getData().length);    
            index += header.getData().length;
        }     
        for(Data data : dataPacket){    
            System.arraycopy(data.getData(), 0, packet, index,
                       data.getData().length);  
            index += data.getData().length;
        }     
        return packet;
    }   
    
    public byte[] getDataArray(){
        byte[] array = new byte[dataLength];
        int index = 0;
        for(Data data : dataPacket){
            System.arraycopy(data.getData(), 0, array, index,
                       data.getData().length);   
            index += data.getData().length;
        }      
        return array;
    }

    public int getHeaderLength(){
        return headerLength;
    }
    
    public int getDataLength(){
        return dataLength;
    }
    
}
