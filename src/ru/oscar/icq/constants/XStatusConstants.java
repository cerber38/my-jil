
package ru.oscar.icq.constants;

/**
 * @author Kornackiy Alexsandr
 */
public class XStatusConstants {

    public static final int NONE = 0;
    public static final int XSTATUS_ANGRY = 1;
    public static final int XSTATUS_TAKING_A_BATH = 2;
    public static final int XSTATUS_TIRED = 3;
    public static final int XSTATUS_PARTY = 4;
    public static final int XSTATUS_DRINKING_BEER = 5;
    public static final int XSTATUS_THINKING = 6;
    public static final int XSTATUS_EATING = 7;
    public static final int XSTATUS_WATCHING_TV = 8;
    public static final int XSTATUS_MEETING = 9;
    public static final int XSTATUS_COFFEE = 10;
    public static final int XSTATUS_LISTENING_TO_MUSIC = 11;
    public static final int XSTATUS_BUSINESS = 12;
    public static final int XSTATUS_SHOOTING = 13;
    public static final int XSTATUS_HAVING_FUN = 14;
    public static final int XSTATUS_ON_THE_PHONE = 15;
    public static final int XSTATUS_GAMING = 16;
    public static final int XSTATUS_STUDYING = 17;
    public static final int XSTATUS_SHOPPING = 18;
    public static final int XSTATUS_FEELING_SICK = 19;
    public static final int XSTATUS_SLEEPING = 20;
    public static final int XSTATUS_SURFING = 21;
    public static final int XSTATUS_BROWSING = 22;
    public static final int XSTATUS_WORKING = 23;
    public static final int XSTATUS_TYPING = 24;
    public static final int XSTATUS_PICNIC = 25;
    public static final int XSTATUS_COOKING = 26;
    public static final int XSTATUS_SMOKING = 27;
    public static final int XSTATUS_I_HIGH = 28;
    public static final int XSTATUS_ON_WC = 29;
    public static final int XSTATUS_QUESTION = 30;
    public static final int XSTATUS_WATCHING_PRO7 = 31;
    public static final int XSTATUS_LOVE = 32;
    public static final int XSTATUS_GOOGLE = 33;
    public static final int XSTATUS_NOTEPAD = 34;
    public static final int XSTATUS_SEX = 35;
    public static final int XSTATUS_RULOVE = 36;
    public static final int XSTATUS_SMOUKE = 37;
    /*
     3FB0BD36AF3B4A609EEFCF190F6A5A7E	 	 Smoke (RnQ client)	 
     E601E41C33734BD1BC06811D6C323D82	 	 Sex (RnQ client)	 
     B7074378F50C777797775778502D0570	 	 Depression (QIP client)	 
     B7074378F50C777797775778502D0575	 	 FFC (QIP client)	 
     B7074378F50C777797775778502D0576	 	 AtHome (QIP client)	 
     B7074378F50C777797775778502D0577	 	 AtWork (QIP client)	 
     B7074378F50C777797775778502D0578	 	 Lunch (QIP client)	 
     B7074378F50C777797775778502D0579	 	 Evil (QIP client)
     */   

    private int code;
    
    private String title;
    private String description;
    
    public XStatusConstants(int code, String title, String description){
        this.code = code;
        this.title = title;
        this.description = description;
    }
    
    public XStatusConstants(int code){
        this.code = code;
        title = "";
        description = "";
    }    
    
    private byte[][] xstatusMatrix = {
        { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },
        { 0x01, (byte) 0xD8, (byte) 0xD7, (byte) 0xEE, (byte) 0xAC, 0x3B,
        0x49, 0x2A, (byte) 0xA5, (byte) 0x8D, (byte) 0xD3,
        (byte) 0xD8, 0x77, (byte) 0xE6, 0x6B, (byte) 0x92 },
        { 0x5A, 0x58, 0x1E, (byte) 0xA1, (byte) 0xE5, (byte) 0x80, 0x43,
        0x0C, (byte) 0xA0, 0x6F, 0x61, 0x22, (byte) 0x98,
        (byte) 0xB7, (byte) 0xE4, (byte) 0xC7 },
        { (byte) 0x83, (byte) 0xC9, (byte) 0xB7, (byte) 0x8E, 0x77,
        (byte) 0xE7, 0x43, 0x78, (byte) 0xB2, (byte) 0xC5,
        (byte) 0xFB, 0x6C, (byte) 0xFC, (byte) 0xC3, 0x5B,
        (byte) 0xEC },
        { (byte) 0xE6, 0x01, (byte) 0xE4, 0x1C, 0x33, 0x73, 0x4B,
        (byte) 0xD1, (byte) 0xBC, 0x06, (byte) 0x81, 0x1D, 0x6C,
        0x32, 0x3D, (byte) 0x81 },
        { (byte) 0x8C, 0x50, (byte) 0xDB, (byte) 0xAE, (byte) 0x81,
        (byte) 0xED, 0x47, (byte) 0x86, (byte) 0xAC, (byte) 0xCA,
        0x16, (byte) 0xCC, 0x32, 0x13, (byte) 0xC7, (byte) 0xB7 },
        { 0x3F, (byte) 0xB0, (byte) 0xBD, 0x36, (byte) 0xAF, 0x3B, 0x4A,
        0x60, (byte) 0x9E, (byte) 0xEF, (byte) 0xCF, 0x19, 0x0F,
        0x6A, 0x5A, 0x7F },
        { (byte) 0xF8, (byte) 0xE8, (byte) 0xD7, (byte) 0xB2, (byte) 0x82,
        (byte) 0xC4, 0x41, 0x42, (byte) 0x90, (byte) 0xF8, 0x10,
        (byte) 0xC6, (byte) 0xCE, 0x0A, (byte) 0x89, (byte) 0xA6 },
        { (byte) 0x80, 0x53, 0x7D, (byte) 0xE2, (byte) 0xA4, 0x67, 0x4A,
        0x76, (byte) 0xB3, 0x54, 0x6D, (byte) 0xFD, 0x07, 0x5F,
        0x5E, (byte) 0xC6 },
        { (byte) 0xF1, (byte) 0x8A, (byte) 0xB5, 0x2E, (byte) 0xDC, 0x57,
        0x49, 0x1D, (byte) 0x99, (byte) 0xDC, 0x64, 0x44, 0x50,
        0x24, 0x57, (byte) 0xAF },
        { 0x1B, 0x78, (byte) 0xAE, 0x31, (byte) 0xFA, 0x0B, 0x4D, 0x38,
        (byte) 0x93, (byte) 0xD1, (byte) 0x99, 0x7E, (byte) 0xEE,
        (byte) 0xAF, (byte) 0xB2, 0x18 },
        { 0x61, (byte) 0xBE, (byte) 0xE0, (byte) 0xDD, (byte) 0x8B,
        (byte) 0xDD, 0x47, 0x5D, (byte) 0x8D, (byte) 0xEE, 0x5F,
        0x4B, (byte) 0xAA, (byte) 0xCF, 0x19, (byte) 0xA7 },
        { 0x48, (byte) 0x8E, 0x14, (byte) 0x89, (byte) 0x8A, (byte) 0xCA,
        0x4A, 0x08, (byte) 0x82, (byte) 0xAA, 0x77, (byte) 0xCE,
        0x7A, 0x16, 0x52, 0x08 },
        { 0x10, 0x7A, (byte) 0x9A, 0x18, 0x12, 0x32, 0x4D, (byte) 0xA4,
        (byte) 0xB6, (byte) 0xCD, 0x08, 0x79, (byte) 0xDB, 0x78,
        0x0F, 0x09 },
        { 0x6F, 0x49, 0x30, (byte) 0x98, 0x4F, 0x7C, 0x4A, (byte) 0xFF,
        (byte) 0xA2, 0x76, 0x34, (byte) 0xA0, 0x3B, (byte) 0xCE,
        (byte) 0xAE, (byte) 0xA7 },
        { 0x12, (byte) 0x92, (byte) 0xE5, 0x50, 0x1B, 0x64, 0x4F, 0x66,
        (byte) 0xB2, 0x06, (byte) 0xB2, (byte) 0x9A, (byte) 0xF3,
        0x78, (byte) 0xE4, (byte) 0x8D },
        { (byte) 0xD4, (byte) 0xA6, 0x11, (byte) 0xD0, (byte) 0x8F, 0x01,
        0x4E, (byte) 0xC0, (byte) 0x92, 0x23, (byte) 0xC5,
        (byte) 0xB6, (byte) 0xBE, (byte) 0xC6, (byte) 0xCC,
        (byte) 0xF0 },
        { 0x60, (byte) 0x9D, 0x52, (byte) 0xF8, (byte) 0xA2, (byte) 0x9A,
        0x49, (byte) 0xA6, (byte) 0xB2, (byte) 0xA0, 0x25, 0x24,
        (byte) 0xC5, (byte) 0xE9, (byte) 0xD2, 0x60 },
        { 0x63, 0x62, 0x73, 0x37, (byte) 0xA0, 0x3F, 0x49, (byte) 0xFF,
        (byte) 0x80, (byte) 0xE5, (byte) 0xF7, 0x09, (byte) 0xCD,
        (byte) 0xE0, (byte) 0xA4, (byte) 0xEE },
        { 0x1F, 0x7A, 0x40, 0x71, (byte) 0xBF, 0x3B, 0x4E, 0x60,
        (byte) 0xBC, 0x32, 0x4C, 0x57, (byte) 0x87, (byte) 0xB0,
        0x4C, (byte) 0xF1 },
        { 0x78, 0x5E, (byte) 0x8C, 0x48, 0x40, (byte) 0xD3, 0x4C, 0x65,
        (byte) 0x88, 0x6F, 0x04, (byte) 0xCF, 0x3F, 0x3F, 0x43,
        (byte) 0xDF },
        { (byte) 0xA6, (byte) 0xED, 0x55, 0x7E, 0x6B, (byte) 0xF7, 0x44,
        (byte) 0xD4, (byte) 0xA5, (byte) 0xD4, (byte) 0xD2,
        (byte) 0xE7, (byte) 0xD9, 0x5C, (byte) 0xE8, 0x1F },
        { 0x12, (byte) 0xD0, 0x7E, 0x3E, (byte) 0xF8, (byte) 0x85, 0x48,
        (byte) 0x9E, (byte) 0x8E, (byte) 0x97, (byte) 0xA7, 0x2A,
        0x65, 0x51, (byte) 0xE5, (byte) 0x8D },
        { (byte) 0xBA, 0x74, (byte) 0xDB, 0x3E, (byte) 0x9E, 0x24, 0x43,
        0x4B, (byte) 0x87, (byte) 0xB6, 0x2F, 0x6B, (byte) 0x8D,
        (byte) 0xFE, (byte) 0xE5, 0x0F },
        { 0x63, 0x4F, 0x6B, (byte) 0xD8, (byte) 0xAD, (byte) 0xD2, 0x4A,
        (byte) 0xA1, (byte) 0xAA, (byte) 0xB9, 0x11, 0x5B,
        (byte) 0xC2, 0x6D, 0x05, (byte) 0xA1 },
        { 0x2C, (byte) 0xE0, (byte) 0xE4, (byte) 0xE5, 0x7C, 0x64, 0x43,
        0x70, (byte) 0x9C, 0x3A, 0x7A, 0x1C, (byte) 0xE8, 0x78,
        (byte) 0xA7, (byte) 0xDC },
        { 0x10, 0x11, 0x17, (byte) 0xC9, (byte) 0xA3, (byte) 0xB0, 0x40,
        (byte) 0xF9, (byte) 0x81, (byte) 0xAC, 0x49, (byte) 0xE1,
        0x59, (byte) 0xFB, (byte) 0xD5, (byte) 0xD4 },
        { 0x16, 0x0C, 0x60, (byte) 0xBB, (byte) 0xDD, 0x44, 0x43,
        (byte) 0xF3, (byte) 0x91, 0x40, 0x05, 0x0F, 0x00,
        (byte) 0xE6, (byte) 0xC0, 0x09 },
        { 0x64, 0x43, (byte) 0xC6, (byte) 0xAF, 0x22, 0x60, 0x45, 0x17,
        (byte) 0xB5, (byte) 0x8C, (byte) 0xD7, (byte) 0xDF,
        (byte) 0x8E, 0x29, 0x03, 0x52 },
        { 0x16, (byte) 0xF5, (byte) 0xB7, 0x6F, (byte) 0xA9, (byte) 0xD2,
        0x40, 0x35, (byte) 0x8C, (byte) 0xC5, (byte) 0xC0,
        (byte) 0x84, 0x70, 0x3C, (byte) 0x98, (byte) 0xFA },
        { 0x63, 0x14, 0x36, (byte) 0xff, 0x3f, (byte) 0x8a, 0x40,
        (byte) 0xd0, (byte) 0xa5, (byte) 0xcb, 0x7b, 0x66,
        (byte) 0xe0, 0x51, (byte) 0xb3, 0x64 },
        { (byte) 0xb7, 0x08, 0x67, (byte) 0xf5, 0x38, 0x25, 0x43, 0x27,
        (byte) 0xa1, (byte) 0xff, (byte) 0xcf, 0x4c, (byte) 0xc1,
        (byte) 0x93, (byte) 0x97, (byte) 0x97 },
        { (byte) 0xdd, (byte) 0xcf, 0x0e, (byte) 0xa9, 0x71, (byte) 0x95,
        0x40, 0x48, (byte) 0xa9, (byte) 0xc6, 0x41, 0x32, 0x06,
        (byte) 0xd6, (byte) 0xf2, (byte) 0x80 },
        { (byte) 0xD4, (byte) 0xE2, (byte) 0xB0, (byte) 0xBA, 0x33, 0x4E,
        0x4F, (byte) 0xA5, (byte) 0x98, (byte) 0xD0, 0x11, 0x7D,
        (byte) 0xBF, 0x4D, 0x3C, (byte) 0xC8 },
        { 0x00, 0x72, (byte) 0xD9, 0x08, 0x4A, (byte) 0xD1, 0x43,
        (byte) 0xDD, (byte) 0x91, (byte) 0x99, 0x6F, 0x02, 0x69,
        0x66, 0x02, 0x6F },
        { (byte) 0xE6, 0x01, (byte) 0xE4, 0x1C, 0x33, 0x73, 0x4B,
        (byte) 0xD1, (byte) 0xBC, 0x06, (byte) 0x81, 0x1D,
        0x6C, 0x32, 0x3D, (byte) 0x82 },
        { (byte) 0xCD, 0x56, 0x43, (byte) 0xA2, (byte) 0xC9, 0x4C,
        0x47, 0x24, (byte) 0xB5, 0x2C, (byte) 0xDC,
        0x01, 0x24, (byte) 0xA1, (byte) 0xD0, (byte) 0xCD },
        {(byte)0x3F, (byte)0xB0, (byte)0xBD, (byte)0x36, (byte)0xAF, (byte)0x3B,
        (byte)0x4A, (byte)0x60, (byte)0x9E, (byte)0xEF, (byte)0xCF,
        (byte)0x19, (byte)0x0F, (byte)0x6A, (byte)0x5A, (byte)0x7E}
    };    
    
    public byte[] getXStatusGUID(){
        return xstatusMatrix[getCode()];
    }
  
    public String toString() {
        switch(getCode()){            
            case NONE:                   
                return "None x-status.";                
            case XSTATUS_ANGRY:            
                return "Angry x-status.";  
            case XSTATUS_TAKING_A_BATH:
                return "Taking a bath x-status.";     
            case XSTATUS_TIRED:
                return "Tired x-status.";  
            case XSTATUS_PARTY:
                return "Party x-status.";     
            case XSTATUS_DRINKING_BEER:
                return "Drinking beer x-status.";      
            case XSTATUS_THINKING:
                return "Thinking x-status.";     
            case XSTATUS_EATING:
                return "Eating x-status.";      
            case XSTATUS_WATCHING_TV:
                return "Watching tv x-status.";      
            case XSTATUS_MEETING:
                return "Meeting x-status.";         
            case XSTATUS_COFFEE:
                return "Coffee x-status.";     
            case XSTATUS_LISTENING_TO_MUSIC:
                return "Listening to Music x-status.";    
            case XSTATUS_BUSINESS:
                return "Business x-status.";    
            case XSTATUS_SHOOTING:
                return "Shooting x-status.";   
            case XSTATUS_HAVING_FUN:
                return "Having fun x-status.";    
            case XSTATUS_ON_THE_PHONE:
                return "On the phone x-status.";    
            case XSTATUS_GAMING:
                return "Gaming x-status.";    
            case XSTATUS_STUDYING:
                return "Studying x-status.";      
            case XSTATUS_SHOPPING:
                return "Shopping x-status."; 
            case XSTATUS_FEELING_SICK:
                return "Feeling sick x-status.";        
            case XSTATUS_SLEEPING:
                return "Sleeping x-status.";           
            case XSTATUS_SURFING:
                return "Surfing x-status.";  
            case XSTATUS_BROWSING:
                return "Browsing x-status.";   
            case XSTATUS_WORKING:
                return "Working x-status.";        
            case XSTATUS_TYPING:
                return "Typing x-status.";  
            case XSTATUS_PICNIC:
                return "Picnic x-status.";  
            case XSTATUS_COOKING:
                return "Cooking x-status.";   
            case XSTATUS_SMOKING:
                return "Smoking x-status.";   
            case XSTATUS_I_HIGH:
                return "I high x-status.";     
            case XSTATUS_ON_WC:
                return "On wc x-status.";   
            case XSTATUS_QUESTION:
                return "Question x-status.";    
            case XSTATUS_WATCHING_PRO7:
                return "Watching x-status.";   
            case XSTATUS_LOVE:
                return "Love x-status.";    
            case XSTATUS_NOTEPAD:
                return "Notepad x-status.";  
            case XSTATUS_SEX:
                return "Sex x-status.";   
            case XSTATUS_RULOVE:
                return "Ru love x-status.";  
            case XSTATUS_SMOUKE:
                return "Smouke x-status.";                  
        }
        return "None x-status.";
        }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
       
    
    }
