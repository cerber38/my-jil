
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public class BotInfoConstants {
    
  public static final int NORMAL_BOT = 0x00000001;   
  public static final int MOBILE_BOT = 0x00000002;  
  
  private int code = 0;
  
  public BotInfoConstants(int code){
      this.code = code;
  }    
  public String toString(){
      switch(code){
          case NORMAL_BOT:
              return "Обычный бот.";   
          case MOBILE_BOT:
              return "Бот для мобильных устройств.";                 
      }
      return "";
  }
      
  public int getCode(){
      return code;
  }  
  
}
