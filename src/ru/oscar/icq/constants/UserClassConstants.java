
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public class UserClassConstants {
   
  public static final int UNCONFIRMED = 0x0001;   
  public static final int ADMINISTRATOR = 0x0002; 
  public static final int AOL = 0x0004; 
  public static final int COMMERCIAL = 0x0008; 
  public static final int AIM = 0x0010; 
  public static final int UNAVAILABLE = 0x0020;
  public static final int ICQ = 0x0040;
  public static final int WIRELESS = 0x0080;
  public static final int INTERNAL = 0x0100;
  public static final int IMF = 0x0200;
  public static final int BOT = 0x0400;
  public static final int BEAST = 0x0800;
  public static final int ONE_WAY_WIRELESS = 0x1000;
  public static final int OFFICIAL = 0x2000;
  public static final int MATCHES_YOURS = 0x00010000;
  public static final int MATCHES_BUDDY = 0x00020000;
  public static final int NO_KNOCK_KNOCK = 0x00040000;
  public static final int FORWARD_MOBILE = 0x00080000;
  public static final int BEBO = 0x00200000;
    
  private int code = 0;
  
  public UserClassConstants(int code){
      this.code = code;
  }    
  public String toString(){
      switch(code){
          case UNCONFIRMED:
              return "Не подтверждённый аккаунт.";   
          case ADMINISTRATOR:
              return "Администратор.";  
          case AOL:
              return "Пользователь AOL.";   
          case COMMERCIAL:
              return "Коммерческий пользователь.";    
          case AIM:
              return "Пользователь AIM.";    
          case UNAVAILABLE:
              return "Пользователь «отошел».";   
          case ICQ:
              return "Пользователь ICQ."; 
          case WIRELESS:
              return "Пользователь на мобильном устройстве.";    
          case INTERNAL:
              return "Внутренний пользователь.";  
          case IMF:
              return "Использует IM-переадресацию.";  
          case BOT:
              return "Бот.";    
          case BEAST:
              return "«Зверь».";  
          case ONE_WAY_WIRELESS:
              return "Клиент поддерживающий только приём сообщений.";     
          case OFFICIAL:
              return "Официальный пользователь.";     
          case MATCHES_YOURS:
              return "Matches an interest of yours."; 
          case MATCHES_BUDDY:
              return "Matches an interest of a buddy.";    
          case NO_KNOCK_KNOCK:
              return "Не показывать запросы «не в списке»."; 
          case FORWARD_MOBILE:
              return "Если нет активных сессий, перенаправить на мобильный.";   
          case BEBO:
              return "Has Bebo Profile.";                
      }
      return "No user class.";
  }
      
  public int getCode(){
      return code;
  }  
  
}
