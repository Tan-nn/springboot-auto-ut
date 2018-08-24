package com.example;

import com.example.services.FactoryServices;
import com.example.services.ICharge;
import com.example.util.Const;
import com.example.util.Util;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * Created by tan on 01/04/2017.
 */
public class FastTest {

    public static void main(String[] arg) {


        Const.Zone s = Const.Zone.valueOf("mx");

        System.out.println("zzzz: " + s);


        String datePattern = "yyyy-MM-dd HH:mm:ss";
        String startDateStr = "2017-03-29 05:20:31"; // 320
        String endDateStr = "2017-04-02 17:36:31"; // 1056 12.26 h
        // [((480 - 320) + (1056 - 960)) * 30] + [(480 - 60) * 20]
        // 7680 + 8400 = 16040

        DateTime startTime = DateTime.parse(startDateStr, DateTimeFormat.forPattern(datePattern));
        DateTime endTime = DateTime.parse(endDateStr, DateTimeFormat.forPattern(datePattern));

        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        Util.parseDayBetween(startTime, endTime).forEach(System.out::println);


        ICharge m1 = FactoryServices.INSTANCES.getParkingService(Const.Zone.m1);
        double cost1 = m1.charge("11", startTime, endTime);

        ICharge m2 = FactoryServices.INSTANCES.getParkingService(Const.Zone.m2);
        double cost2 = m2.charge("12", startTime, endTime);

        ICharge m3 = FactoryServices.INSTANCES.getParkingService(Const.Zone.m3);
        double cost3 = m3.charge("13", startTime, endTime);

        System.out.printf("cost1: %s,  cost2: %s,       cost3: %s", cost1, cost2, cost3);
    }

}
