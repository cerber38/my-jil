
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public class DirectConnectConstants {
    
  public static final int DC_DISABLED = 0x0000;  
  public static final int DC_HTTPS = 0x0001;  
  public static final int DC_SOCKS = 0x0002; 
  public static final int DC_NORMAL = 0x0004; 
  public static final int DC_WEB = 0x0006;  
  
  private int code = 0;
  
  public DirectConnectConstants(int code){
      this.code = code;
  }
  
  public String toString(){
      switch(code){
          case DC_DISABLED:
              return "Direct connection disabled / auth required.";
          case DC_HTTPS:
              return "Direct connection thru firewall or https proxy.";      
          case DC_SOCKS:
              return "Direct connection thru socks4/5 proxy server.";    
          case DC_NORMAL:
              return "Normal direct connection (without proxy/firewall).";    
          case DC_WEB:
              return "Web client - no direct connection.";                 
      }
      return "No direct connection. (" + code + ")";
  }  
  
  public int getCode(){
      return code;
  }
}
