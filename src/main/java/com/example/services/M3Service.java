package com.example.services;

import com.example.model.Period;
import com.example.util.Util;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by tan on 01/04/2017.
 */
public class M3Service implements ICharge {

    @Value("${m3.minute.normal.price}")
    private int normalPrice = 3; // NOK/Minus

    @Value("${m3.minute.promotion.price}")
    private int promotionPrice = 2; // NOK/Minus

    private static final int AT_8H = 480;  // minute Unit
    private static final int AT_16H = 960; // minute Unit
    private static final int DELTA_8_16 = 480; // minute Unit


    private static final M3Service instance = new M3Service();

    private M3Service() {

    }

    public static M3Service getInstance() {
        return instance;
    }


    @Override
    public double charge(String id, DateTime startTime, DateTime endTime) {
        // count how many day over
        List<Period> dateTimeList = Util.parseDayBetween(startTime, endTime);

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

        DateTime startTime = period.getStartTime();
        // free in sunday, because startTime and endTime is same day => just check one
        if (Util.isSunDay(startTime)) {
            return 0;
        }

        // if parking full day
        if (period.isFullDay()) {
            return chargeFullDay();
        }

        DateTime endTime = period.getEndTime();
        // determine check-in point in minute of day
        int startMinute = Util.getMinuteOfDay(startTime);
        // determine check-out point in minute of day
        int endMinute = Util.getMinuteOfDay(endTime);
        // period do parking
        int delta = endMinute - startMinute;
        // zero parking
        if (delta == 0) {
            return 0;
        }
        // period of end-time and 16h
        int del1 = endMinute - AT_16H;
        double cost;

        // this case may be happen;  '>' : check-in,  '|' : check-out
        //0-------------------480-------------------------960-------------------1440
        //>------------------|
        //        >-------------------------|
        //        >-----------------------------------------------------|
        //                    time         >----------------|
        //                                      >-------------------------|
        //                                                       >--------------|

        // if check-out after 16h
        if (del1 >= 0) {
            // if check-in before 8h
            if (startMinute <= AT_8H) {
                int del2 = AT_8H - startMinute;
                cost = baseCharge(del2) + baseCharge(del1) + promotionCharge(DELTA_8_16);
            } else if (startMinute <= AT_16H) {  // if check-in before 16h
                int del2 = AT_16H - startMinute;
                cost = promotionCharge(del2) + baseCharge(del1);
            } else { // if check-in between 8h and 16h
                cost = baseCharge(delta);
            }
        } else { // check-out before 16h
            // if check-in before 8h
            if (startMinute <= AT_8H) {
                int del2 = AT_8H - startMinute;
                cost = baseCharge(del2) + promotionCharge(endMinute - del2);
            } else { // if check-in before 16h
                cost = promotionCharge(delta);
            }

        }
        return cost;
    }

    /**
     * Charge and bonus from monday-Saturday 8h-16h
     *
     * @param minute charge
     * @return cost
     */
    private int promotionCharge(int minute) {
        int result = (minute - 60) * promotionPrice;

        // may be negative, if happen return 0;
        return result > 0 ? result : 0;
    }

    /**
     * Normal charge
     *
     * @param minute charge
     * @return cost
     */
    private int baseCharge(int minute) {
        return minute * normalPrice;
    }

    /**
     * Create this method for detail fun :D
     *
     * @return cost
     */
    private int chargeFullDay() {
        // period 8h -> 16h = 480 minute, free first hour ( 60 minute)
        return (960 * 30) + ((480 - 60) * 20);
    }
}
