package com.example.car_service_agency_new.util;

import lombok.experimental.UtilityClass;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@UtilityClass
public class Util {
    public static Long getStartTimeStampOfDay(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}
