
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */
public class ProtocolVersionConstants {
    
  public static final int DCP_ICQ98 = 0x0004;  
  public static final int DCP_ICQ99 = 0x0006;  
  public static final int DCP_ICQ2000 = 0x0007; 
  public static final int DCP_ICQ2001 = 0x0008; 
  public static final int DCP_ICQLITE = 0x0009;  
  public static final int DCP_ICQ2003B = 0x000A;
  
  private int code = 0;
  
  public ProtocolVersionConstants(int code){
      this.code = code;
  }
  
  public String toString(){
      switch(code){
          case DCP_ICQ98:
              return "ICQ98.";
          case DCP_ICQ99:
              return "ICQ99.";      
          case DCP_ICQ2000:
              return "ICQ2000.";    
          case DCP_ICQ2001:
              return "ICQ2001.";    
          case DCP_ICQLITE:
              return "ICQ Lite.";   
          case DCP_ICQ2003B:
              return "ICQ2003B.";               
      }      
      return "No protocol version.";
  }  
  
  public int getCode(){
      return code;
  }
}
    

