
package ru.oscar.icq.constants;

import java.util.HashMap;

public class MetaTimeZoneConstants {
    
    private static HashMap<Integer, String> timezones = new HashMap<Integer, String>();

    static {
        timezones.put(-100,"Unspecified");
        timezones.put(24  ,"GMT-12:00 Eniwetok; Kwajalein");
        timezones.put(23  ,"GMT-11:30");
        timezones.put(22  ,"GMT-11:00 Midway Island; Samoa");
        timezones.put(21  ,"GMT-10:30");
        timezones.put(20  ,"GMT-10:00 Hawaii");
        timezones.put(19  ,"GMT-9:30");
        timezones.put(18  ,"GMT-9:00 Alaska");
        timezones.put(17  ,"GMT-8:30");
        timezones.put(16  ,"GMT-8:00 Pacific Time; Tijuana");
        timezones.put(15  ,"GMT-7:30");
        timezones.put(14  ,"GMT-7:00 Arizona; Mountain Time");
        timezones.put(13  ,"GMT-6:30");
        timezones.put(12  ,"GMT-6:00 Central Time; Central America; Saskatchewan");
        timezones.put(11  ,"GMT-5:30");
        timezones.put(10  ,"GMT-5:00 Eastern Time; Bogota; Lima; Quito");
        timezones.put(9   ,"GMT-4:30");
        timezones.put(8   ,"GMT-4:00 Atlantic Time; Santiago; Caracas; La Paz");
        timezones.put(7   ,"GMT-3:30 Newfoundland");
        timezones.put(6   ,"GMT-3:00 Greenland; Buenos Aires; Georgetown");
        timezones.put(5   ,"GMT-2:30");
        timezones.put(4   ,"GMT-2:00 Mid-Atlantic");
        timezones.put(3   ,"GMT-1:30");
        timezones.put(2   ,"GMT-1:00 Cape Verde Islands; Azores");
        timezones.put(1   ,"GMT-0:30");
        timezones.put(0   ,"GMT+0:00 London; Dublin; Edinburgh; Lisbon; Casablanca");
        timezones.put(-1  ,"GMT+0:30");
        timezones.put(-2  ,"GMT+1:00 Central European Time; West Central Africa; Warsaw");
        timezones.put(-3  ,"GMT+1:30");
        timezones.put(-4  ,"GMT+2:00 Jerusalem; Helsinki; Harare; Cairo; Bucharest; Athens");
        timezones.put(-5  ,"GMT+2:30");
        timezones.put(-6  ,"GMT+3:00 Moscow; St. Petersburg; Nairobi; Kuwait; Baghdad");
        timezones.put(-7  ,"GMT+3:30 Tehran");
        timezones.put(-8  ,"GMT+4:00 Baku; Tbilisi; Yerevan; Abu Dhabi; Muscat");
        timezones.put(-9  ,"GMT+4:30 Kabul");
        timezones.put(-10 ,"GMT+5:00 Calcutta; Chennai; Mumbai; New Delhi; Ekaterinburg");
        timezones.put(-11 ,"GMT+5:30");
        timezones.put(-12 ,"GMT+6:00 Astana; Dhaka; Almaty; Novosibirsk; Sri Jayawardenepura");
        timezones.put(-13 ,"GMT+6:30 Rangoon");
        timezones.put(-14 ,"GMT+7:00 Bankok; Hanoi; Jakarta; Krasnoyarsk");
        timezones.put(-15 ,"GMT+7:30");
        timezones.put(-16 ,"GMT+8:00 Perth; Taipei; Singapore; Hong Kong; Beijing");
        timezones.put(-17 ,"GMT+8:30");
        timezones.put(-18 ,"GMT+9:00 Tokyo; Osaka; Seoul; Sapporo; Yakutsk");
        timezones.put(-19 ,"GMT+9:30 Darwin; Adelaide");
        timezones.put(-20 ,"GMT+10:00 East Australia; Guam; Vladivostok");
        timezones.put(-21 ,"GMT+10:30");
        timezones.put(-22 ,"GMT+11:00 Magadan; Solomon Is.; New Caledonia");
        timezones.put(-23 ,"GMT+11:30");
        timezones.put(-24 ,"GMT+12:00 Auckland; Wellington; Fiji; Kamchatka; Marshall Is.");
    }

    private int timezone;

    public MetaTimeZoneConstants(int timezone) {
        this.timezone = timezone;
    }

    public int getTimeZone() {
        return timezone;
    }

    public String toString() {
        if (timezones.containsKey(getTimeZone())) {
            return (String) timezones.get(getTimeZone());
        }
        else {
            return "";
        }
    }

    public static HashMap getAllTimeZonesMap() {
        return timezones;
    }

    public static String[] getAllTimeZones() {
        return (String[]) timezones.values().toArray(new String[timezones.size()]);
    }
}