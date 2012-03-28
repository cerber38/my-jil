
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public class LoginErrorConstants {
    
    public static final int INVALID_NICK_PASSWORD = 0x0001;
    public static final int SERVICE_TEMPORARILY = 0x0002;
    public static final int ALL_OTHER_ERRORS = 0x0003;
    public static final int INCORRECT_NICK_PASSWORD = 0x0004;
    public static final int MISMATCH_NICK_PASSWORD = 0x0005;
    public static final int INTERNAL_CLIENT_ERROR = 0x0006;
    public static final int INVALID_ACCOUNT = 0x0007;
    public static final int DELETED_ACCOUNT = 0x0008;
    public static final int EXPIRED_ACCOUNT = 0x0009;
    public static final int NO_ACCESS_DATABASE = 0x000A;
    public static final int NO_ACCESS_RESOLVER = 0x000B;
    public static final int INVALID_DATABASE = 0x000C;
    public static final int BAD_DATABASE_STAUS = 0x000D;
    public static final int BAD_RESOLVER_STAUS = 0x000E;
    public static final int INTERNAL_ERROR = 0x000F;
    public static final int SERVICE_OFFLINE = 0x0010;
    public static final int SUSPENDED_ACCOUNT = 0x0011;
    public static final int DB_SEND_ERRORE = 0x0012;
    public static final int DB_LINK_ERRORE = 0x0013;
    public static final int RESERVATION_MAP_ERROR = 0x0014;
    public static final int RESERVATION_LINK_ERROR = 0x0015;
    public static final int USER_CONNECT_IP = 0x0016;
    public static final int USER_CONNECT_IP_RES = 0x0017;
    public static final int USER_CONNECT_IP_RES2 = 0x0018;
    public static final int USER_WARNED = 0x0019;
    public static final int RESERVATION_TIMEOUT = 0x001A;
    public static final int OLDER_VERSION = 0x001B;
    public static final int OLDER_VERSION_RECOM = 0x001C;
    public static final int RATE_LIMIT = 0x001D;
    public static final int NO_REGISTED_NETWORK = 0x001E;
    public static final int INVALID_SECURID = 0x0020;
    public static final int SUSPENDED_BECAUSE = 0x0022;
    
    public int code;
    
    public LoginErrorConstants(int code){
        this.code = code;
    }        	  	     
       
    public String toString(){
        switch(code){
            case INVALID_NICK_PASSWORD:
                return "Invalid nick or password.";
            case SERVICE_TEMPORARILY:
                return "Service temporarily unavailable.";
            case ALL_OTHER_ERRORS:
                return "All other errors.";       
            case INCORRECT_NICK_PASSWORD:
                return "Incorrect nick or password, re-enter.";       
            case MISMATCH_NICK_PASSWORD:
                return "Mismatch nick or password, re-enter.";     
            case INTERNAL_CLIENT_ERROR:
                return "Internal client error (bad input to authorizer).";    
            case INVALID_ACCOUNT:
                return "Invalid account.";  
            case DELETED_ACCOUNT:
                return "Deleted account.";        
            case EXPIRED_ACCOUNT:
                return "Expired account.";   
            case NO_ACCESS_DATABASE:
                return "No access to database.";    
            case NO_ACCESS_RESOLVER:
                return "No access to resolver.";      
            case INVALID_DATABASE:
                return "Invalid database fields.";  
            case BAD_DATABASE_STAUS:
                return "Bad database status.";   
            case BAD_RESOLVER_STAUS:
                return "Bad resolver status.";     
            case INTERNAL_ERROR:
                return "Internal error.";   
            case SERVICE_OFFLINE:
                return "Service temporarily offline.";       
            case SUSPENDED_ACCOUNT:
                return "Suspended account.";     
            case DB_SEND_ERRORE:
                return "DB send error.";   
            case DB_LINK_ERRORE:
                return "DB link error.";     
            case RESERVATION_MAP_ERROR:
                return "Reservation map error.";     
            case RESERVATION_LINK_ERROR:
                return "Reservation link error.";     
            case USER_CONNECT_IP:
                return "The users num connected from this IP has reached the maximum.";  
            case USER_CONNECT_IP_RES:
                return "The users num connected from this IP has reached the maximum (reservation).";    
            case USER_CONNECT_IP_RES2:
                return "Rate limit exceeded (reservation). Please try to reconnect in a few minutes.";        
            case USER_WARNED:
                return "User too heavily warned.";     
            case RESERVATION_TIMEOUT:
                return "Reservation timeout.";      
            case OLDER_VERSION:
                return "You are using an older version of ICQ. Upgrade required.";   
            case OLDER_VERSION_RECOM:
                return "You are using an older version of ICQ. Upgrade recommended.";    
            case RATE_LIMIT:
                return "Rate limit exceeded. Please try to reconnect in a few minutes.";   
            case NO_REGISTED_NETWORK:
                return "Can't register on the ICQ network. Reconnect in a few minutes.";   
            case INVALID_SECURID:
                return "Invalid SecurID.";   
            case SUSPENDED_BECAUSE:
                return "Account suspended because of your age (age < 13) .";                   
        }
        return "Unknown error (code = " + code + ")";
    }
    
  public int getCode(){
      return code;
  }    
   
}
