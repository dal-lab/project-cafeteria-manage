package com.poppo.dallab.cafeteria.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeUtils {

    public LocalDate stringDateToLocalDate(String workDay) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(workDay, dateTimeFormatter);
    }

}
