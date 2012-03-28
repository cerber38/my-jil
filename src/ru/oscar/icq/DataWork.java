
package ru.oscar.icq;

/**
 * @author Kornackiy Alexsandr
 */

public class DataWork {
    
    public static Data putArray(byte[] array) {
        byte[] data = new byte[array.length];
        System.arraycopy(array, 0, data, 0, array.length);
        return new Data(data);
    }
    
    public static byte[] getArray(byte[] array, int index, int lenght) {
        byte[] data = new byte[lenght];
        System.arraycopy(array, index, data, 0, lenght);
        return data;
    }    
    
    public static Data putByte(int value) {
        return new Data(new byte[]{(byte) (value & 0x000000FF)});
    }  
    
    public static Data putWord(int value) {   
        byte[] data = new byte[2];
        data[0] = (byte) ((value >> 8)  & 0x00000000000000FF);
        data[1] = (byte) ((value)       & 0x00000000000000FF);
        return new Data(data);
    }     
    
    public static Data putDWord(int value) {  
        byte[] data = new byte[4];
        data[0] = (byte) ((value >> 24) & 0x00000000000000FF);
        data[1] = (byte) ((value >> 16) & 0x00000000000000FF);
        data[2] = (byte) ((value >> 8)  & 0x00000000000000FF);
        data[3] = (byte) ((value)       & 0x00000000000000FF);
        return new Data(data);
    }     
    
    
    public static int getByte(byte[] data, int index) {
        return ((byte) data[index]) & 0x000000FF;
    }    
    
    public static int getWord(byte[] data, int index) {
        int value = (((byte) data[index]) << 8) & 0x0000FF00;
        return value | (((byte) data[++index])) & 0x000000FF;
    }    
    
    public static int getDWord(byte[] data, int index) {
        int value;
        value  = (((byte) data[index]) << 24)   & 0xFF000000;
        value |= (((byte) data[++index]) << 16) & 0x00FF0000;
        value |= (((byte) data[++index]) << 8)  & 0x0000FF00;
        value |= (((byte) data[++index]))       & 0x000000FF;
        return value;
    }   
    
    public static int getWordLE(byte[] data, int index) {
        int value = (((int) data[index])) & 0x000000FF;
        return value | (((int) data[++index]) << 8) & 0x0000FF00;
    }   
    
    public static int getDWordLE(byte[] data, int index) {
        int value;
        value  = (((byte) data[index]))         & 0x000000FF;
        value |= (((byte) data[++index]) << 8)  & 0x0000FF00;
        value |= (((byte) data[++index]) << 16) & 0x00FF0000;
        value |= (((byte) data[++index]) << 24) & 0xFF000000;
        return value;
    }    
    
    public static Data putDWordLE(int value) {
        byte[] data = new byte[4];
        data[0] = (byte) ((value)       & 0x00000000000000FF);
        data[1] = (byte) ((value >> 8)  & 0x00000000000000FF);
        data[2] = (byte) ((value >> 16) & 0x00000000000000FF);
        data[3] = (byte) ((value >> 24) & 0x00000000000000FF);
        return new Data(data);
    }
    
    public static Data putWordLE(int value) {
        byte[] data = new byte[2];
        data[0] = (byte) ((value)      & 0x000000FF);
        data[1] = (byte) ((value >> 8) & 0x000000FF);
        return new Data(data);
    }    
    
    public static Data putArrayLE(byte[] array) {
        byte[] data = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            data[i] = array[array.length - 1 - i];
        }
        return new Data(data);
    }
    
    public static byte[] getArrayLE(byte[] array, int index, int lenght) {
        byte[] data = new byte[lenght];
        for (int i = index; i < lenght; i++) {
            data[i] = array[lenght - 1 - i];
        }        
        return data;
    }    

}
