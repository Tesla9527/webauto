package com.test.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateHelper {

    public static LocalDateTime GetCurrentDate(){
        return LocalDateTime.now();
    }
    public static String GetTimeDifference(LocalDateTime startTime, LocalDateTime endTime) {
        String timeDifferenceDetailed;
        long duration = Duration.between(startTime, endTime).getSeconds();
        timeDifferenceDetailed = (duration / 60) + " minute(s), " + (duration % 60) + " seconds";
        return timeDifferenceDetailed;
    }
}
