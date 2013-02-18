
package ru.oscar.icq.constants;

import java.util.HashMap;

public class MetaMaritalStatusConstants {
    public static final int UNSPECIFIED = 0;
    public static final int SINGLE = 10;
    public static final int CLOSE_RELATIONSHIPS = 11;
    public static final int ENGAGED = 12;
    public static final int MARRIED = 20;
    public static final int DIVORCED = 30;
    public static final int SEPARATED = 31;
    public static final int WIDOWED = 40;

    private static HashMap<Integer, String> map = new HashMap<Integer, String>();
    static {
        map.put(UNSPECIFIED, "Unspecified");
        map.put(SINGLE, "Single");
        map.put(CLOSE_RELATIONSHIPS, "Close Relationships");
        map.put(ENGAGED, "Engaged");
        map.put(MARRIED, "Married");
        map.put(DIVORCED, "Divorced");
        map.put(SEPARATED, "Separated");
        map.put(WIDOWED, "Widowed");
    }

    private int maritalstatus;

    public MetaMaritalStatusConstants(int maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public int getMaritalStatus() {
        return maritalstatus;
    }

    public String toString() {
        if (map.containsKey(getMaritalStatus())) {
            return (String) map.get(getMaritalStatus());
        }
        else {
            return "";
        }
    }

    public static HashMap getAllMaritalStatusMap() {
        return map;
    }

    public static String[] getAllMaritalStatuses() {
        return (String[]) map.values().toArray(new String[map.size()]);
    }
}
