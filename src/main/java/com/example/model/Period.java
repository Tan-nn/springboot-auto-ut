package com.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

/**
 * Created by tan on 02/04/2017.
 */
public class Period {

    private DateTime startTime;
    private DateTime endTime;
    private boolean isFullDay;

    public Period(DateTime startTime, DateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Period(DateTime startTime, DateTime endTime, boolean isFullDay) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.isFullDay = isFullDay;
    }

    public DateTime getStartTime() {
        return startTime;

    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isFullDay() {
        return isFullDay;
    }

    public void setFullDay(boolean fullDay) {
        isFullDay = fullDay;
    }

    @Override
    public String toString() {
        return "Period{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", isFullDay=" + isFullDay +
                '}';
    }
}
