
package ru.oscar.icq.packet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ru.oscar.command.Command;
import ru.oscar.icq.packet.parse.buddy.LimitationsResponseBuddy;
import ru.oscar.icq.packet.parse.generic.OnlineInfoResponse;
import ru.oscar.icq.packet.parse.generic.MOTD;
import ru.oscar.icq.packet.parse.generic.FamiliesVersions;
import ru.oscar.icq.packet.parse.generic.SnacFamiliesList;
import ru.oscar.icq.packet.parse.generic.RateLimitsInfo;
import ru.oscar.icq.packet.parse.icbm.Snac__4_11;
import ru.oscar.icq.packet.parse.icbm.ResponseParametersIcbm;
import ru.oscar.icq.packet.parse.icbm.ICBMMessage;
import ru.oscar.icq.packet.parse.location.LimitationsResponseLocation;
import ru.oscar.icq.packet.parse.meta.MetaInfoResponse;
import ru.oscar.icq.packet.parse.privacy.ResponseParametersPrivacy;
import ru.oscar.icq.packet.parse.ssi.SsiServerAck;
import ru.oscar.icq.packet.parse.ssi.FutureAuthorization;
import ru.oscar.icq.packet.parse.ssi.AuthorizationRequest;
import ru.oscar.icq.packet.parse.ssi.AuthorizationReply;
import ru.oscar.icq.packet.parse.ssi.SsiParametersReply;
import ru.oscar.icq.packet.parse.ssi.ContactListReply;
import ru.oscar.icq.packet.send.privacy.RequestParametersPrivacy;

/**
 * У каждого принятого SNAC'а свой класс-парсер.
 * Запоминаем их, и вызываем по типу и подтипу.
 * @author Kornackiy Alexsandr
 */

public class SnacBuilder extends ClassLoader{
    
    private Map<String, String> command;  
    
    private ArrayList<String> commandignor;
        
    public SnacBuilder(){
        builder();
    }
    
    private void builder(){
        command = new HashMap<String, String>();        
        command.put("1_3", new SnacFamiliesList().getClass().getName());
        command.put("1_7", new RateLimitsInfo().getClass().getName());
        command.put("1_15", new OnlineInfoResponse().getClass().getName());
        command.put("1_19", new MOTD().getClass().getName());
        command.put("1_24", new FamiliesVersions().getClass().getName());
        command.put("2_3", new LimitationsResponseLocation().getClass().getName());
        command.put("3_3", new LimitationsResponseBuddy().getClass().getName());
        command.put("4_5", new ResponseParametersIcbm().getClass().getName());
        command.put("4_7", new ICBMMessage().getClass().getName()); 
        command.put("4_11", new Snac__4_11().getClass().getName()); 
        command.put("9_3", new ResponseParametersPrivacy().getClass().getName());
        command.put("19_3", new SsiParametersReply().getClass().getName());   
        command.put("19_6", new ContactListReply().getClass().getName()); 
        command.put("19_14", new SsiServerAck().getClass().getName());
        command.put("19_21", new FutureAuthorization().getClass().getName());
        command.put("19_27", new AuthorizationReply().getClass().getName());
        command.put("19_25", new AuthorizationRequest().getClass().getName());
        command.put("21_3", new MetaInfoResponse().getClass().getName());         
        // 1 10 rate limit
        // 4 12 ask
        commandignor = new ArrayList<String>(1);
        commandignor.add("1_21");
    }    
    
    public Command getCommand(String command) { 
        Command c = null;
        if(!this.command.containsKey(command)){
            return c;
        } 
        try {
            c = (Command) Class.forName(this.command.get(command)).getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Error load class: " + e.getMessage());
        }
        return c;
    }
    
    public boolean isIgnor(String command){
        return commandignor.contains(command);
    }
 
}
