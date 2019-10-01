package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.domain.WorkDayRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
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

        String workDay = "2019-10-01";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(workDay, dateTimeFormatter);

        WorkDay mockWorkDay = WorkDay.builder().id(3L).build();

        given(workDayRepository.findByDate(date)).willReturn(mockWorkDay);

        WorkDay foundResult = workDayService.getWorkDayByString(workDay);

        assertThat(foundResult.getId()).isEqualTo(3L);
    }

}