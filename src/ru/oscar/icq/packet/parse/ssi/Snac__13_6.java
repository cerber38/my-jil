
package ru.oscar.icq.packet.parse.ssi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.Tlv;
import ru.oscar.icq.constants.SsiConstants;
import ru.oscar.icq.contacts.Contact;
import ru.oscar.icq.contacts.Group;
import ru.oscar.icq.contacts.Privacy;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;
import ru.oscar.icq.packet.send.generic.Snac__1_1E;
import ru.oscar.icq.packet.send.generic.Snac__1_2;
import ru.oscar.icq.packet.send.location.Snac__2_4;
import ru.oscar.icq.packet.send.ssi.PrivacyStatus;
import ru.oscar.icq.util.StringUtil;

/**
 * snac (13, 6)
 * Контакт лист с сервера
 * 
 * @author Kornackiy Alexsandr
 */

public class Snac__13_6 extends DefaultCommand{   
    
    private int privacyStatusId;
    
    private boolean isLoadContactList = false;

    private Map<Integer, Group> group;  
    
    private ArrayList<Privacy> privacyList;     
    
    public Snac__13_6(){
        super();       
    }
    
        @Override
    public void exec(Flap f) {    
        group = new HashMap<Integer, Group>(20);    
        privacyList = new ArrayList<Privacy>(10);
        if(f.getSnac().getFlags1() == 0){
            isLoadContactList = true;
        }
        int index = 0;   
        byte[] data = f.getDataArray();
        
        if (data.length < 3) {
            return;
        }
        // Version number of SSI protocol (currently 0x00)
        index++;
        // Number of items in this snac
        int count = DataWork.getWord(data, index);
        index += 2;
        
        for (int i = 0; i < count; ++i) {
            
            if (data.length < index + 2) {
                return;
            }
            //Length of the item name
            int itemNameLen = DataWork.getWord(data, index);
            index += 2;
            
            if (data.length < index + itemNameLen + 8) {
                return;
            }            
            
            //Item name string
            String itemName = StringUtil.utf8ByteArrayToString(data, index, itemNameLen);
            index += itemNameLen;
            
            //Group ID#
            int groupID = DataWork.getWord(data, index);
            index += 2;
            //Item ID#
            int itemID = DataWork.getWord(data, index);
            index += 2;    
            //Type of item flag (see list bellow)
            int itemFlag = DataWork.getWord(data, index);
            index += 2;     
            //Length of the additional data
            int len = DataWork.getWord(data, index);
            index += 2; 
            
            if (data.length < index + len) {
                return;
            }
            
            //Buddy record (uin)
            if (SsiConstants.TYPE_CONTACT == itemFlag) {
                
            String nick = itemName;

            boolean auth = false;                
                
            while (0 < len) {                             
                
                Tlv tlv = new Tlv(data, index);
                
                if (0x0131 == tlv.getTlvType()) {
                    nick = StringUtil.utf8ByteArrayToString(tlv.getDataArray(), 0, tlv.getDataArray().length);
                } else if (0x0066 == tlv.getTlvType()) {
                    auth = true;
                }                                      
                Contact c = new Contact(itemID, itemName, nick, groupID, auth);
                Group g;

                if(group.containsKey(groupID)){
                    g = group.get(groupID);
                    g.putContact(c);
                    group.put(groupID, g);
                } else {
                    g = new Group(groupID, null);
                    g.putContact(c);
                    group.put(groupID, g);
                }
                
                len -= tlv.getTlvLength() + 4;
                index += tlv.getTlvLength() + 4;
                
                }
            // Group record
            } else if (SsiConstants.TYPE_GROUP == itemFlag) {                              
                
                index += len;
                
                Group g;
                
                if(group.containsKey(groupID)){
                    g = group.get(groupID);
                    g.setName(itemName);
                    group.put(groupID, g);                    
                } else {
                    g = new Group(groupID, itemName);
                    group.put(groupID, g);
                }
                
            //Permit/deny settings or/and bitmask of the AIM classes
            } else if (SsiConstants.TYPE_VISIBILITY == itemFlag) {
                
                Tlv tlv = new Tlv(data, index);
                if (0x00CA == tlv.getTlvType()) {
                    privacyStatusId = itemID;
                }   
                
                len -= tlv.getTlvLength() + 4;
                index += tlv.getTlvLength() + 4;                
            } else if (SsiConstants.TYPE_VISIBLE == itemFlag) {
                index += len;
                privacyList.add(new Privacy(itemName, itemID, SsiConstants.TYPE_VISIBLE));
            } else if (SsiConstants.TYPE_INVISIBLE == itemFlag) {
                index += len;
                privacyList.add(new Privacy(itemName, itemID, SsiConstants.TYPE_INVISIBLE));
            } else if (SsiConstants.TYPE_IGNORE_LIST == itemFlag) {    
                index += len;
                privacyList.add(new Privacy(itemName, itemID, SsiConstants.TYPE_IGNORE_LIST));
            } else {    
                index += len;
            }            
        }
    }
        
    public void notify(Connect connect) {
        connect.getContactList().putGroups(group, privacyList);
        group.clear();
        privacyList.clear();
        if(privacyStatusId != 0){
            connect.getOptionsConnect().setPrivacyStatusId(privacyStatusId);
        }
        if(isLoadContactList){
            // контакт лист загружен          
            connect.isLoadedContactList();
            // send privacy status
            connect.sendPacket(new PrivacyStatus(connect.getOptionsConnect().getPrivacyStatus(),
                    connect.getOptionsConnect().getPrivacyStatusId(), connect.getOptionsConnect().isDebug()));
            // send status
            connect.sendPacket(new Snac__1_1E(connect.getOptionsConnect().getStatus(),
                    connect.getOptionsConnect().getStatusFlag(), 
                    connect.getOptionsConnect().getDirectConnect(),
                    connect.getOptionsConnect().getProtocolVersion(),
                    connect.getOptionsConnect().isDebug()));   
            // возможности и х-статус
            connect.sendPacket(new Snac__2_4(connect.getOptionsConnect().getCapabilities(),
                    connect.getOptionsConnect().getXstatus(), connect.getOptionsConnect().getStatus()));
            // Мы готовы выйти в интернет
            connect.sendPacket(new Snac__1_2(connect.getOptionsConnect().isDebug()));

            connect.successfulConnected();            
        }             
    }            
    }
