package com.example.apacheCamel.dto;

import java.util.List;

public class DateResponse {

    public List<MonthDateRange> getDates() {
        return dates;
    }

    public void setDates(List<MonthDateRange> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "Possible dates=" + dates +
                '}';
    }

    private List<MonthDateRange> dates;

    public DateResponse(List<MonthDateRange> dates) {
        this.dates = dates;
    }
    public DateResponse(){}
}
