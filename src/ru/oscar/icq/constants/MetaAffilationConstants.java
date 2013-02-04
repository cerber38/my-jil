
package ru.oscar.icq.constants;

import java.util.HashMap;


public class MetaAffilationConstants {

    public static final int UNSPECIFIED = 0;
    public static final int ALUMNI = 200;
    public static final int CHARITY = 201;
    public static final int CLUB_SOCIAL = 202;
    public static final int COMMUNITY = 203;
    public static final int CULTURAL = 204;
    public static final int FAN_CLUBS = 205;
    public static final int FRATERNITY = 206;
    public static final int HOBBYISTS = 207;
    public static final int INTERNATIONAL = 208;
    public static final int NATURE_AND_ENVIRONMENT = 209;
    public static final int PROFESSIONAL = 210;
    public static final int SCIENTIFIC = 211;
    public static final int SELF_IMPROVEMENT = 212;
    public static final int SPIRITUAL = 213;
    public static final int SPORTS = 214;
    public static final int SUPPORT = 215;
    public static final int TRADE_AND_BUSINESS = 216;
    public static final int UNION = 217;
    public static final int VOLUNTEER = 218;
    public static final int OTHER = 299;
    
    private static HashMap<Integer, String> map = new HashMap<Integer, String>();
    
    private int affiliation;
    
    static {
          map.put(UNSPECIFIED, "Unspecified");
          map.put(ALUMNI, "Alumni Org.");
          map.put(CHARITY, "Charity Org.");
          map.put(CLUB_SOCIAL, "Club/Social Org.");
          map.put(COMMUNITY, "Community Org.");
          map.put(CULTURAL, "Cultural Org.");
          map.put(FAN_CLUBS, "Fan Clubs");
          map.put(FRATERNITY, "Fraternity/Sorority");
          map.put(HOBBYISTS, "Hobbyists Org.");
          map.put(INTERNATIONAL, "International Org.");
          map.put(NATURE_AND_ENVIRONMENT, "Nature and Environment Org.");
          map.put(PROFESSIONAL, "Professional Org.");
          map.put(SCIENTIFIC, "Scientific/Technical Org.");
          map.put(SELF_IMPROVEMENT, "Self Improvement Group");
          map.put(SPIRITUAL, "Spiritual/Religious Org.");
          map.put(SPORTS, "Sports Org.");
          map.put(SUPPORT, "Support Org.");
          map.put(TRADE_AND_BUSINESS, "Trade and Business Org.");
          map.put(UNION, "Union");
          map.put(VOLUNTEER, "Volunteer Org.");
          map.put(OTHER, "Other");
    }    

    public MetaAffilationConstants(int affiliation) {
        this.affiliation = affiliation;
    }  
    
    public int getAffiliation() {
        return affiliation;
    }   
    
    public static HashMap getAffiliationsMap() {
        return map;
    }

    public static String[] getAllAffiliations() {
        return (String[]) map.values().toArray(new String[map.size()]);
    }    
    
}
