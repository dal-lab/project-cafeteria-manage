package com.poppo.dallab.cafeteria.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTimeUtilsTests {

    @Autowired
    DateTimeUtils dateTimeUtils;

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
    public void getThisWeek() throws Exception {

        // given
        LocalDate date = LocalDate.of(2019,10,10);

        // when
        List<LocalDate> localDates = dateTimeUtils.getWeekOfDate(date);

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

}