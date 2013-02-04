
package ru.oscar.icq.constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MetaLanguagesConstants {

    public static final int NONE = 0;
    public static final int AFRIKAANS = 55;
    public static final int ALBANIAN = 58;
    public static final int ARABIC = 1;
    public static final int ARMENIAN = 59;
    public static final int AZERBAIJANI = 68;
    public static final int BELORUSSIAN = 72;
    public static final int BHOJPURI = 2;
    public static final int BOSNIAN = 56;
    public static final int BULGARIAN = 3;
    public static final int BURMESE = 4;
    public static final int CANTONESE = 5;
    public static final int CATALAN = 6;
    public static final int CHAMORRO = 61;
    public static final int CHIMESE = 7;
    public static final int CROATIAN = 8;
    public static final int CZECH = 9;
    public static final int DANISH = 10;
    public static final int DUTH = 11;
    public static final int ENGLISH = 12;
    public static final int ESPERANTO = 13;
    public static final int ESTONIAN = 14;
    public static final int FARCI = 15;
    public static final int FINNISH = 16;
    public static final int FRENCH = 17;
    public static final int GAELIC = 18;
    public static final int GERMAN = 19;
    public static final int GREEK = 20;
    public static final int GUJARATI = 70;
    public static final int HEBREW = 21;
    public static final int HINDI = 22;
    public static final int HUNGARIAN = 23;
    public static final int ICELANDIC = 24;
    public static final int INDONESIAN = 25;
    public static final int ITALIAN = 26;
    public static final int JAPANESE = 27;
    public static final int KHMER = 28;
    public static final int KOREAN = 29;
    public static final int KURDISH = 69;
    public static final int LAO = 30;
    public static final int LATVIAN = 31;
    public static final int LITHUANIAN = 32;
    public static final int MACEDONIAN = 65;
    public static final int MALAY = 33;
    public static final int MANDARIN = 63;
    public static final int MONGOLIAN = 62;
    public static final int NORWEGIAN = 34;
    public static final int PERSIAN = 57;
    public static final int POLISH = 35;
    public static final int PORTUGUESE = 36;
    public static final int PUNJABI = 60;
    public static final int ROMANIAN = 37;
    public static final int RUSSIAN = 38;
    public static final int SERBO_CROATIAN = 39;
    public static final int SINDHI = 66;
    public static final int SLOVAK = 40;
    public static final int SLOVENIAN = 41;
    public static final int SOMALI = 42;
    public static final int SPANISH = 43;
    public static final int SWAHILI = 44;
    public static final int SWEDISH = 45;
    public static final int TAGALOG = 46;
    public static final int TAIWANESS = 64;
    public static final int TAMIL = 71;
    public static final int TATAR = 47;
    public static final int THAI = 48;
    public static final int TURKISH = 49;
    public static final int UKRAINIAN = 50;
    public static final int URDU = 51;
    public static final int VIETNAMESE = 52;
    public static final int WELSH = 67;
    public static final int YIDDISH = 53;
    public static final int YORUBA = 45;

    private static HashMap<Integer, String> map = new HashMap<Integer, String>();
    static {
          map.put(NONE, "None");
          map.put(AFRIKAANS,"Afrikaans");
          map.put(ALBANIAN,"Albanian");
          map.put(ARABIC, "Arabic");
          map.put(ARMENIAN,"Armenian");
          map.put(AZERBAIJANI,"Azerbaijani");
          map.put(BELORUSSIAN,"Belorussian");
          map.put(BHOJPURI, "Bhojpuri");
          map.put(BOSNIAN,"Bosnian");
          map.put(BULGARIAN, "Bulgarian");
          map.put(BURMESE, "Burmese");
          map.put(CANTONESE, "Cantonese");
          map.put(CATALAN, "Catalan");
          map.put(CHAMORRO, "Chamorro");
          map.put(CHIMESE, "Chinese");
          map.put(CROATIAN, "Croatian");
          map.put(CZECH, "Czech");
          map.put(DANISH, "Danish");
          map.put(DUTH, "Dutch");
          map.put(ENGLISH, "English");
          map.put(ESPERANTO, "Esperanto");
          map.put(ESTONIAN, "Estonian");
          map.put(FARCI, "Farci");
          map.put(FINNISH, "Finnish");
          map.put(FRENCH, "French");
          map.put(GAELIC, "Gaelic");
          map.put(GERMAN, "German");
          map.put(GREEK, "Greek");
          map.put(GUJARATI, "Gujarati");
          map.put(HEBREW, "Hebrew");
          map.put(HINDI, "Hindi");
          map.put(HUNGARIAN, "Hungarian");
          map.put(ICELANDIC, "Icelandic");
          map.put(INDONESIAN, "Indonesian");
          map.put(ITALIAN, "Italian");
          map.put(JAPANESE, "Japanese");
          map.put(KHMER, "Khmer");
          map.put(KOREAN, "Korean");
          map.put(KURDISH, "Kurdish");
          map.put(LAO, "Lao");
          map.put(LATVIAN, "Latvian");
          map.put(LITHUANIAN, "Lithuanian");
          map.put(MACEDONIAN, "Macedonian");
          map.put(MALAY, "Malay");
          map.put(MANDARIN, "Mandarin");
          map.put(MONGOLIAN, "Mongolian");
          map.put(NORWEGIAN, "Norwegian");
          map.put(PERSIAN, "Persian");
          map.put(POLISH, "Polish");
          map.put(PORTUGUESE, "Portuguese");
          map.put(PUNJABI, "Punjabi");
          map.put(ROMANIAN, "Romanian");
          map.put(RUSSIAN, "Russian");
          map.put(SERBO_CROATIAN, "Serbo-Croatian");
          map.put(SINDHI, "Sindhi");
          map.put(SLOVAK, "Slovak");
          map.put(SLOVENIAN, "Slovenian");
          map.put(SOMALI, "Somali");
          map.put(SPANISH, "Spanish");
          map.put(SWAHILI, "Swahili");
          map.put(SWEDISH, "Swedish");
          map.put(TAGALOG, "Tagalog");
          map.put(TAIWANESS, "Taiwaness");
          map.put(TAMIL, "Tamil");
          map.put(TATAR, "Tatar");
          map.put(THAI, "Thai");
          map.put(TURKISH, "Turkish");
          map.put(UKRAINIAN, "Ukrainian");
          map.put(URDU, "Urdu");
          map.put(VIETNAMESE, "Vietnamese");
          map.put(WELSH, "Welsh");
          map.put(YIDDISH, "Yiddish");
          map.put(YORUBA, "Yoruba");
    }

    private int lang;

    public MetaLanguagesConstants(int lang) {
        this.lang = lang;
    }

    public int getLanguage() {
        return lang;
    }

    public String toString() {
        if (map.containsKey(getLanguage()))
            return (String) map.get(getLanguage());
        else
            return "";
    }

    /**
     *
     * @return all languages as map
     */
    public static HashMap getLanguagesMap() {
        return map;
    }

    /**
     *
     * @return all languages as String array (sort by Alphabetical)
     */
    public static String[] getAllLanguages() {
        List languages = new ArrayList();
        languages.addAll(map.values());
        Collections.sort(languages);

        return (String[]) languages.toArray(new String[languages.size()]);
    }
}    

