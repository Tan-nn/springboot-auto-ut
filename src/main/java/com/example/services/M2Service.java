package com.example.services;

import com.example.model.Period;
import com.example.util.Util;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by tan on 01/04/2017.
 */
public class M2Service implements ICharge {

    @Value("${m2.hour.normal.price}")
    private int normalPrice = 100; // NOK/hour

    @Value("${m2.hour.weekend.price}")
    private int weekendPrice = 200; // NOK/hour


    private static final M2Service instance = new M2Service();

    private M2Service() {

    }

    public static M2Service getInstance() {
        return instance;
    }

    @Override
    public double charge(String id, DateTime startTime, DateTime endTime) {
        List<Period> dateTimeList = Util.parseDayBetween(startTime, endTime);

        // if parking more day
        double cost = 0.0;
        for (Period period : dateTimeList) {
            cost += chargeOneDay(period);
        }

        return cost;
    }

    /**
     * If check-in and check-out same date
     * Check what the day of week and what time withdraw
     * Math how money charge in day
     *
     * @param period start-end time of day
     * @return cost one day
     */
    private double chargeOneDay(Period period) {
        // determine unit price
        int price = Util.isWeekend(period.getStartTime()) ? weekendPrice : normalPrice;

        // if full day charge
        if (period.isFullDay()) {
            return chargeFullDay(price);
        }

        int startMinute = Util.getMinuteOfDay(period.getStartTime());
        int endMinute = Util.getMinuteOfDay(period.getEndTime());
        int delta = endMinute - startMinute;

        double cost = (delta * 1.0 / 60) * price; // X * 1.0 => cast result to double

        return cost;
    }

    private double chargeFullDay(int price) {
        return 24 * price;// 24h * price
    }

}
