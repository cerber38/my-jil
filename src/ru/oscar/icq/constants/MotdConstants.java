
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */

public class MotdConstants {
    
    public static final int MTD_MDT_UPGRAGE = 0x0001;
    public static final int MTD_ADV_UPGRAGE = 0x0002;
    public static final int MTD_SYS_BULLETIN = 0x0003;
    public static final int MTD_NORMAL = 0x0004;
    public static final int MTD_NONE = 0x0005;
    public static final int MTD_NEWS = 0x0006; 
    
    private int code;
    
    public MotdConstants(int code){
        this.code = code;
    }
    
    public String toString(){
        String s = "";
        switch(code){
            case MTD_MDT_UPGRAGE:
                return "Mandatory upgrade needed notice";
            case MTD_ADV_UPGRAGE:
                return "Advisable upgrade notice";
            case MTD_SYS_BULLETIN:
                return "AIM/ICQ service system announcements";
            case MTD_NORMAL:
                return "Standart notice";
            case MTD_NONE:
                return "MTD none";                
            case MTD_NEWS:
                return "Some news from AOL service";                
        }
        return "Unknown mtd message. (" + code + ")";
    }
    
    public int getCode(){
        return code;
    }

}
