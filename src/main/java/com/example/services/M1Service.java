package com.example.services;

import com.example.util.Util;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by tan on 01/04/2017.
 */
public class M1Service implements ICharge {

    @Value("${m1.hour.price}")
    private int price = 60; // NOK/Minus

    private static final M1Service instance = new M1Service();

    private M1Service() {
    }

    public static M1Service getInstance() {
        return instance;
    }

    @Override
    public double charge(String id, DateTime startTime, DateTime endTime) {
        int minus = Util.convert2MinutePeriod(startTime, endTime);

        // multiple * 1.0 to cast double
        double result = (minus * 1.0 / 60) * price;
        System.out.println("id: " + id + " result: " + result);
        return result;
    }
}
