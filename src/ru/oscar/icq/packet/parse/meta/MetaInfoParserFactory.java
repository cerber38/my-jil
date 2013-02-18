
package ru.oscar.icq.packet.parse.meta;

import ru.oscar.icq.constants.MetaSubTypeConstants;
import ru.oscar.icq.constants.MetaTypeConstants;

/**
 * @author Kornackiy Alexsandr
 */

public class MetaInfoParserFactory {

    /**
     * Строим конкретную реализацию интерфейса IMetaInfoParser для обработки
     * того или иного типа метаинформации
     *
     * @param metaType
     * @param metaSubType
     * @return
     */
    public static IMetaInfoParser buildMetaInfoParser(int metaType, int metaSubType) {
        IMetaInfoParser parser = null;

        switch (metaType) {
            case MetaTypeConstants.SERVER_OFFLINE_MESSAGE:
                parser = new OfflineMessageParser();
                break;

            case MetaTypeConstants.SERVER_END_OF_OFFLINE_MESSAGES:
                parser = new ServerEndOfOflineMessageParser();
                break;

            case MetaTypeConstants.SERVER_ADVANCED_META:
                switch (metaSubType) {
                    case MetaSubTypeConstants.SERVER_SHORT_USER_INFO_REPLY:
                        parser = new ShortUserInfoParser();
                        break;

                    case MetaSubTypeConstants.SERVER_BASIC_USER_INFO_REPLY:
                        parser = new BasicUserInfoParser();
                        break;

                    case MetaSubTypeConstants.SERVER_EXTENDED_EMAIL_USER_INFO_REPLY:
                        parser = new EmailUserInfoParser();
                        break;

                    case MetaSubTypeConstants.SERVER_WORK_USER_INFO_REPLY:
                        parser = new WorkUserInfoParser();
                        break;

                    case MetaSubTypeConstants.SERVER_INTERESTS_USER_INFO_REPLY:
                        parser = new InterestsUserInfoParser();
                        break;

                    case MetaSubTypeConstants.SERVER_MORE_USER_INFO_REPLY:
                        parser = new MoreUserInfoParser();
                        break;

                    case MetaSubTypeConstants.SERVER_ABOUT_USER_INFO_REPLY:
                        parser = new NotesUserInfoParser();
                        break;

                    case MetaSubTypeConstants.SERVER_AFFILATIONS_USER_INFO_REPLY:
                        parser = new AffilationsUserInfoParser();
                        break;
                    case MetaSubTypeConstants.SERVER_FIND_BY_UIN:
                        parser = new FindByUinUserInfoParser();
                        break;   
                    case MetaSubTypeConstants.SERVER_ACK_FOR_FULLINFO: 
                        parser = new MetaAckParser();
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
