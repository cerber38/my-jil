
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
    
//    private final SnacBuilder commands;
    private SnacBuilder commands;
    
    public PacketRecognizer(Connect connect){
        this.connect = connect;
        commands = new SnacBuilder();
    }
    
    public void incoming(byte[] array){
        number++;
        if(authorization(array)){
          workingSnac(array); 
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


    private void workingSnac(byte[] array){                
        Packet flap = new Packet(array, true);
        
        int familyID = flap.getSnac().getFamilyID();
        int subType = flap.getSnac().getSubTypeID();
        
//        Dumper.println("Incoming Packet: (" + familyID + "," + subType + ")", array, true, 8, 16);

        try{            
            Command c = commands.getCommand(familyID + "_" + subType);
            if(c != null){
//                c.init();
                c.exec(flap);
                c.notify(connect);
            } else {
                // unknow snac
//                if(logger.isDebugEnabled()){
//                    System.out.println("unknow snac: (" + familyID + "," + subType + ")"
//                            + "\nDump:" + Dumper.dump(array, true));
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
