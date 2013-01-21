
package ru.oscar.icq.contacts;

import ru.oscar.icq.constants.SsiConstants;

/**
 * @author Kornackiy Alexsandr
 */

public class Privacy {
    private String sn;
    
    private int id;
    private int type;
    
    public Privacy(String sn, int id, int type) {
        this.sn = sn;
        this.id = id;
        this.type = type;
    }

    /**
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }
    
    public String toString(){
        switch(type){
            case SsiConstants.TYPE_VISIBLE:
                return "[VISIBLE]";
            case SsiConstants.TYPE_INVISIBLE:
                return "[INVISIBLE]";
            case SsiConstants.TYPE_IGNORE_LIST:
                return "[IGNOR]";       
    }    
      return "";  
    }
    
}
