package com.example.apacheCamel.route;

import com.example.apacheCamel.dto.DateResponse;
import com.example.apacheCamel.dto.MonthDateRange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Component
public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto)
                .contextPath("/api");

        rest()
                .get("/date")
                .param().name("startDate").type(RestParamType.query).dataType("string").endParam()
                .param().name("endDate").type(RestParamType.query).dataType("string").endParam()
                .to("direct:calculateDateRange");

        from("direct:calculateDateRange")
                .setBody(exchange -> {
                    String startDate = exchange.getIn().getHeader("startDate", String.class);
                    String endDate = exchange.getIn().getHeader("endDate", String.class);

                    LocalDate start = LocalDate.parse(startDate);
                    LocalDate end = LocalDate.parse(endDate);

                    YearMonth startMonth = YearMonth.from(start);
                    YearMonth endMonth = YearMonth.from(end);

                    List<MonthDateRange> dateRanges = new ArrayList<>();

                    YearMonth current = startMonth;
                    while (!current.isAfter(endMonth)) {
                        dateRanges.add(new MonthDateRange(
                                String.format("%s-00", current),
                                String.format("%s-32", current)
                        ));
                        current = current.plusMonths(1);
                    }
                    DateResponse dateResponse=new DateResponse();
                    dateResponse.setDates(dateRanges);
                    exchange.getIn().setBody(dateResponse);
                    return dateResponse;
                });

    }




}
