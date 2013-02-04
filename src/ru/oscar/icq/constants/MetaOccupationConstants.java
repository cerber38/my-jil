
package ru.oscar.icq.constants;

import java.util.HashMap;

public class MetaOccupationConstants {
    
    public static final int UNSPECIFIED = 0;
    public static final int ACADEMIC = 1;
    public static final int ADMINISTRATIVE = 2;
    public static final int ART_ENTERTAIMENT = 3;
    public static final int COLLEGE_STUDENT = 4;
    public static final int COMPUTERS = 5;
    public static final int COMMUNITY = 6;
    public static final int EDUCATION = 7;
    public static final int ENGINEERING = 8;
    public static final int FINANCIAL_SERVICES = 9;
    public static final int GOVERNMENT = 10;
    public static final int HIGH_SCHOOL_STUDENT = 11;
    public static final int HOME = 12;
    public static final int ICQ_PROVIDING_HELP = 13;
    public static final int LAW = 14;
    public static final int MANAGERIAL = 15;
    public static final int MANUFACTURING = 16;
    public static final int MEDICAL = 17;
    public static final int MILITARY = 18;
    public static final int NON_GOVERNMENT = 19;
    public static final int PROFESSIONAL = 20;
    public static final int RETAIL = 21;
    public static final int RETIRED = 22;
    public static final int SCIENCE = 23;
    public static final int SPORTS = 24;
    public static final int TECHNICAL = 25;
    public static final int UNIVERSITY_STUDENT = 26;
    public static final int WEB_BUILDING = 27;
    public static final int OTHER = 99;

    private static HashMap<Integer, String> map = new HashMap<Integer, String>();
    static {
        map.put(0, "Unspecified");
        map.put(1, "Academic");
        map.put(2, "Administrative");
        map.put(3, "Art/Entertainment");
        map.put(4, "College Student");
        map.put(5, "Computers");
        map.put(6, "Community & Social");
        map.put(7, "Education");
        map.put(8, "Engineering");
        map.put(9, "Financial Services");
        map.put(10, "Government");
        map.put(11, "High School Student");
        map.put(12, "Home");
        map.put(13, "ICQ - Providing Help");
        map.put(14, "Law");
        map.put(15, "Managerial");
        map.put(16, "Manufacturing");
        map.put(17, "Medical/Health");
        map.put(18, "Military");
        map.put(19, "Non-Government Organization");
        map.put(20, "Professional");
        map.put(21, "Retail");
        map.put(22, "Retired");
        map.put(23, "Science & Research");
        map.put(24, "Sports");
        map.put(25, "Technical");
        map.put(26, "University Student");
        map.put(27, "Web Building");
        map.put(99, "Other Services");
    }

    private int occupation;

    public MetaOccupationConstants(int occupation) {
        this.occupation = occupation;
    }

    public int getOccupation() {
        return occupation;
    }

    public String toString() {
        if (map.containsKey(getOccupation())) {
            return (String) map.get(getOccupation());
        }
        else {
            return "";
        }
    }

    /**
     *
     * @return all occupations as map
     */
    public static HashMap getOccupationsMap() {
        return map;
    }

    /**
     *
     * @return all occupations as String array (sort by Alphabetical)
     */
    public static String[] getAllOccupations() {
        return (String[]) map.values().toArray(new String[map.size()]);
    }
}