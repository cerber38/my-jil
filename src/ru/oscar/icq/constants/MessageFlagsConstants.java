
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public class MessageFlagsConstants {
    
    public static final int MESSAGE_NORMAL = 0x01;
    public static final int MESSAGE_AUTO = 0x02;
    public static final int MESSAGE_MULTI = 0x80;    
    
    private int code;
    
    public MessageFlagsConstants(int code){
        this.code = code;
    }
    
    public String toString(){
        switch(code){
            case MESSAGE_NORMAL:
                return "Normal message.";
            case MESSAGE_AUTO:
                return "Auto-message flag.";  
            case MESSAGE_MULTI:
                return "This is multiple recipients message.";                   
        }
        return "Unknown message flag. (" + code + ")";
    }    

  public int getCode(){
      return code;
  }    
    
}
