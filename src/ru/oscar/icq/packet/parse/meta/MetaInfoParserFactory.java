
package ru.oscar.icq.packet.parse.meta;

import ru.oscar.icq.constants.MetaSubTypeConstants;
import ru.oscar.icq.constants.MetaTypeConstants;

/**
 * @author Kornackiy Alexsandr
 */

public class MetaInfoParserFactory implements MetaTypeConstants, MetaSubTypeConstants  {
       
    public static IMetaInfoParser buildMetaInfoParser(int metaType, int metaSubType) {
        IMetaInfoParser parser = null;
        
        switch (metaType) {
                case SERVER_OFFLINE_MESSAGE:

                        break;

                case SERVER_END_OF_OFFLINE_MESSAGES:

                        break;

                case SERVER_ADVANCED_META:
                        switch (metaSubType) {
                                case SERVER_SHORT_USER_INFO_REPLY:
                                    parser = new ShortInfoParser();
                                    break;
                                    
                                case 0x019A:// serch by uin
                                    parser = new SearchSnParse();
                                    break;
                        }
                        break;
        }        
        
        if(parser == null){
            System.err.println("Unknown meta type (" + metaType + ") and subtype (" + metaSubType + ")");
        }
        return parser;
    }    
    
}
