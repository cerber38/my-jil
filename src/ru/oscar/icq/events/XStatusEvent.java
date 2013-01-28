
package ru.oscar.icq.events;

import java.util.EventObject;
import ru.oscar.icq.packet.parse.icbm.Snac__4_11;

/**
 * @author Kornackiy Alexsandr
 */
public class XStatusEvent extends EventObject {
    
    private int xstatus = 0;
    private String title = "";
    private String description = "";    
    
    public XStatusEvent(Snac__4_11 source){
        super(source);
        parseXStatusMessage((String)( (Snac__4_11)getSource()).getMessage());
    } 
    
    private void parseXStatusMessage(String message) {
        String[] strs = message.split("[&;]");
        try {
            xstatus = Integer.parseInt(strs[44]);
            title = strs[52];
            description = strs[60];
            }catch (Exception e) {
                System.err.println("Errore parse x-status: " + e.getMessage());
            }
    }    
    
    public String getSenderID() {
            return ( (Snac__4_11) getSource()).getSenderID();	
    }

    public int getXStatus() {
            return xstatus;
}

    public String getTitle(){
            return title;
    }

    public String getDescription() {
            return description;
    }    
    
}
