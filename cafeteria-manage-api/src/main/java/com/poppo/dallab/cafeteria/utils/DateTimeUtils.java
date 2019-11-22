package com.poppo.dallab.cafeteria.utils;

import com.poppo.dallab.cafeteria.domain.WorkDayRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DateTimeUtils {

    private final WorkDayRepository workDayRepository;

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

        // range의 끝 수가 빠지는 듯
        return IntStream.range(1, dayLength + 1)
                .mapToObj(day -> LocalDate.of(thisYear, requestMonth, day))
                .collect(Collectors.toList());
    }

    public Integer getDayLengthOfMonth(Integer year, Integer month) {

        YearMonth yearMonth = YearMonth.of(year, month);

        return yearMonth.lengthOfMonth();
    }

    // TODO: 이제 이걸 기반으로 VO객체 하나 만들어서 year, month, weekCount(주차)를 전달하면, Pageable 객체 반환하도록 구현
    public Integer getFirstWeekLength(Integer year, Integer month) {

        LocalDate firstDate = LocalDate.of(year, month, 1);
        List<LocalDate> datesOfWeek = this.getWeekOfDate(firstDate);

        return (int) datesOfWeek.stream()
                .filter(date -> date.getMonthValue() == month).count();
    }
}
