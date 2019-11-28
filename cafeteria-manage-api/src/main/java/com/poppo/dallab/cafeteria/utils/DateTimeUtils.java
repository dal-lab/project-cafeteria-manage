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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DateTimeUtils {

    public LocalDate stringDateToLocalDate(String workDay) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(workDay, dateTimeFormatter);
    }

    public List<LocalDate> getWeekOfDateExceptWeekend(LocalDate date) {

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

        // range의 끝 수가 빠지는 듯
        return IntStream.range(1, dayLength + 1)
                .mapToObj(day -> LocalDate.of(thisYear, requestMonth, day))
                .collect(Collectors.toList());
    }

    public Integer getDayLengthOfMonth(Integer year, Integer month) {

        YearMonth yearMonth = YearMonth.of(year, month);

        return yearMonth.lengthOfMonth();
    }

    public Integer getFirstWeekLength(Integer year, Integer month) {

        LocalDate firstDate = LocalDate.of(year, month, 1);
        List<LocalDate> datesOfWeek = this.getWeekOfDateExceptWeekend(firstDate);

        return (int) datesOfWeek.stream()
                .filter(date -> date.getMonthValue() == month)
                .count();
    }

    public LocalDate getWeekStartDate(Integer year, Integer month, Integer weekCount) {

        Integer date = 0;

        if (weekCount == 1) {
            date = 1;
        }

        List<LocalDate> weekDates = this.getWeekOfDateExceptWeekend(LocalDate.of(year, month, date)).stream()
                .filter(weekDate -> weekDate.getMonthValue() == month)
                .collect(Collectors.toList());

        return weekDates.get(0);
    }

    public List<LocalDate> getMondaysOfMonth(Integer year, Integer month) {

        Integer dayLengthOfMonth = this.getDayLengthOfMonth(year, month);

        return IntStream.rangeClosed(1, dayLengthOfMonth)
                .mapToObj(dayNumber -> LocalDate.of(year, month, dayNumber))
                .filter(monthDate -> monthDate.getDayOfWeek().name().equals("MONDAY"))
                .collect(Collectors.toList());
    }

    public List<LocalDate> getMondaysOfMonthExceptFirstWeek(Integer year, Integer month) {

        List<LocalDate> mondaysOfMonth = this.getMondaysOfMonth(year, month);

        return mondaysOfMonth.stream()
                .filter(monday -> monday.getDayOfMonth() != 1)
                .filter(monday -> monday.getDayOfMonth() != 2)
                .filter(monday -> monday.getDayOfMonth() != 3)
                .collect(Collectors.toList());
    }
}
