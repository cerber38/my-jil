
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public class StatusFlagConstants {
  
  public static final int STATUS_FLAG_NONE = -1;    
//  public static final int STATUS_FLAG_WEBAWARE = 0x0001;  
//  public static final int STATUS_FLAG_SHOWIP = 0x0002;  
//  public static final int STATUS_FLAG_BIRTHDAY = 0x0008; 
//  public static final int STATUS_FLAG_WEBFRONT = 0x0020; 
  public static final int STATUS_FLAG_WEBAWARE = 0x1001;  
  public static final int STATUS_FLAG_SHOWIP = 0x1002;  
  public static final int STATUS_FLAG_BIRTHDAY = 0x1008; 
  public static final int STATUS_FLAG_WEBFRONT = 0x1020;   
  public static final int STATUS_FLAG_DCDISABLED = 0x0100; 
  public static final int STATUS_FLAG_DCAUTH = 0x1000; 
  public static final int STATUS_FLAG_DCCONT = 0x2000; 
  public static final int STATUS_FLAG_DCALLOWED  = 0x0000;
  
  private int code = 0;
  
  public StatusFlagConstants(int code){
      this.code = code;
  }  
  
  public String toString(){
      switch(code){
          case STATUS_FLAG_WEBAWARE:
              return "Status webaware flag.";
          case STATUS_FLAG_SHOWIP:
              return "Status show ip flag.";      
          case STATUS_FLAG_BIRTHDAY:
              return "User birthday flag.";    
          case STATUS_FLAG_WEBFRONT:
              return "User active webfront flag.";    
          case STATUS_FLAG_DCDISABLED:
              return "Direct connection not supported.";  
          case STATUS_FLAG_DCAUTH:
              return "Direct connection upon authorization.";  
          case STATUS_FLAG_DCCONT:
              return "DC only with contact users.";                
          case STATUS_FLAG_DCALLOWED:
              return "DC all lowed.";                
      }      
      return "No status flag.";
  }
  
  public int getCode(){
      return code;
  }
    
}
