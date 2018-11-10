package com.example.meirlen.orc.helper;

import java.util.Calendar;
import java.util.Locale;

public class DateManager {

    public static String getDayOfMonth(String date) {
        String result;
        try {
            result = date.substring(8, 10);

        } catch (Exception e) {
            result = "NPE";
        }
        return result;
    }

    public static String getMonthOfYear(String date) {
        String result;
        try {
            Calendar calendar = Calendar.getInstance(new Locale("ru", "RU"));
            calendar.set(Calendar.MONTH, Integer.valueOf(date.substring(5, 7)) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            String format = "%tB";
            result = String.format(new Locale("ru", "RU"), format, calendar);
        } catch (Exception e) {
            result = "NPE";
        }

        return result.toUpperCase();
    }

}

