
package ru.oscar.icq.constants;

import java.util.HashMap;

public class MetaInterestsConstants {

    public static final int UNSPECIFIED = 0;
    public static final int ART = 100;
    public static final int CARS = 101;
    public static final int CELEBRITY_FANS = 102;
    public static final int COLLECTIONS = 103;
    public static final int COMPUTERS = 104;
    public static final int CULTURE_LITERATURE = 105;
    public static final int FITNES = 106;
    public static final int GAMES = 107;
    public static final int HOBBIES = 108;
    public static final int ICQ_PROVIDING_HELP = 109;
    public static final int INTERNET = 110;
    public static final int LIFESTYLE = 111;
    public static final int MOVIES_TV = 112;
    public static final int MUSIC = 113;
    public static final int OUTDOOR_ACTIVITIES = 114;
    public static final int PARENTING = 115;
    public static final int PETS = 116;
    public static final int RELIGION = 117;
    public static final int SCIENCE = 118;
    public static final int SKILLS = 119;
    public static final int SPORTS = 120;
    public static final int WEB_DESIGN = 121;
    public static final int NATURE_AND_ENVIRONMENT = 122;
    public static final int NEWS_AND_MEDIA = 123;
    public static final int GOVERNMENT = 124;
    public static final int BUSINESS_ECONOMY = 125;
    public static final int MYSTICS = 126;
    public static final int TRAVEL = 127;
    public static final int ASTRONOMY = 128;
    public static final int SPACE = 129;
    public static final int CLOTHING = 130;
    public static final int PARTIES = 131;
    public static final int WOMEN = 132;
    public static final int SOCIAL_SCIENCE = 133;
    public static final int L60S = 134;
    public static final int L70S = 135;
    public static final int L80S = 136;
    public static final int L50S = 137;
    public static final int FINANCE_AND_CORPORATE = 138;
    public static final int ENTERTAINMENT = 139;
    public static final int CONSUMER_ELECTRONICS = 140;
    public static final int RETAIL_STORES = 141;
    public static final int HEALS_AND_BEAUTY = 142;
    public static final int MEDIA = 143;
    public static final int HOUSEHOLD_PRODUCTS = 144;
    public static final int MAIL_ORDER_CATALOG = 145;
    public static final int BUSINESS_SERVICES = 146;
    public static final int AUDIO_AND_VIRTUAL = 147;
    public static final int SPORTING_AND_ATHLETIC = 148;
    public static final int PUBLISHING = 149;
    public static final int HOME_AUTOMATION = 150;

    private static HashMap<Integer, String> map = new HashMap<Integer, String>();
    
    static {
          map.put(UNSPECIFIED, "Unspecified");
          map.put(ART, "Art");
          map.put(CARS, "Cars");
          map.put(CELEBRITY_FANS, "Celebrity Fans");
          map.put(COLLECTIONS, "Collections");
          map.put(COMPUTERS, "Computers");
          map.put(CULTURE_LITERATURE, "Culture & Literature");
          map.put(FITNES, "Fitness");
          map.put(GAMES, "Games");
          map.put(HOBBIES, "Hobbies");
          map.put(ICQ_PROVIDING_HELP, "ICQ - Providing Help");
          map.put(INTERNET, "Internet");
          map.put(LIFESTYLE, "Lifestyle");
          map.put(MOVIES_TV, "Movies/TV");
          map.put(MUSIC, "Music");
          map.put(OUTDOOR_ACTIVITIES, "Outdoor Activities");
          map.put(PARENTING, "Parenting");
          map.put(PETS, "Pets/Animals");
          map.put(RELIGION, "Religion");
          map.put(SCIENCE, "Science/Technology");
          map.put(SKILLS, "Skills");
          map.put(SPORTS, "Sports");
          map.put(WEB_DESIGN, "Web Design");
          map.put(NATURE_AND_ENVIRONMENT, "Nature and Environment");
          map.put(NEWS_AND_MEDIA, "News & Media");
          map.put(GOVERNMENT, "Government");
          map.put(BUSINESS_ECONOMY, "Business & Economy");
          map.put(MYSTICS, "Mystics");
          map.put(TRAVEL, "Travel");
          map.put(ASTRONOMY, "Astronomy");
          map.put(SPACE, "Space");
          map.put(CLOTHING, "Clothing");
          map.put(PARTIES, "Parties");
          map.put(WOMEN, "Women");
          map.put(SOCIAL_SCIENCE, "Social science");
          map.put(L60S, "60's");
          map.put(L70S, "70's");
          map.put(L80S, "80's");
          map.put(L50S, "50's");
          map.put(FINANCE_AND_CORPORATE, "Finance and corporate");
          map.put(ENTERTAINMENT, "Entertainment");
          map.put(CONSUMER_ELECTRONICS, "Consumer electronics");
          map.put(RETAIL_STORES, "Retail stores");
          map.put(HEALS_AND_BEAUTY, "Health and beauty");
          map.put(MEDIA, "Media");
          map.put(HOUSEHOLD_PRODUCTS, "Household products");
          map.put(MAIL_ORDER_CATALOG, "Mail order catalog");
          map.put(BUSINESS_SERVICES, "Business services");
          map.put(AUDIO_AND_VIRTUAL, "Audio and visual");
          map.put(SPORTING_AND_ATHLETIC, "Sporting and athletic");
          map.put(PUBLISHING, "Publishing");
          map.put(HOME_AUTOMATION, "Home automation");
    }

    private int interest;

    public MetaInterestsConstants(int interest) {
        this.interest = interest;
    }

    public int getInterest() {
        return interest;
    }

    public String toString() {
        if (map.containsKey(getInterest())) {
            return (String) map.get(getInterest());
        }
        else {
            return "";
        }
    }

    /**
     *
     * @return all occupations as map
     */
    public static HashMap getAllInterestsMap() {
        return map;
    }

    /**
     *
     * @return all interests as String array
     */
    public static String[] getAllInterests() {
        return (String[]) map.values().toArray(new String[map.size()]);
    }
}
