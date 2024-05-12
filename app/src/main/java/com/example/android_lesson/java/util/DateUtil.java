package com.example.android_lesson.java.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static void main(String[] args) {
        System.out.println(getOffsetUTCTime(1));
    }

    public void getTimeStamp() {
        long timeMillis = System.currentTimeMillis();
        getTimeStamp();

        new Date(timeMillis);
    }

    /**
     * 返回当前时间，格式"yyyyMMddHHmmssSSS"
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.CHINA).format(new Date());
    }

    /**
     * 返回UTC格式时间，格式"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
     */
    public static String getUTCTime() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA).format(new Date());
    }

    public static String getOffsetUTCTime(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (day == 0) {
            //calendar.add(Calendar.SECOND, -1);
            calendar.add(Calendar.DATE, +1);
            calendar.add(Calendar.SECOND, -1);
        } else {
            calendar.add(Calendar.DATE, -day);
        }
        Date date = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA).format(date);
    }

    public static String getFormatDateTime(String format, long time) {
        return new SimpleDateFormat(format, Locale.CHINA).format(new Date(time));
    }
}
