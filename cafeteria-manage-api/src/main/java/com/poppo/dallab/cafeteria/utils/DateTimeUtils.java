package com.poppo.dallab.cafeteria.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class DateTimeUtils {

    public LocalDate stringDateToLocalDate(String workDay) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(workDay, dateTimeFormatter);
    }

    public List<LocalDate> getWeekOfDate(LocalDate date) {

        TemporalField fieldKR = WeekFields.of(Locale.KOREA).dayOfWeek();

        List<LocalDate> localDates = new ArrayList<>();
        for (int i = 2; i < 7; i++) {
            localDates.add(date.with(fieldKR, i));
        }

        return localDates;
    }

    public List<LocalDate> getLocalDatesByMonth(Integer requestMonth) {

        Integer thisYear = LocalDate.now().getYear();
        Integer dayLength = this.getDayLengthOfMonth(thisYear, requestMonth);

        List<LocalDate> monthDays = new ArrayList<>();
        for (int i = 1; i < dayLength + 1; i ++) {
            monthDays.add(LocalDate.of(thisYear, requestMonth, i));
        }

        return monthDays;
    }

    public Integer getDayLengthOfMonth(Integer year, Integer month) {

        YearMonth yearMonth = YearMonth.of(year, month);

        return yearMonth.lengthOfMonth();
    }
}
