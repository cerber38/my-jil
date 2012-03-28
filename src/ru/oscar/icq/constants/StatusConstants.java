
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public class StatusConstants {
    
  public static final int STATUS_ONLINE = 0x0000;  
  public static final int STATUS_AWAY = 0x0001;  
  public static final int STATUS_DND = 0x0002; 
  public static final int STATUS_NA = 0x0004; 
  public static final int STATUS_OCCUPIED = 0x0010; 
  public static final int STATUS_FREE4CHAT = 0x0020; 
  public static final int STATUS_INVISIBLE = 0x0100; 
  
  public static final int STATUS_EVIL = 0x3000;
  public static final int STATUS_DEPRESSION = 0x4000;
  public static final int STATUS_HOME = 0x5000;
  public static final int STATUS_WORK = 0x6000;
  public static final int STATUS_LUNCH = 0x2001;
  
  public static final int STATUS_OFFLINE = 0xFF;   
  public static final int STATUS_INVISIBLE_ALL = 0x200;  
  
  private int code = 0;
  
  public StatusConstants(int code){
      this.code = code;
  }
  
  private byte[][] statusMatrix = {      
      {(byte)0xb7, 0x07, 0x43, 0x78,
      (byte)0xf5, 0x0c, 0x77, 0x77, (byte)0x97,
      0x77, 0x57, 0x78, 0x50, 0x2d,
      0x05, 0x79},
      {(byte)0xb7, 0x07, 0x43, 0x78,
      (byte)0xf5, 0x0c, 0x77, 0x77, (byte)0x97,
      0x77, 0x57, 0x78, 0x50, 0x2d,
      0x05, 0x70},
      {(byte)0xb7, 0x07, 0x43, 0x78,
      (byte)0xf5, 0x0c, 0x77, 0x77, (byte)0x97,
      0x77, 0x57, 0x78, 0x50, 0x2d,
      0x05, 0x76},
      {(byte)0xb7, 0x07, 0x43, 0x78,
      (byte)0xf5, 0x0c, 0x77, 0x77, (byte)0x97,
      0x77, 0x57, 0x78, 0x50, 0x2d,
      0x05, 0x77},
      {(byte)0xb7, 0x07, 0x43, 0x78,
      (byte)0xf5, 0x0c, 0x77, 0x77, (byte)0x97,
      0x77, 0x57, 0x78, 0x50, 0x2d,
      0x05, 0x78}       
  };
  
  public byte[] getHEXStatus(int code){
      switch(code){     
          case STATUS_EVIL:
              return statusMatrix[0]; 
          case STATUS_DEPRESSION:
              return statusMatrix[1];    
          case STATUS_HOME:
              return statusMatrix[2];    
          case STATUS_WORK:
              return statusMatrix[3];   
          case STATUS_LUNCH:
              return statusMatrix[4];               
      }
      return new byte[16];      
  }

  public String toString(){
      switch(code){
          case STATUS_ONLINE:
              return "Status is online.";
          case STATUS_AWAY:
              return "Status is away.";      
          case STATUS_DND:
              return "Status is no not disturb (DND).";    
          case STATUS_NA:
              return "Status is not available (N/A).";    
          case STATUS_OCCUPIED:
              return "Status is occupied (BISY).";  
          case STATUS_FREE4CHAT:
              return "Status is free for chat.";  
          case STATUS_INVISIBLE:
              return "Status is invisible.";      
          case STATUS_EVIL:
              return "Status is evil."; 
          case STATUS_DEPRESSION:
              return "Status is depression.";    
          case STATUS_HOME:
              return "Status is home.";    
          case STATUS_WORK:
              return "Status is work.";   
          case STATUS_LUNCH:
              return "Status is lunch.";   
          case STATUS_INVISIBLE_ALL:
              return "Status is invisible all.";   
          case STATUS_OFFLINE:
              return "Status is offline.";               
      }
      return "No status.";
  }
    
  
  public int getCode(){
      return code;
  }
  
}
