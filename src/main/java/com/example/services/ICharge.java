package com.example.services;

import org.joda.time.DateTime;

/**
 * Created by tan on 01/04/2017.
 */
public interface ICharge {

    /**
     * Charge method
     *
     * @param id        this determine who parking
     * @param startTime check-in time
     * @param endTime   check-out time
     * @return money charged
     */
    double charge(String id, DateTime startTime, DateTime endTime);

}
