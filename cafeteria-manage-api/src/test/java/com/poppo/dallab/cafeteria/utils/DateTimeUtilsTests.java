package com.poppo.dallab.cafeteria.utils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTimeUtilsTests {

    @Autowired
    private DateTimeUtils dateTimeUtils;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        dateTimeUtils = new DateTimeUtils();
    }

    @Test
    public void stringDateToLocalDate() {

        // given
        String workDay = "2019-10-01";

        // when
        LocalDate resultDate = dateTimeUtils.stringDateToLocalDate(workDay);

        // then
        assertThat(resultDate.getYear()).isEqualTo(2019);
        assertThat(resultDate.getMonthValue()).isEqualTo(10);
        assertThat(resultDate.getDayOfMonth()).isEqualTo(1);

    }

    @Test
    public void getThisWeek() {

        // given
        LocalDate date = LocalDate.of(2019,10,10);

        // when
        List<LocalDate> localDates = dateTimeUtils.getWeekOfDateExceptWeekend(date);

        // then
        assertThat(localDates.get(0).getDayOfWeek().name()).isEqualTo("MONDAY");
        assertThat(localDates.get(0).toString()).isEqualTo("2019-10-07");
        assertThat(localDates.get(1).getDayOfWeek().name()).isEqualTo("TUESDAY");
        assertThat(localDates.get(1).toString()).isEqualTo("2019-10-08");
        assertThat(localDates.get(2).getDayOfWeek().name()).isEqualTo("WEDNESDAY");
        assertThat(localDates.get(2).toString()).isEqualTo("2019-10-09");
        assertThat(localDates.get(3).getDayOfWeek().name()).isEqualTo("THURSDAY");
        assertThat(localDates.get(3).toString()).isEqualTo("2019-10-10");
        assertThat(localDates.get(4).getDayOfWeek().name()).isEqualTo("FRIDAY");
        assertThat(localDates.get(4).toString()).isEqualTo("2019-10-11");
    }

    @Test
    public void 월로부터_날짜_여러개_받기() {
        List<LocalDate> monthDays = dateTimeUtils.getLocalDatesByMonth(11);

        assertThat(monthDays.size()).isEqualTo(30);
        assertThat(monthDays.get(0).getDayOfMonth()).isEqualTo(1);
        assertThat(monthDays.get(29).getDayOfMonth()).isEqualTo(30);
    }

    @Test
    public void 해당년도_월의_날짜수_받기() {

        Integer monthDaySize = dateTimeUtils.getDayLengthOfMonth(2019, 11);

        assertThat(monthDaySize).isEqualTo(30);
    }

    @Test
    public void 윤달이_아닌_2월_날짜수_받기() {

        Integer monthDaySize = dateTimeUtils.getDayLengthOfMonth(2019, 2);

        assertThat(monthDaySize).isEqualTo(28);

    }

    @Test
    public void 윤달인_2월_날짜수_받기() {

        Integer monthDaySize = dateTimeUtils.getDayLengthOfMonth(2016, 2);

        assertThat(monthDaySize).isEqualTo(29);
    }

    @Test
    public void 제시된_월의_첫주_갯수_구하기() {

        Integer firstWeekLength = dateTimeUtils.getFirstWeekLength(2019, 11);

        assertThat(firstWeekLength).isEqualTo(1);
    }

    @Test
    public void 제시된_월의_월요일만_골라내기() {

        List<LocalDate> mondays = dateTimeUtils.getMondaysOfMonth(2019, 11);

        assertThat(mondays.get(0).getDayOfMonth()).isEqualTo(4);
        assertThat(mondays.get(1).getDayOfMonth()).isEqualTo(11);
        assertThat(mondays.get(2).getDayOfMonth()).isEqualTo(18);
        assertThat(mondays.get(3).getDayOfMonth()).isEqualTo(25);
    }

    @Test
    public void 제시된_월의_월요일_중_첫주가_월요일로_시작하는_경우를_빼고_골라내기_4월예시() {

        List<LocalDate> mondays = dateTimeUtils.getMondaysOfMonthExceptFirstWeek(2019, 4);

        assertThat(mondays.get(0).getDayOfMonth()).isEqualTo(8);
    }

    @Test
    public void 제시된_월의_월요일_중_첫주가_월요일로_시작하는_경우를_빼고_골라내기_6월예시() {

        List<LocalDate> mondays = dateTimeUtils.getMondaysOfMonthExceptFirstWeek(2019, 6);

        assertThat(mondays.get(0).getDayOfMonth()).isEqualTo(10);
    }

    @Test
    public void 제시된_월의_월요일_중_첫주가_월요일로_시작하는_경우를_빼고_골라내기_9월예시() {

        List<LocalDate> mondays = dateTimeUtils.getMondaysOfMonthExceptFirstWeek(2019, 9);

        assertThat(mondays.get(0).getDayOfMonth()).isEqualTo(9);
    }
}