
package ru.oscar.icq.packet;

import ru.oscar.core.PacketHandler;
import ru.oscar.Flap;
import ru.oscar.command.Command;
import ru.oscar.core.Connect;
import ru.oscar.icq.packet.login.AuthorizationReply;
import ru.oscar.icq.packet.login.AuthorizationRequest;
import ru.oscar.icq.packet.login.SendsCookie;
import ru.oscar.util.Dumper;

/**
 * Парсер пакетов
 * @author Kornackiy Alexsandr
 */

public class ParserPackage extends PacketHandler{
    
    private Connect connect;
    
    private int number = 0;   
    
    private SnacBuilder commands;
    
    public ParserPackage(Connect connect){
        this.connect = connect;
        commands = new SnacBuilder();
    }
    
    public boolean authorization(byte[] array){ 
        //MD5 based authorization
        if(connect.getOptionsConnect().isMD5uthorization()){

        }else{ //Channel 0x01 authorization       
            switch(number){
                case 1:
                    connect.sendPacket(new AuthorizationRequest(connect.getSN(), connect.getPassword()));
                    return false;
                case 2:
                    AuthorizationReply a = new AuthorizationReply(array);
                    a.notify(connect);
                    return false;
                case 3:
                    connect.sendPacket(new SendsCookie((byte[])connect.getCookies().get(1)));
                    connect.getCookies().clear();
                    return false;                   
            }            
        }
        return true;
    }


    public void parser (byte[] array){ 
        number++;  
        
        if(!authorization(array)){
            return;// We have not authorized
        }   
        
        Flap flap = new Flap(array, true);
        
        int familyID = flap.getSnac().getFamilyID();
        int subType = flap.getSnac().getSubTypeID();
        
        if(connect.getOptionsConnect().isDebug()){
            System.out.println("Incoming Packet: (" + familyID + "," + subType + ")\nDump:\n" + Dumper.dump(array, true));
        }
        
        // ignor packet
        if(commands.isIgnor(familyID + "_" + subType)){
            return;
        }
        
        // This is an error notification snac.
        if(subType == 1){
            connect.errorNotification(familyID, subType, flap.getSnac().getError());
            return;
        }
   
        try{            
            Command c = commands.getCommand(familyID + "_" + subType);
            if(c != null){
                synchronized (c) {
                c.exec(flap);
                c.notify(connect);
                }
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
