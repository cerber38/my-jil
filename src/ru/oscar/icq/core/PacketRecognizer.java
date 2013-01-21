
package ru.oscar.icq.core;

import ru.oscar.icq.core.cmd.Command;
import ru.oscar.icq.packet.Packet;
import ru.oscar.icq.packet.SnacBuilder;
import ru.oscar.icq.packet.parse.AuthorizationReply;
import ru.oscar.icq.packet.send.AuthorizationRequest;
import ru.oscar.icq.packet.send.SendsCookie;
import ru.oscar.icq.util.Dumper;

/**
 * Парсер пакетов
 * @author Kornackiy Alexsandr
 */

public class PacketRecognizer {
    
    private Connect connect;
    
    private int number = 0;   
    
    private SnacBuilder commands;
    
    public PacketRecognizer(Connect connect){
        this.connect = connect;
        commands = new SnacBuilder();
    }
    
    public void parserPackage(byte[] array){
        number++;
        if(authorization(array)){
          parserSnac(array); 
        }
    }
       
    private boolean authorization(byte[] array){
        if(number == 1){
            connect.sendPacket(new AuthorizationRequest(connect.getSN(), connect.getPassword()));
            return false;
        }else if(number == 2){           
            AuthorizationReply a = new AuthorizationReply(array, connect);
            a.notify(connect);
            return false;
        } else if(number == 3){
            connect.sendPacket(new SendsCookie((byte[])connect.getCookies().get(1)));
            connect.getCookies().clear();
            return false;
        }
        return true;        
    }


    private void parserSnac(byte[] array){                
        Packet flap = new Packet(array, true);
        
        int familyID = flap.getSnac().getFamilyID();
        int subType = flap.getSnac().getSubTypeID();
        
        if(connect.getOptionsConnect().isDebug()){
            System.out.println("Incoming Packet: (" + familyID + "," + subType + ")\nDump:\n" + Dumper.dump(array, true));
        }
        
        // This is an error notification snac.
        if(subType == 1){
            connect.errorNotification(familyID, subType, flap.getSnac().getError());
            return;
        }
   
        try{            
            Command c = commands.getCommand(familyID + "_" + subType);
            if(c != null){
                c.exec(flap);
                c.notify(connect);
            } else {
                // unknow snac
                if(connect.getOptionsConnect().isDebug()){
                        System.out.println("Unknow snac: (" + familyID + "," + subType + ")"
                                + "\nDump:\n" + Dumper.dump(array, true));
                }
            }
            array = null;
            }catch (Exception e) {
                System.err.println("Error in packet processing: (" + familyID + "," + subType + ")" 
                        + "\nDump:" + Dumper.dump(array, true));    
                e.printStackTrace();
            }
    }
    
    public int getNumber(){
        return number;
    }
    

}
