
package ru.oscar.icq.packet.parse.meta;

import ru.oscar.icq.DataWork;
import ru.oscar.icq.Flap;
import ru.oscar.icq.constants.MetaTypeConstants;
import ru.oscar.icq.core.Connect;
import ru.oscar.icq.core.cmd.DefaultCommand;
import ru.oscar.icq.util.Dumper;

/**
 * Ответ на SNAC(15,02). 
 * @author Kornackiy Alexsandr
 */
public class Snac__15_3 extends DefaultCommand{
    
    private int metaType = 0;
    private int metaSubType = 0;	

    private IMetaInfoParser metaInfoParser = null;     
    
    public Snac__15_3(){
        super();
    }
    
    public void exec(Flap f) {
        int index = 0;
        int request = f.getSnac().getRequestID();
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
            metaInfoParser.parse(data, index, request);
        }

    }
    
    @Override
    public void notify(Connect connect) {
        if (metaInfoParser != null)
                metaInfoParser.notify(connect);        
    }    
    
}
