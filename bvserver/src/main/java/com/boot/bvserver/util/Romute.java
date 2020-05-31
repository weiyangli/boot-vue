package com.boot.bvserver.util;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;

public class Romute {

    public static void main(String[] args) throws ParseException {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周", "周五", "周六"};
        Calendar calendar=Calendar.getInstance();
        System.out.println(weekDays[calendar.get(Calendar.DAY_OF_WEEK)-1]);
        String a = "";
        String [] arr = a.split(",");
        System.out.println(Arrays.toString(arr));
    }
}
