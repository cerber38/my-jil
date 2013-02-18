
package ru.oscar.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTools {
    
    public static Date makeDate(int year, int month, int day, int hour, int minute) {
            Calendar calendar = new GregorianCalendar(year, month - 1, day, hour, minute);
            return calendar.getTime();
    }    
    
}
