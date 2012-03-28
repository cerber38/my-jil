
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public class MessageTypesConstants {
    
    public static final int MESSAGE_PLAIN = 0x01;
    public static final int MESSAGE_CHAT = 0x02;
    public static final int MESSAGE_FILEREQ = 0x03;
    public static final int MESSAGE_URL = 0x04;
    public static final int MESSAGE_AUTHREQ = 0x06;
    public static final int MESSAGE_AUTHDENY = 0x07;
    public static final int MESSAGE_AUTHOK = 0x08;
    public static final int MESSAGE_SERVER = 0x09;
    public static final int MESSAGE_ADDED = 0x0C;
    public static final int MESSAGE_WWP = 0x0D;
    public static final int MESSAGE_EEXPRESS = 0x0E;
    public static final int MESSAGE_CONTACTS = 0x13;
    public static final int MESSAGE_PLUGIN = 0x1A;
    public static final int MESSAGE_AUTOAWAY = 0xE8;
    public static final int MESSAGE_AUTOBUSY = 0xE9;
    public static final int MESSAGE_AUTONA = 0xEA;
    public static final int MESSAGE_AUTODND = 0xEB;
    public static final int MESSAGE_AUTOFFC = 0xEC;
    
    private int code;
    
    public MessageTypesConstants(int code){
        this.code = code;
    }
    
    public String toString(){
        switch(code){
            case MESSAGE_PLAIN:
                return "Plain text (simple) message.";
            case MESSAGE_CHAT:
                return "Chat request message.";  
            case MESSAGE_FILEREQ:
                return "File request / file ok message.";   
            case MESSAGE_URL:
                return "URL message (0xFE formatted).";    
            case MESSAGE_AUTHREQ:
                return "Authorization request message (0xFE formatted).";        
            case MESSAGE_AUTHDENY:
                return "Authorization denied message (0xFE formatted).";   
            case MESSAGE_AUTHOK:
                return "Authorization given message (empty).";       
            case MESSAGE_SERVER:
                return "Message from OSCAR server (0xFE formatted).";   
            case MESSAGE_ADDED:
                return "\"You-were-added\" message (0xFE formatted)."; 
            case MESSAGE_WWP:
                return "Web pager message (0xFE formatted).";     
            case MESSAGE_EEXPRESS:
                return "Email express message (0xFE formatted).";     
            case MESSAGE_CONTACTS:
                return "Contact list message.";   
            case MESSAGE_PLUGIN:
                return "Plugin message described by text string.";       
            case MESSAGE_AUTOAWAY:
                return "Auto away message."; 
            case MESSAGE_AUTOBUSY:
                return "Auto occupied message.";       
            case MESSAGE_AUTONA:
                return "Auto not available message.";       
            case MESSAGE_AUTODND:
                return "Auto do not disturb message.";       
            case MESSAGE_AUTOFFC:
                return "Auto free for chat message.";                 
        }
        return "Unknown message type. (" + code + ")";
    }
    
  public int getCode(){
      return code;
  }
  
}
