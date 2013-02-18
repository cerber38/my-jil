
package ru.oscar.icq.packet.parse.meta;

import ru.oscar.DataWork;
import ru.oscar.Flap;
import ru.oscar.command.DefaultCommand;
import ru.oscar.core.Connect;
import ru.oscar.icq.constants.MetaTypeConstants;

/**
 * Ответ на SNAC(15,02). 
 * @author Kornackiy Alexsandr
 */

public class MetaReply extends DefaultCommand  {
	
    private int metaType = 0;
    private int metaSubType = 0;	

    private IMetaInfoParser metaInfoParser = null; 

    public MetaReply(){
        super();
    }
    
    public void exec(Flap f) {
        int index = 0;
        byte[] data = f.getDataArray();

        // Skipping 4 byte of TLV(1) + length field + my uin
        index += 10; 
        
        //request type
        metaType = DataWork.getWordLE(data, index);
        index += 2;
        
        // request sequence number
        index += 2;
        
        if (metaType == MetaTypeConstants.SERVER_ADVANCED_META) {
            //request subtype
            metaSubType = DataWork.getWordLE(data, index);
            index += 2;            
        }
        
        // Build parser
        metaInfoParser = MetaInfoParserFactory.buildMetaInfoParser(metaType, metaSubType); 

        // Parse metainfo data
        if (metaInfoParser != null) {   
            metaInfoParser.parse(data, index, f.getSnac().getRequestID());
        }

    }

	public void notify(Connect connection) {
            if (metaInfoParser != null) {
                metaInfoParser.notifyEvent(connection);
            }
	}	
}