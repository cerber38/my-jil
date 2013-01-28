
package ru.oscar.icq.packet.parse.ssi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.Tlv;
import ru.oscar.icq.constants.SsiConstants;
import ru.oscar.icq.contacts.Contact;
import ru.oscar.icq.contacts.Group;
import ru.oscar.icq.contacts.Privacy;
import ru.oscar.core.Connect;
import ru.oscar.command.DefaultCommand;
import ru.oscar.icq.packet.send.ssi.PrivacyStatus;
import ru.oscar.util.StringUtil;

/**
 * snac (13, 6)
 * Контакт лист с сервера
 * 
 * @author Kornackiy Alexsandr
 */

public class ContactListReply extends DefaultCommand{   
    
    private int privacyStatusId;
    
    private boolean isLoadContactList = false;

    private Map<Integer, Group> group;  
    
    private ArrayList<Privacy> privacyList;     
    
    public ContactListReply(){
        super();      
        group = new HashMap<Integer, Group>(20);    
        privacyList = new ArrayList<Privacy>(10);
    }
    
        @Override
    public void exec(Flap f) {    
  
        if(f.getSnac().getFlags1() == 0){
            isLoadContactList = true;
        }

        int index = 8;   
        byte[] data = f.getDataArray();    
        
        // Version number of SSI protocol (currently 0x00)
        index++;
        // Number of items in this snac
        int count = DataWork.getWord(data, index);
        index += 2;
        for (int i = 0; i < count; ++i) {
            
            //Length of the item name
            int itemNameLen = DataWork.getWord(data, index);
            index += 2;   
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
            
            switch(itemFlag){
                case SsiConstants.TYPE_CONTACT:
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
                    break;
                case SsiConstants.TYPE_GROUP:
//                    index += len;
                    Group g;
                    if(group.containsKey(groupID)){
                        g = group.get(groupID);
                        g.setName(itemName);
                        group.put(groupID, g);                    
                    } else {
                        g = new Group(groupID, itemName);
                        group.put(groupID, g);
                    } 
                    break;
                case SsiConstants.TYPE_VISIBILITY:
                    while (0 < len) { 
                        Tlv tlv = new Tlv(data, index);
                        if (0x00CA == tlv.getTlvType()) {
                            privacyStatusId = itemID;
                        }    
                        len -= tlv.getTlvLength() + 4;
                        index += tlv.getTlvLength() + 4;                         
                    }
                    break;
                case SsiConstants.TYPE_VISIBLE:
//                    index += len;
                    privacyList.add(new Privacy(itemName, itemID, SsiConstants.TYPE_VISIBLE));                    
                    break;
                case SsiConstants.TYPE_INVISIBLE:
//                    index += len;
                    privacyList.add(new Privacy(itemName, itemID, SsiConstants.TYPE_INVISIBLE));
                    break;
                case SsiConstants.TYPE_IGNORE_LIST:
//                    index += len;
                    privacyList.add(new Privacy(itemName, itemID, SsiConstants.TYPE_IGNORE_LIST));
                    break;                    
            }                       
            index += len;
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
            // send privacy status
            connect.sendPacket(new PrivacyStatus(connect.getOptionsConnect().getPrivacyStatus(),
                    connect.getOptionsConnect().getPrivacyStatusId(), connect.getOptionsConnect().isDebug()));             
            // контакт лист загружен          
            connect.isLoadedContactList();  
        }
    }            
    }
