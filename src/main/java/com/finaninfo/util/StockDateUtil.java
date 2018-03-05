package com.finaninfo.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StockDateUtil {

    /**
    *
    * @return 1-6 Monday through Saturday 0 Sunday
    *
    */
    public static int getCurrentWeekDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek - 1;
    }


    /**
     * whether stock opens or not
     *
     *  @return true for open false for close
     */

    public static boolean  stockOpenTime(){
        int week = getCurrentWeekDay();
        if (week == 0 ||week == 6){
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (hour >= 9 && hour <= 11){
            if (hour == 11 && minute > 30) {
                return false;
            } else if (hour == 9 && minute < 30) {
                return false;
            } else {
                return true;
            }
        }else if ( hour >=13 && hour <= 15){
            return true;
        }

        return false;
    }


    public static String[] getTimeLine(int years){
        List<String> list = new ArrayList<>();
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        int year = calendar.getWeekYear();
        for (int i = 0; i <years ; i++) {
            int currentYear = year -i;
            for (int j = 1; j < 13; j++) {
                String date = String.format("%s-%s-15", currentYear, j < 10 ? "0"+j:j);
                list.add(date);
            }
        }
        return list.toArray(new String[]{});
    }

}
