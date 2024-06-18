package com.example.apacheCamel.dto;

public class MonthDateRange {
    private  String minDate;
    private  String maxDate;

    @Override
    public String toString() {
        return "{ minDate='" + minDate + '\'' +
                ", maxDate='" + maxDate + '\'' +
                '}';
    }

    public MonthDateRange(String minDate, String maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public String getMinDate() {
        return minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }
}

