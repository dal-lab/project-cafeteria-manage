package com.poppo.dallab.cafeteria.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

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

}