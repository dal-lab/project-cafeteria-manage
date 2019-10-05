package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.domain.WorkDayRepository;
import com.poppo.dallab.cafeteria.utils.DateTimeUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkDayServiceTests {

    WorkDayService workDayService;

    @Mock
    WorkDayRepository workDayRepository;

    @MockBean
    DateTimeUtils dateTimeUtils;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        workDayService = new WorkDayService(workDayRepository, dateTimeUtils);
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

}