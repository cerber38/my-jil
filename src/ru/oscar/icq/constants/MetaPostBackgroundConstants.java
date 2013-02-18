
package ru.oscar.icq.constants;

import java.util.HashMap;

public class MetaPostBackgroundConstants {

    public static final int UNSPECIFIED = 0;
    public static final int ELEMENTARY_SCHOOL = 300;
    public static final int HIGH_SCHOOL = 301;
    public static final int COLLEGE = 302;
    public static final int UNIVERSITY = 303;
    public static final int MILITARY = 304;
    public static final int PAST_WORK_PLACE = 305;
    public static final int PAST_ORGANIZATION = 306;
    public static final int OTHER = 399;

    private static HashMap<Integer, String> map = new HashMap<Integer, String>();
    
    static {
        map.put(UNSPECIFIED, "Unspecified");
        map.put(ELEMENTARY_SCHOOL, "Elementary school");
        map.put(HIGH_SCHOOL, "High school");
        map.put(COLLEGE, "College");
        map.put(UNIVERSITY, "University");
        map.put(MILITARY, "Military");
        map.put(PAST_WORK_PLACE, "Past work place");
        map.put(PAST_ORGANIZATION, "Past organization");
        map.put(OTHER, "Other");
    }

    private int postbackground;

    public MetaPostBackgroundConstants(int postbackground) {
        this.postbackground = postbackground;
    }

    public int getPostBackground() {
        return postbackground;
    }

    public String toString() {
        if (map.containsKey(getPostBackground())) {
            return (String) map.get(getPostBackground());
        }
        else {
            return "";
        }
    }

    public static HashMap getAllPostBackgroundMap() {
        return map;
    }

    public static String[] getAllPostBackgrounds() {
        return (String[]) map.values().toArray(new String[map.size()]);
    }
}
