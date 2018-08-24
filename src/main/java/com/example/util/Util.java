package com.example.util;


import com.example.common.handler.ErrorResponse;
import com.example.common.handler.ErrorResponseException;
import com.example.model.Period;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tan on 01/04/2017.
 */
public class Util {

    /**
     * Math minute period startTime and endTime
     *
     * @param startTime begin time
     * @param endTime last time
     * @return minute
     */
    public static int convert2MinutePeriod(DateTime startTime, DateTime endTime) {
        long start = startTime.getMillis();
        long end = endTime.getMillis();

        return Math.round((end - start) / (1000 * 60));
    }

    /**
     * <pre>
     *  Parse check-in and check-out to Period list
     *
     *  Have 3 case:
     *  1.check-in and check-out is same day:
     *        Start: 2017-03-29T05:20:31.000+07:00
     *        End: 2017-03-29T17:36:31.000+07:00
     *        dateNum: 0
     *        Period{startTime=2017-03-29T05:20:31.000+07:00, endTime=2017-03-29T17:36:31.000+07:00, isFullDay=false}
     *
     *  2.check-out in next day:
     *       Start: 2017-03-29T05:20:31.000+07:00
     *       End: 2017-03-30T17:36:31.000+07:00
     *       dateNum: 1
     *       Period{startTime=2017-03-29T05:20:31.000+07:00, endTime=2017-03-29T23:59:59.999+07:00, isFullDay=false}
     *       Period{startTime=2017-03-30T00:00:00.000+07:00, endTime=2017-03-30T17:36:31.000+07:00, isFullDay=false}
     *
     *  3.check-out after more days:
     *        Start: 2017-03-29T05:20:31.000+07:00
     *        End: 2017-04-02T17:36:31.000+07:00
     *        dateNum: 4
     *        Period{startTime=2017-03-29T05:20:31.000+07:00, endTime=2017-03-29T23:59:59.999+07:00, isFullDay=false}
     *        Period{startTime=2017-04-02T00:00:00.000+07:00, endTime=2017-04-02T17:36:31.000+07:00, isFullDay=false}
     *        Period{startTime=2017-03-30T00:00:00.000+07:00, endTime=2017-03-30T23:59:59.999+07:00, isFullDay=true}
     *        Period{startTime=2017-03-31T00:00:00.000+07:00, endTime=2017-03-31T23:59:59.999+07:00, isFullDay=true}
     *        Period{startTime=2017-04-01T00:00:00.000+07:00, eUtilndTime=2017-04-01T23:59:59.999+07:00, isFullDay=true}
     *
     * @param startTime check-in time
     * @param endTime check-out time
     * @return List Period Time
     *
     * </pre>
     */
    public static List<Period> parseDayBetween(DateTime startTime, DateTime endTime) {
        int dayNum = Days.daysBetween(startTime.withTimeAtStartOfDay(), endTime.withTimeAtStartOfDay()).getDays();

        System.out.println("dateNum: " + dayNum);

        List<Period> dateTimeList = new ArrayList<>();
        if (dayNum == 0) {
            Period p = new Period(startTime, endTime);
            dateTimeList.add(p);
        } else if (dayNum == 1) {
            Period p = new Period(startTime, setAtEndOfDay(startTime));
            Period p1 = new Period(endTime.withTimeAtStartOfDay(), endTime);
            dateTimeList.add(p);
            dateTimeList.add(p1);
        } else {
            Period p = new Period(startTime, setAtEndOfDay(startTime));
            Period p1 = new Period(endTime.withTimeAtStartOfDay(), endTime);
            dateTimeList.add(p);
            dateTimeList.add(p1);

            // add start time to list
            for (int i = 1; i < dayNum; i++) {
                DateTime tmpStart = startTime.withTimeAtStartOfDay().plusDays(i);
                DateTime tmpEnd = setAtEndOfDay(startTime).plusDays(i);
                Period tmpPeriod = new Period(tmpStart, tmpEnd, true);
                dateTimeList.add(tmpPeriod);
            }
        }

        // how to parse and list day or duration
        return dateTimeList;
    }


    /**
     * Set end of day
     *
     * @param dateTime time
     * @return Date at end day
     */
    public static DateTime setAtEndOfDay(DateTime dateTime) {
        return new DateTime(dateTime).withTime(23, 59, 59, 999);
    }

    /**
     * If sec more than 30s plus a minute else remain
     *
     * @param dateTime time
     * @return round minute
     */
    public static int getMinuteOfDay(DateTime dateTime) {
        int sec = dateTime.getSecondOfMinute();
        int buffer = (sec > 30) ? 1 : 0;
        return dateTime.getMinuteOfDay() + buffer;
    }

    /**
     * Check the day is weekend
     *
     * @param dateTime time
     * @return is weekend day
     */
    public static boolean isWeekend(DateTime dateTime) {
        int dayOfWeek = dateTime.getDayOfWeek();
        return DateTimeConstants.SATURDAY == dayOfWeek || DateTimeConstants.SUNDAY == dayOfWeek;
    }

    /**
     * Check the day is sunday
     *
     * @param dateTime time
     * @return isSunday
     */
    public static boolean isSunDay(DateTime dateTime) {
        return DateTimeConstants.SUNDAY == dateTime.getDayOfWeek();
    }



    public static void validateDateBefore(DateTime startTime, DateTime endTime)  {
            if (startTime.isAfter(endTime)) {
                ErrorResponse error = new ErrorResponse();
                error.setStatus(400);
                error.setMessage("Start time not before endTime!");
                throw new ErrorResponseException(error);
            }
    }
}
