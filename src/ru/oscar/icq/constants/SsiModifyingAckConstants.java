
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public class SsiModifyingAckConstants {
    
    public static final int NO_ERRORS = 0x00;
    public static final int DATABASE_ERROR = 0x01;  
    public static final int NOT_FOUND_IN_LIST = 0x02;  
    public static final int ALLREADY_EXISTS = 0x03;
    public static final int NOT_WORKING = 0x05;
    public static final int ERROR_ADDING_ITEM = 0x0A;
    public static final int TIMED_OUT = 0x0B;
    public static final int LIMIT_OF_ITEMS_EXCEEDED = 0x0C;
    public static final int TRYING_ADD_ICQ_TO_AIM = 0x0D;
    public static final int CANT_ADD_BECAUSE_REQUEST_AUTH = 0x0E;  
    public static final int AUTOMATIC_AUTHORIZATION = 0x0F;  
    public static final int BAD_USER_ID = 0x10;
    public static final int MANY_CONTACTS = 0x11;
    public static final int FRIEND_SMART_GROUP = 0x14;// ?
    public static final int TOTAL_TIME_ELAPSED = 0x1A;
    
    private int code;
    
    public SsiModifyingAckConstants(int code){
        this.code = code;
    }
    
public String toString() {
    switch(getCode()){
        case NO_ERRORS:
            return "No errors (success).";
        case DATABASE_ERROR:
            return "Database error.";            
        case NOT_FOUND_IN_LIST:
            return "Item you want to modify not found in list.";
        case ALLREADY_EXISTS:
            return "Item you want to add allready exists.";
        case NOT_WORKING:
            return "Server or database is not working.";            
        case ERROR_ADDING_ITEM:
            return "Error adding item (invalid id, allready in list, invalid data).";
        case TIMED_OUT:
            return "Time for the operation timed out.";            
        case LIMIT_OF_ITEMS_EXCEEDED:
            return "Can't add item. Limit for this type of items exceeded.";
        case TRYING_ADD_ICQ_TO_AIM:
            return "Trying to add ICQ contact to an AIM list.";
        case CANT_ADD_BECAUSE_REQUEST_AUTH:
            return "Can't add this contact because it requires authorization.";
        case AUTOMATIC_AUTHORIZATION:
            return "Automatic authorization.";   
        case BAD_USER_ID:
            return "Bad user id.";   
        case MANY_CONTACTS:
            return "There are too many contacts.";   
        case FRIEND_SMART_GROUP:
            return "Trying to add friends in smart group.";     
        case TOTAL_TIME_ELAPSED:
            return "Total time elapsed.";             
    }
    return "Unknown error code.";
}

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }
}
