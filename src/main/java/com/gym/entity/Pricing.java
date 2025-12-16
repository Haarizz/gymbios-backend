package com.gym.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Pricing {

    private String hour;
    private String halfDay;
    private String fullDay;

    public Pricing() {}

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getHalfDay() {
        return halfDay;
    }

    public void setHalfDay(String halfDay) {
        this.halfDay = halfDay;
    }

    public String getFullDay() {
        return fullDay;
    }

    public void setFullDay(String fullDay) {
        this.fullDay = fullDay;
    }
}
