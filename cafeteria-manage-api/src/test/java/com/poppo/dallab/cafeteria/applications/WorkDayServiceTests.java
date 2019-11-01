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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

    @Test
    public void getWorkWeekFromNow() throws Exception {

        // given
        LocalDate testDate = LocalDate.of(2019,10,10);

        List<LocalDate> mockThisWeek = new ArrayList<>();
        mockThisWeek.add(LocalDate.of(2019, 10, 7));
        given(dateTimeUtils.getWeekOfDate(testDate)).willReturn(mockThisWeek);

        WorkDay mockWorkDay = WorkDay.builder()
                .date(LocalDate.of(2019,10,7))
                .day(LocalDate.of(2019,10,7).getDayOfWeek().name())
                .build();
        given(workDayRepository.findByDate(any())).willReturn(mockWorkDay);

        // when
        List<WorkDay> workDays = workDayService.getWorkWeek(testDate);

        // then
        assertThat(workDays.get(0).getDate().toString()).isEqualTo("2019-10-07");
        assertThat(workDays.get(0).getDay()).isEqualTo("MONDAY");

    }

    @Test
    public void 한방에_해당월_날짜_전부_만들기() {

        List<LocalDate> mockLocalDates = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mockLocalDates.add(LocalDate.now());
        }
        given(dateTimeUtils.getLocalDatesByMonth(11))
                .willReturn(mockLocalDates);

        List<WorkDay> workDays = workDayService.bulkCreate(11);

        verify(workDayRepository).saveAll(any());
        assertThat(workDays).hasSize(30);

    }

}