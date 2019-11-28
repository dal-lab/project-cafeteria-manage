package com.poppo.dallab.cafeteria.applications;

import com.poppo.dallab.cafeteria.domain.WorkDay;
import com.poppo.dallab.cafeteria.domain.WorkDayRepository;
import com.poppo.dallab.cafeteria.exceptions.WeekCountExceedException;
import com.poppo.dallab.cafeteria.utils.DateTimeUtils;
import com.poppo.dallab.cafeteria.utils.PageUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class  WorkDayServiceTests {

    private WorkDayService workDayService;

    @Mock
    private WorkDayRepository workDayRepository;

    @MockBean
    private DateTimeUtils dateTimeUtils;

    @MockBean
    private PageUtils pageUtils;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        workDayService = new WorkDayService(workDayRepository, dateTimeUtils, pageUtils);
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
    public void getWorkWeekFromNow() {

        // given
        LocalDate testDate = LocalDate.of(2019,10,10);

        List<LocalDate> mockThisWeek = new ArrayList<>();
        mockThisWeek.add(LocalDate.of(2019, 10, 7));
        given(dateTimeUtils.getWeekOfDateExceptWeekend(testDate)).willReturn(mockThisWeek);

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

        List<LocalDate> mockLocalDates = Stream.generate(LocalDate::now)
                .limit(30)
                .collect(Collectors.toList());

        given(dateTimeUtils.getLocalDatesByMonth(11))
                .willReturn(mockLocalDates);

        List<WorkDay> workDays = workDayService.bulkCreate(11);

        verify(workDayRepository).saveAll(any());
        assertThat(workDays).hasSize(30);

    }

    @Test
    public void workDay가_있는_달만_가져오기() {

        given(dateTimeUtils.getDayLengthOfMonth(any(), any())).willReturn(20);
        given(workDayRepository.existsByDateBetween(any(), any())).willReturn(true);

        List<Integer> workMonths = workDayService.getWorkMonths();

        assertThat(workMonths.get(0)).isEqualTo(1);
        assertThat(workMonths.get(1)).isEqualTo(2);
    }

    @Test
    public void 제시된_달의_저장된_workDay가_있는고() {
        given(dateTimeUtils.getDayLengthOfMonth(2019, 10)).willReturn(30);
        given(workDayRepository.existsByDateBetween(any(), any())).willReturn(true);

        Boolean result = workDayService.isThisMonthExists(10);

        assertThat(result).isTrue();
    }

    @Test
    public void 아이디로_workday_가져오기() {
        given(workDayRepository.getOne(1L)).willReturn(WorkDay.builder()
                .id(1L)
                .build());

        WorkDay workday = workDayService.getWorkDayById(1L);

        assertThat(workday.getId()).isEqualTo(1L);
    }

    @Test
    public void 해당월의_첫번째주의_workDay_가져오기() {

        List<LocalDate> firstWeekDays = new ArrayList<>();
        firstWeekDays.add(LocalDate.of(2019, 9,30));
        firstWeekDays.add(LocalDate.of(2019,10,1));
        firstWeekDays.add(LocalDate.of(2019,10,2));

        List<WorkDay> workDays = Arrays.asList(WorkDay.builder().id(1L).build());

        given(dateTimeUtils.getWeekOfDateExceptWeekend(LocalDate.of(2019,10,4)))
                .willReturn(firstWeekDays);
        given(workDayRepository.findAllByDateBetweenAndDayNotLikeAndDayNotLike(
                LocalDate.of(2019,10,1),
                LocalDate.of(2019,10,2),
                "SATURDAY",
                "SUNDAY"
        )).willReturn(workDays);

        List<WorkDay> firstWeekWorkDays = workDayService.getFirstWeekWorkDay(2019, 10);

        assertThat(firstWeekWorkDays.get(0).getId()).isEqualTo(1L);
    }

    @Test
    public void 해당월의_첫번째주를_제외한_주의_workDay_가져오기() {

        List<WorkDay> workDays = Arrays.asList(WorkDay.builder().id(1L).build());

        given(dateTimeUtils.getMondaysOfMonthExceptFirstWeek(2019,11)).willReturn(
                Arrays.asList(
                        LocalDate.of(2019,11,4),
                        LocalDate.of(2019,11,11)
                )
        );
        given(workDayRepository.findAllByDateBetweenAndDayNotLikeAndDayNotLike(
                LocalDate.of(2019,11,4),
                LocalDate.of(2019,11,8),
                "SATURDAY",
                "SUNDAY"
                )
        ).willReturn(workDays);

        List<WorkDay> workDaysByMonth = workDayService.getWorkDaysByMonth(2019, 11,2);

        assertThat(workDaysByMonth.get(0).getId()).isEqualTo(1L);
    }

    @Test(expected = WeekCountExceedException.class)
    public void 해당월을_벗어난_주차의_workDay_가져오기_요청하면_에러가_발생한다() {

        given(dateTimeUtils.getMondaysOfMonth(2019,11)).willReturn(
                Arrays.asList(LocalDate.of(2019,11,4))
        );

        workDayService.getWorkDaysByMonth(2019, 11,1000);
    }
}