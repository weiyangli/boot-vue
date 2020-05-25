package com.boot.bvserver.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        Date endTime = DateUtils.addDays(new Date(), 3);
        Long times = endTime.getTime() - System.currentTimeMillis();
        long hours = times / 1000 / 60;
        String day = hours / 60 / 24 + "天";
        String hour = hours / 60 % 24 + "小时";
        String minute = hours % 60 + "分钟";
        System.out.println("还剩" + day + hour + minute);
    }
}
