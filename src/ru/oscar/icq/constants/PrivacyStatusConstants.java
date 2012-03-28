
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public class PrivacyStatusConstants {
    
    public static final int VISIBLE_ALL = 1;
    public static final int INVISIBLE_ALL = 2;
    public static final int VISIBLE_TO_VISIBLE_LIST = 3;
    public static final int INVISIBLE_TO_INVISIBLE_LIST = 4;
    public static final int VISIBLE_TO_CONTACT_LIST = 5;

    private int code;

    public PrivacyStatusConstants(int code) {       
        this.code = code;
    }

    public String toString() {     
           switch (code)  {
               case VISIBLE_ALL :
                   return "Visible to all.";
               case INVISIBLE_ALL :
                   return "Invisible to all.";
               case VISIBLE_TO_VISIBLE_LIST :
                   return "Visible to the list of those who see.";
               case INVISIBLE_TO_INVISIBLE_LIST :
                   return "Invisible to the unseeing list.";
               case VISIBLE_TO_CONTACT_LIST : 
                   return "Visible to the list of contacts."; 
                   }     
           return "No private status.";
           }

    public int getCode() {
        return code;
    }    
}
