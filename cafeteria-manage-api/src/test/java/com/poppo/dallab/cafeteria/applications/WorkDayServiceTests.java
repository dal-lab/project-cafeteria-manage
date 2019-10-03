package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.domain.WorkDayRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class WorkDayServiceTests {

    WorkDayService workDayService;

    @Mock
    WorkDayRepository workDayRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        workDayService = new WorkDayService(workDayRepository);
    }

    @Test
    public void getWorkDayIdByString() {

        // given
        String workDay = "2019-10-01";
        WorkDay mockWorkDay = WorkDay.builder().id(3L).build();
        given(workDayRepository.findByDate(any())).willReturn(mockWorkDay);

        // when
        WorkDay foundResult = workDayService.getWorkDayByString(workDay);

        // then
        assertThat(foundResult.getId()).isEqualTo(3L);
    }

    @Test
    public void stringDateToLocalDate() {

        // given
        String workDay = "2019-10-01";

        // when
        LocalDate resultDate = workDayService.stringDateToLocalDate(workDay);

        // then
        assertThat(resultDate.getYear()).isEqualTo(2019);
        assertThat(resultDate.getMonthValue()).isEqualTo(10);
        assertThat(resultDate.getDayOfMonth()).isEqualTo(1);

    }

}