package com.liweiyang.lwyFrame.util;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.util.Calendar;
import java.util.Date;

/**
 * java8 时间类学习
 *
 */
public class JavaDate {
    public static void main(String[] args) {
        // 原始的 jdk 方法
        Date oldDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(1, Calendar.MONTH);
        System.out.println(calendar.getTime().toString());
        calendar.toInstant();
        // java8 创建日期
        LocalDate date = LocalDate.now();
        // 获取本周的是周几
        date.get(ChronoField.DAY_OF_WEEK);
        date.getDayOfWeek();
        // 获取年
        date.getYear();
        // 本月第几天
        date.getDayOfMonth();
        // 当前日期基础上加几天
        date.plusDays(2);
        // 当前日期基础上减几天
        date.minusDays(1);
        // 判断是否是闰年
        date.isLeapYear();
        // with替换对应年月日
        LocalDate aa = date.withMonth(10);
        System.out.println(aa.toString());
        // 转为 LocalDateTime
        LocalDateTime localDateTime = date.atTime(LocalTime.now());

        // java8 创建时间
        LocalTime localTime = LocalTime.now().withNano(0);
        localTime.getHour();
        localTime.get(ChronoField.HOUR_OF_AMPM);
        LocalDateTime localDateTime1 = localTime.atDate(date);

        // java8 创建某一天某时
        LocalDateTime localDateTime2 = LocalDateTime.now();
        LocalDateTime localDateTime3 = LocalDateTime.of(2020, 6, 28, 14, 50, 45);
        System.out.println(localDateTime2.isBefore(localDateTime3));
        LocalDateTime localDateTime4 = LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
        System.out.println(localDateTime4.toString());
        System.out.println(localDateTime2.toString());
        String str = localDateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(str);
        LocalDateTime localDateTime5 = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(localDateTime5.toString());
        System.out.println(testFunction().toString());
        System.out.println(planB().adjustInto(LocalDate.now()).toString());
        java.util.Date date1 = new java.util.Date();
        // java.util.date 转 LocalDateTime，ZoneId.systemDefault() 获取当前计算机的时区
        LocalDateTime localDateTimer = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault());
        System.out.println(ZoneId.systemDefault());
    }

    /**
     * 请设计一个NextWorkingDay类，该类实现了TemporalAdjuster接口，能够计算明天
     * 的日期，同时过滤掉周六和周日这些节假日。格式如下所示：
     * date = date.with(new NextWorkingDay());
     * 如果当天的星期介于周一至周五之间，日期向后移动一天；如果当天是周六或者周日，则
     * 返回下一个周一。
     */
    // 实现方式一：普通方法实现
    public static LocalDate testFunction() {
        LocalDate date = LocalDate.now();
        switch (date.getDayOfWeek()) {
            case FRIDAY:
                date = date.plusDays(3);
                break;
            case SATURDAY:
                date = date.plusDays(2);
                break;
            default:
                date = date.plusDays(1);
        }
        return date;
    }
    // 实现方式二: 通过 TemporalAdjuster 接口实现
    public static TemporalAdjuster planB() {
        TemporalAdjuster temporalAdjuster = (date) -> {
            int week = date.get(ChronoField.DAY_OF_WEEK);
            int addDay = 1;
            switch (DayOfWeek.of(week)) {
                case FRIDAY:
                    addDay = 3;
                    break;
                case SATURDAY:
                    addDay = 2;
                    break;
                default:
                    addDay = 1;
            }
            return date.plus(addDay, ChronoUnit.DAYS);
        };
        return temporalAdjuster;
    }
}
